package com.vk;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.*;

import java.util.ArrayList;
import java.util.List;

public class Geocoder {
    static String GOOGLEAPICLIENTID="GOOGLE_API_KEY";
    static String GOOGLEAPICYTOGRAPHICVALUE="GOOGLE_API_CYTOGRAPHICVALUE";
    static final int MINIMUM_INTERVAL = 1;
    static final int MAX_QPS = 100;
    public List<String> getAddress(LatLng latlng) {
        GeoApiContext geoApiContext = getGeoApiContext();
        GeocodingApiRequest geocodingApiRequest = GeocodingApi.reverseGeocode(geoApiContext, latlng);
        String address2 = "";
        String address1 = null;
        try {
            GeocodingResult[] await = geocodingApiRequest.await();
            if(await.length>0) {
                    for (AddressComponent addressComponent : await[0].addressComponents) {
                        for (AddressComponentType type : addressComponent.types) {
                            if(type.name().equals(AddressType.ROUTE.name()))
                                address1 = addressComponent.longName;
                            if(type.name().equals(AddressType.LOCALITY.name()))
                                address2 += addressComponent.longName + ", ";
                            if (type.name().equals(AddressType.ADMINISTRATIVE_AREA_LEVEL_2.name())) {
                                address2 += addressComponent.longName + ", ";
                            }
                            if (type.name().equals(AddressType.ADMINISTRATIVE_AREA_LEVEL_1.name())) {
                                address2 += addressComponent.longName + ", ";
                            }
                            if (type.name().equals(AddressType.POSTAL_CODE.name())) {
                                address2 += addressComponent.longName;
                            }
                            if (type.name().equals(AddressType.COUNTRY.name())) {
                                address2 += addressComponent.longName + ", ";
                            }
                        }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

     /*   if(formattedAddress!= null) {
            System.out.println(formattedAddress.toString());
            if (formattedAddress.length() > 200) {
                address1 = formattedAddress.substring(0, 200);
                address2 = formattedAddress.substring(200, 400);
            } else {
                address1 = formattedAddress;
                address2 = null;
            }
        }*/

        List<String> addresses = new ArrayList<String>();
        addresses.add(address1);
        addresses.add(address2);
        return addresses;
    }


    private GeoApiContext getGeoApiContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setQueryRateLimit(MAX_QPS, MINIMUM_INTERVAL);
        geoApiContext.setEnterpriseCredentials(GOOGLEAPICLIENTID, GOOGLEAPICYTOGRAPHICVALUE);
        return geoApiContext;
    }
}
