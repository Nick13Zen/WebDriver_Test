package com.epam.easyjet.test;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.bean.Price;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightOptionsPage;
import com.epam.easyjet.step.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class AutomationTest {

    MainPageSteps mainPageSteps;
    FlightStep flightStep;
    FlightOptionsPageSteps flightOptionsPageSteps;
    HotelStep hotelStep;
    CarStep carStep;
    CheckoutStep checkoutStep;

    @BeforeTest
    public void setUp() {
        mainPageSteps = new MainPageSteps();
        flightStep = new FlightStep();
        flightOptionsPageSteps = new FlightOptionsPageSteps();
        hotelStep = new HotelStep();
        carStep = new CarStep();
        checkoutStep = new CheckoutStep();
    }

    @DataProvider(name = "invalid client count")
    public Object[][] getInvalidData() throws Exception {
        return new Object[][] {
                {41, 0 , 0},
                {0, 41, 0},
                {20, 21, 0},
                {1, 1, 2},
                {8, 8, 8},
                {0, 0, 0}
        };
    }

    @DataProvider(name = "valid client count")
    public Object[][] getValidData() throws Exception {
        return new Object[][] {
                {40, 0 , 0},
                {20, 20, 0},
                {1, 1, 1},
                {7, 0, 7}
        };
    }

//    @Test(dataProvider = "invalid client count")
//    public void testInvalidCountOfClients(int adultCount, int childCount, int infantCount) throws Exception {
//        Flight flight1 = new Flight();
//        flight1.setDeparturePlace("Madrid");
//        flight1.setDestinationPlace("Lisbon");
//        flight1.setDestinationDate("2017-03-19");
//        flight1.setAdultCount(1);
//        flight1.setChildCount(1);
//        flight1.setInfantCount(1);
//        Flight flight2 = new Flight();
//        flight2.setDeparturePlace("Lisbon");
//        flight2.setDestinationPlace("Madrid");
//        flight2.setDestinationDate("2017-03-24");
//        flight2.setAdultCount(1);
//        flight2.setChildCount(1);
//        flight2.setInfantCount(1);
//        Order order = new Order();
//        ArrayList<Flight> flights = new ArrayList<Flight>();
//        flights.add(flight1);
//        flights.add(flight2);
//        order.setFlights(flights);
//        mainPageSteps.setRoutePlace(order.getFlights().get(0));
//        mainPageSteps.setRouteDate(order.getFlights());
//        mainPageSteps.setClientCount(flight1);
//        Assert.assertTrue(mainPageSteps.isWarningDisplayed());
//    }
//
//    @Test(dataProvider = "valid client count")
//    public void testValidCountOfClients(int adultCount, int childCount, int infantCount) throws Exception {
//        Flight flight1 = new Flight();
//        flight1.setDeparturePlace("Madrid");
//        flight1.setDestinationPlace("Lisbon");
//        flight1.setDestinationDate("2017-03-19");
//        flight1.setAdultCount(1);
//        flight1.setChildCount(1);
//        flight1.setInfantCount(1);
//        Flight flight2 = new Flight();
//        flight2.setDeparturePlace("Lisbon");
//        flight2.setDestinationPlace("Madrid");
//        flight2.setDestinationDate("2017-03-24");
//        flight2.setAdultCount(1);
//        flight2.setChildCount(1);
//        flight2.setInfantCount(1);
//        Order order = new Order();
//        ArrayList<Flight> flights = new ArrayList<Flight>();
//        flights.add(flight1);
//        flights.add(flight2);
//        order.setFlights(flights);
//        mainPageSteps.setRoutePlace(order.getFlights().get(0));
//        mainPageSteps.setRouteDate(order.getFlights());
//        mainPageSteps.setClientCount(flight1);
//        Assert.assertTrue(!mainPageSteps.isWarningDisplayed());
//    }


    @Test
    public void testMainPage() throws Exception {
        Flight flight1 = new Flight();
        flight1.setDeparturePlace("Madrid");
        flight1.setDestinationPlace("Lisbon");
        flight1.setDepartureDate("2017-03-19");
        flight1.setReturnDate("2017-03-24");
        flight1.setAdultCount(1);
        flight1.setChildCount(1);
        flight1.setInfantCount(1);
        Order order = new Order();
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        order.setFlights(flights);
        mainPageSteps.fillMainPage(order);
        flightStep.fillFlightsPage(order);
        flightOptionsPageSteps.fillFlightOptions(order.getFlights());
        hotelStep.submitHotelPage();
        carStep.submitCarPage();
        Price price = checkoutStep.fillCheckoutPage();
        System.out.println(price.getFirstPart() + "." + price.getSecondPart());
    }

    @AfterClass
    public void driverRelease() {
        DriverSingleton.closeDriver();
    }
}
