package com.gis.xian.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExportTaskTableInitializer {

    private final JdbcTemplate jdbcTemplate;

    public ExportTaskTableInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        try {
            jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS export_task (
                    id BIGSERIAL PRIMARY KEY,
                    table_name VARCHAR(255) NOT NULL,
                    total_rows INT DEFAULT 0,
                    processed_rows INT DEFAULT 0,
                    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                    file_path VARCHAR(500),
                    error_message TEXT,
                    created_at TIMESTAMP DEFAULT now(),
                    completed_at TIMESTAMP
                )
                """);
            jdbcTemplate.execute(
                "CREATE INDEX IF NOT EXISTS idx_export_task_status ON export_task(status)"
            );
            log.info("export_task 表已就绪");
        } catch (Exception e) {
            log.warn("export_task 表初始化失败: {}", e.getMessage());
        }
    }
}
