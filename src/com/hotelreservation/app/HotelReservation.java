
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.hotelreservation.exception.HotelReservationException;
import com.hotelreservation.model.CustomerType;
import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;
import com.hotelreservation.service.HotelReservationService;
import com.hotelreservation.service.InputValidator;

/**
 * HotelReservation is the entry point of the application.
 * Ability to add special rates for 
 * reward customers as a Part of loyalty program. UC-8 logic.
 */
public class HotelReservation {

    public static void main(String[] args) throws HotelReservationException {
        
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservationSystem system = new HotelReservationSystem();

        system.addHotels(new Hotel("LakeWood", 110, 90, 3,80, 80 ));
        system.addHotels(new Hotel("Bridgewood", 150, 50, 4, 110, 50));
        system.addHotels(new Hotel("Ridgewood", 220, 150, 5,100, 40));
        System.out.println();

        HotelReservationService service = new HotelReservationService(system);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        LocalDate startDate = LocalDate.parse("11Sep2020",formatter);
        LocalDate endDate = LocalDate.parse("12Sep2020",formatter);

        //validates the input
        InputValidator.validateInput(CustomerType.REWARD, startDate, endDate);

        Hotel hotel = service.findCheapestBestHotelForRewardCustomer(startDate, endDate);
        int totalCost = service.calculateTotalCostForRewardCustomer(hotel, startDate, endDate);

        System.out.println(
                    hotel.getName() +
                    ", Rating: " + hotel.getRating() +
                    " and Total Rates: $" + totalCost
        );
    }
}