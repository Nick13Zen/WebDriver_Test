package com.epam.easyjet.page;

import com.epam.easyjet.bean.Luggage;
import com.epam.easyjet.bean.Price;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class FlightOptionsPage extends AbstractPage {

    @FindBy(xpath = ".//*[@id='addDefaultHoldBaggage']")
    private WebElement luggageButton;

    @FindBy(xpath = ".//*[@class=holdScalePrice]")
    private WebElement luggagePrice;

    @FindBy(xpath = ".//*[@class=holdScaleDial-value]")
    private WebElement luggageType;

    @FindBy(xpath = ".//*[@id='bagIndex_1']")
    private WebElement luggageHoldBag;

    public FlightOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {

    }

    public void addLuggageButton() {
        luggageButton.click();
    }


    public Luggage getLuggage() {
        Luggage luggage = new Luggage();
        Price price = new Price();

        String[] fullPrice = luggagePrice.getText().replaceAll("£", "").split(".");

        price.setFirstPart(Integer.parseInt(fullPrice[0]));
        price.setSecondPart(Integer.parseInt(fullPrice[1]));

        luggage.setPrice(price);
        luggage.setType(luggageType.getText());

        return luggage;
    }
}
