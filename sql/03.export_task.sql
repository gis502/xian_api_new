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
);
CREATE INDEX IF NOT EXISTS idx_export_task_status ON export_task(status);
