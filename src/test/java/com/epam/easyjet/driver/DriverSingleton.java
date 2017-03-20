package com.epam.easyjet.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_EXE_PATH = ".\\geckodriver\\geckodriver.exe";
    private static final String NORMAL_PARAMETER = "normal";
    private static final int PAGE_LOAD_TIMEOUT = 60;
    private static final int DEFAULT_IMPLICITLY = 10;

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_EXE_PATH);
            DesiredCapabilities firefoxCap = DesiredCapabilities.firefox();
            firefoxCap.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, NORMAL_PARAMETER);

            driver = new FirefoxDriver(firefoxCap);
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICITLY, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
