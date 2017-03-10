package com.epam.easyjet.test;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.bean.Price;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightOptionsPage;
import com.epam.easyjet.step.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

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
    Order order;
    ArrayList<Flight> flights;

    @BeforeMethod
    public void setUp() {
        mainPageSteps = new MainPageSteps();
        flightStep = new FlightStep();
        flightOptionsPageSteps = new FlightOptionsPageSteps();
        hotelStep = new HotelStep();
        carStep = new CarStep();
        checkoutStep = new CheckoutStep();
        Flight flight = new Flight();
        flight.setDeparturePlace("Madrid");
        flight.setDestinationPlace("Lisbon");
        flight.setDepartureDate("2017-03-19");
        flight.setReturnDate("2017-03-24");
        order = new Order();
        flights = new ArrayList<>();
        flights.add(flight);
        order.setFlights(flights);
    }

    @DataProvider(name = "invalid client count")
    public Object[][] getInvalidData() throws Exception {
        return new Object[][]{
                {41, 0, 0},
                {0, 41, 0},
                {20, 21, 0},
        };
    }

    @DataProvider(name = "valid client count")
    public Object[][] getValidData() throws Exception {
        return new Object[][]{
                {40, 0, 0},
                {20, 20, 0},
                {0, 40, 0}
        };
    }

    @Test(dataProvider = "invalid client count")
    public void testInvalidCountOfClients(int adultCount, int childCount, int infantCount) throws Exception {
        Flight flight = new Flight();
        flight.setAdultCount(adultCount);
        flight.setChildCount(childCount);
        flight.setInfantCount(infantCount);
        mainPageSteps.setClientCount(flight);
        Assert.assertTrue(mainPageSteps.isWarningMaxPassengersDisplayed());
    }

    @Test(dataProvider = "valid client count")
    public void testValidCountOfClients(int adultCount, int childCount, int infantCount) throws Exception {
        Flight flight = new Flight();
        flight.setAdultCount(adultCount);
        flight.setChildCount(childCount);
        flight.setInfantCount(infantCount);
        mainPageSteps.setClientCount(flight);
        Assert.assertTrue(!mainPageSteps.isWarningMaxPassengersDisplayed());
    }

    @Test
    public void testValidInfantCount() {
        Flight flight = new Flight();
        flight.setAdultCount(2);
        flight.setInfantCount(2);
        mainPageSteps.setClientCount(flight);
        Assert.assertTrue(!mainPageSteps.isWarningInfantDisplayed());
    }

    @Test
    public void testInvalidInfantCount() {
        Flight flight = new Flight();
        flight.setAdultCount(2);
        flight.setInfantCount(4);
        mainPageSteps.setClientCount(flight);
        Assert.assertTrue(mainPageSteps.isWarningInfantDisplayed());
    }

    @Test
    public void testMainPage() throws Exception {
        mainPageSteps.fillMainPage(order);
        flightStep.fillFlightsPage(order);
        flightOptionsPageSteps.fillFlightOptions(order.getFlights());
        hotelStep.submitHotelPage();
        carStep.submitCarPage();
        Price price = checkoutStep.fillCheckoutPage();
        System.out.println(price.getFirstPart() + "." + price.getSecondPart());
    }

    @Test
    public void testInfantPrice() {
        order.getFlights().get(0).setAdultCount(1);
        order.getFlights().get(0).setChildCount(1);
        order.getFlights().get(0).setInfantCount(1);
        mainPageSteps.fillMainPage(order);
        flightStep.clickOfPrice(flights);
        Price price = flightStep.selectInfantsPrice();
        Assert.assertEquals(price.getFirstPart(), 28);
        Assert.assertEquals(price.getSecondPart(), 0);
    }

    @Test
    public void testIsHotelAdded() {
        order.getFlights().get(0).setAdultCount(1);
        mainPageSteps.fillMainPage(order);
        flightStep.clickOfPrice(flights);
        flightStep.fillFlightsPage(order);
        flightOptionsPageSteps.fillFlightOptions(flights);
        hotelStep.addHotel(order);
        boolean hotel = hotelStep.isHotelAdded();
        Assert.assertEquals(hotel,true);
    }

    @AfterMethod
    public void driverRelease() {

        DriverSingleton.closeDriver();
    }
}
