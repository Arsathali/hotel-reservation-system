package com.hotelreservation.repository;

import java.util.ArrayList;
import java.util.List;
import com.hotelreservation.model.Hotel;

/**
 * HotelRepository stores and provides access
 * to the list of available hotels.
 */
public class HotelReservationSystem {
    
    private List<Hotel> hotels = new ArrayList<>();

    public void addHotels(Hotel hotel){
        hotels.add(hotel);
    }

    public List<Hotel> getHotels(){
        return hotels;
    }
}
