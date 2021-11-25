CREATE TABLE project_card (
    card_id SERIAL PRIMARY KEY,
    project_name VARCHAR (150),
    technologies VARCHAR [],
    project_phase VARCHAR (150),
    functional_area VARCHAR (150),
    stakeholders VARCHAR [],
    project_description TEXT,
    topical_area VARCHAR (1024),
    card_status VARCHAR (150),
    gost_documentation BOOLEAN,
    location_id INT,
    project_type_id INT,
    working_hours_pattern_id INT,
    author_id INT,
    CONSTRAINT fk_location
        FOREIGN KEY (location_id)
            REFERENCES location (location_id),
    CONSTRAINT fk_project_type
        FOREIGN KEY (project_type_id)
            REFERENCES project_type (type_id),
    CONSTRAINT fk_working_hours_pattern
        FOREIGN KEY (working_hours_pattern_id)
            REFERENCES working_hours_pattern (whp_id),
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
            REFERENCES employee (empl_id)
);