package com.epam.easyjet.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Raman_Darasinets on 3/2/2017.
 */
public class HoldScriptUtil {
    public static void waitScriptLoading(WebDriver driver) throws InterruptedException {

        WebElement loadElem = driver.findElement(By.cssSelector("div.holdOptionBusyIconContainer"));
        boolean flag = loadElem.isEnabled();
        while (flag){
            Thread.sleep(100);
            flag = loadElem.isDisplayed();
        }
    }
}
