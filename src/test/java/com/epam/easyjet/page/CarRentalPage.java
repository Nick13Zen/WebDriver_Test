package com.epam.easyjet.page;

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

    public CarRentalPage(WebDriver driver) {
        super(driver);
    }


    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void addCarClick() {
       addCar.click();
    }

    public void submit() {
        continueButton.click();
    }


}
