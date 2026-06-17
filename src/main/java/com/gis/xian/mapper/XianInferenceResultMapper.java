package com.gis.xian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wzy
 * @description 针对表【xian_inference_result(预测结果表)】的数据库操作Mapper
 * @createDate 2026-06-17 11:58:32
 * @Entity com.gis.xian.entity.XianInferenceResult
 */
@Mapper
public interface XianInferenceResultMapper {
    /**
     * 根据id和pointId获取概率
     * @param id 预测结果id
     * @param pointId 隐患点/风险点id和类型
     * @return 预测概率
     */
    String getProbabilityByIdAndPointId(@Param("id") Long id, @Param("pointId") String pointId);
}
