package com.epam.easyjet.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FindBy(xpath = "//input[contains(@name, 'Adults')]")
    private WebElement addAdultInput;

    @FindBy(xpath = "//input[contains(@name, 'Infants')]")
    private WebElement addInfantInput;

    @FindBy(xpath = "//input[contains(@name, 'Children')]")
    private WebElement addChildInput;

    @FindBy(xpath = "//button[contains(text(), '\t\t\t\tOk, continue\n\t\t\t')]")
    private WebElement infoSubmitButton;

    @FindBy(css = "a[ng-if='!IsReturnDate']")
    private WebElement departureDateButton;

    @FindBy(css = "a[title='Click to choose a return date']")
    private WebElement destinationDateButton;

    private static final String WARNING_FORM_CLOSE_BUTTON_XPATH = "close-drawer-link";
    private static final String CALENDAR_DAY_BUTTON_XPATH = "div[data-date='";
    private static final String SELECTABLE_DAY_BUTTON_XPATH = "a.selectable";
    private static final String WARNING_FORM_XPATH = "div.anim-slide-rtl.drawer-section-wrapper";


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(PAGE_URL);
        PageFactory.initElements(this.driver, this);
        if (isWarningPresents()) {
            WebElement closeButton = driver.findElement(By.id(WARNING_FORM_CLOSE_BUTTON_XPATH));
            closeButton.click();
        }
    }

    public void typeDeparturePlace(String placeName) {
        departureInput.clear();
        departureInput.sendKeys(placeName);
        departureInput.sendKeys(Keys.ENTER);
    }

    public void typeDestinationPlace(String placeName) {
        destinationInput.clear();
        destinationInput.sendKeys(placeName);
        destinationInput.sendKeys(Keys.ENTER);
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
        addAdultInput.sendKeys(Keys.ENTER);
    }

    public void setChildCount(int count) {
        addChildInput.clear();
        addChildInput.sendKeys(String.valueOf(count));
        addChildInput.sendKeys(Keys.ENTER);
    }

    public void setInfantCount(int count) {
        addInfantInput.clear();
        addInfantInput.sendKeys(String.valueOf(count));
        addInfantInput.sendKeys(Keys.ENTER);
    }

    public void submitPage() {
        submitButton.click();
        if (isWarningPresents()) {
           infoSubmitButton.click();
        }
    }

    private void pickDate(String date) {
        WebElement departureDateValue = driver.
                findElement(By.cssSelector(CALENDAR_DAY_BUTTON_XPATH + date + "'")).
                findElement(By.cssSelector(SELECTABLE_DAY_BUTTON_XPATH));
        departureDateValue.click();
    }

    public boolean isWarningPresents() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.
                findElements(By.cssSelector(WARNING_FORM_XPATH));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return list.size() != 0 && list.get(0).isDisplayed();
    }
}
