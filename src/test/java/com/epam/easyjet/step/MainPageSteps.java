package com.epam.easyjet.step;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class MainPageSteps {
    private final Logger logger = LogManager.getRootLogger();

    private WebDriver driver;
    private MainPage mainPage;

    public MainPageSteps() {
        driver = DriverSingleton.getDriver();
        mainPage = new MainPage(driver);
        mainPage.openPage();
    }

    public void setRoutePlace(Flight flight) {
        mainPage.typeDeparturePlace(flight.getDeparturePlace());
        mainPage.typeDestinationPlace(flight.getDestinationPlace());
    }

    public void setRouteDate(List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight.isOneWay()) {
                mainPage.chooseDepartureDate(flight.getDepartureDate());
            } else {
                mainPage.chooseDepartureDate(flight.getDepartureDate());
                mainPage.chooseDestinationDate(flight.getReturnDate());
            }
        }
    }

    public void setClientCount(Flight flight) {
        mainPage.setAdultCount(0);

        for (int i = 0; i < flight.getAdultCount(); i++) {
            mainPage.addAdult();
        }

        for (int i = 0; i < flight.getInfantCount(); i++) {
            mainPage.addInfant();
        }

        for (int i = 0; i < flight.getChildCount(); i++) {
            mainPage.addChild();
        }
    }

    public boolean isWarningMaxPassengersDisplayed() {
        return mainPage.isWarningMaxPassengersPresents();
    }

    public boolean isWarningInfantDisplayed() {
        return mainPage.isWarningInfantsPresents();
    }

    public void fillMainPage(Order order) {
        setRoutePlace(order.getFlights().get(0));
        setRouteDate(order.getFlights());
        setClientCount(order.getFlights().get(0));
        try {
            mainPage.submitPage();
        } catch (StaleElementReferenceException e) {
            logger.error(e);
        }
    }
}