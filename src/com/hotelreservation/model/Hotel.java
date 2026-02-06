package com.hotelreservation.model;

public class Hotel {
    
    private String name;
    private int rating;

    //regular customers rates
    private int weekdayRate;
    private int weekendRate;

    //reward customers rates
    private int rewardWeekdayRate;
    private int rewardWeekendRates;
    
    public Hotel(
        String name, 
        int weekdayRate,
        int weekendRate,
        int rating,
        int rewardWeekdayRate,
        int rewardWeekendRates
    ) {
        this.name = name;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate; 
        this.rating = rating;
        this.rewardWeekdayRate = rewardWeekdayRate;
        this.rewardWeekendRates = rewardWeekendRates;
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
    
    public int getRewardWeekdayRate() {
        return rewardWeekdayRate;
    }

    public int getRewardWeekendRates() {
        return rewardWeekendRates;
    }
    
}

