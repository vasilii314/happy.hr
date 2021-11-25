CREATE TABLE working_hours_pattern (
    whp_id SERIAL PRIMARY KEY,
    whp_from TIME,
    whp_till TIME,
    has_overtime BOOLEAN,
    has_flextime BOOLEAN
);