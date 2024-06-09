CREATE TABLE addresses
(
    hotel_id           INT PRIMARY KEY,
    house_number INT          NOT NULL,
    street       VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    county       VARCHAR(255) NOT NULL,
    post_code    VARCHAR(20)  NOT NULL,
    CONSTRAINT fk_address FOREIGN KEY (hotel_id) REFERENCES hotels (id)
);