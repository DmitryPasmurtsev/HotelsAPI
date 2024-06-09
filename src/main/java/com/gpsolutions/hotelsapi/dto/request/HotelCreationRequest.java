package com.gpsolutions.hotelsapi.dto.request;

import com.gpsolutions.hotelsapi.dto.AddressDto;
import com.gpsolutions.hotelsapi.dto.ArrivalTimeDto;
import com.gpsolutions.hotelsapi.dto.ContactsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.gpsolutions.hotelsapi.util.AppUtils.*;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelCreationRequest {
    @NotBlank(message = NOT_BLANK)
    @Schema(example = "DoubleTree by Hilton Minsk")
    String name;
    @NotBlank(message = NOT_BLANK)
    @Schema(example = "Any information")
    String description;
    @NotBlank(message = NOT_BLANK)
    @Schema(example = "Hilton")
    String brand;
    @NotNull(message = NOT_NULL)
    @Valid
    AddressDto address;
    @NotNull(message = NOT_NULL)
    @Valid
    ContactsDto contacts;
    @NotNull(message = NOT_NULL)
    @Valid
    ArrivalTimeDto arrivalTime;
}
