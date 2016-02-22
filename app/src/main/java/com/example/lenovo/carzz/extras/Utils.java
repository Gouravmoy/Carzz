package com.example.lenovo.carzz.extras;

import org.json.JSONObject;

/**
 * Created by lenovo on 2/22/2016.
 */
public class Utils {
    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }
}
