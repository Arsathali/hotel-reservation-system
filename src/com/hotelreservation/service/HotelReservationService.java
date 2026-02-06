package com.hotelreservation.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;

/**
 * HotelReservationService handles business logic related to
 * hotel price calculation and selection.
 *
 * UC-2:
 * Ability to find the cheapest hotel for a given date range
 * for a regular customer.
 */
public class HotelReservationService {
    
    private HotelReservationSystem system;

    public HotelReservationService(HotelReservationSystem system){
        this.system = system;
    }

    /**
     * Finds the cheapest hotel for the given date range
     * considering regular customer weekday rates only.
     *
     * Assumptions:
     * - All dates are weekdays
     * - Rates are applied per day
     *
     * @param startDate start date of stay (inclusive)
     * @param endDate end date of stay (inclusive)
     * @return cheapest Hotel for the given date range
     */
    public Hotel findCheapestHotel(LocalDate starDate,LocalDate enDate){

            long numberOfDays = ChronoUnit.DAYS.between(starDate, enDate)+1;

            Hotel cheapestHotel = null;
            int minCost = Integer.MAX_VALUE;

            for(Hotel hotel : system.getHotels()){

                int totalCost =  (int) numberOfDays * hotel.getWeekdayRate();

                if(totalCost < minCost){
                    cheapestHotel = hotel;
                    minCost = totalCost;
                }

            }
            return cheapestHotel;
    }

    /**
     * Calculates total hotel cost for the given date range
     * using regular weekday rates.
     *
     * @param hotel selected hotel
     * @param startDate start date (inclusive)
     * @param endDate end date (inclusive)
     * @return total cost for stay
     */
    public int calculateTotalCost(Hotel hotel , LocalDate  starDate, LocalDate enDate){
        return ((int) ChronoUnit.DAYS.between(starDate, enDate)+1) * hotel.getWeekdayRate();
    }
}
