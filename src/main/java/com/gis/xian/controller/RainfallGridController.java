package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.service.RainfallGridService;
import com.gis.xian.vo.RainfallGridVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 降雨网格控制器
 */
@Slf4j
@RestController
public class RainfallGridController extends BaseController {
    
    @Resource
    private SimpMessagingTemplate messagingTemplate;
    
    @Resource
    private RainfallGridService rainfallGridService;
    
    // 存储上一次的 ID，用于检测变化
    private final AtomicReference<String> lastIdentifier = new AtomicReference<>(null);

    /**
     * 处理客户端订阅请求，首次订阅时返回当前数据
     * 客户端发送任意消息到 /app/rainfall/grid 即可触发
     */
    @MessageMapping("/rainfall/grid")
    @SendTo("/topic/rainfall/grid/messages")
    public ApiResponse<RainfallGridVo> handleSubscription() {
        RainfallGridVo data = rainfallGridService.getRainfallGridData();
        
        // 记录当前 identifier，用于后续变化检测
        String currentIdentifier = rainfallGridService.getCurrentIdentifier();
        if (currentIdentifier != null) {
            lastIdentifier.set(currentIdentifier);
            log.info("客户端订阅，记录初始 identifier: {}", currentIdentifier);
        }
        
        log.info("响应客户端订阅请求: id={}", data.getId());
        return ApiResponse.ok(data);
    }

    /**
     * 每秒检查 Redis 中的 identifier 是否变化，如果变化则推送新数据
     */
    @Scheduled(fixedRate = 1000)
    public void checkAndPushIfChanged() {
        try {
            String currentIdentifier = rainfallGridService.getCurrentIdentifier();
            
            // 如果 identifier 为空，跳过
            if (currentIdentifier == null) {
                return;
            }
            
            // 检查 identifier 是否发生变化
            String previousIdentifier = lastIdentifier.get();
            if (previousIdentifier == null || !previousIdentifier.equals(currentIdentifier)) {
                log.info("检测到 identifier 变化: {} -> {}", previousIdentifier, currentIdentifier);
                
                // 获取最新数据并推送
                RainfallGridVo data = rainfallGridService.getRainfallGridData();
                messagingTemplate.convertAndSend("/topic/rainfall/grid/messages", ApiResponse.ok(data));
                log.info("推送更新数据: id={}", data.getId());
                
                // 更新记录的 identifier
                lastIdentifier.set(currentIdentifier);
            }
        } catch (Exception e) {
            log.error("检查并推送数据失败: {}", e.getMessage(), e);
        }
    }
}
