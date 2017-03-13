package com.epam.easyjet.bean;

import java.io.Serializable;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Luggage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private double price;

    public Luggage() {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Luggage luggage = (Luggage) o;

        if (Double.compare(luggage.price, price) != 0) {
            return false;
        }
        return type != null ? type.equals(luggage.type) : luggage.type == null;
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

    @Override
    public String toString() {
        return "Luggage{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
