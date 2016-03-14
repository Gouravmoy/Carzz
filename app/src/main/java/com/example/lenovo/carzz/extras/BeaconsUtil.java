package com.example.lenovo.carzz.extras;

import com.android.volley.RequestQueue;
import com.example.lenovo.carzz.json.EndPoints;
import com.example.lenovo.carzz.json.Parser;
import com.example.lenovo.carzz.json.Requestor;
import com.example.lenovo.carzz.pojo.Gallery;

import org.json.JSONArray;

/**
 * Created by lenovo on 3/14/2016.
 */
public class BeaconsUtil {

    public static String getCarIdForBeacon(String namespace, String instance, RequestQueue requestQueue) {
        JSONArray response = Requestor.sendGETRequest(requestQueue, EndPoints.getBeaconQueryUrl(namespace, instance));
        String carId = Parser.parseBeaconResponse(response);
        return carId;
    }
}
