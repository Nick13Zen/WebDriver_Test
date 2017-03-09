package com.epam.easyjet.step;

import com.epam.easyjet.bean.Car;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.CarRentalPage;
import org.apache.xpath.operations.Or;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

/**
 * Created by Maria on 06.03.2017.
 */
public class CarStep {

    private WebDriver driver;
    private CarRentalPage carRentalPage;

    public CarStep() {
        driver = DriverSingleton.getDriver();
        carRentalPage = new CarRentalPage(driver);
        carRentalPage.openPage();
    }

    public void closeDriver() {
        driver.close();
    }

    private Car setCarInfo() {
        return carRentalPage.selectCarParameters();
    }

    public void addCar(Order order) {
        order.setCar(setCarInfo());
        carRentalPage.addCarClick();
    }

    public void submitCarPage() {
        carRentalPage.submitPage();
    }
}