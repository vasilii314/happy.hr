CREATE TABLE location (
    location_id SERIAL PRIMARY KEY,
    address VARCHAR(1024),
    location_type VARCHAR(150)
);