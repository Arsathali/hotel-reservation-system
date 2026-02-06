
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;
import com.hotelreservation.service.HotelReservationService;

/**
 * HotelReservation is the entry point of the application.
 * Ability to find the cheapest Hotel for a 
 * given Date Range based on weekday and weakend. UC-4 logic.
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

        LocalDate starDate = LocalDate.parse("11Sep2020",formatter);
        LocalDate endDate = LocalDate.parse("12Sep2020",formatter);

        List<Hotel> cheapestHotels = service.findCheapestHotel(starDate, endDate);

        int totalCost = service.calculateTotalCost(cheapestHotels.get(0), starDate, endDate);

        System.out.print("Cheapest Hotel(s): ");
        for(Hotel hotel : cheapestHotels){
            System.out.print(hotel.getName()+" ");
        }
        System.out.print(": "+totalCost);
    }
}