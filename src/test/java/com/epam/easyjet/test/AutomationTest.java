package com.epam.easyjet.test;

import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.step.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class AutomationTest {

    private static final int FIRST_FLIGHT = 0;

    private static final int VALID_INFANT_ADULT_COUNT = 3;
    private static final int INVALID_INFANT_ADULT_COUNT = 2;
    private static final int DEFAULT_COUNT = 1;

    private static final double INFANT_PRICE = 28.0;

    private MainPageSteps mainPageSteps;
    private FlightStep flightStep;
    private FlightOptionsPageSteps flightOptionsPageSteps;
    private HotelStep hotelStep;
    private CarStep carStep;
    private CheckoutStep checkoutStep;
    private Order order;
    private ArrayList<Flight> flights;

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
        flight.setDepartureDate("2017-04-10");
        flight.setReturnDate("2017-04-20");
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
        mainPageSteps.typeChildCount(childCount);
        mainPageSteps.typeAdultCount(adultCount);
        mainPageSteps.typeInfantCount(infantCount);
        mainPageSteps.clickEnter();
        Assert.assertTrue(mainPageSteps.isWarningMaxPassengersDisplayed());
    }

    @Test(dataProvider = "valid client count")
    public void testValidCountOfClients(int adultCount, int childCount, int infantCount) throws Exception {
        mainPageSteps.typeAdultCount(adultCount);
        mainPageSteps.typeChildCount(childCount);
        mainPageSteps.typeInfantCount(infantCount);
        mainPageSteps.clickEnter();
        Assert.assertTrue(!mainPageSteps.isWarningMaxPassengersDisplayed());
    }

    @Test
    public void testValidInfantCount() {
        mainPageSteps.typeAdultCount(VALID_INFANT_ADULT_COUNT);
        mainPageSteps.typeInfantCount(VALID_INFANT_ADULT_COUNT);
        mainPageSteps.clickEnter();
        Assert.assertTrue(!mainPageSteps.isWarningInfantDisplayed());
    }

    @Test
    public void testInvalidInfantCount() {
        mainPageSteps.typeInfantCount(INVALID_INFANT_ADULT_COUNT);
        mainPageSteps.clickEnter();
        Assert.assertTrue(mainPageSteps.isWarningInfantDisplayed());
    }

    @Test
    public void testInfantPrice() {
        order.getFlights().get(FIRST_FLIGHT).setAdultCount(DEFAULT_COUNT);
        order.getFlights().get(FIRST_FLIGHT).setChildCount(DEFAULT_COUNT);
        order.getFlights().get(FIRST_FLIGHT).setInfantCount(DEFAULT_COUNT);

        mainPageSteps.fillMainPage(order);
        flightStep.setFlights(flights);
        Assert.assertEquals(flightStep.getInfantsPrice(), INFANT_PRICE);
    }

    @Test
    public void testIsHotelAdded() {
        order.getFlights().get(FIRST_FLIGHT).setAdultCount(DEFAULT_COUNT);
        mainPageSteps.fillMainPage(order);
        flightStep.setFlights(flights);
        flightStep.fillFlightsPage(order);
        flightOptionsPageSteps.submitPageWithoutfilling();
        hotelStep.setHotel(order);
        Assert.assertTrue(hotelStep.isHotelAdded());
    }

    @Test
    public void testIsCarAdded() {
        order.getFlights().get(FIRST_FLIGHT).setAdultCount(DEFAULT_COUNT);
        mainPageSteps.fillMainPage(order);
        flightStep.fillFlightsPage(order);
        flightOptionsPageSteps.submitPageWithoutfilling();
        hotelStep.submitHotelPage();
        carStep.addCar(order);
        Assert.assertTrue(carStep.isCarAdded());
    }

    @Test
    public void testIsLuggageAdded() {
        order.getFlights().get(FIRST_FLIGHT).setAdultCount(DEFAULT_COUNT);
        mainPageSteps.fillMainPage(order);
        flightStep.fillFlightsPage(order);
        flightOptionsPageSteps.setLuggage(order);
        Assert.assertTrue(flightOptionsPageSteps.isItemAdded());
    }

    @Test
    public void testFinalPrice() {
        order.getFlights().get(FIRST_FLIGHT).setAdultCount(DEFAULT_COUNT);
        order.getFlights().get(FIRST_FLIGHT).setChildCount(DEFAULT_COUNT);
        order.getFlights().get(FIRST_FLIGHT).setInfantCount(DEFAULT_COUNT);
        mainPageSteps.fillMainPage(order);
        flightStep.fillFlightsPage(order);
        flightOptionsPageSteps.fillFlightOptions(order);
        hotelStep.submitHotelPage();
        carStep.submitCarPage();
        Assert.assertEquals(checkoutStep.getFinalPrice(), order.getPrice());
    }

    @AfterMethod
    public void driverRelease() {
        DriverSingleton.closeDriver();
    }
}
