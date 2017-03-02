package com.epam.easyjet.page;

import com.epam.easyjet.bean.Insurance;
import com.epam.easyjet.bean.Luggage;
import com.epam.easyjet.bean.Price;
import com.epam.easyjet.bean.Seats;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class FlightOptionsPage extends AbstractPage {

    @FindBy(xpath = "//button[@id='addDefaultHoldBaggage']")
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

    @FindBy(css = "button[id^='SeatSelectorNext']")
    private WebElement saveSeatsButton;

    public FlightOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void addLuggageButton() {
        luggageButton.click();
    }


    public Luggage getLuggage() {
        Luggage luggage = new Luggage();
        Price price = new Price();

        String fullPrice = luggagePrice.getText().replaceAll("[^0-9.]", "");
        String[] parsedPrice = fullPrice.split("\\.");

        System.out.println("fullprice  " + fullPrice);
        System.out.println(parsedPrice[0]);
        System.out.println(parsedPrice[1]);

        price.setFirstPart(Integer.parseInt(parsedPrice[0]));
        price.setSecondPart(Integer.parseInt(parsedPrice[1]));

        luggage.setPrice(price);
        System.out.println(luggageType.getText());
        luggage.setType(luggageType.getText());

        return luggage;
    }

    public Seats chooseEconomSeat() {
        WebElement priceBand = driver.findElement(
                By.xpath(".//*[@id='price_band_3']/div/p[@class='band_price']"));

        String[] fullPrice = priceBand.getText().replaceAll("£", "").split(".");
        Price price = new Price();
        price.setFirstPart(Integer.parseInt(fullPrice[0]));
        price.setSecondPart(Integer.parseInt(fullPrice[1]));

        WebElement seatButton = driver.findElement(
                By.xpath("//*[@data-priceband='3']/td/div[@class='available']"));
        seatButton.click();

        Seats seats = new Seats();
        seats.setPrice(price);
        seats.setType("econom");
        return seats;
    }

    public Seats chooseStandardSeat() {
        WebElement priceBand = driver.findElement(
                By.xpath(".//*[@id='price_band_2']/div/p[@class='band_price']"));

        String[] fullPrice = priceBand.getText().replaceAll("£", "").split(".");
        Price price = new Price();
        price.setFirstPart(Integer.parseInt(fullPrice[0]));
        price.setSecondPart(Integer.parseInt(fullPrice[1]));

        WebElement seatButton = driver.findElement(
                By.xpath("//*[@data-priceband='2']/td/div[@class='available']"));
        seatButton.click();

        Seats seats = new Seats();
        seats.setPrice(price);
        seats.setType("standard");
        return seats;
    }

    public Seats chooseXLSeat() {
        WebElement priceBand = driver.findElement(
                By.xpath(".//*[@id='price_band_1']/div/p[@class='band_price']"));

        String[] fullPrice = priceBand.getText().replaceAll("£", "").split(".");
        Price price = new Price();
        price.setFirstPart(Integer.parseInt(fullPrice[0]));
        price.setSecondPart(Integer.parseInt(fullPrice[1]));

        WebElement seatButton = driver.findElement(
                By.xpath("//*[@data-priceband='1']/td/div[@class='available']"));
        seatButton.click();

        Seats seats = new Seats();
        seats.setPrice(price);
        seats.setType("XL");
        return seats;
    }

    public void saveSeats() {
        saveSeatsButton.click();
    }

    public Insurance takeInsurance() {

        return null;
    }

    public Insurance takeSingleTripInsuranceButton() {
        singleTripInsuranceButton.click();
        return null;
    }

    public Insurance takeMissedFlightCoverButton() {
        missedFlightCoverButton.click();
        return null;
    }
}
