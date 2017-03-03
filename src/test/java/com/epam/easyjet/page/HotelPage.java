package com.epam.easyjet.page;

import com.epam.easyjet.bean.Hotel;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class HotelPage extends AbstractPage {

    @FindBy(xpath = "//input[contains(@name,'b_booknow1')]")
    private WebElement addRoomButton;

    @FindBy(id = "btnContinue")
    private WebElement continueButton;

    @FindBy(id = "bookingp_iframe")
    private WebElement frameElement;

    public HotelPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void addHotel() {
        driver.switchTo().frame(frameElement);
        addRoomButton.click();
    }

    public void submit() {
        driver.switchTo().defaultContent();
        continueButton.click();
    }

    public boolean isHotelAdded() throws Exception {
        List<WebElement> list = driver.findElements(By.cssSelector("div#bookingDotComHotel"));
        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}