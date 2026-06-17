package com.gis.xian.dto.qgis.earthquake;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: DZProductDTO
 * @date 2026/5/26 上午10:59
 */
@Data
public class EarthquakeProductDTO {


    private String eqQueueId;
    private Integer templetId;  // 模板编码id
    private String code;    // A3 A4
    private LocalDateTime proTime;
    private String fileType;    // 图片 文档
    private String fileName;
    private String filePath;    // 模板路径 查询时设置为空
    private String fileExtension;
    private Double fileSize;
    private String proType;     // 暴雨/地震 灾害专题图 暴雨/地震 灾害文档
    private String sourceFile;  // 存储位置


}

