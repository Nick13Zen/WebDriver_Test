package com.epam.easyjet.page;

import com.epam.easyjet.bean.Car;
import com.epam.easyjet.util.PriceConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class CarRentalPage extends AbstractPage {

    @FindBy(id = "btnAddCar1")
    private WebElement addCar;


    @FindBy(id = "btnContinue")
    private WebElement continueButton;

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
        continueButton.click();
    }

    public Car selectCarParameters() {
        Car car = new Car();
        car.setName(carName.getText());
        car.setPrice(PriceConverter.convertStringPrice(priceOfCar.getText()));
        return car;
    }

}