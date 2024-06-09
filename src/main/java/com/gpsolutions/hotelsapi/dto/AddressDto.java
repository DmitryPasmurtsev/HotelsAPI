package com.gpsolutions.hotelsapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AddressDto {
    @NotNull(message = NOT_NULL)
    @Min(value = 1, message = HOUSE_NUMBER_MIN)
    @Schema(example = "9")
    Integer houseNumber;
    @NotBlank(message = NOT_BLANK)
    @Schema(example = "Pobediteley Avenue")
    String street;
    @NotBlank(message = NOT_BLANK)
    @Schema(example = "Minsk")
    String city;
    @NotBlank(message = NOT_BLANK)
    @Schema(example = "Belarus")
    String county;
    @NotBlank(message = NOT_BLANK)
    @Pattern(regexp = "^\\d{6}$", message = POST_CODE_INVALID_MESSAGE)
    @Schema(example = "220004")
    String postCode;
}
