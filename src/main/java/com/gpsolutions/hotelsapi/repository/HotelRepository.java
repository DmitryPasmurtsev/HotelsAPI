package com.gpsolutions.hotelsapi.repository;

import com.gpsolutions.hotelsapi.dto.response.HistogramResponse;
import com.gpsolutions.hotelsapi.entity.Hotel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT NEW com.gpsolutions.hotelsapi.dto.response.HistogramResponse(h.brand, COUNT(h)) FROM Hotel h GROUP BY h.brand")
    List<HistogramResponse> histogramByBrand();

    @Query("SELECT NEW com.gpsolutions.hotelsapi.dto.response.HistogramResponse(h.address.city, COUNT(h)) FROM Hotel h GROUP BY h.address.city")
    List<HistogramResponse> histogramByCity();

    @Query("SELECT NEW com.gpsolutions.hotelsapi.dto.response.HistogramResponse(h.address.county, COUNT(h)) FROM Hotel h GROUP BY h.address.county")
    List<HistogramResponse> histogramByCounty();

    @Query("SELECT NEW com.gpsolutions.hotelsapi.dto.response.HistogramResponse(a.name, COUNT(h)) FROM Amenity a JOIN a.hotels h GROUP BY a.name")
    List<HistogramResponse> histogramByAmenities();

    List<Hotel> findAll(Specification<Hotel> specification);

    Optional<Hotel> findHotelByContactsPhone(String phone);

    Optional<Hotel> findHotelByContactsEmail(String email);

    Optional<Hotel> findHotelByAddress_CountyAndAddress_CityAndAddress_HouseNumber(String county, String city, Integer houseNumber);
}
