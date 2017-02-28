package com.epam.easyjet.bean;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Race {

    private String from;
    private String to;
    private Price price;
    private List<Seats> seatsList;

    public Race() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Race race = (Race) o;

        if (from != null ? !from.equals(race.from) : race.from != null) {
            return false;
        }
        if (to != null ? !to.equals(race.to) : race.to != null) {
            return false;
        }
        if (price != null ? !price.equals(race.price) : race.price != null) {
            return false;
        }
        return seatsList != null ? seatsList.equals(race.seatsList) : race.seatsList == null;

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (seatsList != null ? seatsList.hashCode() : 0);
        return result;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Seats> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(List<Seats> seatsList) {
        this.seatsList = seatsList;
    }
}
