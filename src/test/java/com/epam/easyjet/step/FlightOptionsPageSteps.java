package com.epam.easyjet.step;

import com.epam.easyjet.bean.Flight;
import com.epam.easyjet.bean.Luggage;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.bean.Seats;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.FlightOptionsPage;
import org.apache.xpath.operations.Or;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raman_Darasinets on 3/7/2017.
 */
public class FlightOptionsPageSteps {
    private FlightOptionsPage flightOptionsPage;

    private static final int FIRST_FLIGHT = 0;

    public FlightOptionsPageSteps() {
        WebDriver driver = DriverSingleton.getDriver();
        flightOptionsPage = new FlightOptionsPage(driver);
        flightOptionsPage.openPage();
    }

    public void setLuggage(Order order) {
        flightOptionsPage.addLuggageButton();
        Flight flight = order.getFlights().get(FIRST_FLIGHT);
        int generalCount = flight.getAdultCount() + flight.getChildCount() + flight.getInfantCount();
        order.setLuggage(flightOptionsPage.getDataLuggage(generalCount));
        for (Luggage luggage : order.getLuggage()) {
            order.addPrice(luggage.getPrice());
        }
    }

    public void setSeats() {
        flightOptionsPage.pickSeat();
    }

    public void setEconomSeat(Flight flight, Order order) {
        if (flight.getSeatsList() == null) {
            flight.setSeatsList(new ArrayList<Seats>());
        }
        Seats seats = flightOptionsPage.chooseEconomSeat();
        flight.getSeatsList().add(seats);
        order.addPrice(seats.getPrice());
    }

    public void setStandardSeat(Flight flight) {
        if (flight.getSeatsList() == null) {
            flight.setSeatsList(new ArrayList<Seats>());
        }
        flight.getSeatsList().add(flightOptionsPage.chooseStandardSeat());
    }

    public void setXLSeat(Flight flight) {
        if (flight.getSeatsList() == null) {
            flight.setSeatsList(new ArrayList<Seats>());
        }
        flight.getSeatsList().add(flightOptionsPage.chooseXLSeat());
    }

    public void setSaveSeats() {
        flightOptionsPage.saveSeats();
    }

    public void takeInsurance(Order order) {

        order.setInsurance(flightOptionsPage.takeInsurance());
        order.addPrice(order.getInsurance().getPrice());
        flightOptionsPage.pickInsurance();
    }

    public void fillFlightOptions(Order order) {
        setSeats();
        List<Flight> flights = order.getFlights();
        for (Flight flight : flights) {
            int allNumberSeats = flight.getChildCount() + flight.getAdultCount();
            if (flight.isOneWay()) {
                setEconomSeats(flight, order, allNumberSeats);
            } else {
                setEconomSeats(flight, order, allNumberSeats);
                setEconomSeats(flight, order, allNumberSeats);
            }
        }
        setLuggage(order);
        takeInsurance(order);
        flightOptionsPage.submitPage();
    }

    private void setEconomSeats(Flight flight, Order order, int allNumberSeats) {
        for (int i = 0; i < allNumberSeats; i++) {
            setEconomSeat(flight, order);
        }
        setSaveSeats();
    }

    public boolean isItemAdded() {
        return flightOptionsPage.isItemAdded();
    }
}
