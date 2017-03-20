package com.epam.easyjet.step;

import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.CheckoutPage;

/**
 * Created by Nikita_Zenchyk on 3/7/2017.
 */
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
