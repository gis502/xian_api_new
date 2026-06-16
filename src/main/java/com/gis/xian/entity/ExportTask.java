package com.gis.xian.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExportTask {
    private Long id;
    private String tableName;
    private Integer totalRows;
    private Integer processedRows;
    private String status;
    private String filePath;
    private String errorMessage;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}
