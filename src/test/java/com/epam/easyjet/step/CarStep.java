package com.epam.easyjet.step;

import com.epam.easyjet.bean.Car;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.CarRentalPage;

/**
 * Created by Maria on 06.03.2017.
 */
public class CarStep {
    private CarRentalPage carRentalPage;

    public CarStep() {
        carRentalPage = new CarRentalPage(DriverSingleton.getDriver());
        carRentalPage.openPage();
    }

    private Car setCarInfo() {
        return carRentalPage.selectCarParameters();
    }

    public void addCar(Order order) {
        Car car = setCarInfo();
        order.setCar(car);
        order.addPrice(car.getPrice());
        carRentalPage.addCarClick();
    }

    public void submitCarPage() {
        carRentalPage.submitPage();
    }

    public boolean isCarAdded() {
        return carRentalPage.isCarAdded();
    }
}