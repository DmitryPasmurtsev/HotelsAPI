package com.gpsolutions.hotelsapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static com.gpsolutions.hotelsapi.util.AppUtils.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArrivalTimeDto {
    @NotBlank(message = NOT_BLANK)
    @Pattern(regexp = "([01][0-9]|2[0-3]):([0-5][0-9])", message = TIME_FORMAT_INVALID_MESSAGE)
    @Schema(example = "14:00")
    String checkIn;
    @Pattern(regexp = "([01][0-9]|2[0-3]):([0-5][0-9])", message = TIME_FORMAT_INVALID_MESSAGE)
    @Schema(example = "12:00")
    String checkOut;
}
