package com.epam.easyjet.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class MainPage extends AbstractPage {

    private final String PAGE_URL = "https://www.easyjet.com/";

    @FindBy(xpath = "//input[contains(@aria-label, 'To:')]")
    private WebElement destinationInput;

    @FindBy(css = "button.ej-button.rounded-corners.arrow-button.search-submit")
    private WebElement submitButton;

    @FindBy(xpath = "//input[contains(@aria-label, 'From:')]")
    private WebElement departureInput;

    @FindBy(css = "div.anim-slide-rtl.drawer-section-wrapper")
    private WebElement warningField;

    @FindBy(xpath = "//input[contains(@name, 'Adults')]")
    private WebElement addAdultInput;

    @FindBy(xpath = "//input[contains(@name, 'Infants')]")
    private WebElement addInfantInput;

    @FindBy(xpath = "//input[contains(@name, 'Children')]")
    private WebElement addChildInput;

    @FindBy(xpath = "//button[contains(text(), 'Ok, continue')]")
    private WebElement infoSubmitButton;

    @FindBy(css = "a[ng-if='!IsReturnDate']")
    private WebElement departureDateButton;

    @FindBy(css = "a[title='Click to choose a return date']")
    private WebElement destinationDateButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(PAGE_URL);
        PageFactory.initElements(this.driver, this);
        WebElement closeButton = driver.findElement(By.id("close-drawer-link"));
        closeButton.click();
    }

    public void typeDeparturePlace(String placeName) {
        departureInput.clear();
        departureInput.sendKeys(placeName);
    }

    public void typeDestinationPlace(String placeName) {
        destinationInput.clear();
        destinationInput.sendKeys(placeName);
    }

    public void chooseDepartureDate(String date) {
        departureDateButton.click();
        pickDate(date);
    }

    public void chooseDestinationDate(String date) {
        destinationDateButton.click();
        pickDate(date);
    }

    public void setAdultCount(int count) {
        addAdultInput.clear();
        addAdultInput.sendKeys(String.valueOf(count));
    }

    public void setChildCount(int count) {
        addChildInput.clear();
        addChildInput.sendKeys(String.valueOf(count));
    }

    public void setInfantCount(int count) {
        addInfantInput.clear();
        addInfantInput.sendKeys(String.valueOf(count));
    }

    public void submit() throws Exception {
        submitButton.click();

        Thread.sleep(3000);
        if (warningField.isEnabled()) {
            infoSubmitButton.click();
        }
    }

    private void pickDate(String date) {
        WebElement departureDateValue = driver.findElement(By.cssSelector("div[data-date='" + date + "'")).
                findElement(By.cssSelector("a.selectable"));
        departureDateValue.click();
    }
}
