package com.epam.easyjet.step;

import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Price;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightOptionsPage;
import com.epam.easyjet.page.FlightsPage;
import com.epam.easyjet.page.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
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

    public void closeDriver() {
        driver.close();
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

    public Price selectInfantsPrice() {
        try {
            return flightsPage.selectInfantPrice();
        } catch (StaleElementReferenceException e) {
            System.out.println(); //TODO
        }
        return null;
    }

    public void fillFlightsPage(Order order) {
        setRoutePrice(order.getFlights());
        try {
            flightsPage.submitPage();
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage()); //TODO
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