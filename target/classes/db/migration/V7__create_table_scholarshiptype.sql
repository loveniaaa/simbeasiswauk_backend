CREATE TABLE IF NOT EXISTS t_scholarhsiptype
(
    uuid             VARCHAR(36)                NOT NULL,
    scholarship_name VARCHAR(32),
    description      TEXT,
    created_by       VARCHAR(32)                NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_by       VARCHAR(32)                NOT NULL,
    updated_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted       BOOLEAN                     NOT NULL,
    deleted_at       TIMESTAMP WITHOUT TIME ZONE,
    deleted_by       VARCHAR(32),
    CONSTRAINT pk_t_scholarhsiptype PRIMARY KEY (uuid)
);