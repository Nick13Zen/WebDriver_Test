package com.epam.easyjet.step;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Race;
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

    public void initDriver() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        driver.close();
    }

    public void setClientCount(int adultCount, int childCount, int infantCount) {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
            mainPage.openPage();
        }
        mainPage.setAdultCount(adultCount);
        mainPage.setChildCount(childCount);
        mainPage.setInfantCount(infantCount);
    }

    public boolean isWarningDisplayed() {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
        }
        return mainPage.isWarningPresents();
    }

    public void setRoutePlace(String departure, String destination) throws Exception {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
            mainPage.openPage();
        }
        mainPage.typeDeparturePlace(departure);
        mainPage.typeDestinationPlace(destination);
    }

    public void setRouteDate(String departureDate, String returnDate) throws Exception {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
            mainPage.openPage();
        }
        mainPage.chooseDepartureDate(departureDate);
        mainPage.pickDate(departureDate);
        Thread.sleep(1000);
        mainPage.chooseDestinationDate(returnDate);
        mainPage.pickDate(returnDate);
    }

    public void fillMainPage(Order order) throws Exception {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
            mainPage.openPage();
        }
        List<Race> races = order.getRaces();
        if (races.size() == 2) {
            setRoutePlace(races.get(0).getDeparturePlace(), races.get(0).getDestinationPlace());
            setRouteDate(races.get(0).getDestinationDate(), races.get(1).getDestinationDate());
            setClientCount(races.get(0).getAdultCount(), races.get(0).getChildCount(), races.get(0).getInfantCount());
        }
        mainPage.submit();
    }

    public void fillFlightsPage(Order order) {
        if (null == flightsPage) {
            flightsPage = new FlightsPage(driver);
            flightsPage.openPage();
        }
        List<Race> races = order.getRaces();
        if (races.size() == 2) {
            chooseDeparture(races.get(0));
            chooseReturn(races.get(1));
        } else if (races.size() == 1) {
            chooseDeparture(races.get(0));
        }
    }

    private void chooseDeparture(Race race) {
        if (null == flightsPage) {
            flightsPage = new FlightsPage(driver);
            flightsPage.openPage();
        }
        flightsPage.clickDeparturePrice();
        race.setPrice(flightsPage.selectOutBoundPrice());
    }

    private void chooseReturn(Race race) {
        if (null == flightsPage) {
            flightsPage = new FlightsPage(driver);
            flightsPage.openPage();
        }
        flightsPage.clickReturnPrice();
        race.setPrice(flightsPage.selectReturnPrice());
    }
}
