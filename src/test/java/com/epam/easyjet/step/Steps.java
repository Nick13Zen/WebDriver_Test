package com.epam.easyjet.step;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.MainPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Steps {

    private WebDriver driver;
    private MainPage mainPage;

    public Steps() {
        driver = DriverSingleton.getDriver();
        mainPage = new MainPage(driver);
        mainPage.openPage();
    }

    private static final int TWO_WAYS_FLIGHTS_COUNT = 2;
    private static final int ONE_WAY_FLIGHTS_COUNT = 1;

    public void closeDriver() {
        driver.close();
    }

    public void setClientCount(Flight flight) {
        mainPage.setAdultCount(flight.getAdultCount());
        mainPage.setChildCount(flight.getChildCount());
        mainPage.setInfantCount(flight.getInfantCount());
    }

    public boolean isWarningDisplayed() {
        return mainPage.isWarningPresents();
    }

    public void setRoutePlace(Flight flight) {
        mainPage.typeDeparturePlace(flight.getDeparturePlace());
        mainPage.typeDestinationPlace(flight.getDestinationPlace());
    }

    public void setRouteDate(List<Flight> flights) throws Exception { //TODO
        if (flights.size() == TWO_WAYS_FLIGHTS_COUNT) {
            mainPage.chooseDepartureDate(flights.get(0).getDestinationDate());
            Thread.sleep(1000);//TODO
            mainPage.chooseDestinationDate(flights.get(1).getDestinationDate());
        } else if (flights.size() == ONE_WAY_FLIGHTS_COUNT) {
            mainPage.chooseDepartureDate(flights.get(0).getDestinationDate());
        }
    }

    public void fillMainPage(Order order) throws Exception {
        setRoutePlace(order.getFlights().get(0));
        setRouteDate(order.getFlights());
        setClientCount(order.getFlights().get(0));
        mainPage.submitPage();
    }
}
