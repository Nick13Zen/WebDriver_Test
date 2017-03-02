package com.epam.easyjet.test;

import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightOptionsPage;
import com.epam.easyjet.page.FlightsPage;
import com.epam.easyjet.page.MainPage;
import com.epam.easyjet.step.Steps;
import com.epam.easyjet.util.HoldScriptUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Set;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class AutomationTest {

    WebDriver driver;
    Steps steps;

    /**
     * Method should be deleted, when all pages will be ready
     */
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
        mainPage.submit();

        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.openPage();
        Thread.sleep(1000);
        flightsPage.clickDeparturePrice();
        Thread.sleep(1000);
        flightsPage.clickReturnPrice();
        Thread.sleep(1000);
        flightsPage.submit();
        System.out.println(driver.getCurrentUrl());

        FlightOptionsPage flightOptionsPage = new FlightOptionsPage(driver);
        flightOptionsPage.openPage();
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.addLuggageButton();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.getLuggage().toString();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.getSeat();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.chooseEconomSeat();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.chooseStandardSeat();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.saveSeats();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.chooseEconomSeat();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.chooseStandardSeat();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.saveSeats();
        HoldScriptUtil.waitScriptLoading(driver);
        Thread.sleep(1000);
        flightOptionsPage.submit();
        HoldScriptUtil.waitScriptLoading(driver);
        flightOptionsPage.goNext();
    }

    /*@BeforeTest
    public void setUp() {
        steps = new Steps();
        steps.initDriver();
    }

    @DataProvider(name = "invalid client count")
    public Object[][] getInvalidData() throws Exception {
        return new Object[][] {
                {41, 0 , 0},
                {0, 41, 0},
                {20, 21, 0},
                {1, 1, 2},
                {8, 8, 8}
        };
    }

    @DataProvider(name = "valid client count")
    public Object[][] getValidData() throws Exception {
        return new Object[][] {
                {40, 0 , 0},
                {20, 20, 0},
                {1, 1, 1},
                {7, 0, 7}
        };
    }

    @Test(dataProvider = "invalid client count")
    public void testInvalidCountOfClients(int adultCount, int childCount, int infantCount) throws Exception {
        steps.setClientCount(adultCount, childCount, infantCount);
        Assert.assertTrue(steps.isWarningDisplayed());
    }

    @Test(dataProvider = "valid client count")
    public void testValidCountOfClients(int adultCount, int childCount, int infantCount) throws Exception {
        steps.setClientCount(adultCount, childCount, infantCount);
        Assert.assertTrue(!steps.isWarningDisplayed());
    }*/
}
