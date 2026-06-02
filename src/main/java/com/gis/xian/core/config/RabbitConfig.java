package com.gis.xian.core.config;

import com.gis.xian.constant.BaseConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzw
 * @description: RabbitMq 配置
 * @date 2026/5/26 下午6:36
 */
@Slf4j
@Configuration
public class RabbitConfig {



    // 专题图队列
    @Bean
    public Queue mapsQueue() {
        return new Queue(BaseConstants.MAPS_QUEUE, true);
    }

    // 文档队列
    @Bean
    public Queue documents() {
        return new Queue(BaseConstants.DOCUMENTS_QUEUE, true);
    }

    // 死信队列
    @Bean
    public Queue dlqQueue() {
        return new Queue(BaseConstants.DLQ_QUEUE, true);
    }

    // 定义主题交换机
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(BaseConstants.ASSESS_EXCHANGE);
    }

    // 定义死信交换机
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange (BaseConstants.DLX_EXCHANGE);
    }
    // 将 mapsQueue 队列和 assess 交换机绑定,而且绑定的键值为 maps
    // 这样只要是消息携带的路由键是 maps,才会分发到该队列
    @Bean
    public Binding bindingExchangeMessageOfMaps() {
        return BindingBuilder.bind(mapsQueue()).to(exchange()).with(BaseConstants.MAPS_QUEUE);
    }

    @Bean
    public Binding bindingExchangeMessageOfDocuments() {
        return BindingBuilder.bind(documents()).to(exchange()).with(BaseConstants.DOCUMENTS_QUEUE);
    }

    @Bean
    public Binding bindingExchangeMessageOfFiles() {
        return BindingBuilder.bind(dlqQueue()).to(exchange()).with(BaseConstants.DLQ_QUEUE);
    }

    // 设置消息回调函数 自动确认消息 ack
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory，才能触发回调函数，无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        // 1. 确认回调 (ConfirmCallback) - 建议使用 Lambda 简化写法
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("✅ 消息成功发送到 Exchange");
            } else {
                System.err.println("❌ 消息发送到 Exchange 失败: " + cause);
            }
        });

        // 2. 退回回调 (ReturnsCallback) - 修复报错的核心部分
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            System.out.println("⚠️ 消息无法路由被退回！");
            System.out.println("回复码 replyCode: " + returnedMessage.getReplyCode());
            System.out.println("回复文本 replyText: " + returnedMessage.getReplyText());
            System.out.println("交换机 exchange: " + returnedMessage.getExchange());
            System.out.println("路由键 routingKey: " + returnedMessage.getRoutingKey());
            // 获取原始消息内容
            String msgBody = new String(returnedMessage.getMessage().getBody());
            System.out.println("被退回的消息内容: " + msgBody);
        });

        return rabbitTemplate;
    }
}
