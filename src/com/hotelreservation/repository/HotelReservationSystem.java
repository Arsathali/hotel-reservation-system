package com.hotelreservation.repository;

import java.util.ArrayList;
import java.util.List;
import com.hotelreservation.model.Hotel;

public class HotelReservationSystem {
    
    private List<Hotel> hotels = new ArrayList<>();

    public void addHotels(Hotel hotel){
        hotels.add(hotel);
    }
}
