package com.hotelreservation.model;

public class Hotel {
    
    private String name;
    private int weekdayRate;

    public Hotel(String name, int weekdayRate) {
        this.name = name;
        this.weekdayRate = weekdayRate;
    }

    public String getName() {
        return name;
    }

    public int getWeekdayRate() {
        return weekdayRate;
    }
}
