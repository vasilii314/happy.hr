CREATE TABLE project_type (
    type_id SERIAL PRIMARY KEY,
    is_mvp BOOLEAN,
    soft_or_pac VARCHAR(3),
    system_type VARCHAR(150)
)