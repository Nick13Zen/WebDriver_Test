package com.epam.easyjet.page;

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
public class FlightsPage extends AbstractPage {

    private static final String SPAN_CLASS_AMOUNT = "//span[@class='amount ']";

    @FindBy(xpath = "//div[@class='OutboundDaySlider']//div[@class='day selected']//a[1]")
    private WebElement selectOutBoundDate;

    @FindBy(xpath = "//div[@class='ReturnDaySlider']//div[@class='day selected']//a[1]")
    private WebElement selectReturnDate;

    @FindBy(xpath = "//div[@class='OutboundDaySlider']//div[@class='day selected']//a[1]/span[contains(@class,'targetPrice')]")
    private WebElement selectOutBoundPrice;

    @FindBy(xpath = "//div[@class='ReturnDaySlider']//div[@class='day selected']//a[1]/span[contains(@class,'targetPrice')]")
    private WebElement selectReturnPrice;

    @FindBy(css = "div.detail.done.contain>div.amount.subtotal")
    private WebElement finalPrice;

    @FindBy(xpath = "//button[@id='btnContinue']")
    private WebElement continueButton;

    @FindBy(xpath = "//span[@class='amount ']")
    private WebElement infantsPrice;

    private static final String DISABLER_ATTRIBUTE_FOR_CONTINUE_BUTTON = "aria-disabled";
    private static final String DISABLER_VALUE_FOR_CONTINUE_BUTTON = "false";

    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public double getInfantPrice() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SPAN_CLASS_AMOUNT)));
        return PriceConverter.converterStringWithX(infantsPrice.getText());
    }

    public double getFinalPrice() {
        driverWait.until(ExpectedConditions.visibilityOf(finalPrice));
        return PriceConverter.convertStringPrice(finalPrice.getText());
    }

    public void clickDepartureFlight() {
        driverWait.until(ExpectedConditions.elementToBeClickable(selectOutBoundDate));
        selectOutBoundDate.click();
    }

    public double getOutBoundPrice() {
        driverWait.until(ExpectedConditions.visibilityOf(selectOutBoundPrice));
        return PriceConverter.convertStringPrice(selectOutBoundPrice.getText());
    }

    public double getReturnPrice() {
        driverWait.until(ExpectedConditions.visibilityOf(selectReturnPrice));
        return PriceConverter.convertStringPrice(selectReturnPrice.getText());
    }

    public void clickReturnFlight() {
        driverWait.until(ExpectedConditions.elementToBeClickable(selectReturnDate));
        selectReturnDate.click();
    }

    public void submitPage() {
        driverWait.until(ExpectedConditions.attributeContains(continueButton,
                DISABLER_ATTRIBUTE_FOR_CONTINUE_BUTTON,
                DISABLER_VALUE_FOR_CONTINUE_BUTTON));
        continueButton.click();
    }
}