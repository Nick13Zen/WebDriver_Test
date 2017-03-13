package com.epam.easyjet.bean;

import java.io.Serializable;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (Double.compare(hotel.price, price) != 0) return false;
        return name != null ? name.equals(hotel.name) : hotel.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
