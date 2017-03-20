package com.epam.easyjet.util;

public class PriceConverter {
    private static final String EXCESS_PART_REGEX = "[^0-9.]";
    private static final String EXCESS_PART_WITH_X_REGEX = "[^0-9.x]";
    private static final String PRICE_SEPARATOR = "x";
    private static final int PRICE_INDEX = 1;

    public static double convertStringPrice(String price) {
        String fullPrice = price.replaceAll(EXCESS_PART_REGEX, "");
        return Double.parseDouble(fullPrice);
    }

    public static double converterStringWithX(String price) {
        String[] coefPrice = price.replaceAll(EXCESS_PART_WITH_X_REGEX, "").split(PRICE_SEPARATOR);
        String priceLine = coefPrice[PRICE_INDEX];

        return Double.parseDouble(priceLine);
    }
}