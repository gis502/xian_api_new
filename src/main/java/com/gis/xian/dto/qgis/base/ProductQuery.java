package com.gis.xian.dto.qgis.base;

import lombok.Data;

/**
 * @author zzw
 * @description: 产品查询参数
 * @date 2026/5/26 上午11:00
 */
@Data
public class ProductQuery {

    private String queueId; // 产品Id,必填项

    private String code;    // A3 A4 专题图尺寸,选填项

    private String fileType;    // 图片 文档 产品类型（图片/文档）,选填

    private String fileName; // 产品名称（模糊查询）,选填

    private String proType;     // 暴雨/地震 灾害专题图 暴雨/地震 灾害文档
}
