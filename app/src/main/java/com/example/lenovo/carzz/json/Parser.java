package com.example.lenovo.carzz.json;

import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.pojo.Image;
import com.example.lenovo.carzz.pojo.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.lenovo.carzz.extras.Constants.NA;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_CARS_ID;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_CAR_NAME;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_DESCRIPTION;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_GALLERY;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_GALLERY_PROFILE;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_GALLERY_THUMBNAIL;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_MODEL_COST;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_MODEL_ID;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_MODEL_IMAGES;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_MODEL_NAME;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_MODEL_SPEC_DETAILS;
import static com.example.lenovo.carzz.extras.Utils.contains;

/**
 * Created by lenovo on 2/22/2016.
 */
public class Parser {
    public static Gallery parseGalleryJson(JSONArray responseArray) {
        Gallery gallery;
        gallery = new Gallery();
        String carName = "";
        String carId = "";
        List<Image> imageList = new ArrayList<Image>();
        Image image;
        JSONObject response;
        for (int i = 0; i < responseArray.length(); i++) {
            try {
                response = responseArray.getJSONObject(i);
                if (response != null) {
                    if (response.length() > 0) {
                        try {
                            if (contains(response, KEY_CAR_NAME)) {
                                carName = response.getString(KEY_CAR_NAME);
                            }
                            if (contains(response, KEY_CARS_ID)) {
                                carId = response.getString(KEY_CARS_ID);
                            }
                            getImagesFromJSON(imageList, response, KEY_GALLERY);
                        } catch (Exception err) {
                            err.printStackTrace();
                        }
                        gallery.setId(carId);
                        gallery.setCarName(carName);
                        gallery.setImageList(imageList);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return gallery;
    }


    public static ArrayList<Models> parseModelsJson(JSONArray responseArray) {
        ArrayList<Models> modelsList = new ArrayList<Models>();
        Models model = new Models();
        JSONObject response;
        String modelName = "";
        String modelId = "";
        double modelCost = 0;
        List<String> modelDescription = new ArrayList<String>();
        List<Image> imageList = new ArrayList<Image>();
        JSONArray descriptionsJSONArray;
        for (int i = 0; i < responseArray.length(); i++) {
            model = new Models();
            try {
                response = responseArray.getJSONObject(i);
                imageList = new ArrayList<Image>();
                if (response != null) {
                    if (response.length() > 0) {
                        try {
                            if (contains(response, KEY_MODEL_ID)) {
                                modelId = response.getString(KEY_MODEL_ID);
                            }
                            if (contains(response, KEY_MODEL_NAME)) {
                                modelName = response.getString(KEY_MODEL_NAME);
                            }
                            if (contains(response, KEY_MODEL_COST)) {
                                modelCost = response.getDouble(KEY_MODEL_COST);
                            }
                            if (contains(response, KEY_MODEL_COST)) {
                                descriptionsJSONArray = response.getJSONArray(KEY_MODEL_SPEC_DETAILS);
                                modelDescription = getDescriptions(descriptionsJSONArray);
                            }
                            if (contains(response, KEY_MODEL_IMAGES)) {
                                getImagesFromJSON(imageList, response, KEY_MODEL_IMAGES);
                            }
                            model.setModel_id(modelId);
                            model.setModel_name(modelName);
                            model.setCost(modelCost);
                            model.setDescription(modelDescription);
                            model.setImage(imageList.get(0));
                            modelsList.add(model);
                        } catch (Exception err) {
                            err.printStackTrace();
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return modelsList;

    }

    private static List<String> getDescriptions(JSONArray descriptionsJSONArray) {
        List<String> descriptions = new ArrayList<String>();
        for (int i = 0; i < descriptionsJSONArray.length(); i++) {
            try {
                JSONObject descriptionJSON = descriptionsJSONArray.getJSONObject(i);
                if (contains(descriptionJSON, KEY_DESCRIPTION)) {
                    descriptions.add(descriptionJSON.getString(KEY_DESCRIPTION));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return descriptions;
    }

    private static void getImagesFromJSON(List<Image> imageList, JSONObject response, String key) {
        Image image;
        JSONArray imageListObject = null;
        try {
            imageListObject = response.getJSONArray(key);
            for (int j = 0; j < imageListObject.length(); j++) {
                image = new Image();
                image.setThumnail(NA);
                image.setProfile(NA);
                JSONObject imageJSON = imageListObject.getJSONObject(j);
                if (contains(imageJSON, KEY_GALLERY_PROFILE)) {
                    image.setProfile(imageJSON.getString(KEY_GALLERY_PROFILE));
                }
                if (contains(imageJSON, KEY_GALLERY_THUMBNAIL)) {
                    image.setThumnail(imageJSON.getString(KEY_GALLERY_THUMBNAIL));
                }
                imageList.add(image);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
