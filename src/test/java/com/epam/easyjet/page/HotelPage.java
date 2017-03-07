package com.epam.easyjet.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(xpath = "//div[1][@id='bookingDotComHotel']//*[@class='content']//h4")
    private WebElement hotelName;
    
    private static final String ADDED_HOTEL_FORM = "div#bookingDotComHotel";

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
    
    public Hotel selectHotelParameters() {
        Hotel hotel = new Hotel();
        hotel.setName(hotelName.getText());
        return hotel;
    }

    public void submitPage() {
        driver.switchTo().defaultContent();
        continueButton.click();
    }

    public boolean isHotelAdded() {
        List<WebElement> list = driver.findElements(By.cssSelector(ADDED_HOTEL_FORM));
        return list.size() != 0;
    }
}
