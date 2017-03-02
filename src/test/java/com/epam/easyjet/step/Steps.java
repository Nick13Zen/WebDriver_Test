package com.epam.easyjet.step;

import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.MainPage;
import org.openqa.selenium.WebDriver;

/**
 * Created by Yauheni_Borbut on 2/28/2017.
 */
public class Steps {

    private WebDriver driver;
    MainPage mainPage;

    public void initDriver() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        driver.close();
    }

    public void setClientCount(int adultCount, int childCount, int infantCount) {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
        }
        mainPage.openPage();
        mainPage.setAdultCount(adultCount);
        mainPage.setChildCount(childCount);
        mainPage.setInfantCount(infantCount);
    }

    public boolean isWarningDisplayed() {
        if (null == mainPage) {
            mainPage = new MainPage(driver);
        }
        return mainPage.isWarningPresents();
    }
}
