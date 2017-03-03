package com.epam.easyjet.bean;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Race {

    private String destinationPlace;
    private String departurePlace;
    private String destinationDate;
    private int adultCount;
    private int childCount;
    private int infantCount;
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

        if (adultCount != race.adultCount) {
            return false;
        }

        if (childCount != race.childCount) {
            return false;
        }

        if (infantCount != race.infantCount) {
            return false;
        }

        if (destinationPlace != null ? !destinationPlace.equals(race.destinationPlace) : race.destinationPlace != null) {
            return false;
        }

        if (departurePlace != null ? !departurePlace.equals(race.departurePlace) : race.departurePlace != null) {
            return false;
        }

        if (destinationDate != null ? !destinationDate.equals(race.destinationDate) : race.destinationDate != null) {
            return false;
        }

        if (price != null ? !price.equals(race.price) : race.price != null) {
            return false;
        }
        return seatsList != null ? seatsList.equals(race.seatsList) : race.seatsList == null;
    }

    @Override
    public int hashCode() {
        int result = destinationPlace != null ? destinationPlace.hashCode() : 0;
        result = 31 * result + (departurePlace != null ? departurePlace.hashCode() : 0);
        result = 31 * result + (destinationDate != null ? destinationDate.hashCode() : 0);
        result = 31 * result + adultCount;
        result = 31 * result + childCount;
        result = 31 * result + infantCount;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (seatsList != null ? seatsList.hashCode() : 0);
        return result;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(String destinationDate) {
        this.destinationDate = destinationDate;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(int infantCount) {
        this.infantCount = infantCount;
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
