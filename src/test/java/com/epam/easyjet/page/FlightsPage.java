package com.epam.easyjet.page;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class FlightsPage extends AbstractPage {

    public FlightsPage(WebDriver driver) {
        super(driver);

    }

    //div[@class='OutboundDaySlider']/div[@class='day selected']/ul[@class='middleRow']
    //@FindBy(xpath = "//div[@class='OutboundDaySlider']//div[@class='day selected']")
    @FindBy(xpath = "//div[@class='OutboundDaySlider']//div[@class='day selected']")
    private WebElement selectOutBoundDate;

    //div[@class='ReturnDaySlider']/div[@class='day selected']/ul[@class='middleRow']
    @FindBy(xpath = "//div[@class='ReturnDaySlider']//div[@class='day selected']")
    private WebElement selectReturnDate;

    //div[@class='OutboundDaySlider']//ul[@class='middleRow']//span[contains(@class,'priceSmaller')]
    @FindBy(xpath = "//div[@class='OutboundDaySlider']//@charge-debit-full")
    //div[@class='OutboundDaySlider']//@charge-debit-full
    private WebElement selectOutBoundPrice;

    //div[@class='ReturnDaySlider']//ul[@class='middleRow']//span[contains(@class,'priceSmaller')]
    @FindBy(xpath = "//div[@class='ReturnDaySlider']//@charge-debit-full")
    private WebElement selectReturnPrice;

    @FindBy(xpath = "//div[contains(@class,'TotalCost')]//span[@id='price5']")
    private WebElement finalPrice;

    @FindBy(xpath = "//button[@id='btnContinue']")
    private WebElement continueButton;


    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void clickDeparturePrice() {
        selectOutBoundDate.click();
    }

    public void clickReturnPrice() {
        selectReturnDate.click();
    }

    public void submit() {
        continueButton.click();
    }
}
