package com.example.lenovo.carzz.json;

import com.example.lenovo.carzz.pojo.Catagory;
import com.example.lenovo.carzz.pojo.Features;
import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.pojo.Image;
import com.example.lenovo.carzz.pojo.Models;
import com.example.lenovo.carzz.pojo.SubFeatures;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.lenovo.carzz.extras.Constants.NA;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_CARS_ID;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_CAR_NAME;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_DESCRIPTION;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_FEATURE_CATAGORY;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_FEATURE_CATAGORY_FEATURES;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_FEATURE_CATAGORY_FEATURES_IMAGES;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_FEATURE_CATAGORY_FEATURES_SYNOPSIS;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_FEATURE_CATAGORY_FEATURES_TITLE;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_FEATURE_CATAGORY_NAME;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_FEATURE_ID;
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
                            getImagesFromJSON(imageList, response, KEY_GALLERY, true);
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
                                getImagesFromJSON(imageList, response, KEY_MODEL_IMAGES, true);
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

    public static Features parseFeaturesJson(JSONArray featuresJSONArray) {

        String carName = "";
        String feature_id = "";
        JSONObject featureJSON;
        JSONArray catagoryJsonArray;
        Features feature = new Features();
        ArrayList<Catagory> catagories = new ArrayList<Catagory>();
        ArrayList<Features> featuresArrayList = new ArrayList<Features>();

        for (int i = 0; i < featuresJSONArray.length(); i++) {
            try {
                featureJSON = featuresJSONArray.getJSONObject(i);
                if (featureJSON != null) {
                    if (featureJSON.length() > 0) {
                        if (contains(featureJSON, KEY_FEATURE_ID)) {
                            feature_id = featureJSON.getString(KEY_FEATURE_ID);
                        }
                        if (contains(featureJSON, KEY_CAR_NAME)) {
                            carName = featureJSON.getString(KEY_CAR_NAME);
                        }
                        if (contains(featureJSON, KEY_FEATURE_CATAGORY)) {
                            catagoryJsonArray = featureJSON.getJSONArray(KEY_FEATURE_CATAGORY);
                            catagories = getFeatureCatagory(catagoryJsonArray);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            feature.setCarName(carName);
            feature.setFeatureId(feature_id);
            feature.setCatagoryArrayList(catagories);
        }
        return feature;
    }

    private static ArrayList<Catagory> getFeatureCatagory(JSONArray catagoryJsonArray) {
        String catagoryname = "";
        Catagory catagory = new Catagory();
        JSONObject catagoryJSON;
        JSONArray subFeaturesJsonArray;
        ArrayList<SubFeatures> subFeatures = new ArrayList<SubFeatures>();
        ArrayList<Catagory> catagories = new ArrayList<Catagory>();

        for (int i = 0; i < catagoryJsonArray.length(); i++) {
            catagory = new Catagory();
            try {
                catagoryJSON = catagoryJsonArray.getJSONObject(i);
                if (catagoryJSON != null) {
                    if (catagoryJSON.length() > 0) {
                        if (contains(catagoryJSON, KEY_FEATURE_CATAGORY_NAME)) {
                            catagoryname = catagoryJSON.getString(KEY_FEATURE_CATAGORY_NAME);
                        }
                        if (contains(catagoryJSON, KEY_FEATURE_CATAGORY_FEATURES)) {
                            subFeaturesJsonArray = catagoryJSON.getJSONArray(KEY_FEATURE_CATAGORY_FEATURES);
                            subFeatures = getFeaturesSubCatagory(subFeaturesJsonArray);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            catagory.setCatagoryName(catagoryname);
            catagory.setSubFeaturesArrayList(subFeatures);
            catagories.add(catagory);
        }
        return catagories;
    }

    private static ArrayList<SubFeatures> getFeaturesSubCatagory(JSONArray subFeaturesJsonArray) {
        String title = "";
        String synopsis = "";
        SubFeatures subFeature = new SubFeatures();
        JSONObject subFeatureJson;
        List<Image> imageList = new ArrayList<Image>();
        ArrayList<SubFeatures> subFeatures = new ArrayList<SubFeatures>();

        for (int i = 0; i < subFeaturesJsonArray.length(); i++) {
            subFeature = new SubFeatures();
            try {
                subFeatureJson = subFeaturesJsonArray.getJSONObject(i);
                if (subFeatureJson != null) {
                    if (subFeatureJson.length() > 0) {
                        if (contains(subFeatureJson, KEY_FEATURE_CATAGORY_FEATURES_TITLE)) {
                            title = subFeatureJson.getString(KEY_FEATURE_CATAGORY_FEATURES_TITLE);
                        }
                        if (contains(subFeatureJson, KEY_FEATURE_CATAGORY_FEATURES_SYNOPSIS)) {
                            synopsis = subFeatureJson.getString(KEY_FEATURE_CATAGORY_FEATURES_SYNOPSIS);
                        }
                        if (contains(subFeatureJson, KEY_FEATURE_CATAGORY_FEATURES_IMAGES)) {
                            getImagesFromJSON(imageList, subFeatureJson, KEY_FEATURE_CATAGORY_FEATURES_IMAGES, false);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            subFeature.setTitle(title);
            subFeature.setSynopsis(synopsis);
            subFeature.setImage(imageList.get(0));
            subFeatures.add(subFeature);
        }
        return subFeatures;
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

    private static void getImagesFromJSON(List<Image> imageList, JSONObject response, String key, boolean isArray) {
        Image image;
        JSONArray imageListObject = null;
        JSONObject imageObject = null;
        try {
            if (isArray == true) {
                imageListObject = response.getJSONArray(key);
                getImageFromJsonArray(imageList, imageListObject);
            } else {
                imageObject = response.getJSONObject(key);
                getImageFromJsonObject(imageList, imageObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static void getImageFromJsonArray(List<Image> imageList, JSONArray imageListObject) throws JSONException {
        Image image;
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
    }

    private static void getImageFromJsonObject(List<Image> imageList, JSONObject imageJSON) {
        Image image;
        image = new Image();
        image.setThumnail(NA);
        image.setProfile(NA);
        try {
            if (contains(imageJSON, KEY_GALLERY_PROFILE)) {
                image.setProfile(imageJSON.getString(KEY_GALLERY_PROFILE));
            }
            if (contains(imageJSON, KEY_GALLERY_THUMBNAIL)) {
                image.setThumnail(imageJSON.getString(KEY_GALLERY_THUMBNAIL));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        imageList.add(image);
    }
}
