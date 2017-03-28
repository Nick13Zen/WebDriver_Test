package com.epam.easyjet.step;

import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.CheckoutPage;

public class CheckoutStep {
    private final CheckoutPage checkoutPage;

    public CheckoutStep() {
        checkoutPage = new CheckoutPage(DriverSingleton.getDriver());
        checkoutPage.openPage();
    }

    public double getFinalPrice() {
        return checkoutPage.getFinalPrice();
    }
}
