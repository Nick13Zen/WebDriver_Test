package com.epam.easyjet.util;

import com.epam.easyjet.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;

/**
 * Created by Yauheni_Borbut on 3/13/2017.
 */
public final class WindowsManager {

    public static void switchToMainWindow() {
        WebDriver driver = DriverSingleton.getDriver();
        String parentWindow = driver.getWindowHandle();
        driver.switchTo().window(parentWindow);
    }
}
