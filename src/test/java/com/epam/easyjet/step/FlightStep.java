package com.epam.easyjet.step;

import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightsPage;
import com.epam.easyjet.page.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mark_Harbunou on 3/6/2017.
 */
public class FlightStep {

    private WebDriver driver;
    private FlightsPage flightsPage;

    public FlightStep() {
        driver = DriverSingleton.getDriver();
        flightsPage = new FlightsPage(driver);
        flightsPage.openPage();
    }

    private static final int TWO_WAYS_FLIGHTS_COUNT = 2;
    private static final int ONE_WAY_FLIGHTS_COUNT = 1;

    public void closeDriver() {
        driver.close();
    }

    public void setRoutePrice(List<Flight> flights) {
        if (flights.size() == TWO_WAYS_FLIGHTS_COUNT) {
            flights.get(0).setPrice(flightsPage.selectOutBoundPrice());
        } else if (flights.size() == ONE_WAY_FLIGHTS_COUNT) {
            flights.get(1).setPrice(flightsPage.selectReturnPrice());
        }
    }

    public void fillFlightsPage(Order order) {
        flightsPage.clickDeparturePrice();
        flightsPage.clickReturnPrice();
        setRoutePrice(order.getFlights());
        try {
            flightsPage.submitPage();
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage()); //TODO
        }

    }

}
