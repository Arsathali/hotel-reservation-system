package com.hotelreservation.exception;

/*
 * Custom exception class used to handle
 * invalid inputs and business rule violations
 * in the Hotel Reservation System.
 */
public class HotelReservationException extends Exception {
    
    public HotelReservationException(String message) {
        super(message);
    }
}

