package com.epam.easyjet.bean;

import java.io.Serializable;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Insurance implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private double price;

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

        Insurance insurance = (Insurance) o;

        if (Double.compare(insurance.price, price) != 0) {
            return false;
        }
        return type != null ? type.equals(insurance.type) : insurance.type == null;
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
        return "Insurance{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
