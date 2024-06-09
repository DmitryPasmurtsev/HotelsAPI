CREATE TABLE hotels_amenities
(
    hotel_id   INT NOT NULL,
    amenity_id INT NOT NULL,
    PRIMARY KEY (hotel_id, amenity_id),
    CONSTRAINT fk_hotel FOREIGN KEY (hotel_id) REFERENCES hotels (id),
    CONSTRAINT fk_amenity FOREIGN KEY (amenity_id) REFERENCES amenities (id)
);