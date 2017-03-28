package com.epam.easyjet.step;

import com.epam.easyjet.bean.Hotel;
import com.epam.easyjet.bean.Order;
import com.epam.easyjet.driver.DriverSingleton;
import com.epam.easyjet.page.HotelPage;


public class HotelStep {

    private final HotelPage hotelPage;

    public HotelStep() {
        hotelPage = new HotelPage(DriverSingleton.getDriver());
        hotelPage.openPage();
    }

    public void setHotel(Order order) {
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