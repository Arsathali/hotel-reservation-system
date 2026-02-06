
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;
import com.hotelreservation.service.HotelReservationService;

/**
 * HotelReservation is the entry point of the application.
 * Ability to add weekday and weekend rates for hotels. UC-3 logic.
 */
public class HotelReservation {

    public static void main(String[] args) {
        
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservationSystem system = new HotelReservationSystem();

        system.addHotels(new Hotel("LakeWood", 110, 90));
        system.addHotels(new Hotel("Bridgewood", 150, 50));
        system.addHotels(new Hotel("Ridgewood", 220, 150));

        HotelReservationService service = new HotelReservationService(system);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        LocalDate starDate = LocalDate.parse("10Sep2020",formatter);
        LocalDate enDate = LocalDate.parse("12Sep2020",formatter);

        Hotel cheapestHotel = service.findCheapestHotel(starDate, enDate);

        int totalCost = service.calculateTotalCost(cheapestHotel, starDate, enDate);

        System.out.println("Cheapest Hotel: " + cheapestHotel.getName() + ", Total Rates: $" + totalCost);
    }
}