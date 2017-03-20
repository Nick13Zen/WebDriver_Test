package com.epam.easyjet.page;

import com.epam.easyjet.bean.Hotel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class HotelPage extends AbstractPage {
    private static final String ADDED_HOTEL_FORM = "div#bookingDotComHotel";

    @FindBy(xpath = "//input[contains(@name,'b_booknow1')]")
    private WebElement addRoomButton;

    @FindBy(id = "btnContinue")
    private WebElement btnContinue;

    @FindBy(id = "bookingp_iframe")
    private WebElement frameElement;

    @FindBy(xpath = "//div[contains(@class, 'detail recentlySelected contain')]/div[contains(@class, 'content')]/h4")
    private WebElement hotelName;

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
        driver.switchTo().defaultContent();
    }

    public void setHotelParameters(Hotel hotel) {
        hotel.setName(hotelName.getText());
    }

    public void submitPage() {
        driverWait.until(ExpectedConditions.visibilityOf(btnContinue));
        btnContinue.click();
    }

    public boolean isHotelAdded() {
        List<WebElement> list = driver.findElements(By.cssSelector(ADDED_HOTEL_FORM));
        return list.size() > 0;
    }
}