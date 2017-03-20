package com.epam.easyjet.step;

import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.bean.Luggage;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Seats;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightOptionsPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raman_Darasinets on 3/7/2017.
 */
public class FlightOptionsPageSteps {
    private final FlightOptionsPage flightOptionsPage;

    private static final int FIRST_FLIGHT = 0;

    public FlightOptionsPageSteps() {
        WebDriver driver = DriverSingleton.getDriver();
        flightOptionsPage = new FlightOptionsPage(driver);
        flightOptionsPage.openPage();
    }

    public void setLuggage(Order order) {
        flightOptionsPage.clickLuggageButton();
        Flight flight = order.getFlights().get(FIRST_FLIGHT);
        int generalCount = flight.getAdultCount() + flight.getChildCount() + flight.getInfantCount();
        order.setLuggage(flightOptionsPage.getDataLuggage(generalCount));
        for (Luggage luggage : order.getLuggage()) {
            order.addPrice(luggage.getPrice());
        }
    }

    public void pickSeats() {
        flightOptionsPage.clickPickSeatsButton();
    }

    public void setEconomSeat(Flight flight, Order order) {
        if (flight.getSeatsList() == null) {
            flight.setSeatsList(new ArrayList<>());
        }
        Seats seats = flightOptionsPage.chooseEconomSeat();
        flight.getSeatsList().add(seats);
        order.addPrice(seats.getPrice());
    }

    public void setStandardSeat(Flight flight, Order order) {
        if (flight.getSeatsList() == null) {
            flight.setSeatsList(new ArrayList<>());
        }
        Seats seats = flightOptionsPage.chooseStandardSeat();
        flight.getSeatsList().add(seats);
        order.addPrice(seats.getPrice());
    }

    public void setXLSeat(Flight flight, Order order) {
        if (flight.getSeatsList() == null) {
            flight.setSeatsList(new ArrayList<>());
        }
        Seats seats = flightOptionsPage.chooseXLSeat();
        flight.getSeatsList().add(seats);
        order.addPrice(seats.getPrice());
    }

    public void saveSeats() {
        flightOptionsPage.saveSeats();
    }

    public void setInsurance(Order order) {
        order.setInsurance(flightOptionsPage.getInsurance());
        order.addPrice(order.getInsurance().getPrice());
        flightOptionsPage.clickInsuranceButton();
    }

    public void fillFlightOptions(Order order) {
        pickSeats();
        List<Flight> flights = order.getFlights();
        for (Flight flight : flights) {
            int allSeatsCount = flight.getChildCount() + flight.getAdultCount();
            if (flight.isOneWay()) {
                setEconomSeats(flight, order, allSeatsCount);
            } else {
                setEconomSeats(flight, order, allSeatsCount);
                setEconomSeats(flight, order, allSeatsCount);
            }
        }
        setLuggage(order);
        setInsurance(order);
        flightOptionsPage.submitPage();
    }

    public void submitPageWithoutfilling() {
        flightOptionsPage.submitPage();
        flightOptionsPage.closeHelpWindow();
    }

    public boolean isItemAdded() {
        return flightOptionsPage.isItemAdded();
    }

    private void setEconomSeats(Flight flight, Order order, int allSeatsCount) {
        for (int i = 0; i < allSeatsCount; i++) {
            setEconomSeat(flight, order);
        }
        saveSeats();
    }
}
