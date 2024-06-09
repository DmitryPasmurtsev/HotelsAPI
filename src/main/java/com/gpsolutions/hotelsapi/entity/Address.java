package com.gpsolutions.hotelsapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @Column(name = "hotel_id")
    Long id;
    @Column(name = "house_number")
    Integer houseNumber;
    @Column(name = "street")
    String street;
    @Column(name = "city")
    String city;
    @Column(name = "county")
    String county;
    @Column(name = "post_code")
    String postCode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

    @Override
    public String toString() {
        return String.format("%d %s, %s, %s, %s", houseNumber, street, city, postCode, county);
    }
}
