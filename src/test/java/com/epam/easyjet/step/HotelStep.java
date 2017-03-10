package com.epam.easyjet.step;

import com.epam.easyjet.bean.Hotel;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.HotelPage;
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

    private void setHotelInfo(Hotel hotel) {
        hotelPage.selectHotelParameters(hotel);
    }

    public void addHotel(Order order) {
        hotelPage.addHotel();
        Hotel hotel = new Hotel();
        hotelPage.selectHotelParameters(hotel);
        order.setHotel(hotel);
    }

    public  boolean isHotelAdded() {
        return  hotelPage.isHotelAdded();
    }

    public void submitHotelPage() {
        hotelPage.submitPage();
    }
}