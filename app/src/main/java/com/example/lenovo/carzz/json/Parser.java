package com.example.lenovo.carzz.json;

import com.example.lenovo.carzz.extras.Constants;
import com.example.lenovo.carzz.extras.Keys;
import com.example.lenovo.carzz.extras.Utils;
import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.pojo.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.lenovo.carzz.extras.Constants.NA;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_CARS_ID;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_CAR_NAME;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_GALLERY;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_GALLERY_PROFILE;
import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_GALLERY_THUMBNAIL;
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
                            JSONArray imageListObject = response.getJSONArray(KEY_GALLERY);
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
}
