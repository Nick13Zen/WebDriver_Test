package com.epam.easyjet.bean;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Order {
    private List<Flight> flights;
    private List<Luggage> luggage;
    private Insurance insurance;
    private Car car;
    private Hotel hotel;
    private Price price;

    public Order() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (flights != null ? !flights.equals(order.flights) : order.flights != null) {
            return false;
        }
        if (luggage != null ? !luggage.equals(order.luggage) : order.luggage != null) {
            return false;
        }
        if (insurance != null ? !insurance.equals(order.insurance) : order.insurance != null) {
            return false;
        }
        if (car != null ? !car.equals(order.car) : order.car != null) {
            return false;
        }
        if (hotel != null ? !hotel.equals(order.hotel) : order.hotel != null) {
            return false;
        }
        return price != null ? price.equals(order.price) : order.price == null;

    }

    @Override
    public int hashCode() {
        int result = flights != null ? flights.hashCode() : 0;
        result = 31 * result + (luggage != null ? luggage.hashCode() : 0);
        result = 31 * result + (insurance != null ? insurance.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Luggage> getLuggage() {
        return luggage;
    }

    public void setLuggage(List<Luggage> luggage) {
        this.luggage = luggage;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
