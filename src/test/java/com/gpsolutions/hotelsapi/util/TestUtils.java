package com.gpsolutions.hotelsapi.util;

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
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@UtilityClass
public class TestUtils {
    public static final long DEFAULT_ID = 1L;
    public static final long NEW_ID = 2L;
    public static final String DEFAULT_NAME = "Name";
    public static final String NEW_NAME = "NewName";
    public static final String DEFAULT_BRAND = "Brand";
    public static final String NEW_BRAND = "NewBrand";
    public static final String DEFAULT_DESCRIPTION = "Brand";
    public static final String DEFAULT_EMAIL = "111@gmail.com";
    public static final String DEFAULT_PHONE = "+375 44 111-11-11";
    public static final Integer HOUSE_NUMBER = 1;
    public static final String POST_CODE = "220040";
    public static final String NEW_EMAIL = "222@gmail.com";
    public static final String NEW_PHONE = "+375 44 222-22-22";
    public static final String DEFAULT_STREET = "Street";
    public static final String NEW_STREET = "NewStreet";
    public static final String DEFAULT_CITY = "City";
    public static final String NEW_CITY = "NewCity";
    public static final String DEFAULT_COUNTY = "County";
    public static final String NEW_COUNTY = "NewCounty";
    public static final String DEFAULT_CHECK_IN = "14:00";
    public static final String DEFAULT_CHECK_OUT = "12:00";
    public static final int VALID_PAGE = 0;
    public static final int VALID_SIZE = 10;
    public static final int VALID_TOTAL = 2;
    public static final int INVALID_PAGE = -1;
    public static final int INVALID_SIZE = -1;
    public static final String INVALID_ORDER_BY = "xyz";
    public static final String VALID_ORDER_BY = "id";
    public static final boolean NOT_BLOCKED = false;
    public static final boolean BLOCKED = true;

    public static Hotel getDefaultHotel() {
        return Hotel.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .brand(DEFAULT_BRAND)
                .description(DEFAULT_DESCRIPTION)
                .address(getDefaultAddress())
                .arrivalTime(getDefaultArrivalTime())
                .contacts(getDefaultContacts())
                .amenities(getDefaultAmenities())
                .build();
    }

    public static Hotel getNewHotel() {
        return Hotel.builder()
                .id(NEW_ID)
                .name(NEW_NAME)
                .brand(NEW_BRAND)
                .description(DEFAULT_DESCRIPTION)
                .address(getNewAddress())
                .arrivalTime(getDefaultArrivalTime())
                .contacts(getNewContacts())
                .amenities(getDefaultAmenities())
                .build();
    }

    public static Set<Amenity> getDefaultAmenities() {
        return new HashSet<>(Arrays.asList(new Amenity("AAA"), new Amenity("BBB")));
    }

    public static Set<String> getDefaultAmenitiesNames() {
        return new HashSet<>(Arrays.asList("AAA", "BBB"));
    }

    public static List<HotelResponse> getHotelResponsesList() {
        return new ArrayList<>(Arrays.asList(getDefaultHotelResponse(), getNewHotelResponse()));
    }

    public static List<Hotel> getHotelsList() {
        return new ArrayList<>(Arrays.asList(getDefaultHotel(), getNewHotel()));
    }

    public static HotelCreationRequest getHotelCreationRequest() {
        return HotelCreationRequest.builder()
                .name(DEFAULT_NAME)
                .brand(DEFAULT_BRAND)
                .description(DEFAULT_DESCRIPTION)
                .address(getDefaultAddressDto())
                .arrivalTime(getDefaultArrivalTimeDto())
                .contacts(getDefaultContactsDto())
                .build();
    }

//    public static Hotel getSecondHotel() {
//        return Hotel.builder()
//                .id(NEW_ID)
//                .name(NEW_NAME)
//                .surname(NEW_SURNAME)
//                .email(NEW_EMAIL)
//                .phone(NEW_PHONE)
//                .build();
//    }


    public static HotelDetailResponse getDefaultHotelDetailResponse() {
        return HotelDetailResponse.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .brand(DEFAULT_BRAND)
                .address(getDefaultAddressDto())
                .arrivalTime(getDefaultArrivalTimeDto())
                .contacts(getDefaultContactsDto())
                .amenities(getDefaultAmenitiesNames())
                .build();
    }

    public static HotelDetailResponse getHotelDetailResponseWithNewAmenities() {
        HotelDetailResponse response = getDefaultHotelDetailResponse();
        response.getAmenities().addAll(getNewAmenitiesNames());
        return response;
    }

    public static HotelResponse getDefaultHotelResponse() {
        return HotelResponse.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .address(getDefaultAddress().toString())
                .phone(DEFAULT_PHONE)
                .description(DEFAULT_DESCRIPTION)
                .build();
    }

    public static HotelResponse getNewHotelResponse() {
        return HotelResponse.builder()
                .id(NEW_ID)
                .name(NEW_NAME)
                .address(getNewAddress().toString())
                .phone(NEW_PHONE)
                .description(DEFAULT_DESCRIPTION)
                .build();
    }

    public static AddressDto getDefaultAddressDto() {
        return AddressDto.builder()
                .houseNumber(HOUSE_NUMBER)
                .county(DEFAULT_COUNTY)
                .city(DEFAULT_CITY)
                .street(DEFAULT_STREET)
                .postCode(POST_CODE)
                .build();
    }

    public static ArrivalTimeDto getDefaultArrivalTimeDto() {
        return ArrivalTimeDto.builder()
                .checkIn(DEFAULT_CHECK_IN)
                .checkOut(DEFAULT_CHECK_OUT)
                .build();
    }

    public static ContactsDto getDefaultContactsDto() {
        return ContactsDto.builder()
                .email(DEFAULT_EMAIL)
                .phone(DEFAULT_PHONE)
                .build();
    }

    public static AddressDto getNewAddressDto() {
        return AddressDto.builder()
                .houseNumber(HOUSE_NUMBER)
                .county(NEW_COUNTY)
                .city(NEW_CITY)
                .street(NEW_STREET)
                .postCode(POST_CODE)
                .build();
    }

    public static ContactsDto getNewContactsDto() {
        return ContactsDto.builder()
                .email(NEW_EMAIL)
                .phone(NEW_PHONE)
                .build();
    }

    public static Address getDefaultAddress() {
        return Address.builder()
                .houseNumber(HOUSE_NUMBER)
                .county(DEFAULT_COUNTY)
                .city(DEFAULT_CITY)
                .street(DEFAULT_STREET)
                .postCode(POST_CODE)
                .build();
    }

    public static Address getNewAddress() {
        return Address.builder()
                .houseNumber(HOUSE_NUMBER)
                .county(NEW_COUNTY)
                .city(NEW_CITY)
                .street(NEW_STREET)
                .postCode(POST_CODE)
                .build();
    }

    public static ArrivalTime getDefaultArrivalTime() {
        return ArrivalTime.builder()
                .checkIn(DEFAULT_CHECK_IN)
                .checkOut(DEFAULT_CHECK_OUT)
                .build();
    }

    public static Contacts getDefaultContacts() {
        return Contacts.builder()
                .email(DEFAULT_EMAIL)
                .phone(DEFAULT_PHONE)
                .build();
    }

    public static Contacts getNewContacts() {
        return Contacts.builder()
                .email(NEW_EMAIL)
                .phone(NEW_PHONE)
                .build();
    }

    public static Map<String, Long> getHistogramByCity() {
        Map<String, Long> map = new HashMap<>();
        map.put("City", 1L);
        map.put("NewCity", 1L);
        return map;
    }

    public static List<HistogramResponse> getHistogramResponsesList() {
        return new ArrayList<>(Arrays.asList(
                new HistogramResponse("City", 1L),
                new HistogramResponse("NewCity", 1L)));
    }

    public static Set<String> getNewAmenitiesNames() {
        return new HashSet<>(Arrays.asList("CCC", "DDD"));
    }
//
//    public static PassengerResponse getDefaultPassengerResponse() {
//        return PassengerResponse.builder()
//                .id(DEFAULT_ID)
//                .name(DEFAULT_NAME)
//                .surname(DEFAULT_SURNAME)
//                .email(DEFAULT_EMAIL)
//                .phone(DEFAULT_PHONE)
//                .build();
//    }
//
//    public static PassengerResponse getSecondPassengerResponse() {
//        return PassengerResponse.builder()
//                .id(NEW_ID)
//                .name(NEW_NAME)
//                .surname(NEW_SURNAME)
//                .email(NEW_EMAIL)
//                .phone(NEW_PHONE)
//                .build();
//    }
//
//    public static Passenger getNotSavedPassengerEntity() {
//        return Passenger.builder()
//                .name(DEFAULT_NAME)
//                .surname(DEFAULT_SURNAME)
//                .email(DEFAULT_EMAIL)
//                .phone(DEFAULT_PHONE)
//                .build();
//    }
//
//    public static RatingUpdateDto getRatingUpdateDto() {
//        return RatingUpdateDto.builder()
//                .userId(DEFAULT_ID)
//                .rating(NEW_RATING)
//                .build();
//    }
//
//    public static List<Passenger> getEntityList() {
//        return new ArrayList<>(Arrays.asList(
//                getDefaultPassenger(),
//                getSecondPassenger()
//        ));
//    }
//
//    public static Page<Passenger> getEntityPage() {
//        return new PageImpl<>(Arrays.asList(
//                getDefaultPassenger(),
//                getSecondPassenger()),
//                Pageable.ofSize(VALID_SIZE),
//                VALID_TOTAL
//        );
//    }
//
//    public static List<PassengerResponse> getPassengerResponsesList() {
//        return new ArrayList<>(Arrays.asList(
//                getDefaultPassengerResponse(),
//                getSecondPassengerResponse()
//        ));
//    }
//
//    public static PassengersListResponse getDefaultPassengersListResponse(List<PassengerResponse> passengers) {
//        return PassengersListResponse.builder()
//                .passengers(passengers)
//                .size(passengers.size())
//                .total(passengers.size())
//                .build();
//    }
//
//    public static PassengersListResponse getPassengersListResponseWithSort(List<PassengerResponse> passengers) {
//        return PassengersListResponse.builder()
//                .passengers(passengers)
//                .size(passengers.size())
//                .total(passengers.size())
//                .sortedByField(VALID_ORDER_BY)
//                .build();
//    }
//
//    public static PassengersListResponse getPassengersListResponseWithPagination(List<PassengerResponse> passengers) {
//        return PassengersListResponse.builder()
//                .passengers(passengers)
//                .size(passengers.size())
//                .total(passengers.size())
//                .page(VALID_PAGE)
//                .build();
//    }
//
//    public static PassengersListResponse getPassengersListResponseWithSortAndPagination(List<PassengerResponse> passengers) {
//        return PassengersListResponse.builder()
//                .passengers(passengers)
//                .size(passengers.size())
//                .total(passengers.size())
//                .page(VALID_PAGE)
//                .sortedByField(VALID_ORDER_BY)
//                .build();
//    }
//
//    public static PassengerCreationRequest getDefaultPassengerCreationRequest() {
//        return PassengerCreationRequest.builder()
//                .name(DEFAULT_NAME)
//                .surname(DEFAULT_SURNAME)
//                .email(DEFAULT_EMAIL)
//                .phone(DEFAULT_PHONE)
//                .build();
//    }
//
//    public static PassengerCreationRequest getDefaultPassengerRequestForUpdate() {
//        return PassengerCreationRequest.builder()
//                .name(NEW_NAME)
//                .surname(NEW_SURNAME)
//                .email(NEW_EMAIL)
//                .phone(NEW_PHONE)
//                .build();
//    }
//
//    public static PassengerResponse getUpdatedPassengerResponse() {
//        return PassengerResponse.builder()
//                .id(DEFAULT_ID)
//                .name(NEW_NAME)
//                .surname(NEW_SURNAME)
//                .email(NEW_EMAIL)
//                .phone(NEW_PHONE)
//                .build();
//    }

}
