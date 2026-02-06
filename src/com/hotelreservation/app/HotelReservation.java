
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;
import com.hotelreservation.service.HotelReservationService;

/**
 * HotelReservation is the entry point of the application.
 * Displays welcome message and invokes UC-2 logic.
 */
public class HotelReservation {

    public static void main(String[] args) {
        
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservationSystem system = new HotelReservationSystem();

        system.addHotels(new Hotel("LakeWood", 110));
        system.addHotels(new Hotel("Bridgewood", 150));
        system.addHotels(new Hotel("Ridgewood", 220));

        HotelReservationService service = new HotelReservationService(system);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        LocalDate starDate = LocalDate.parse("10Sep2020",formatter);
        LocalDate enDate = LocalDate.parse("12Sep2020",formatter);

        Hotel cheapestHotel = service.findCheapestHotel(starDate, enDate);

        int totalCost = service.calculateTotalCost(cheapestHotel, starDate, enDate);

         System.out.println("Cheapest Hotel: " + cheapestHotel.getName() + ", Total Rates: $" + totalCost);
    }
}