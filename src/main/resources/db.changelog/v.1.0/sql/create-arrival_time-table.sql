CREATE TABLE arrival_time
(
    hotel_id  INT PRIMARY KEY,
    check_in  VARCHAR(5) NOT NULL,
    check_out VARCHAR(5),
    CONSTRAINT fk_arrival_time FOREIGN KEY (hotel_id) REFERENCES hotels (id)
);