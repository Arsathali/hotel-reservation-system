package com.hotelreservation.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;

import com.hotelreservation.exception.HotelReservationException;
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
     * @throws HotelReservationException 
     */
    public Hotel findCheapestBestHotelForRegularCustomer(LocalDate startDate,LocalDate endDate) throws HotelReservationException{

            Hotel bestHotel = system.getHotels().stream().min(
                Comparator.comparingInt(
                    hotel -> calculateTotalCostForRegularCustomer((Hotel)hotel, startDate, endDate)
                ).thenComparing(
                     (h1, h2) -> Integer.compare(((Hotel) h2).getRating(), ((Hotel) h1).getRating()
                )
            )).orElseThrow(
                () -> new HotelReservationException("No suitable hotel found")
            );
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
     * @throws HotelReservationException 
     */
    public Hotel findBestRatedHotel() throws HotelReservationException {

        Hotel bestHotel = null;
        int highestRating = 0;

        for (Hotel hotel : system.getHotels()) {
            if (hotel.getRating() > highestRating) {
                highestRating = hotel.getRating();
                bestHotel = hotel;
            }
        }

        if (bestHotel == null) {
                throw new HotelReservationException(
                    "No suitable hotel found"
                );
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
    public int calculateTotalCostForRegularCustomer(Hotel hotel , LocalDate  starDate, LocalDate enDate){
        
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


    /*
    * Finds the cheapest hotel for a Reward customer within
    * the given date range.
    *
    * If multiple hotels have the same minimum total cost,
    * the hotel with the highest rating is selected.
    *
    * This method strictly follows UC-10 requirements and
    * does not modify logic from earlier use cases.
    *
    * @param hotels     list of available hotels
    * @param startDate  reservation start date
    * @param endDate    reservation end date
    * @return           cheapest best-rated hotel for Reward customer
    */
     public Hotel findCheapestBestHotelForRewardCustomer(LocalDate starDate,LocalDate enDate) throws HotelReservationException {

            Hotel bestHotel = system.getHotels().stream().min(
                Comparator.comparingInt(
                    hotel -> calculateTotalCostForRewardCustomer((Hotel)hotel, starDate, enDate)
                ).thenComparing(
                     (h1, h2) -> Integer.compare(((Hotel) h2).getRating(), ((Hotel) h1).getRating()
                )
            )).orElseThrow(
                () -> new HotelReservationException("No suitable hotel found")
            );
            return bestHotel;
    }

    /*
    * Calculates the total reservation cost for a Reward customer
    * for the specified date range.
    *
    * Weekday and weekend are identified using Java 8
    * LocalDate and DayOfWeek APIs.
    *
    * Reward customer weekday and weekend rates are applied
    * based on the day type.
    *
    * @param hotel      hotel for which cost is calculated
    * @param startDate  reservation start date
    * @param endDate    reservation end date
    * @return           total cost for Reward customer
    */
    public int calculateTotalCostForRewardCustomer(Hotel hotel , LocalDate  starDate, LocalDate enDate){
        
        int totalCost = 0;

        LocalDate currDate = starDate;

        while(!currDate.isAfter(enDate)){

            DayOfWeek dayOfWeek = currDate.getDayOfWeek();

            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                totalCost += hotel.getRewardWeekendRates();
            }else{
                totalCost += hotel.getRewardWeekdayRate();
            }

            currDate = currDate.plusDays(1);
        }
        return totalCost;
    }


}
