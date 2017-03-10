package com.epam.easyjet.bean;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Flight {

    private String destinationPlace;
    private String departurePlace;
    private String departureDate;
    private String returnDate;
    private int adultCount;
    private int childCount;
    private int infantCount;
    private double departurePrice;
    private double returnPrice;
    private boolean isOneWay;
    private List<Seats> seatsList;

    public Flight() {
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
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

    public double getDeparturePrice() {
        return departurePrice;
    }

    public void setDeparturePrice(double departurePrice) {
        this.departurePrice = departurePrice;
    }

    public double getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(double returnPrice) {
        this.returnPrice = returnPrice;
    }

    public boolean isOneWay() {
        return isOneWay;
    }

    public void setOneWay(boolean oneWay) {
        isOneWay = oneWay;
    }

    public List<Seats> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(List<Seats> seatsList) {
        this.seatsList = seatsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (adultCount != flight.adultCount) return false;
        if (childCount != flight.childCount) return false;
        if (infantCount != flight.infantCount) return false;
        if (Double.compare(flight.departurePrice, departurePrice) != 0) return false;
        if (Double.compare(flight.returnPrice, returnPrice) != 0) return false;
        if (isOneWay != flight.isOneWay) return false;
        if (destinationPlace != null ? !destinationPlace.equals(flight.destinationPlace) : flight.destinationPlace != null)
            return false;
        if (departurePlace != null ? !departurePlace.equals(flight.departurePlace) : flight.departurePlace != null)
            return false;
        if (departureDate != null ? !departureDate.equals(flight.departureDate) : flight.departureDate != null)
            return false;
        if (returnDate != null ? !returnDate.equals(flight.returnDate) : flight.returnDate != null) return false;
        return seatsList != null ? seatsList.equals(flight.seatsList) : flight.seatsList == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = destinationPlace != null ? destinationPlace.hashCode() : 0;
        result = 31 * result + (departurePlace != null ? departurePlace.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + adultCount;
        result = 31 * result + childCount;
        result = 31 * result + infantCount;
        temp = Double.doubleToLongBits(departurePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(returnPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isOneWay ? 1 : 0);
        result = 31 * result + (seatsList != null ? seatsList.hashCode() : 0);
        return result;
    }
}