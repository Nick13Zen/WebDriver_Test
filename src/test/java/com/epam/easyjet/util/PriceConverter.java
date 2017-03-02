package com.epam.easyjet.util;

import com.epam.easyjet.bean.Price;

public class PriceConverter {
    public static Price convertStringPrice(String price) {
        String[] fullPrice = price.replaceAll("[^0-9.]", "").split("\\.");

        Price priceObj = new Price();
        priceObj.setFirstPart(Integer.parseInt(fullPrice[0]));
        priceObj.setSecondPart(Integer.parseInt(fullPrice[1]));

        return priceObj;
    }
}
