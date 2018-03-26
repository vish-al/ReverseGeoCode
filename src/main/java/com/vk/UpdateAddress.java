package com.vk;

import com.google.maps.model.LatLng;

import java.util.List;

public class UpdateAddress {

    public static void main(String[] args) {
        Geocoder geocoder = new Geocoder();
        PlotLatLng landLatLngs = new PlotLatLng(27.174929,78.042102);

            if(landLatLngs.getLatitude()!=null && landLatLngs.getLongitude()!=null) {
                LatLng latLng = new LatLng(landLatLngs.getLatitude(), landLatLngs.getLongitude());
                List<String> addresses = geocoder.getAddress(latLng);
                for (String address : addresses) {
                    System.out.println(address);
                }
            }
    }
}
