package com.epam.easyjet.bean;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Insurance {
    private String type;
    private Price price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Insurance insurance = (Insurance) o;

        if (type != null ? !type.equals(insurance.type) : insurance.type != null) {
            return false;
        }
        return price != null ? price.equals(insurance.price) : insurance.price == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
