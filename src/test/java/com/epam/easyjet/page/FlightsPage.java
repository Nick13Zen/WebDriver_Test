package com.epam.easyjet.page;

import com.epam.easyjet.bean.Price;
import com.epam.easyjet.util.PriceConverter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class FlightsPage extends AbstractPage {

    List<WebElement> outboundDays = new ArrayList<WebElement>();
    List<WebElement> returnDays = new ArrayList<WebElement>();


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


    public FlightsPage(WebDriver driver) {
        super(driver);

    }

    public void pickOutBoundDate() {
        for (int i = 0; i <= 2; i++) {
            List<WebElement> summ = driver.findElements(By.xpath("//div[@id='OutboundLowestFlightDetails']//li[" + i + "][contains(@class,'standard')]"));
            outboundDays.addAll(summ);
        }
        outboundDays.get(1).click();
    }

    public void pickReturnDate() {
        for (int i = 0; i <= 2; i++) {
            List<WebElement> summ = driver.findElements(By.xpath("//div[@id='ReturnLowestFlightDetails']//li[" + i + "][contains(@class,'standard')]"));
            returnDays.addAll(summ);
        }
        returnDays.get(returnDays.size() - 1).click();
    }

    public void clickthreeWeeksButton() {
        threeWeeksButton.click();
    }


    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void clickDeparturePrice() {
        selectOutBoundDate.click();
    }

    public Price selectOutBoundPrice() {
        Price price = PriceConverter.convertStringPrice(selectOutBoundPrice.getText());
        return price;
    }

    public Price selectReturnPrice() {
        Price price = PriceConverter.convertStringPrice(selectReturnPrice.getText());
        return price;
    }


    public void clickReturnPrice() {
        selectReturnDate.click();
    }

    public void submit() {
        continueButton.click();
    }
}