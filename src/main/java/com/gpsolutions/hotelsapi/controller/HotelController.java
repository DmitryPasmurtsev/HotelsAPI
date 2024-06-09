package com.gpsolutions.hotelsapi.controller;

import com.gpsolutions.hotelsapi.dto.request.HotelCreationRequest;
import com.gpsolutions.hotelsapi.dto.response.HotelDetailResponse;
import com.gpsolutions.hotelsapi.dto.response.HotelResponse;
import com.gpsolutions.hotelsapi.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    @Operation(summary = "Get hotels list")
    public List<HotelResponse> getHotelsList() {
        return hotelService.getHotelsList();
    }

    @PostMapping("/hotels")
    @Operation(summary = "Add new hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel creation is successful"),
            @ApiResponse(responseCode = "400", description = "Hotel's fields are not uniq")})
    public HotelResponse addHotel(@RequestBody @Valid HotelCreationRequest request) {
        return hotelService.addHotel(request);
    }

    @PostMapping("/hotels/{id}/amenities")
    @Operation(summary = "Add amenities to hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Amenities adding is successful"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")})
    public HotelDetailResponse addAmenities(@PathVariable Long id, @RequestBody Set<String> amenities) {
        return hotelService.addAmenities(id, amenities);
    }

    @GetMapping("/hotels/{id}")
    @Operation(summary = "Get detail hotel information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")})
    public HotelDetailResponse getHotelDetail(@PathVariable Long id) {
        return hotelService.getHotelDetail(id);
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Get histogram by certain parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histogram creation is successful"),
            @ApiResponse(responseCode = "400", description = "Wrong histogram parameter")})
    public Map<String, Long> getHistogram(@PathVariable String param) {
        return hotelService.getHistogram(param);
    }

    @GetMapping("/search")
    @Operation(summary = "Search hotels")
    public List<HotelResponse> findHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String county,
            @RequestParam(required = false) List<String> amenities
    ) {
        return hotelService.findHotelsByParams(name, brand, city, county, amenities);
    }
}
