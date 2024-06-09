CREATE TABLE contacts
(
    hotel_id    INT PRIMARY KEY,
    phone VARCHAR(20)  NOT NULL,
    email VARCHAR(255) NOT NULL,
    CONSTRAINT fk_contacts FOREIGN KEY (hotel_id) REFERENCES hotels (id)
);