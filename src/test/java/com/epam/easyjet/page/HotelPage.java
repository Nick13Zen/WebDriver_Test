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

    public void selectHotelParameters(Hotel hotel) {
        WebElement name = driverWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[contains(@class, 'detail recentlySelected contain')]/div[contains(@class, 'content')]/h4")));
        hotel.setName(name.getText());
    }

    public void submitPage() {
        driver.switchTo().defaultContent();
        continueButton.click();
    }

    public boolean isHotelAdded() { //TODO
        List<WebElement> list = driver.findElements(By.cssSelector(ADDED_HOTEL_FORM));
        return list.size() != 0;
    }
}