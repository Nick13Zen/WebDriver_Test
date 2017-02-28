package com.epam.easyjet.bean;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Price {

    private int firstPart;
    private int secondPart;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Price price = (Price) o;

        if (firstPart != price.firstPart) {
            return false;
        }
        return secondPart == price.secondPart;
    }

    @Override
    public int hashCode() {
        int result = firstPart;
        result = 31 * result + secondPart;
        return result;
    }

    public void setFirstPart(int firstPart) {
        this.firstPart = firstPart;
    }

    public void setSecondPart(int secondPart) {
        this.secondPart = secondPart;
    }

    public int getFirstPart() {
        return firstPart;
    }

    public int getSecondPart() {
        return secondPart;
    }
}
