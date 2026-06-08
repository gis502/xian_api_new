package com.gis.xian.listener;

import com.gis.xian.config.DataSourceContextHolder;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.entity.pub.DZProduct;
import com.gis.xian.params.QgisArgs;
import com.gis.xian.service.ex.ServeException;
import com.gis.xian.service.pub.IDZProductService;
import com.rabbitmq.client.Channel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 专题图产出监听器
 * @date 2026/6/4 下午4:10
 */
@Slf4j
@Component
public class MapReceiveListener {

    @Resource
    private IDZProductService idzProductService;

    @PostConstruct
    public void init() {
        log.info("========================================");
        log.info("MapReceiveListener 监听器已初始化");
        log.info("正在监听队列: maps");
        log.info("监听方法: receive(QgisArgs args, Channel channel, ...)");
        log.info("ACK模式: MANUAL (手动确认)");
        log.info("========================================");
    }

    // rabbitmq 监听专题图队列
    @RabbitListener(queues = "maps", ackMode = "MANUAL")
    public void receive(QgisArgs args, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Message message) throws IOException {
        log.info("接收通知：{} 已生成！", args.getName());
        try {
            // 获取路径
            File originFile = new File(args.getOutFile());
            if (!originFile.exists()) {
                throw new ServeException(BaseConstants.FILE_NOT_FOUND_ERROR);
            }

            // 直接使用本地文件路径保存到数据库
            handleData(args, originFile.getAbsolutePath());
            log.info("{} 本地路径已保存到数据库!", args.getName());

            // 手动确认消息
            channel.basicAck(deliveryTag, false);
            log.info("ack：消息确认成功！");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("处理消息失败: {}", ex);

            /**
             * 参数说明：
             * 1. deliveryTag：消息唯一标识
             * 2. multiple：是否拒绝多条
             * 3. requeue：是否重新入队（false=直接丢弃，true=重新入队重试）
             * 建议：非幂等业务设置为 false，避免死循环；幂等业务可设置为 true
             */
            channel.basicNack(deliveryTag, false, false);

            // 抛出异常
            throw new ServeException(BaseConstants.THEMATIC_FAILED);
        }
    }

    private void handleData(QgisArgs args, String filePath) {
        try {
            DataSourceContextHolder.setDataSource("slave1");
            log.debug("切换数据源到: slave1");

            DZProduct product = new DZProduct();
            product.setEqQueueId(args.getQueueId());
            product.setProTime(LocalDateTime.now());
            product.setCode(args.getMapLayout());
            product.setFileType("图片");
            product.setFileName(args.getName());
            product.setFilePath(args.getPath());
            product.setFileExtension(".jpg");
            // 地震/暴雨 专题图
            product.setProType(args.getDisaster());

            // 将本地文件路径设置到源文件字段中
            product.setSourceFile(filePath);

            // 将图件信息插入到结果表中
            idzProductService.save(product);
            log.info("{} 已保存到数据库!", args.getName());
        } finally {
            DataSourceContextHolder.clearDataSource();
            log.debug("清除数据源上下文");
        }
    }
}
