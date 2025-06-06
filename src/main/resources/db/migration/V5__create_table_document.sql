CREATE TABLE t_document (
                            uuid varchar(36) PRIMARY KEY DEFAULT gen_random_uuid(),
                            file_name VARCHAR(255) NOT NULL,
                            file_type VARCHAR(100) NOT NULL,
                            path TEXT NOT NULL,
                            category VARCHAR(100) NOT NULL,
                            uploaded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            uploaded_by VARCHAR(100) NOT NULL,
                            file_data BYTEA
);
