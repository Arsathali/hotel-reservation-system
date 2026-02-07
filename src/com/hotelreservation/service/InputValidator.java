package com.hotelreservation.service;

import java.time.LocalDate;

import com.hotelreservation.exception.HotelReservationException;
import com.hotelreservation.model.CustomerType;

/*
 * Validates user input for customer type and date range.
 *
 * Throws a custom HotelReservationException when:
 * - Customer type is null
 * - Dates are null
 * - End date occurs before start date
 *
 * This validation ensures robustness before
 * processing reservation logic.
 */
public class InputValidator {
    

    public static void validateInput(CustomerType type , LocalDate startDate , LocalDate endDate) throws HotelReservationException{

        if (type == null) {
            throw new HotelReservationException("Invalid customer type");
        }

        if (startDate == null || endDate == null) {
            throw new HotelReservationException("Date cannot be null");
        }

        if (endDate.isBefore(startDate)) {
            throw new HotelReservationException(
                    "End date cannot be before start date");
        }

    }
}
