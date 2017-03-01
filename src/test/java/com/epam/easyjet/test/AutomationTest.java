package com.epam.easyjet.test;

import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightsPage;
import com.epam.easyjet.page.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class AutomationTest {
    WebDriver driver;

    @Test
    public void test() throws Exception {
        driver = DriverSingleton.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.typeDeparturePlace("Madrid");
        mainPage.typeDestinationPlace("Lisbon");
        Thread.sleep(1000);
        mainPage.chooseDepartureDate("2017-03-16");
        Thread.sleep(1000);
        mainPage.chooseDestinationDate("2017-03-19");
        mainPage.setInfantCount(1);
        mainPage.setChildCount(1);
        Thread.sleep(1000);
        mainPage.submit();
        driver.getCurrentUrl();
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.openPage();
        flightsPage.clickDeparturePrice();
        flightsPage.clickReturnPrice();
        Thread.sleep(1000);
        flightsPage.submit();
    }
}
