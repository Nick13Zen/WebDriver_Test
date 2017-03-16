package com.epam.easyjet.step;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class MainPageSteps {
    private final Logger logger = LogManager.getRootLogger();

    private MainPage mainPage;

    public MainPageSteps() {
        mainPage = new MainPage(DriverSingleton.getDriver());
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
//        mainPage.setAdultCount(0);

        addAdults(flight.getAdultCount());
        addChildren(flight.getChildCount()-1);
        addInfants(flight.getInfantCount()-1);

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

    public void addAdults(int count) {
        for (int i = 0; i < count-1; i++) {
            mainPage.addAdult();
        }
    }

    public void addInfants(int count) {
        for (int i = 0; i < count; i++) {
            mainPage.addInfant();
        }
    }

    public void addChildren(int count) {
        for (int i = 0; i < count; i++) {
            mainPage.addChild();
        }
    }

    public void removeAdult() {
        mainPage.removeAdult();
    }
}