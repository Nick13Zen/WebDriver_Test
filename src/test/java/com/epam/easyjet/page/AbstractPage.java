package com.epam.easyjet.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    private static final int TIME_OUT_IN_SECONDS = 20;

    protected final WebDriver driver;
    protected final WebDriverWait driverWait;

    public abstract void openPage();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }
}
