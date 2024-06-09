package com.gpsolutions.hotelsapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponse {
    @Schema(example = "1")
    Long id;
    @Schema(example = "DoubleTree by Hilton Minsk")
    String name;
    @Schema(example = "Any information")
    String description;
    @Schema(example = "9 Pobediteley Avenue, Minsk, 220004, Belarus")
    String address;
    @Schema(example = "+375 44 111-11-11")
    String phone;
}
