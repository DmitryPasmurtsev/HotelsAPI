package com.gpsolutions.hotelsapi.service;

import com.gpsolutions.hotelsapi.dto.AddressDto;
import com.gpsolutions.hotelsapi.dto.ArrivalTimeDto;
import com.gpsolutions.hotelsapi.dto.ContactsDto;
import com.gpsolutions.hotelsapi.dto.request.HotelCreationRequest;
import com.gpsolutions.hotelsapi.dto.response.HotelDetailResponse;
import com.gpsolutions.hotelsapi.dto.response.HotelResponse;
import com.gpsolutions.hotelsapi.entity.Amenity;
import com.gpsolutions.hotelsapi.entity.Hotel;
import com.gpsolutions.hotelsapi.exceptions.BadRequestException;
import com.gpsolutions.hotelsapi.exceptions.NotFoundException;
import com.gpsolutions.hotelsapi.repository.AmenityRepository;
import com.gpsolutions.hotelsapi.repository.HotelRepository;
import com.gpsolutions.hotelsapi.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.gpsolutions.hotelsapi.util.TestUtils.DEFAULT_ID;
import static com.gpsolutions.hotelsapi.util.TestUtils.getDefaultHotel;
import static com.gpsolutions.hotelsapi.util.TestUtils.getDefaultHotelDetailResponse;
import static com.gpsolutions.hotelsapi.util.TestUtils.getDefaultHotelResponse;
import static com.gpsolutions.hotelsapi.util.TestUtils.getHistogramByCity;
import static com.gpsolutions.hotelsapi.util.TestUtils.getHistogramResponsesList;
import static com.gpsolutions.hotelsapi.util.TestUtils.getHotelCreationRequest;
import static com.gpsolutions.hotelsapi.util.TestUtils.getHotelDetailResponseWithNewAmenities;
import static com.gpsolutions.hotelsapi.util.TestUtils.getHotelResponsesList;
import static com.gpsolutions.hotelsapi.util.TestUtils.getNewAmenitiesNames;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private AmenityRepository amenityRepository;
    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private HotelService hotelService;

    @Test
    void getHotelsList() {
        List<HotelResponse> expected = getHotelResponsesList();

        doReturn(TestUtils.getHotelsList())
                .when(hotelRepository)
                .findAll();

        List<HotelResponse> actual = hotelService.getHotelsList();

        assertEquals(expected, actual);
    }

    @Test
    void addHotel_whenPhoneNotUniq_shouldThrowException() {
        HotelCreationRequest request = getHotelCreationRequest();

        doReturn(Optional.of(getDefaultHotel()))
                .when(hotelRepository)
                .findHotelByContactsPhone(request.getContacts().getPhone());

        assertThrows(
                BadRequestException.class,
                () -> hotelService.addHotel(request)
        );

        verify(hotelRepository).findHotelByContactsPhone(request.getContacts().getPhone());
    }

    @Test
    void addHotel_whenDataUniq() {
        HotelCreationRequest request = getHotelCreationRequest();

        HotelResponse expected = getDefaultHotelResponse();
        expected.setId(null);

        doReturn(Optional.empty())
                .when(hotelRepository)
                .findHotelByContactsPhone(request.getContacts().getPhone());
        doReturn(Optional.empty())
                .when(hotelRepository)
                .findHotelByContactsEmail(request.getContacts().getEmail());
        doReturn(Optional.empty())
                .when(hotelRepository)
                .findHotelByAddress_CountyAndAddress_CityAndAddress_HouseNumber(
                        request.getAddress().getCounty(),
                        request.getAddress().getCity(),
                        request.getAddress().getHouseNumber());

        HotelResponse actual = hotelService.addHotel(request);

        verify(hotelRepository).findHotelByContactsPhone(any(String.class));
        verify(hotelRepository).findHotelByContactsEmail(any(String.class));
        verify(hotelRepository).findHotelByAddress_CountyAndAddress_CityAndAddress_HouseNumber(
                any(String.class), any(String.class), any(Integer.class));
        verify(hotelRepository).save(any(Hotel.class));

        assertEquals(expected, actual);
    }

    @Test
    void addAmenities() {
        Set<String> newAmenities = getNewAmenitiesNames();
        HotelDetailResponse expected = getHotelDetailResponseWithNewAmenities();

        Hotel entity = getDefaultHotel();

        doReturn(Optional.of(entity))
                .when(hotelRepository)
                .findById(DEFAULT_ID);
        doReturn(Optional.of(new Amenity("CCC")))
                .when(amenityRepository)
                .findByName("CCC");
        doReturn(Optional.of(new Amenity("DDD")))
                .when(amenityRepository)
                .findByName("DDD");

        HotelDetailResponse actual = hotelService.addAmenities(DEFAULT_ID, newAmenities);

        verify(hotelRepository).save(entity);
        verify(amenityRepository, times(2)).findByName(any(String.class));
        verify(modelMapper).map(entity.getAddress(), AddressDto.class);
        verify(modelMapper).map(entity.getContacts(), ContactsDto.class);
        verify(modelMapper).map(entity.getArrivalTime(), ArrivalTimeDto.class);

        assertEquals(expected, actual);
    }

    @Test
    void getHotelDetail_whenHotelExists() {
        HotelDetailResponse expected = getDefaultHotelDetailResponse();
        Hotel entity = getDefaultHotel();

        doReturn(Optional.of(entity))
                .when(hotelRepository)
                .findById(DEFAULT_ID);

        HotelDetailResponse actual = hotelService.getHotelDetail(DEFAULT_ID);

        verify(hotelRepository).findById(DEFAULT_ID);
        verify(modelMapper).map(entity.getAddress(), AddressDto.class);
        verify(modelMapper).map(entity.getContacts(), ContactsDto.class);
        verify(modelMapper).map(entity.getArrivalTime(), ArrivalTimeDto.class);

        assertEquals(expected, actual);
    }

    @Test
    void getHotelDetail_whenHotelNotExists_shouldThrowException() {
        doReturn(Optional.empty())
                .when(hotelRepository)
                .findById(DEFAULT_ID);

        assertThrows(
                NotFoundException.class,
                () -> hotelService.getHotelDetail(DEFAULT_ID)
        );

        verify(hotelRepository).findById(DEFAULT_ID);
    }

    @Test
    void getHistogramByCityParam() {
        Map<String, Long> expected = getHistogramByCity();

        doReturn(getHistogramResponsesList())
                .when(hotelRepository)
                .histogramByCity();

        Map<String, Long> actual = hotelService.getHistogram("city");

        assertEquals(expected, actual);
    }

    @Test
    void getHistogramByInvalidParam_shouldThrowException() {
        assertThrows(
                BadRequestException.class,
                () -> hotelService.getHistogram("wrong")
        );
    }
}