package com.epam.easyjet.bean;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Seats {

    private String type;
    private double price;

    public Seats() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        Seats seats = (Seats) o;

        if (Double.compare(seats.price, price) != 0) return false;
        return type != null ? type.equals(seats.type) : seats.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type != null ? type.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
