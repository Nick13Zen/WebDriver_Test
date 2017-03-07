package com.epam.easyjet.page;

import com.epam.easyjet.bean.Price;
import com.epam.easyjet.util.PriceConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class FlightsPage extends AbstractPage {

    private List<WebElement> outboundDays = new ArrayList<>();
    private List<WebElement> returnDays = new ArrayList<>();


    @FindBy(xpath = "//div[@class='OutboundDaySlider']//div[@class='day selected']//a[1]")
    private WebElement selectOutBoundDate;

    @FindBy(xpath = "//div[@class='ReturnDaySlider']//div[@class='day selected']//a[1]")
    private WebElement selectReturnDate;

    @FindBy(xpath = "//div[@class='OutboundDaySlider']//div[@class='day selected']//a[1]/span[contains(@class,'targetPrice')]")
    private WebElement selectOutBoundPrice;

    @FindBy(xpath = "//div[@class='ReturnDaySlider']//div[@class='day selected']//a[1]/span[contains(@class,'targetPrice')]")
    private WebElement selectReturnPrice;

    @FindBy(xpath = "//div[contains(@class,'TotalCost')]//span[@id='price5']")
    private WebElement finalPrice;

    @FindBy(xpath = "//button[@id='btnContinue']")
    private WebElement continueButton;

    @FindBy(id = "SelectLowestFlightsTab")
    private WebElement threeWeeksButton;

    private static final String DISABLER_ATTRIBUTE_FOR_CONTINUE_BUTTON = "aria-disabled";
    private static final String DISABLER_VALUE_FOR_CONTINUE_BUTTON = "false";

    private static final String PRICE_DEPARTURE_XPATH_PART1 = "//div[@id='OutboundLowestFlightDetails']//li[";
    private static final String PRICE_XPATH_PART2 = "][contains(@class,'standard')]";

    private static final String PRICE_RETURN_XPATH_PART1 = "//div[@id='ReturnLowestFlightDetails']//li[";

    private static final int COUNT_OF_LINES_IN_WEEK = 3;


    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    public void pickOutBoundDate() {
        for (int i = 0; i < COUNT_OF_LINES_IN_WEEK; i++) {
            List<WebElement> summ = driver.findElements(By.xpath(PRICE_DEPARTURE_XPATH_PART1 + i + PRICE_XPATH_PART2));
            outboundDays.addAll(summ);
        }
    //    driverWait.until(ExpectedConditions.elementToBeClickable(outboundDays.get(1)));
        outboundDays.get(1).click();
    }

    public void pickReturnDate() {
        for (int i = 0; i < COUNT_OF_LINES_IN_WEEK; i++) {
            List<WebElement> summ = driver.findElements(By.xpath(PRICE_RETURN_XPATH_PART1 + i + PRICE_XPATH_PART2));
            returnDays.addAll(summ);
        }
    //    driverWait.until(ExpectedConditions.elementToBeClickable(returnDays.get(returnDays.size() - 1)));
        returnDays.get(returnDays.size() - 1).click();
    }

    public void clickthreeWeeksButton() {
   //     driverWait.until(ExpectedConditions.elementToBeClickable(threeWeeksButton));
        threeWeeksButton.click();
    }


    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void clickDeparturePrice() {
    //    driverWait.until(ExpectedConditions.elementToBeClickable(selectOutBoundDate));
        selectOutBoundDate.click();
    }

    public Price selectOutBoundPrice() {
        driverWait.until(ExpectedConditions.visibilityOf(selectOutBoundPrice));
        Price price = PriceConverter.convertStringPrice(selectOutBoundPrice.getText());
        return price;
    }

    public Price selectReturnPrice() {
        driverWait.until(ExpectedConditions.visibilityOf(selectReturnPrice));
        Price price = PriceConverter.convertStringPrice(selectReturnPrice.getText());
        return price;
    }


    public void clickReturnPrice() {
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