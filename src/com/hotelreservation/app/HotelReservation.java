import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelReservationSystem;

public class HotelReservation {

    public static void main(String[] args) {
        
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservationSystem system = new HotelReservationSystem();

        system.addHotels(new Hotel("LakeWood", 110));
        system.addHotels(new Hotel("Bridgewood", 150));
        system.addHotels(new Hotel("Ridgewood", 220));
    }
}