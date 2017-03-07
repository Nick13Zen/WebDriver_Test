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

    private WebDriver driver;
    private FlightOptionsPage flightOptionsPage;

    public FlightOptionsPageSteps() {
        driver = DriverSingleton.getDriver();
        flightOptionsPage = new FlightOptionsPage(driver);
        flightOptionsPage.openPage();
    }

    public void closeDriver() {
        driver.close();
    }

    public void setLuggage(Order order ) {
        if (order.getLuggage() == null) {
            order.setLuggage(new ArrayList<Luggage>());
        }
        flightOptionsPage.addLuggageButton();
        order.getLuggage().add(flightOptionsPage.getLuggage());
    }

    public void setSeats() {
        flightOptionsPage.getSeat();
    }

    public void setEconomSeat(Flight flight) {
        if (flight.getSeatsList() == null) {
            flight.setSeatsList(new ArrayList<Seats>());
        }
        flight.getSeatsList().add(flightOptionsPage.chooseEconomSeat());
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

    public void fillFlightOptions(List<Flight> flights) {
        setSeats();
        for (Flight flight : flights) {
            int allNumberSeats = flight.getChildCount() + flight.getAdultCount();
            for (int i = 0; i < allNumberSeats; i++) {
                setEconomSeat(flight);
            }
            setSaveSeats();
        }
        flightOptionsPage.submitPage();
        flightOptionsPage.goNext();
    }
}
