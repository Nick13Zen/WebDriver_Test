package com.epam.easyjet.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class MainPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://www.easyjet.com/en";
    private static final String DIALOG_FORM_ID = "drawer-dialog";
    private static final String DRAWER_SECTION_NO_PASSENGERS_XPATH = "//div[@class='drawer-section no-passengers']";
    private static final String DRAWER_BUTTON_XPATH = "//div[@class='drawer-button']/button";
    private static final String WARNING_FORM_CLOSE_BUTTON_XPATH = "close-drawer-link";

    private static final String WARNING_COOKIES_CSS = "div.drawer-section.cookie-policy-drawer";

    private static final String WARNING_FLYING_WITH_INFANTS_CSS = "div.drawer-section.search-with-infants";
    private static final String WARNING_MAX_PASSENGER_FORM_CSS = "div.drawer-section.max-passengers";
    private static final String WARNING_INFANT_FORM_CSS = "div.drawer-section.more-infants-than-adults";
    private static final String SELECTABLE_DAY_BUTTON_XPATH = "a.selectable";
    private static final String CALENDAR_DAY_BUTTON_XPATH = "div[data-date='";

    private static final String WARNING_FLYING_WITH_INFANTS_TEXT = "Flying with infants?";
    private static final String WARNING_COOKIES_TEXT = "Cookies help us give you a better experience on easyJet.com. " +
            "By continuing to use our site, you are agreeing to the use of cookies as set in our Cookie Policy.";
    private static final String MAXIMUM_NUMBER_TEXT = "The maximum number of adults and children per booking is 40.";
    private static final String INFANT_PRESENTS_TEXT = "You can only book one infant per adult online. " +
            "Please visit the Contact Us section for further information.";
    private static final String NO_PASSENGER_TEXT = "Please select at least one passenger!";

    private static final int DEFAULT_TIMEOUT = 10;
    private static final int WARNING_TIMEOUT = 2;

    @FindBy(xpath = "//div[@class='message-drawer-content']/p")
    private WebElement lineWarningInvalidCount;

    @FindBy(xpath = "//div[@class='field-row']/p")
    private WebElement lineWarningCookies;

    @FindBy(xpath = "//div[@class='message-summary']")
    private WebElement lineWarningFlyingWithInfants;

    @FindBy(xpath = "//input[contains(@aria-label, 'To:')]")
    private WebElement inputDestination;

    @FindBy(css = "button.ej-button.rounded-corners.arrow-button.search-submit")
    private WebElement btnSubmit;

    @FindBy(xpath = "//input[contains(@aria-label, 'From:')]")
    private WebElement inputDeparture;

    @FindBy(xpath = "//input[contains(@name, 'Adults')]")
    private WebElement inputAddAdult;

    @FindBy(xpath = "//input[contains(@name, 'Infants')]")
    private WebElement inputAddInfant;

    @FindBy(xpath = "//input[contains(@name, 'Children')]")
    private WebElement inputAddChild;

    @FindBy(xpath = "//button[contains(text(), '\t\t\t\tOk, continue\n\t\t\t')]")
    private WebElement btnInfoSubmit;

    @FindBy(css = "a[ng-if='!IsReturnDate']")
    private WebElement btnDepartureDate;

    @FindBy(css = "a[title='Click to choose a return date']")
    private WebElement btnDestinationDate;

    @FindBy(xpath = "//div[@class='search-passengers-adults search-row']//button[@class='quantity-button-add']")
    private WebElement btnAddAdult;

    @FindBy(xpath = "//div[@class='search-passengers-infants search-row']//button[@class='quantity-button-add']")
    private WebElement btnAddInfant;

    @FindBy(xpath = "//div[@class='search-passengers-children search-row']//button[@class='quantity-button-add']")
    private WebElement btnAddChild;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.navigate().to(PAGE_URL);
        PageFactory.initElements(this.driver, this);
        if (isWarningCookiesPresents()) {
            WebElement closeButton = driver.findElement(By.id(WARNING_FORM_CLOSE_BUTTON_XPATH));
            closeButton.click();
        }
    }

    public void typeDeparturePlace(String placeName) {
        inputDeparture.clear();
        inputDeparture.sendKeys(placeName);
        inputDeparture.sendKeys(Keys.ENTER);
    }

    public void typeDestinationPlace(String placeName) {
        inputDestination.clear();
        inputDestination.sendKeys(placeName);
        inputDestination.sendKeys(Keys.ENTER);
    }

    public void chooseDepartureDate(String date) {
        driverWait.until(ExpectedConditions.elementToBeClickable(btnDepartureDate));
        btnDepartureDate.click();
        pickDate(date);
    }

    public void chooseDestinationDate(String date) {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(DIALOG_FORM_ID)));
        driverWait.until(ExpectedConditions.elementToBeClickable(btnDestinationDate));
        btnDestinationDate.click();
        pickDate(date);
    }

    public void typeAdultCount(int count) {
        inputAddAdult.clear();
        checkNoPassengerWarning();
        inputAddAdult.sendKeys(String.valueOf(count));
    }

    public void typeChildCount(int count) {
        inputAddChild.clear();
        checkNoPassengerWarning();
        inputAddChild.sendKeys(String.valueOf(count));
    }

    public void typeInfantCount(int count) {
        inputAddInfant.clear();
        checkNoPassengerWarning();
        inputAddInfant.sendKeys(String.valueOf(count));
    }

    public void addAdult() {
        btnAddAdult.click();
    }

    public void addChild() {
        btnAddChild.click();
    }

    public void addInfant() {
        btnAddInfant.click();
    }

    public void submitByEnter() {
        inputAddAdult.sendKeys(Keys.ENTER);
    }

    public void submitPage() {
        driverWait.until(ExpectedConditions.visibilityOf(btnSubmit));

        btnSubmit.click();

        if (isWarningFlyingWithInfantsPresents()) {
            btnInfoSubmit.click();
        }
    }

    public boolean isWarningInfantsPresents() {
        List<WebElement> list = driver.
                findElements(By.cssSelector(WARNING_INFANT_FORM_CSS));
        return list.size() > 0 && lineWarningInvalidCount.getText().equals(INFANT_PRESENTS_TEXT);
    }

    public boolean isWarningMaxPassengersPresents() {
        List<WebElement> list = driver.
                findElements(By.cssSelector(WARNING_MAX_PASSENGER_FORM_CSS));
        return list.size() > 0 && lineWarningInvalidCount.getText().equals(MAXIMUM_NUMBER_TEXT);
    }

    private boolean isWarningNoPassengersPresents() {
        driver.manage().timeouts().implicitlyWait(WARNING_TIMEOUT, TimeUnit.SECONDS);
        List<WebElement> list = driver.
                findElements(By.xpath(DRAWER_SECTION_NO_PASSENGERS_XPATH));
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        if (list.size() == 0) {
            return false;
        }

        String line = null;
        boolean flag;
        do {
            try {
                line = lineWarningInvalidCount.getText();
                flag = true;
            } catch (StaleElementReferenceException e) {
                flag = false;
            }
        } while (!flag);

        return line.equals(NO_PASSENGER_TEXT);
    }

    private boolean isWarningCookiesPresents() {
        driver.manage().timeouts().implicitlyWait(WARNING_TIMEOUT, TimeUnit.SECONDS);
        List<WebElement> list = driver.
                findElements(By.cssSelector(WARNING_COOKIES_CSS));
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return list.size() > 0 && lineWarningCookies.getText().equals(WARNING_COOKIES_TEXT);
    }

    private boolean isWarningFlyingWithInfantsPresents() {
        driver.manage().timeouts().implicitlyWait(WARNING_TIMEOUT, TimeUnit.SECONDS);
        List<WebElement> list = driver.
                findElements(By.cssSelector(WARNING_FLYING_WITH_INFANTS_CSS));
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return list.size() > 0 && lineWarningFlyingWithInfants.getText().equals(WARNING_FLYING_WITH_INFANTS_TEXT);
    }

    private void pickDate(String date) {
        WebElement departureDateValue = driver.
                findElement(By.cssSelector(CALENDAR_DAY_BUTTON_XPATH + date + "'")).
                findElement(By.cssSelector(SELECTABLE_DAY_BUTTON_XPATH));
        departureDateValue.click();
    }

    private void checkNoPassengerWarning() {
        if (isWarningNoPassengersPresents()) {
            try {
                driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DRAWER_BUTTON_XPATH))).click();
            } catch (StaleElementReferenceException e) {
                logger.error(e);
            }
        }
    }
}
