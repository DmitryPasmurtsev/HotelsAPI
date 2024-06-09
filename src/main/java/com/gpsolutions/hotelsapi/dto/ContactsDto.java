package com.gpsolutions.hotelsapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
public class ContactsDto {
    @Pattern(regexp = "\\+375\\s(17|25|29|33|44)\\s\\d{3}-\\d{2}-\\d{2}", message = PHONE_FORMAT_INVALID_MESSAGE)
    @NotBlank(message = NOT_BLANK)
    @Schema(example = "+375 44 111-11-11")
    String phone;
    @NotBlank(message = NOT_BLANK)
    @Email(message = EMAIL_FORMAT_INVALID_MESSAGE)
    @Schema(example = "pochta@gmail.com")
    String email;
}
