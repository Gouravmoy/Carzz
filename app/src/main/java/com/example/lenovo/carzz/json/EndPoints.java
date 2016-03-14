package com.example.lenovo.carzz.json;


import com.example.lenovo.carzz.extras.Constants;
import com.example.lenovo.carzz.extras.UrlEndpoints;

import static com.example.lenovo.carzz.extras.Constants.beaconInstance;
import static com.example.lenovo.carzz.extras.Constants.beaconNameSpace;
import static com.example.lenovo.carzz.extras.Constants.separator;

/**
 * Created by lenovo on 1/7/2016.
 */
public class EndPoints {
    public static String getGalleryRequestUrl() {
        return UrlEndpoints.URL_CARS_GALLERY;
    }

    public static String getModelRequestUrl() {
        return UrlEndpoints.URL_CARS_MODELS;
    }

    public static String getFeaturesRequestUrl() {
        return UrlEndpoints.URL_CARS_FEATURES;
    }

    public static String getSpecRequestUrl() {
        return UrlEndpoints.URL_CARS_SPEC;
    }

    public static String getBeaconQueryUrl(String namespace, String instance) {
        String url = "";
        url = UrlEndpoints.URL_CARS_BEACON;
        url += beaconNameSpace + separator+ namespace + separator + beaconInstance + separator + instance;
        return url;
    }
}
