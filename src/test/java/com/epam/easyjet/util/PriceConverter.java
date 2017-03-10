package com.epam.easyjet.util;

import com.epam.easyjet.bean.*;

public class PriceConverter { //TODO
    private static final int INFANT_TICKET_PRICE = 28;
    private static final int TWO_WAY_TRIP_COUNT_FOR_INFANT = 2;
    private static final int SECOND_PART_MAX_VALUE = 100;

    public static double convertStringPrice(String price) {
        String fullPrice = price.replaceAll("[^0-9.]", "");
        return Double.parseDouble(fullPrice);
    }

    public static double converterStringWithX(String price) {
        String[] coefPrice = price.replaceAll("[^0-9.x]", "").split("x");
        String priceLine = coefPrice[1];

        return Double.parseDouble(priceLine);
    }

//    public static Price calculateFinalPriceFromOrder(Order order) {
//    int finalPriceFirstPart = 0;
//    int finalPriceSecondPart = 0;
//
//    Price finalPrice = new Price();
//    finalPrice.setFirstPart(finalPriceFirstPart);
//    finalPrice.setSecondPart(finalPriceSecondPart);
//
//    etCarPriceFromOrder(finalPrice, order);
//    GetHotelPriceFromOrder(finalPrice, order);
//    GetFlightPriceFromOrder(finalPrice, order);
//    GetLuggagePriceFromOrder(finalPrice, order);
//    GetInsurancePriceFromOrder(finalPrice, order);
//
//    while (finalPrice.getSecondPart() >= SECOND_PART_MAX_VALUE) {
//        finalPrice.setFirstPart(finalPrice.getFirstPart() + 1);
//        finalPrice.setSecondPart(finalPrice.getSecondPart() - SECOND_PART_MAX_VALUE);
//    }
//    return finalPrice;
//}
//
//    private static void getCarPriceFromOrder(Price price, Order order) {
//        if (order.getCar() != null) {
//            price.setFirstPart(price.getFirstPart() + order.getCar().getPrice().getFirstPart());
//            price.setSecondPart(price.getSecondPart() + order.getCar().getPrice().getSecondPart());
//        }
//    }
//
//    private static void getHotelPriceFromOrder(Price price, Order order) {
//        if (order.getHotel() != null) {
//            price.setFirstPart(price.getFirstPart() + order.getHotel().getPrice().getFirstPart());
//            price.setSecondPart(price.getSecondPart() + order.getHotel().getPrice().getSecondPart());
//        }
//    }
//
//    private static void getInsurancePriceFromOrder(Price price, Order order) {
//        if (order.getInsurance() != null) {
//            price.setFirstPart(price.getFirstPart() + order.getInsurance().getPrice().getFirstPart());
//            price.setSecondPart(price.getSecondPart() + order.getInsurance().getPrice().getSecondPart());
//        }
//    }
//
//    private static void getFlightPriceFromOrder(Price price, Order order) { //TODO
//        if (order.getFlights() != null) {
//            for (Flight flight : order.getFlights()) {
//                int departureFirstPartPrice = flight.getDeparturePrice().getFirstPart();
//                int departureSecondPartPrice = flight.getDeparturePrice().getSecondPart();
//
//                int returnFirstPartPrice = flight.getReturnPrice().getFirstPart();
//                int returnSecondPartPrice = flight.getReturnPrice().getSecondPart();
//
//                int adultAndChildCount = flight.getAdultCount() + flight.getChildCount();
//                int infantCount = flight.getInfantCount();
//
//                if (flight.isOneWay()) {
//                    price.setFirstPart(price.getFirstPart()
//                            + departureFirstPartPrice * adultAndChildCount
//                            + INFANT_TICKET_PRICE * infantCount);
//                    price.setSecondPart(price.getSecondPart()
//                            + departureSecondPartPrice * adultAndChildCount);
//                } else {
//                    price.setFirstPart(price.getFirstPart()
//                            + departureFirstPartPrice * adultAndChildCount
//                            + returnFirstPartPrice * adultAndChildCount
//                            + INFANT_TICKET_PRICE * infantCount * TWO_WAY_TRIP_COUNT_FOR_INFANT);
//                    price.setSecondPart(price.getSecondPart() + departureSecondPartPrice * adultAndChildCount
//                            + returnSecondPartPrice * adultAndChildCount);
//                }
//                for (Seats seats : flight.getSeatsList()) {
//                    price.setFirstPart(price.getFirstPart() + seats.getPrice().getFirstPart());
//                    price.setSecondPart(price.getSecondPart() + seats.getPrice().getSecondPart());
//                }
//            }
//        }
//    }
//
//    private static void getLuggagePriceFromOrder(Price price, Order order) {
//        if (order.getLuggage() != null) {
//            for (Luggage luggage : order.getLuggage()) {
//                price.setFirstPart(price.getFirstPart() + luggage.getPrice().getFirstPart());
//                price.setSecondPart(price.getSecondPart() + luggage.getPrice().getSecondPart());
//            }
//        }
//    }
}