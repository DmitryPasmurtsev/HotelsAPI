package com.gpsolutions.hotelsapi.dto.response;

import com.gpsolutions.hotelsapi.dto.AddressDto;
import com.gpsolutions.hotelsapi.dto.ArrivalTimeDto;
import com.gpsolutions.hotelsapi.dto.ContactsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Builder
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelDetailResponse {
    @Schema(example = "1")
    Long id;
    @Schema(example = "DoubleTree by Hilton Minsk")
    String name;
    @Schema(example = "Hilton")
    String brand;
    AddressDto address;
    ContactsDto contacts;
    ArrivalTimeDto arrivalTime;
    Set<String> amenities;
}
