package com.epam.easyjet.page;

import com.epam.easyjet.bean.Car;
import com.epam.easyjet.util.PriceConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class CarRentalPage extends AbstractPage {
    private static final String ADDED_CAR_FORM = "div.detail.recentlySelected.contain";

    @FindBy(id = "btnAddCar1")
    private WebElement addCar;

    @FindBy(id = "btnContinue")
    private WebElement btnContinue;

    @FindBy(xpath = "//div[1][@class='CarContainer']//span[@class='targetPrice']")
    private WebElement priceOfCar;

    @FindBy(xpath = "//div[1][@class='CarContainer']//*[@class='CarDetails']//h2")
    private WebElement carName;

    public CarRentalPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void addCarClick() {
        addCar.click();
    }

    public void submitPage() {
        driverWait.until(ExpectedConditions.visibilityOf(btnContinue));
        btnContinue.click();
    }

    public Car selectCarParameters() {
        Car car = new Car();
        car.setName(carName.getText());
        car.setPrice(PriceConverter.convertStringPrice(priceOfCar.getText()));
        return car;
    }

    public boolean isCarAdded() {
        return driver.findElements(By.cssSelector(ADDED_CAR_FORM)).size() > 0;
    }
}