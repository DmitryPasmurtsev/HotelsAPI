package com.gpsolutions.hotelsapi.service;

import com.gpsolutions.hotelsapi.dto.AddressDto;
import com.gpsolutions.hotelsapi.dto.ArrivalTimeDto;
import com.gpsolutions.hotelsapi.dto.ContactsDto;
import com.gpsolutions.hotelsapi.dto.request.HotelCreationRequest;
import com.gpsolutions.hotelsapi.dto.response.HistogramResponse;
import com.gpsolutions.hotelsapi.dto.response.HotelDetailResponse;
import com.gpsolutions.hotelsapi.dto.response.HotelResponse;
import com.gpsolutions.hotelsapi.entity.Address;
import com.gpsolutions.hotelsapi.entity.Amenity;
import com.gpsolutions.hotelsapi.entity.ArrivalTime;
import com.gpsolutions.hotelsapi.entity.Contacts;
import com.gpsolutions.hotelsapi.entity.Hotel;
import com.gpsolutions.hotelsapi.exceptions.BadRequestException;
import com.gpsolutions.hotelsapi.exceptions.NotFoundException;
import com.gpsolutions.hotelsapi.repository.AmenityRepository;
import com.gpsolutions.hotelsapi.repository.HotelRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gpsolutions.hotelsapi.util.AppUtils.*;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final ModelMapper modelMapper;

    public List<HotelResponse> getHotelsList() {
        return hotelRepository.findAll().stream()
                .map(this::mapHotelToResponse)
                .toList();
    }

    public HotelResponse addHotel(HotelCreationRequest dto) {
        checkHotelsUniq(dto);

        Hotel hotel = modelMapper.map(dto, Hotel.class);
        Address address = modelMapper.map(dto.getAddress(), Address.class);
        address.setHotel(hotel);
        Contacts contacts = modelMapper.map(dto.getContacts(), Contacts.class);
        contacts.setHotel(hotel);
        ArrivalTime arrivalTime = modelMapper.map(dto.getArrivalTime(), ArrivalTime.class);
        arrivalTime.setHotel(hotel);

        hotel.setAddress(address);
        hotel.setContacts(contacts);
        hotel.setArrivalTime(arrivalTime);
        hotelRepository.save(hotel);

        return mapHotelToResponse(hotel);
    }

    public HotelDetailResponse addAmenities(Long id, Set<String> amenities) {
        Hotel hotel = getHotelEntity(id);
        for (String name : amenities) {
            Optional<Amenity> amenityOptional = amenityRepository.findByName(name);
            if (amenityOptional.isPresent()) {
                hotel.getAmenities().add(amenityOptional.get());
            } else {
                Amenity amenity = new Amenity(name);
                amenityRepository.save(amenity);
                hotel.getAmenities().add(amenity);
            }
        }
        hotelRepository.save(hotel);

        return mapHotelToDetailResponse(hotel);
    }

    public HotelDetailResponse getHotelDetail(Long id) {
        return mapHotelToDetailResponse(getHotelEntity(id));
    }

    public Map<String, Long> getHistogram(String param) {
        List<HistogramResponse> list;
        switch (param) {
            case "brand" -> list = hotelRepository.histogramByBrand();
            case "city" -> list = hotelRepository.histogramByCity();
            case "county" -> list = hotelRepository.histogramByCounty();
            case "amenities" -> list = hotelRepository.histogramByAmenities();
            default -> throw new BadRequestException("parameter", INVALID_HISTOGRAM_PARAMETER_MESSAGE);
        }

        Map<String, Long> result = new HashMap<>();
        for (HistogramResponse entry : list) {
            result.put(entry.getName(), entry.getAmount());
        }

        return result;
    }

    public List<HotelResponse> findHotelsByParams(String name, String brand, String city, String county, List<String> amenities) {
        List<Hotel> list = hotelRepository.findAll((Specification<Hotel>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%")));
            }
            if (brand != null && !brand.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(criteriaBuilder.lower(root.get("brand")), brand.toLowerCase())));
            }
            if (city != null && !city.isEmpty()) {
                Join<Hotel, Address> addressJoin = root.join("address");
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("city")), "%" + city.toLowerCase() + "%")));
            }
            if (county != null && !county.isEmpty()) {
                Join<Hotel, Address> addressJoin = root.join("address");
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("county")), "%" + county.toLowerCase() + "%")));
            }
            if (amenities != null && !amenities.isEmpty()) {
                for (String amenityName : amenities) {
                    Join<Hotel, Amenity> amenityJoin = root.join("amenities");
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(amenityJoin.get("name")), "%" + amenityName.toLowerCase() + "%")));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        return list.stream()
                .map(this::mapHotelToResponse)
                .toList();
    }

    private void checkHotelsUniq(HotelCreationRequest request) {
        Optional<Hotel> hotelByPhone = hotelRepository.findHotelByContactsPhone(request.getContacts().getPhone());
        Optional<Hotel> hotelByEmail = hotelRepository.findHotelByContactsEmail(request.getContacts().getEmail());
        Optional<Hotel> hotelByAddress = hotelRepository.findHotelByAddress_CountyAndAddress_CityAndAddress_HouseNumber(
                request.getAddress().getCounty(),
                request.getAddress().getCity(),
                request.getAddress().getHouseNumber());
        if (hotelByPhone.isPresent()) {
            throw new BadRequestException("phone", PHONE_ALREADY_EXISTS_MESSAGE);
        }
        if (hotelByEmail.isPresent()) {
            throw new BadRequestException("email", EMAIL_ALREADY_EXISTS_MESSAGE);
        }
        if (hotelByAddress.isPresent()) {
            throw new BadRequestException("address", ADDRESS_ALREADY_EXISTS_MESSAGE);
        }
    }

    private HotelDetailResponse mapHotelToDetailResponse(Hotel hotel) {
        return HotelDetailResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .brand(hotel.getBrand())
                .address(modelMapper.map(hotel.getAddress(), AddressDto.class))
                .contacts(modelMapper.map(hotel.getContacts(), ContactsDto.class))
                .arrivalTime(modelMapper.map(hotel.getArrivalTime(), ArrivalTimeDto.class))
                .amenities(hotel.getAmenities().stream()
                        .map(Amenity::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

    private HotelResponse mapHotelToResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .phone(hotel.getContacts().getPhone())
                .address(hotel.getAddress().toString())
                .description(hotel.getDescription())
                .build();
    }

    private Hotel getHotelEntity(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("id", HOTEL_NOT_FOUND_MESSAGE));
    }
}
