package com.epam.easyjet.step;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class MainPageSteps {

    private WebDriver driver;
    private MainPage mainPage;

    private final Logger logger = LogManager.getRootLogger();

    public MainPageSteps() {
        driver = DriverSingleton.getDriver();
        mainPage = new MainPage(driver);
        mainPage.openPage();
    }

    private static final String CALENDAR_FIELD = "div.drawer-section.routedatepicker";

    public void closeDriver() {
        driver.close();
    }

    public void setClientCount(Flight flight) {
        mainPage.setInfantCount(flight.getInfantCount());
        mainPage.setAdultCount(flight.getAdultCount());
        mainPage.setChildCount(flight.getChildCount());

    }

    public boolean isWarningDisplayed() {
        return mainPage.isWarningPresents();
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

    public void fillMainPage(Order order) {
        setRoutePlace(order.getFlights().get(0));
        setRouteDate(order.getFlights());
        setClientCount(order.getFlights().get(0));
        try {
            mainPage.submitPage();
        } catch (StaleElementReferenceException e) {
            logger.error(e); //TODO
        }
    }
}