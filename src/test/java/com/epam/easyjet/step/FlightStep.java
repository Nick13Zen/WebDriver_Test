package com.epam.easyjet.step;

import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Set;

/**
 * Created by Mark_Harbunou on 3/6/2017.
 */
public class FlightStep {
    private static final Logger logger = LogManager.getRootLogger();
    private static final String PRICE_NOT_INITIALIZED = "Price not initialized";

    private WebDriver driver;
    private FlightsPage flightsPage;

    public FlightStep() {
        driver = DriverSingleton.getDriver();
        flightsPage = new FlightsPage(driver);
        flightsPage.openPage();
    }

    public void setRoutePrice(List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight.isOneWay()) {
                flight.setDeparturePrice(flightsPage.selectOutBoundPrice());
            } else {
                flight.setDeparturePrice(flightsPage.selectOutBoundPrice());
                flight.setReturnPrice(flightsPage.selectReturnPrice());
            }
        }
    }

    public void setFinalPrice() {
        flightsPage.selectFinalPrice();
    }

    public void clickOfPrice(List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight.isOneWay()) {
                flightsPage.clickDeparturePrice();
            } else {
                flightsPage.clickDeparturePrice();
                flightsPage.clickReturnPrice();
            }
        }
    }

    public double selectInfantsPrice() throws Exception { //TODO
        try {
            return flightsPage.selectInfantPrice();
        } catch (StaleElementReferenceException e) {
            logger.error(e);
        }

        throw new Exception(PRICE_NOT_INITIALIZED);
    }

    public void fillFlightsPage(Order order) {
        setRoutePrice(order.getFlights());
        try {
            flightsPage.submitPage();
        } catch (StaleElementReferenceException e) {
            logger.error(e);
        }
        closeAllChildWindows();
    }

    private void closeAllChildWindows() {
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
}