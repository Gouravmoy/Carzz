package com.example.lenovo.carzz.extras;

import com.android.volley.RequestQueue;
import com.example.lenovo.carzz.json.EndPoints;
import com.example.lenovo.carzz.json.Parser;
import com.example.lenovo.carzz.json.Requestor;
import com.example.lenovo.carzz.pojo.Gallery;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by lenovo on 2/22/2016.
 */
public class CarsUtils {
    public static Gallery loadGalleryImages(RequestQueue requestQueue) {
        JSONArray response = Requestor.sendGalleryRequest(requestQueue, EndPoints.getGalleryRequestUrl());
        Gallery gallery = Parser.parseGalleryJson(response);
        return gallery;
    }
}
