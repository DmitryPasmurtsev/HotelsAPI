package com.gpsolutions.hotelsapi.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppUtils {
    public static final String NOT_BLANK = "Shouldn't be empty";
    public static final String NOT_NULL = "Shouldn't be null";
    public static final String HOUSE_NUMBER_MIN = "Min value is 1";
    public static final String POST_CODE_INVALID_MESSAGE = "Postal code must be 6 digits";
    public static final String TIME_FORMAT_INVALID_MESSAGE = "Time must be between 00:00 and 23:59";
    public static final String EMAIL_FORMAT_INVALID_MESSAGE = "Invalid email format";
    public static final String PHONE_FORMAT_INVALID_MESSAGE = "Phone pattern is '+375 xx xxx-xx-xx'. Valid codes are 29, 44, 33, 25, 17";
    public static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Hotel with this email already exists";
    public static final String PHONE_ALREADY_EXISTS_MESSAGE = "Hotel with this phone already exists";
    public static final String ADDRESS_ALREADY_EXISTS_MESSAGE = "Hotel with this address already exists";
    public static final String HOTEL_NOT_FOUND_MESSAGE = "Hotel with this id not found";
    public static final String INVALID_HISTOGRAM_PARAMETER_MESSAGE = "Invalid histogram parameter";
}
