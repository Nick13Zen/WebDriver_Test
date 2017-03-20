package com.epam.easyjet.step;

import com.epam.easyjet.bean.Car;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.CarRentalPage;

/**
 * Created by Maria on 06.03.2017.
 */
public class CarStep {
    private final CarRentalPage carRentalPage;

    public CarStep() {
        carRentalPage = new CarRentalPage(DriverSingleton.getDriver());
        carRentalPage.openPage();
    }

    public void addCar(Order order) {
        Car car = getCarInfo();
        order.setCar(car);
        order.addPrice(car.getPrice());
        carRentalPage.addCarClick();
    }

    public boolean isCarAdded() {
        return carRentalPage.isCarAdded();
    }

    public void submitCarPage() {
        carRentalPage.submitPage();
    }

    private Car getCarInfo() {
        return carRentalPage.selectCarParameters();
    }
}