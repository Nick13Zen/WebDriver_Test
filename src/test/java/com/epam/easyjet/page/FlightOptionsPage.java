package com.epam.easyjet.page;

import com.epam.easyjet.bean.*;
import com.epam.easyjet.util.PriceConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class FlightOptionsPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    private static final String STANDARD_TYPE_TEXT = "standard";
    private static final String ECONOM_TYPE_TEXT = "econom";
    private static final String XL_TYPE_TEXT = "XL";

    private static final String PRICE_ECONOM_XPATH = "//*[@id='price_band_3']/div/p[@class='band_price']";
    private static final String PRICE_STANDARD_XPATH = "//*[@id='price_band_2']/div/p[@class='band_price']";
    private static final String PRICE_XL_XPATH = "//*[@id='price_band_1']/div/p[@class='band_price']";
    private static final String PRICE_SEATS = "//div[@class='seating_details_block']/span[@class='amount']";

    private static final String SEAT_ECONOM_BUTTON_XPATH = "//*[@data-priceband='3']/td/div[@class='available']";
    private static final String SEAT_STANDARD_BUTTON_XPATH = "//*[@data-priceband='2']/td/div[@class='available']";
    private static final String SEAT_XL_BUTTON_XPATH = "//*[@data-priceband='1']/td/div[@class='available']";

    private static final String CONTAINER_ADDED_SEATS_XPATH = "//*[@class='seatsinbasket detail done contain']";

    private static final String HELP_WINDOW_XPATH = "//div[@class='cabinbagclose']/a";

    private static final String INSURANCE_PRICE_XPATH = "(//div[@class='optionAdd']/p)[1]";
    private static final String INSURANCE_PRICE_ATTRIBUTE = "data-totaldebitcharge";
    private static final String INSURANCE_TYPE_ATTRIBUTE = "data-travelinsurancename";

    private static final String DISABLER_ATTRIBUTE_FOR_CONTINUE_BUTTON = "aria-disabled";
    private static final String DISABLER_VALUE_FOR_CONTINUE_BUTTON = "false";

    private static final String BUSY_ICON_CONTAINER_CSS = "div.holdOptionBusyIconContainer";
    private static final String RECENTLY_ADDED_FORM_CSS = "div.detail.recentlySelected.contain";
    private static final String CONTAIN_INSURANCE_CSS = "div.detail.recentlySelected.contain>div#Insurance";

    private static final String CONTINUOUS_XL_SEATS = "//div[@id='restrictedSeatsDialog']//a[@id='acceptSeatRestriction']";

    @FindBy(id = "addDefaultHoldBaggage")
    private WebElement btnLuggage;

    @FindBy(css = "div.basketHoldItemSummary>span.itemPrice")
    private WebElement luggagePrice;

    @FindBy(xpath = "//*[@class='holdScaleDial-value']")
    private WebElement luggageType;

    @FindBy(id = "ChooseSeatsButton")
    private WebElement btnChooseSeats;

    @FindBy(css = "button[id^='SeatSelector']")
    private WebElement btnSaveSeats;

    @FindBy(id = "btnContinue")
    private WebElement btnContinue;

    @FindBy(id = "ContinueButton")
    private WebElement btnNoThanks;

    @FindBy(css = "button[id^='btnAddTravelInsurance']")
    private WebElement btnInsurance;

    public FlightOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public boolean isSeatsAdded() {
        List<WebElement> list = driver.findElements(By.xpath(CONTAINER_ADDED_SEATS_XPATH));
        return list.size() > 0;
    }

    public void clickLuggageButton() {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(BUSY_ICON_CONTAINER_CSS)));
        driverWait.until(ExpectedConditions.visibilityOf(btnLuggage));
        btnLuggage.click();
    }

    public List<Luggage> getDataLuggage(int count) {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(BUSY_ICON_CONTAINER_CSS)));

        double price = PriceConverter.converterStringWithX(luggagePrice.getText());
        List<Luggage> luggages = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Luggage luggage = new Luggage();
            luggage.setPrice(price);
            luggage.setType(luggageType.getText());
            luggages.add(luggage);
        }
        return luggages;
    }

    public void clickPickSeatsButton() {
        btnChooseSeats.click();
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

    public void saveSeats() {
        WebElement closeHelpWindow = driver.findElement(By.xpath(HELP_WINDOW_XPATH));
        if (closeHelpWindow.isDisplayed()) {
            closeHelpWindow.click();
        }
        driverWait.until(ExpectedConditions.visibilityOf(btnSaveSeats));
        btnSaveSeats.click();
    }

    public void clickInsuranceButton() {
        btnInsurance.click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(CONTAIN_INSURANCE_CSS)));
    }

    public Insurance getInsurance() {
        Insurance insurance = new Insurance();

        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(BUSY_ICON_CONTAINER_CSS)));

        WebElement insurancePrice = driver.findElement(By.xpath(INSURANCE_PRICE_XPATH));

        double price = PriceConverter.convertStringPrice(insurancePrice.getAttribute(INSURANCE_PRICE_ATTRIBUTE));

        insurance.setPrice(price);
        insurance.setType(btnInsurance.getAttribute(INSURANCE_TYPE_ATTRIBUTE));

        return insurance;
    }

    public void submitPage() {
        driverWait.until(ExpectedConditions.attributeContains(btnContinue,
                DISABLER_ATTRIBUTE_FOR_CONTINUE_BUTTON,
                DISABLER_VALUE_FOR_CONTINUE_BUTTON));
        btnContinue.click();
    }

    public void closeHelpWindow() {
        driverWait.until(ExpectedConditions.visibilityOf(btnNoThanks));
        btnNoThanks.click();
    }

    public boolean isItemAdded() {
        List<WebElement> list = driver.findElements(By.cssSelector(RECENTLY_ADDED_FORM_CSS));
        return list.size() > 0;
    }

    private Seats chooseSeats(String type, String buttonXpath, String priceXpath) {
        Seats seats = new Seats();
        try {
            WebElement seatButton = driverWait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(buttonXpath)));
            seatButton.click();

            WebElement priceBand = driver.findElement(By.xpath(priceXpath));
            double price = PriceConverter.convertStringPrice(priceBand.getText());
            seats.setPrice(price);
            seats.setType(type);
        } catch (StaleElementReferenceException e) {
            logger.error(e);
        }
        return seats;
    }

    public void continuousXLSeats() {
        WebElement buttonContinuous = driver.findElement(By.xpath(CONTINUOUS_XL_SEATS));
        buttonContinuous.click();
    }

    public double getPriceSeats() {
        double price = 0;
        List<WebElement> priceSeats = driver.findElements(By.xpath(PRICE_SEATS));
        for (WebElement element : priceSeats) {
            price += PriceConverter.convertStringPrice(element.getText());
        }
        return price;
    }
}