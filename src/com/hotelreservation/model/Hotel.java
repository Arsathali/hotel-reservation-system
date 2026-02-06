package com.hotelreservation.model;

public class Hotel {
    
    private String name;
    private int weekdayRate;
    private int weekendRate;
    private int rating;

    public Hotel(String name, int weekdayRate,int weekendRate,int rating) {
        this.name = name;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate; 
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getWeekdayRate() {
        return weekdayRate;
    }

    public int getWeekendRate() {
        return weekendRate;
    }

    public int getRating() {
        return rating;
    }
}

