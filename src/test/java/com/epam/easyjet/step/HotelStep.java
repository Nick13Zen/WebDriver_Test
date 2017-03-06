package com.epam.easyjet.step;

import com.epam.easyjet.bean.Hotel;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.HotelPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

/**
 * Created by Maria on 06.03.2017.
 */
public class HotelStep {

    private WebDriver driver;
    private HotelPage hotelPage;

    public HotelStep() {
        driver = DriverSingleton.getDriver();
        hotelPage = new HotelPage(driver);
        hotelPage.openPage();
    }

    public void closeDriver() {
        driver.close();
    }

    private void setHotelInfo(Hotel hotel) {
        String name = driver.findElement(By.xpath(".//*[@id='bookingDotComHotel']/div[1]/div[2]/h4/text()[3]")).getText(); //getting name of hotel in a form after adding room
        hotel.setName(name);
    }

    public void fillHotelPage(Order order) {
        hotelPage.addHotel();
        if(hotelPage.isHotelAdded()) {
            Hotel hotel = new Hotel();
            setHotelInfo(hotel);
            order.setHotel(hotel);
        }
        try {
            hotelPage.submitPage();
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage()); //TODO
        }
    }
}
