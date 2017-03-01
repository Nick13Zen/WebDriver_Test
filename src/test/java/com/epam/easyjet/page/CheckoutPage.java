package com.epam.easyjet.page;

import com.epam.easyjet.bean.Price;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class CheckoutPage extends AbstractPage {

    @FindBy(xpath = ".//*[@id='price0']/text()")
    private WebElement finalPrice;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {

    }

    public Price getFinalPrice() {
        Price price = new Price();

        String[] fullPrice = finalPrice.getText().replaceAll("£", "").split(".");

        price.setFirstPart(Integer.parseInt(fullPrice[0]));
        price.setSecondPart(Integer.parseInt(fullPrice[1]));

        return price;
    }
}
