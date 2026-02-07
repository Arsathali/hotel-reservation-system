package com.hotelreservation.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.hotelreservation.exception.HotelReservationException;
import com.hotelreservation.model.CustomerType;
import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;
import com.hotelreservation.service.HotelReservationService;
import com.hotelreservation.service.InputValidator;

public class HotelReservationTest {

    private HotelReservationService service;
    private DateTimeFormatter formatter;

    @Before
    public void setUp() {
        HotelReservationSystem system = new HotelReservationSystem();
        service = new HotelReservationService(system);

        formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        // Regular + Reward rates + Ratings
        system.addHotels(new Hotel("Lakewood", 110, 90, 3, 80, 80));
        system.addHotels(new Hotel("Bridgewood", 150, 50, 4, 110, 50));
        system.addHotels(new Hotel("Ridgewood", 220, 150, 5, 100, 40));
    }

    // ---------------- UC: Regular Customer Cost ----------------

    @Test
    public void givenDateRange_whenRegularCustomer_shouldCalculateCorrectTotalCost() throws HotelReservationException {
        LocalDate start = LocalDate.parse("11Sep2020", formatter);
        LocalDate end   = LocalDate.parse("12Sep2020", formatter);

        Hotel lakewood = service.findBestRatedHotel(); 
        int cost = service.calculateTotalCostForRegularCustomer(
                lakewood, start, end);

        assertEquals(370, cost);
    }

    // ---------------- UC: Reward Customer Cost ----------------

    @Test
    public void givenDateRange_whenRewardCustomer_shouldCalculateCorrectTotalCost() {
        LocalDate start = LocalDate.parse("11Sep2020", formatter);
        LocalDate end   = LocalDate.parse("12Sep2020", formatter);

        Hotel ridgewood =
                service.findCheapestBestHotelForRewardCustomer(start, end);

        int cost = service.calculateTotalCostForRewardCustomer(
                ridgewood, start, end);

        assertEquals(140, cost);
    }

    // ---------------- Cheapest Best-Rated (Regular) ----------------

    @Test
    public void givenDateRange_whenRegularCustomer_shouldReturnCheapestBestRatedHotel()
            throws HotelReservationException {

        LocalDate start = LocalDate.parse("11Sep2020", formatter);
        LocalDate end   = LocalDate.parse("12Sep2020", formatter);

        Hotel hotel =
                service.findCheapestBestHotelForRegularCustomer(start, end);

        assertEquals("Bridgewood", hotel.getName());
        assertEquals(4, hotel.getRating());
    }

    // ---------------- Best Rated Hotel ----------------

    @Test
    public void givenDateRange_shouldReturnBestRatedHotel()
            throws HotelReservationException {

        Hotel hotel = service.findBestRatedHotel();

        assertEquals("Ridgewood", hotel.getName());
        assertEquals(5, hotel.getRating());
    }

    // ----------------  Invalid Date Range ----------------

    @Test
    public void givenInvalidDateRange_shouldThrowException() {
        LocalDate start = LocalDate.of(2020, 9, 12);
        LocalDate end   = LocalDate.of(2020, 9, 11);

        assertThrows(
                HotelReservationException.class,
                () -> InputValidator.validateInput(
                        CustomerType.REGULAR, start, end)
        );
    }

    // ---------------- Null Customer Type ----------------

    @Test
    public void givenNullCustomerType_shouldThrowException() {
        LocalDate start = LocalDate.of(2020, 9, 11);
        LocalDate end   = LocalDate.of(2020, 9, 12);

        assertThrows(
                HotelReservationException.class,
                () -> InputValidator.validateInput(
                        null, start, end)
        );
    }
}
