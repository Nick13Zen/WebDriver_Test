package com.epam.easyjet.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class MainPage extends AbstractPage {

    private final String PAGE_URL = "https://www.easyjet.com/en";
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

    @FindBy(xpath = "//div[@class='search-passengers-adults search-row']//button[contains(@class,'passenger-button-add')]")
    private WebElement addAdultButton;

    @FindBy(xpath = "//div[@class='search-passengers-infants search-row']//button[contains(@class,'passenger-button-add')]")
    private WebElement addInfantButton;

    @FindBy(xpath = "//div[@class='search-passengers-children search-row']//button[contains(@class,'passenger-button-add')]")
    private WebElement addChildButton;

    @FindBy(xpath = " //div[@class='search-passengers-adults search-row']//button[contains(@class,'passenger-button-remove')]")
    private WebElement removeAdultButton;


    private static final String WARNING_FORM_CLOSE_BUTTON_XPATH = "close-drawer-link";

    private static final String DRAWER_SECTION_NO_PASSENGERS_XPATH = "//div[@class='drawer-section no-passengers']";

    private static final String CALENDAR_DAY_BUTTON_XPATH = "div[data-date='";
    private static final String SELECTABLE_DAY_BUTTON_XPATH = "a.selectable";
    private static final String DIALOG_FORM_ID = "drawer-dialog";
    private static final String WARNING_MAX_PASSENGER_FORM_XPATH = "div.drawer-section.max-passengers";
    private static final String WARNING_INFANT_FORM_XPATH = "div.drawer-section.more-infants-than-adults";
    private static final String DRAWER_BUTTON_XPATH = "//div[@class='drawer-button']/button";


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.navigate().to(PAGE_URL);
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
        driverWait.until(ExpectedConditions.elementToBeClickable(departureDateButton));
        departureDateButton.click();
        pickDate(date);
    }

    public void chooseDestinationDate(String date) {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(DIALOG_FORM_ID)));
        driverWait.until(ExpectedConditions.elementToBeClickable(destinationDateButton));
        destinationDateButton.click();
        pickDate(date);
    }

    public void setAdultCount(int count) {
        addAdultInput.clear();
        if (isWarningNoPassengersPresents()) {
            driver.findElement(By.xpath(DRAWER_BUTTON_XPATH)).click();
        }
        addAdultInput.sendKeys(String.valueOf(count));
    }

    public void setChildCount(int count) {
        addChildInput.clear();
        if (isWarningNoPassengersPresents()) {
            driver.findElement(By.xpath(DRAWER_BUTTON_XPATH)).click();
        }
        addChildInput.sendKeys(String.valueOf(count));
    }

    public void setInfantCount(int count) {
        addInfantInput.clear();
        if (isWarningNoPassengersPresents()) {
            driver.findElement(By.xpath(DRAWER_BUTTON_XPATH)).click();
        }
        addInfantInput.sendKeys(String.valueOf(count));
    }

    public void clickSubmit() {
        addAdultInput.sendKeys(Keys.ENTER);
    }
    public void submitPage() {
        driverWait.until(ExpectedConditions.visibilityOf(submitButton));

        submitButton.click();

        if (isWarningPresents()) {
            infoSubmitButton.click();
        }
    }

    public boolean isWarningMaxPassengersPresents() {
        List<WebElement> list = driver.
                findElements(By.cssSelector(WARNING_MAX_PASSENGER_FORM_XPATH));
        return list.size() > 0;
    }

    public boolean isWarningInfantsPresents() {
        List<WebElement> list = driver.
                findElements(By.cssSelector(WARNING_INFANT_FORM_XPATH));
        return list.size() > 0;
    }

    public boolean isWarningNoPassengersPresents() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.
                findElements(By.xpath(DRAWER_SECTION_NO_PASSENGERS_XPATH));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return list.size() > 0;
    }

    private void pickDate(String date) {
        WebElement departureDateValue = driver.
                findElement(By.cssSelector(CALENDAR_DAY_BUTTON_XPATH + date + "'")).
                findElement(By.cssSelector(SELECTABLE_DAY_BUTTON_XPATH));
        departureDateValue.click();
    }

    public void addAdult() {
        driverWait.until(ExpectedConditions.elementToBeClickable(addAdultButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(addAdultButton).click().perform();
//        driverWait.until(ExpectedConditions.elementToBeClickable(addAdultButton));
//        addAdultButton.click();
    }

    public void removeAdult() {
        driverWait.until(ExpectedConditions.elementToBeClickable(addAdultButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(removeAdultButton).click().perform();
//        driverWait.until(ExpectedConditions.elementToBeClickable(addAdultButton));
//        addAdultButton.click();
    }

    public void addChild() {
        driverWait.until(ExpectedConditions.elementToBeClickable(addChildButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(addChildButton).click().perform();
//        addChildButton.click();
    }

    public void addInfant() {
        driverWait.until(ExpectedConditions.elementToBeClickable(addInfantButton));
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("scroll(250, 0)");
        Actions actions = new Actions(driver);
        actions.moveToElement(addInfantButton).click().perform();
//        addInfantButton.click();
    }

    private boolean isWarningPresents() {
//        return driver.findElement(By.id(DIALOG_FORM_ID)).isDisplayed();
        List<WebElement> list = driver.
                findElements(By.id(DIALOG_FORM_ID));
        return list.size() > 0;
    }
}
