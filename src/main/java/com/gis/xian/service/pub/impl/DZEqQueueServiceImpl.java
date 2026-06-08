package com.gis.xian.service.pub.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gis.xian.config.DataSource;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.dto.pub.EqAssessmentDTO;
import com.gis.xian.dto.pub.RAssessmentDTO;
import com.gis.xian.entity.pub.DZEqQueue;
import com.gis.xian.enums.BaseEnums;
import com.gis.xian.mapper.pub.DZEqQueueMapper;
import com.gis.xian.service.dzxx.IDZXXDistanceService;
import com.gis.xian.service.dzxx.IDZXXInfluenceService;
import com.gis.xian.service.ex.ParmaException;
import com.gis.xian.service.ex.ServeException;
import com.gis.xian.service.pub.IDZEqQueueService;
import com.gis.xian.service.pub.IDZProductService;
import com.gis.xian.utils.BaseUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 地震评估业务
 * @date 2026/5/25 下午6:09
 */
@Slf4j
@Service
@DataSource("slave1")
public class DZEqQueueServiceImpl extends ServiceImpl<DZEqQueueMapper, DZEqQueue> implements IDZEqQueueService {

    @Resource
    private IDZXXDistanceService idzxxDistanceService;
    @Resource
    private IDZXXInfluenceService idzxxInfluenceService;
    @Resource
    private IDZProductService idzProductService;

    // 地震评估
    @Transactional
    @Override
    public void assess(EqAssessmentDTO assess) {
        log.info("开始评估,评估参数：{}", assess);
        // 异常值
        if (assess == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        // 评估业务
        try {
            // 初始化评估
            initial(assess, BaseConstants.MANUAL);
            // 地震影响场评估 5s
            idzxxInfluenceService.handle(assess);
            updated(assess.getEvent(), assess.getEqQueueId(), BaseUtils.compute(1, 0), BaseEnums.CALCULATING.getCode());
            // 震中距评估
            idzxxDistanceService.handle(assess.getLongitude(), assess.getLatitude(), assess.getEqQueueId());
            // 专题图产出
            idzProductService.makeEarthquakeMaps(assess);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ServeException(BaseConstants.ASSESS_SERVER_ERROR);
        }
    }

    // 地震初始化评估进度和评估状态
    private void initial(EqAssessmentDTO assess, int type) {

        DZEqQueue dzqueue = new DZEqQueue();
        BeanUtils.copyProperties(assess, dzqueue);
        dzqueue.setId(assess.getEqQueueId());
        dzqueue.setBatch(1);
        dzqueue.setType(type);   // 手动
        dzqueue.setState(BaseEnums.NOT_STARTED.getCode());
        dzqueue.setMode(1); // 0 地震参数 1 影响场
        dzqueue.setBeginTime(LocalDateTime.now());
        dzqueue.setProgress(0.0);   // 评估进度
        dzqueue.setRemark(BaseEnums.NOT_STARTED.getDesc());

        save(dzqueue);
        log.info("地震评估业务初始化完成!");
    }

    // 暴雨初始化评估进度和评估状态
    private void initial(RAssessmentDTO assess, int type) {

        DZEqQueue dzqueue = new DZEqQueue();
        BeanUtils.copyProperties(assess, dzqueue);
        dzqueue.setId(assess.getRainQueueId());
        dzqueue.setBatch(1);
        dzqueue.setType(type);   // 手动
        dzqueue.setState(BaseEnums.NOT_STARTED.getCode());
        dzqueue.setMode(null); // 0 地震参数 1 影响场
        dzqueue.setBeginTime(LocalDateTime.now());
        dzqueue.setProgress(0.0);   // 评估进度
        dzqueue.setRemark(BaseEnums.NOT_STARTED.getDesc());

        save(dzqueue);
        log.info("暴雨评估业务初始化完成!");
    }


    // 更新评估进度和评估状态
    public void updated(String event, String queueId, double progress, int state) {

        DZEqQueue dzqueue = new DZEqQueue();
        // 条件
        LambdaQueryWrapper<DZEqQueue> lambdaQuery = Wrappers.lambdaQuery(DZEqQueue.class)
                .eq(DZEqQueue::getEvent, event)
                .eq(DZEqQueue::getId, queueId);

        // 评估异常终止
        if (state == BaseEnums.TIMEOUT_OR_EXCEPTION.getCode()) {
            dzqueue.setState(BaseEnums.TIMEOUT_OR_EXCEPTION.getCode());
            dzqueue.setEndTime(LocalDateTime.now());
            dzqueue.setProgress(progress);   // 评估进度
            dzqueue.setRemark(BaseEnums.TIMEOUT_OR_EXCEPTION.getDesc());
            // 更新状态
            update(dzqueue, lambdaQuery);
            log.info("评估异常结束!");
            return;
        }
        // 人工停止
        if (state == BaseEnums.MANUAL_STOPPED.getCode()) {
            dzqueue.setState(BaseEnums.MANUAL_STOPPED.getCode());
            dzqueue.setEndTime(LocalDateTime.now());
            dzqueue.setProgress(progress);   // 评估进度
            dzqueue.setRemark(BaseEnums.MANUAL_STOPPED.getDesc());
            // 更新状态
            update(dzqueue, lambdaQuery);
            log.info("评估人工停止!");
            return;
        }
        // 不计算
        if (state == BaseEnums.NOT_CALCULATE.getCode()) {
            dzqueue.setState(BaseEnums.NOT_CALCULATE.getCode());
            dzqueue.setEndTime(LocalDateTime.now());
            dzqueue.setProgress(progress);   // 评估进度
            dzqueue.setRemark(BaseEnums.NOT_CALCULATE.getDesc());
            // 更新状态
            update(dzqueue, lambdaQuery);
            log.info("本次事件不参与评估计算!");
            return;
        }
        // 正常继续评估
        if (state == BaseEnums.CALCULATING.getCode()) {
            if (progress < 100) {
                dzqueue.setProgress(progress);
                // 更新进度
                update(dzqueue, lambdaQuery);
                log.info("评估业务进度：{}%", progress);
                return;
            }
            // 评估完成
            dzqueue.setState(BaseEnums.NORMAL_COMPLETED.getCode());
            dzqueue.setEndTime(LocalDateTime.now());
            dzqueue.setProgress(progress);   // 评估进度
            dzqueue.setRemark(BaseEnums.NORMAL_COMPLETED.getDesc());
            update(dzqueue, lambdaQuery);
            log.info("评估工作结束!");
        }

    }

}
