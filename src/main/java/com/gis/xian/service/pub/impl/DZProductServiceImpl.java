package com.gis.xian.service.pub.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gis.xian.config.DataSource;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.dto.pub.DZProductDTO;
import com.gis.xian.dto.pub.EqAssessmentDTO;
import com.gis.xian.entity.pub.DZProduct;
import com.gis.xian.enums.BaseEnums;
import com.gis.xian.enums.EqMapsEnums;
import com.gis.xian.handler.EarthquakeHandler;
import com.gis.xian.mapper.pub.DZProductMapper;
import com.gis.xian.query.ProductQuery;
import com.gis.xian.service.base.IActiveFaultService;
import com.gis.xian.service.dzxx.IDZXXInfluenceService;
import com.gis.xian.service.ex.ParmaException;
import com.gis.xian.service.ex.ServeException;
import com.gis.xian.service.pub.IDZProductService;
import com.gis.xian.service.pub.IFeignService;
import com.gis.xian.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

import com.gis.xian.params.QgisArgs;
import org.springframework.util.StringUtils;

/**
 * @author zzw
 * @description: 地震产品类
 * @date 2026/5/26 上午11:26
 */
@Slf4j
@Service
@DataSource("slave1")
public class DZProductServiceImpl extends ServiceImpl<DZProductMapper, DZProduct> implements IDZProductService {

    private static final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private IFeignService iFeignService;
    @Autowired
    private IActiveFaultService iActiveFaultService;
    @Autowired
    private IDZXXInfluenceService idzxxInfluenceService;

    // qgis 地震制图服务
    @Override
    @Async("xianPool")
    public void makeEarthquakeMaps(EqAssessmentDTO assess) {
        // 待产专题图集
        List<EqMapsEnums> maps = Arrays.asList(EqMapsEnums.values());
        // 设置制图参数
        List<QgisArgs> args = setMakeEqMapsArgs(assess, maps);
        // 调用出图服务
        iFeignService.invoke(args);
    }

    // 获取产品
    @Override
    public List<DZProductDTO> getProducts(ProductQuery query) {
        try {
            log.info("查询产品参数：{}", query);
            // 空值
            if (query.getQueueId() == null || query.getQueueId().trim().isEmpty()) {
                throw new ParmaException(BaseConstants.QUEUE_ID_ERROR);
            }
            // 构造条件
            LambdaQueryWrapper<DZProduct> lambdaQuery = Wrappers.lambdaQuery(DZProduct.class);
            // 必填项
            lambdaQuery.eq(DZProduct::getEqQueueId, query.getQueueId());
            // 选填项
            lambdaQuery.or().eq(StringUtils.hasText(query.getCode()), DZProduct::getCode, query.getCode());
            lambdaQuery.or().eq(StringUtils.hasText(query.getFileType()), DZProduct::getFileType, query.getFileType());
            lambdaQuery.or().like(StringUtils.hasText(query.getFileName()), DZProduct::getFileName, query.getFileName());
            lambdaQuery.or().eq(StringUtils.hasText(query.getProType()), DZProduct::getProType, query.getProType());
            // 获取产品服务
            List<DZProduct> productList = this.baseMapper.selectList(lambdaQuery);
            List<DZProductDTO> dtos = new ArrayList<>();
            for (DZProduct product : productList) {
                DZProductDTO dto = new DZProductDTO();
                BeanUtils.copyProperties(product, dto);
                dto.setTempletId(null);

                dtos.add(dto);
            }
            return dtos;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ServeException(BaseConstants.PRODUCTS_ERROR);
        }
    }

    // 地震 制图参数
    private List<QgisArgs> setMakeEqMapsArgs(EqAssessmentDTO assess, List<EqMapsEnums> maps) {
        // 专题图集参数
        List<QgisArgs> args = new ArrayList<>();

        // A4 画幅
        for (EqMapsEnums map : maps) {
            // qgis参数
            QgisArgs arg = new QgisArgs();
            arg.setId(map.getNum());
            arg.setEvent(assess.getEvent());
            arg.setQueueId(assess.getEqQueueId());
            arg.setCenterX(assess.getLongitude());
            arg.setCenterY(assess.getLatitude());
            arg.setInfo(EarthquakeHandler.parseInfo(assess.getEqTime(), assess.getEqMagnitude(), assess.getEqAddr()));
            arg.setMapTitle(EarthquakeHandler.combine(assess.getEqName(), assess.getEqType(), map));
            arg.setMapTime(BaseUtils.formatTime(LocalDateTime.now(), false));
            arg.setMapLayout(BaseConstants.MAP_LAYOUT_A4); // A4
            arg.setMapUint(BaseConstants.MAP_UNIT);    // 单位
            // 死信队列中获取单张图片
            arg.setName(map.getName());
            arg.setOutFile(EarthquakeHandler.getPath(assess.getEvent(), assess.getEqQueueId(), BaseConstants.MAP_LAYOUT_A4, map));
            arg.setPath(BaseConstants.EQ_MAPS_TEMPLATE_PATH + map.getName() + ".qgz");
            arg.setDisaster(BaseConstants.EQ_DISASTER_MAP);  // 地震灾害

            // 缩放规则
            Map<String, String> changed = change(assess, "A4", map.getName());
            arg.setZoomRule(changed.get("k"));    // 默认不缩放
            arg.setZoomValue(changed.get("v"));  // 默认缩放值

            args.add(arg);
        }

        // 增加 A3 画幅
        for (EqMapsEnums map : maps) {
            // qgis参数
            QgisArgs arg = new QgisArgs();
            arg.setId(map.getNum());
            arg.setEvent(assess.getEvent());
            arg.setQueueId(assess.getEqQueueId());
            arg.setCenterX(assess.getLongitude());
            arg.setCenterY(assess.getLatitude());
            arg.setInfo(EarthquakeHandler.parseInfo(assess.getEqTime(), assess.getEqMagnitude(), assess.getEqAddr()));
            arg.setMapTitle(EarthquakeHandler.combine(assess.getEqName(), assess.getEqType(), map));
            arg.setMapTime(BaseUtils.formatTime(LocalDateTime.now(), false));
            arg.setMapLayout(BaseConstants.MAP_LAYOUT_A3); // A3
            arg.setMapUint(BaseConstants.MAP_UNIT);    // 单位
            // 死信队列中获取单张图片
            arg.setName(map.getName());
            arg.setOutFile(EarthquakeHandler.getPath(assess.getEvent(), assess.getEqQueueId(), BaseConstants.MAP_LAYOUT_A3, map));
            arg.setPath(BaseConstants.EQ_MAPS_TEMPLATE_PATH + map.getName() + ".qgz");
            arg.setDisaster(BaseConstants.EQ_DISASTER_MAP);  // 地震灾害

            // 缩放规则
            Map<String, String> changed = change(assess, "A3", map.getName());
            arg.setZoomRule(changed.get("k"));    // 默认不缩放
            arg.setZoomValue(changed.get("v"));  // 默认缩放值

            args.add(arg);
        }
        log.info("制图参数设置完成!");

        return args;
    }


    /**
     * 专题图缩放变化：
     * A3：
     * 4级地震：不放缩
     * 5-6级地震：所有A3都放缩到intensity
     * 6级以上，除了影响场intensity，其他不变
     * <p>
     * A4：
     * 所有都不变
     */
    public Map<String, String> change(EqAssessmentDTO assess, String size, String name) {
        Map<String, String> map = new HashMap<>();

        if (size.equals("A3")) {
            if (assess.getEqMagnitude() >= 4 && assess.getEqMagnitude() < 5) {
                map.put("k", BaseEnums.NO.getCode().toString());
                map.put("v", "");
                return map;
            }
            if (assess.getEqMagnitude() >= 5 && assess.getEqMagnitude() < 6) {
                map.put("k", BaseEnums.M_LAYER2.getCode().toString());
                map.put("v", "intensity");
                return map;
            }
            if (assess.getEqMagnitude() >= 6) {
                if (name.equals("地震影响估计范围分布图")) {
                    map.put("k", BaseEnums.M_LAYER2.getCode().toString());
                    map.put("v", "intensity");
                    return map;
                } else {
                    map.put("k", BaseEnums.NO.getCode().toString());
                    map.put("v", "");
                    return map;
                }
            }
        }
        map.put("k", BaseEnums.NO.getCode().toString());
        map.put("v", "");
        return map;
    }

}
