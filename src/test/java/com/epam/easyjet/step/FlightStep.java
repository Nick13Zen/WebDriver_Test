package com.epam.easyjet.step;

import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightsPage;
import com.epam.easyjet.step.exception.StepException;
import com.epam.easyjet.util.WindowsManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;


public class FlightStep {
    private static final Logger logger = LogManager.getRootLogger();

    private static final String PRICE_NOT_INITIALIZED = "Price not initialized";

    private final FlightsPage flightsPage;

    public FlightStep() {
        flightsPage = new FlightsPage(DriverSingleton.getDriver());
        flightsPage.openPage();
    }

    public void setRoutePrice(List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight.isOneWay()) {
                flight.setDeparturePrice(flightsPage.getOutBoundPrice());
            } else {
                flight.setDeparturePrice(flightsPage.getOutBoundPrice());
                flight.setReturnPrice(flightsPage.getReturnPrice());
            }
        }
    }

    public void setFinalPrice(Order order) {
        order.addPrice(flightsPage.getFinalPrice());
    }

    public void setFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight.isOneWay()) {
                flightsPage.clickDepartureFlight();
            } else {
                flightsPage.clickDepartureFlight();
                flightsPage.clickReturnFlight();
            }
        }
    }

    public double getInfantsPrice() {
        double price;
        try {
            price = flightsPage.getInfantPrice();
        } catch (StaleElementReferenceException e) {
            logger.error(e);
            throw new StepException(PRICE_NOT_INITIALIZED, e);
        }

        return price;
    }

    public void fillFlightsPage(Order order) {
        setFlights(order.getFlights());
        setRoutePrice(order.getFlights());
        setFinalPrice(order);
        try {
            flightsPage.submitPage();
        } catch (StaleElementReferenceException e) {
            logger.error(e);
        }
        WindowsManager.switchToMainWindow();
    }
}