package com.epam.easyjet.page;

import com.epam.easyjet.bean.*;
import com.epam.easyjet.util.PriceConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class FlightOptionsPage extends AbstractPage {

    @FindBy(id = "addDefaultHoldBaggage")
    private WebElement luggageButton;

    @FindBy(xpath = "//*[@class='holdScalePrice']")
    private WebElement luggagePrice;

    @FindBy(xpath = "//*[@class='holdScaleDial-value']")
    private WebElement luggageType;

    @FindBy(id = "ChooseSeatsButton")
    private WebElement chooseSeatsButton;

    @FindBy(xpath = "//*[@class='seat']/div[@class='available']")
    private WebElement availableSeats;

    @FindBy(id = "addDefaultHoldBaggage")
    private WebElement addLuggageButton;

    @FindBy(xpath = "//*/button[@data-travelinsurancename='Single Trip Insurance']")
    private WebElement singleTripInsuranceButton;

    @FindBy(xpath = "//*/button[@data-travelinsurancename='Missed Flight Cover']")
    private WebElement missedFlightCoverButton;

    @FindBy(css = "button[id^='SeatSelector']")
    private WebElement saveSeatsButton;

    @FindBy(id = "btnContinue")
    private WebElement continueButton;

    @FindBy(id = "ContinueButton")
    private WebElement noThanksButton;

    private static final String STANDARD_TYPE_TEXT = "standard";
    private static final String ECONOM_TYPE_TEXT = "econom";
    private static final String XL_TYPE_TEXT = "XL";

    private static final String PRICE_ECONOM_XPATH = "//*[@id='price_band_3']/div/p[@class='band_price']";
    private static final String PRICE_STANDARD_XPATH = "//*[@id='price_band_2']/div/p[@class='band_price']";
    private static final String PRICE_XL_XPATH = "//*[@id='price_band_1']/div/p[@class='band_price']";
    private static final String SEAT_ECONOM_BUTTON_XPATH = "//*[@data-priceband='3']/td/div[@class='available']";
    private static final String SEAT_STANDARD_BUTTON_XPATH = "//*[@data-priceband='2']/td/div[@class='available']";
    private static final String SEAT_XL_BUTTON_XPATH = "//*[@data-priceband='1']/td/div[@class='available']";

    private static final String HELP_WINDOW_XPATH = "//div[@class='cabinbagclose']/a";

    private static final String DISABLER_ATTRIBUTE_FOR_CONTINUE_BUTTON = "aria-disabled";
    private static final String DISABLER_VALUE_FOR_CONTINUE_BUTTON = "false";

    private static final String LUGGAGE_CONTAINER_CSS = "div.holdOptionBusyIconContainer";
    private static final String RECENTLY_ADDED_FORM = "div.detail.recentlySelected.contain";

    public FlightOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void addLuggageButton() {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(LUGGAGE_CONTAINER_CSS)));
        luggageButton.click();
    }

    public void setLuggage(Order order) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(LUGGAGE_CONTAINER_CSS)));

        Price price = PriceConverter.convertStringPrice(luggagePrice.getText());

        ArrayList<Luggage> luggages = new ArrayList<>();
        Flight flight = order.getFlights().get(0);
        int count = flight.getAdultCount() + flight.getChildCount() + flight.getInfantCount();
        for (int i = 0; i < count; i++) {
            Luggage luggage = new Luggage();
            luggage.setPrice(price);
            luggage.setType(luggageType.getText());
            luggages.add(luggage);
        }
        order.setLuggage(luggages);
    }

    public void getSeat() {
        chooseSeatsButton.click();
    }

    public Seats chooseEconomSeat() {
        return chooseSeats(ECONOM_TYPE_TEXT, SEAT_ECONOM_BUTTON_XPATH, PRICE_ECONOM_XPATH);
    }

    public Seats chooseStandardSeat() {
        return chooseSeats(STANDARD_TYPE_TEXT, SEAT_STANDARD_BUTTON_XPATH, PRICE_STANDARD_XPATH);
    }

    public Seats chooseXLSeat() {
        return chooseSeats(XL_TYPE_TEXT, SEAT_XL_BUTTON_XPATH, PRICE_XL_XPATH);
    }

    private Seats chooseSeats(String type, String buttonXpath, String priceXpath) {
        WebElement priceBand = driver.findElement(
                By.xpath(priceXpath));

        Price price = PriceConverter.convertStringPrice(priceBand.getText());

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buttonXpath)));
        WebElement seatButton = driver.findElement(
                By.xpath(buttonXpath));
        seatButton.click();

        Seats seats = new Seats();
        seats.setPrice(price);
        seats.setType(type);
        return seats;
    }

    public void saveSeats() {
        WebElement closeHelpWindow = driver.findElement(By.xpath(HELP_WINDOW_XPATH));
        if (closeHelpWindow.isDisplayed()) {
            closeHelpWindow.click();
        }
        driverWait.until(ExpectedConditions.visibilityOf(saveSeatsButton));
        saveSeatsButton.click();
    }

    public Insurance takeInsurance() {
        return null;
    } // TODO: 3/7/2017

    public Insurance takeSingleTripInsuranceButton() { // TODO: 3/7/2017
        singleTripInsuranceButton.click();
        return null;
    }

    public Insurance takeMissedFlightCoverButton() { // TODO: 3/7/2017
        missedFlightCoverButton.click();
        return null;
    }

    public void submitPage() {
        driverWait.until(ExpectedConditions.attributeContains(continueButton,
                DISABLER_ATTRIBUTE_FOR_CONTINUE_BUTTON,
                DISABLER_VALUE_FOR_CONTINUE_BUTTON));
        continueButton.click();
    }

    public void goNext() {
        driverWait.until(ExpectedConditions.visibilityOf(noThanksButton));
        noThanksButton.click();
    }

    public boolean isItemAdded() {
        List<WebElement> list = driver.findElements(By.cssSelector(RECENTLY_ADDED_FORM));
        return list.size() != 0;
    }
}
