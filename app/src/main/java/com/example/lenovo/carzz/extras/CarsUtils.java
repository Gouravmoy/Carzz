package com.example.lenovo.carzz.extras;

import com.android.volley.RequestQueue;
import com.example.lenovo.carzz.json.EndPoints;
import com.example.lenovo.carzz.json.Parser;
import com.example.lenovo.carzz.json.Requestor;
import com.example.lenovo.carzz.pojo.Features;
import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.pojo.Models;
import com.example.lenovo.carzz.pojo.Specs;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2/22/2016.
 */
public class CarsUtils {
    public static Gallery loadGalleryImages(RequestQueue requestQueue) {
        JSONArray response = Requestor.sendGETRequest(requestQueue, EndPoints.getGalleryRequestUrl());
        Gallery gallery = Parser.parseGalleryJson(response);
        return gallery;
    }

    public static ArrayList<Models> getModels(RequestQueue requestQueue) {
        JSONArray response = Requestor.sendGETRequest(requestQueue, EndPoints.getModelRequestUrl());
        ArrayList<Models> modelsList = Parser.parseModelsJson(response);
        return modelsList;
    }

    public static Features getFeatures(RequestQueue requestQueue) {
        JSONArray response = Requestor.sendGETRequest(requestQueue, EndPoints.getFeaturesRequestUrl());
        Features features = Parser.parseFeaturesJson(response);
        return features;
    }

    public static Specs getSpecs(RequestQueue requestQueue) {
        JSONArray response = Requestor.sendGETRequest(requestQueue, EndPoints.getSpecRequestUrl());
        Specs spec = Parser.parseSpecsJson(response);
        return spec;
    }
}
