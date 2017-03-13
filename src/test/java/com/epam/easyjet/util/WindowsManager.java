package com.epam.easyjet.util;

import com.epam.easyjet.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;

import java.util.Set;

/**
 * Created by Yauheni_Borbut on 3/13/2017.
 */
public final class WindowsManager {

    public static void closeAllChildWindows() {
        WebDriver driver = DriverSingleton.getDriver();
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
}
