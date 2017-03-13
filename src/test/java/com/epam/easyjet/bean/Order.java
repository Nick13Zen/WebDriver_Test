package com.epam.easyjet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Flight> flights;
    private List<Luggage> luggage;
    private Insurance insurance;
    private Car car;
    private Hotel hotel;
    private double price;

    public Order() {

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

    public double getPrice() {
        return price;
    }

    public void addPrice(double price) {
        this.price += price;
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

        if (Double.compare(order.price, price) != 0) {
            return false;
        }
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
        return hotel != null ? hotel.equals(order.hotel) : order.hotel == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = flights != null ? flights.hashCode() : 0;
        result = 31 * result + (luggage != null ? luggage.hashCode() : 0);
        result = 31 * result + (insurance != null ? insurance.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "flights=" + flights +
                ", luggage=" + luggage +
                ", insurance=" + insurance +
                ", car=" + car +
                ", hotel=" + hotel +
                ", price=" + price +
                '}';
    }
}
