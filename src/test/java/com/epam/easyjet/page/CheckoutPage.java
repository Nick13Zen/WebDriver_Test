package com.epam.easyjet.page;

import com.epam.easyjet.util.PriceConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class CheckoutPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='formatedPrice cellPrice']/strong")
    private WebElement finalPrice;

    @FindBy(xpath = ".//*[@id='new-signin']")
    private WebElement pageLoaded;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public double getFinalPrice() {
        driverWait.until(ExpectedConditions.visibilityOf(pageLoaded));
        return PriceConverter.convertStringPrice(finalPrice.getText());
    }
}