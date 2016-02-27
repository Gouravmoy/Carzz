package com.example.lenovo.carzz.json;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.lenovo.carzz.MyApplication;
import com.example.lenovo.carzz.logging.L;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by lenovo on 2/22/2016.
 */
public class Requestor {
    public static JSONArray sendGETRequest(RequestQueue requestQueue, String url) {
        JSONArray response = null;
        RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();
        //url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=ny97sdcpqetasj8a4v2na8va&limit=30";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, (String) null, requestFuture, requestFuture);
        requestQueue.add(request);
        try {
            response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
            L.m("Data Fetched");
            //L.t(MyApplication.getAppContext(), "Data Fetched");
        } catch (InterruptedException e) {
            L.m(e.getMessage() + "");
        } catch (ExecutionException e) {
            L.m(e.getMessage() + "");
        } catch (TimeoutException e) {
            L.m(e.getMessage() + "");
        }
        return response;
    }


}