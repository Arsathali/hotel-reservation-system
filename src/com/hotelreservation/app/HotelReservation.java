
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;
import com.hotelreservation.service.HotelReservationService;

/**
 * HotelReservation is the entry point of the application.
 * Ability to find the cheapest best rated 
 * Hotel for a given Date Range. UC-5 logic.
 */
public class HotelReservation {

    public static void main(String[] args) {
        
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservationSystem system = new HotelReservationSystem();

        system.addHotels(new Hotel("LakeWood", 110, 90,3));
        system.addHotels(new Hotel("Bridgewood", 150, 50, 4));
        system.addHotels(new Hotel("Ridgewood", 220, 150, 5));
        System.out.println();

        HotelReservationService service = new HotelReservationService(system);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        LocalDate starDate = LocalDate.parse("11Sep2020",formatter);
        LocalDate endDate = LocalDate.parse("12Sep2020",formatter);

        Hotel bestHotel = service.findCheapestHotel(starDate, endDate);

        int totalCost = service.calculateTotalCost(bestHotel, starDate, endDate);

        System.out.println("Cheapest and best Rated Hotel is: " + bestHotel.getName());
        
        System.out.print("Total Cost: "+ totalCost);
    }
}