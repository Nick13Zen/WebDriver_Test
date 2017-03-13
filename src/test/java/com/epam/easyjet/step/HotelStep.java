package com.epam.easyjet.step;

import com.epam.easyjet.bean.Hotel;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.HotelPage;

/**
 * Created by Maria on 06.03.2017.
 */
public class HotelStep {

    private HotelPage hotelPage;

    public HotelStep() {
        hotelPage = new HotelPage(DriverSingleton.getDriver());
        hotelPage.openPage();
    }

    public void addHotel(Order order) {
        hotelPage.addHotel();
        Hotel hotel = new Hotel();
        hotelPage.setHotelParameters(hotel);
        order.setHotel(hotel);
    }

    public boolean isHotelAdded() {
        return hotelPage.isHotelAdded();
    }

    public void submitHotelPage() {
        hotelPage.submitPage();
    }
}