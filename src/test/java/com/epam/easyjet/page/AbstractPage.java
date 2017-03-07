package com.epam.easyjet.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public abstract void openPage();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, 10);
    }
}
