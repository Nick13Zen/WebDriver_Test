package com.epam.easyjet.step;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightsPage;
import com.epam.easyjet.page.MainPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Steps {

    private WebDriver driver;
    private MainPage mainPage;
    private FlightsPage flightsPage;

    public Steps() {
        driver = DriverSingleton.getDriver();
        mainPage = new MainPage(driver);
        flightsPage = new FlightsPage(driver);
    }

    private static final int TWO_WAYS_FLIGHTS_COUNT = 2;
    private static final int ONE_WAY_FLIGHTS_COUNT = 1;

    public void closeDriver() {
        driver.close();
    }

    public void setClientCount(Flight flight) {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
            mainPage.openPage();
        }
        mainPage.setAdultCount(flight.getAdultCount());
        mainPage.setChildCount(flight.getChildCount());
        mainPage.setInfantCount(flight.getInfantCount());
    }

    public boolean isWarningDisplayed() {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
        }
        return mainPage.isWarningPresents();
    }

    public void setRoutePlace(Flight flight) {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
            mainPage.openPage();
        }
        mainPage.typeDeparturePlace(flight.getDeparturePlace());
        mainPage.typeDestinationPlace(flight.getDestinationPlace());
    }

    public void setRouteDate(List<Flight> flights) throws Exception { //TODO
        if (null == mainPage) {
            mainPage = new MainPage(driver);
            mainPage.openPage();
        }
        if (flights.size() == TWO_WAYS_FLIGHTS_COUNT) {
            mainPage.chooseDepartureDate(flights.get(0).getDestinationDate());
            Thread.sleep(1000);//TODO
            mainPage.chooseDestinationDate(flights.get(1).getDestinationDate());
        } else if (flights.size() == ONE_WAY_FLIGHTS_COUNT) {
            mainPage.chooseDepartureDate(flights.get(0).getDestinationDate());
        }

    }

    public void fillMainPage(Order order) throws Exception {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
            mainPage.openPage();
        }
        setRoutePlace(order.getFlights().get(0));
        setRouteDate(order.getFlights());
        setClientCount(order.getFlights().get(0));
        mainPage.submitPage();
    }

    public void fillFlightsPage(Order order) {
        if (null == flightsPage) {
            flightsPage = new FlightsPage(driver);
            flightsPage.openPage();
        }
        List<Flight> flights = order.getFlights();
        if (flights.size() == TWO_WAYS_FLIGHTS_COUNT) {
            chooseDeparture(flights.get(0));
            chooseReturn(flights.get(1));
        } else if (flights.size() == ONE_WAY_FLIGHTS_COUNT) {
            chooseDeparture(flights.get(0));
        }
    }

    private void chooseDeparture(Flight flight) {
        if (null == flightsPage) {
            flightsPage = new FlightsPage(driver);
            flightsPage.openPage();
        }
        flightsPage.clickDeparturePrice();
        flight.setPrice(flightsPage.selectOutBoundPrice());
    }

    private void chooseReturn(Flight flight) {
        if (null == flightsPage) {
            flightsPage = new FlightsPage(driver);
            flightsPage.openPage();
        }
        flightsPage.clickReturnPrice();
        flight.setPrice(flightsPage.selectReturnPrice());
    }
}
