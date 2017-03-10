package com.epam.easyjet.util;

import com.epam.easyjet.bean.*;

public class PriceConverter { //TODO
    private static final int INFANT_TICKET_PRICE = 28;
    private static final int TWO_WAY_TRIP_COUNT_FOR_INFANT = 2;
    private static final int SECOND_PART_MAX_VALUE = 100;

    public static Price convertStringPrice(String price) {
        String[] fullPrice = price.replaceAll("[^0-9.]", "").split("\\.");

        Price priceObj = new Price();
        priceObj.setFirstPart(Integer.parseInt(fullPrice[0]));
        priceObj.setSecondPart(Integer.parseInt(fullPrice[1]));

        return priceObj;
    }

    public static Price converterStringWithX(String price) {
        String[] coefPrice = price.replaceAll("[^0-9.x]", "").split("x");
        String[] partPrice = coefPrice[1].split("\\.");

        Price priceObj = new Price();
        priceObj.setFirstPart(Integer.parseInt(partPrice[0]));
        priceObj.setSecondPart(Integer.parseInt(partPrice[1]));
        return priceObj;
    }

    public static Price CalculateFinalPriceFromOrder(Order order) {
        int finalPriceFirstPart = 0;
        int finalPriceSecondPart = 0;

        Price finalPrice = new Price();
        finalPrice.setFirstPart(finalPriceFirstPart);
        finalPrice.setSecondPart(finalPriceSecondPart);

        GetCarPriceFromOrder(finalPrice, order);
        GetHotelPriceFromOrder(finalPrice, order);
        GetFlightPriceFromOrder(finalPrice, order);
        GetLuggagePriceFromOrder(finalPrice, order);
        GetInsurancePriceFromOrder(finalPrice, order);

        while (finalPrice.getSecondPart() >= SECOND_PART_MAX_VALUE) {
            finalPrice.setFirstPart(finalPrice.getFirstPart() + 1);
            finalPrice.setSecondPart(finalPrice.getSecondPart() - SECOND_PART_MAX_VALUE);
        }
        return finalPrice;
    }

    private static void GetCarPriceFromOrder(Price price, Order order) {
        if (order.getCar() != null) {
            price.setFirstPart(price.getFirstPart() + order.getCar().getPrice().getFirstPart());
            price.setSecondPart(price.getSecondPart() + order.getCar().getPrice().getSecondPart());
        }
    }

    private static void GetHotelPriceFromOrder(Price price, Order order) {
        if (order.getHotel() != null) {
            price.setFirstPart(price.getFirstPart() + order.getHotel().getPrice().getFirstPart());
            price.setSecondPart(price.getSecondPart() + order.getHotel().getPrice().getSecondPart());
        }
    }

    private static void GetInsurancePriceFromOrder(Price price, Order order) {
        if (order.getInsurance() != null) {
            price.setFirstPart(price.getFirstPart() + order.getInsurance().getPrice().getFirstPart());
            price.setSecondPart(price.getSecondPart() + order.getInsurance().getPrice().getSecondPart());
        }
    }

    private static void GetFlightPriceFromOrder(Price price, Order order) { //TODO
        if (order.getFlights() != null) {
            for (Flight flight : order.getFlights()) {
                int departureFirstPartPrice = flight.getDeparturePrice().getFirstPart();
                int departureSecondPartPrice = flight.getDeparturePrice().getSecondPart();

                int returnFirstPartPrice = flight.getReturnPrice().getFirstPart();
                int returnSecondPartPrice = flight.getReturnPrice().getSecondPart();

                int adultAndChildCount = flight.getAdultCount() + flight.getChildCount();
                int infantCount = flight.getInfantCount();

                if (flight.isOneWay()) {
                    price.setFirstPart(price.getFirstPart()
                            + departureFirstPartPrice * adultAndChildCount
                            + INFANT_TICKET_PRICE * infantCount);
                    price.setSecondPart(price.getSecondPart()
                            + departureSecondPartPrice * adultAndChildCount);
                } else {
                    price.setFirstPart(price.getFirstPart()
                            + departureFirstPartPrice * adultAndChildCount
                            + returnFirstPartPrice * adultAndChildCount
                            + INFANT_TICKET_PRICE * infantCount * TWO_WAY_TRIP_COUNT_FOR_INFANT);
                    price.setSecondPart(price.getSecondPart() + departureSecondPartPrice * adultAndChildCount
                            + returnSecondPartPrice * adultAndChildCount);
                }
                for (Seats seats : flight.getSeatsList()) {
                    price.setFirstPart(price.getFirstPart() + seats.getPrice().getFirstPart());
                    price.setSecondPart(price.getSecondPart() + seats.getPrice().getSecondPart());
                }
            }
        }
    }

    private static void GetLuggagePriceFromOrder(Price price, Order order) {
        if (order.getLuggage() != null) {
            for (Luggage luggage : order.getLuggage()) {
                price.setFirstPart(price.getFirstPart() + luggage.getPrice().getFirstPart());
                price.setSecondPart(price.getSecondPart() + luggage.getPrice().getSecondPart());
            }
        }
    }
}