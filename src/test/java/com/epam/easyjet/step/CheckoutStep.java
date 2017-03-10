package com.epam.easyjet.step;

import com.epam.easyjet.bean.Price;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.CheckoutPage;
import org.openqa.selenium.WebDriver;

/**
 * Created by Nikita_Zenchyk on 3/7/2017.
 */
public class CheckoutStep {

    private WebDriver driver;
    private CheckoutPage checkoutPage;

    public CheckoutStep() {
        driver = DriverSingleton.getDriver();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.openPage();
    }

    public void closeDriver() {
        driver.close();
    }

    public Price fillCheckoutPage() {
        Price finalPrice = checkoutPage.getFinalPrice();
        return finalPrice;
    }

}
