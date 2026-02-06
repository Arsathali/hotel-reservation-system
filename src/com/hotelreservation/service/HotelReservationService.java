package com.hotelreservation.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;

/**
 * HotelReservationService handles business logic related to
 * hotel price calculation and selection.
 *
 * Ability to find the cheapest hotels for a given date range
 * for a regular customer.
 */
public class HotelReservationService {
    
    private HotelReservationSystem system;

    public HotelReservationService(HotelReservationSystem system){
        this.system = system;
    }

    /**
     * Finds the cheapest hotel and well rated hotel for the given date range
     * considering regular customer weekday rates and weekends.
     *
     * Assumptions:
     * - Rates are applied per day
     *
     * @param startDate start date of stay (inclusive)
     * @param endDate end date of stay (inclusive)
     * @return cheapest Hotels and best rated for the given date range
     */
    public Hotel findCheapestHotel(LocalDate starDate,LocalDate enDate){

            Hotel bestHotel = null;
            int minCost = Integer.MAX_VALUE;

            for(Hotel hotel : system.getHotels()){

                int totalCost =  calculateTotalCost(hotel , starDate , enDate);

                if(totalCost < minCost){
                    bestHotel = hotel;
                    minCost = totalCost;
                }else if(totalCost == minCost && hotel.getRating() > bestHotel.getRating()){
                    bestHotel = hotel;
                }

            }
            return bestHotel;
    }

    /**
     * Finds the Best Rated hotel  for the given date range
     * considering regular customer weekday rates and weekends.
     *
     * Assumptions:
     * - Rates are applied per day
     *
     * @return best rated Hotels for the given date range
     */
    public Hotel findBestRatedHotel() {

        Hotel bestHotel = null;
        int highestRating = 0;

        for (Hotel hotel : system.getHotels()) {
            if (hotel.getRating() > highestRating) {
                highestRating = hotel.getRating();
                bestHotel = hotel;
            }
        }
        return bestHotel;
    }


    /**
     * Calculates total hotel cost for the given date range
     * using regular weekday rates and weekends.
     *
     * @param hotel selected hotel
     * @param startDate start date (inclusive)
     * @param endDate end date (inclusive)
     * @return total cost for stay
     */
    public int calculateTotalCost(Hotel hotel , LocalDate  starDate, LocalDate enDate){
        
        int totalCost = 0;

        LocalDate currDate = starDate;

        while(!currDate.isAfter(enDate)){

            DayOfWeek dayOfWeek = currDate.getDayOfWeek();

            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                totalCost += hotel.getWeekendRate();
            }else{
                totalCost += hotel.getWeekdayRate();
            }

            currDate = currDate.plusDays(1);
        }
        return totalCost;
    }
}
