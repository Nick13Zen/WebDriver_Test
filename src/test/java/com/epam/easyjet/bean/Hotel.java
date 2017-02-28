package com.epam.easyjet.bean;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Hotel {

    private String name;
    private Price price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hotel hotel = (Hotel) o;

        if (name != null ? !name.equals(hotel.name) : hotel.name != null) {
            return false;
        }
        return price != null ? price.equals(hotel.price) : hotel.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
