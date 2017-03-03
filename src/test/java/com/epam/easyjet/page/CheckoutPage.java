package com.epam.easyjet.page;

import com.epam.easyjet.bean.Price;
import com.epam.easyjet.util.PriceConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class CheckoutPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='price0']")
    private WebElement finalPrice;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public Price getFinalPrice() {
        Price price = PriceConverter.convertStringPrice(finalPrice.getText());
        return price;
    }
}