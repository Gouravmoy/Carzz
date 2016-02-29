package com.example.lenovo.carzz.tasks;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.example.lenovo.carzz.callbacks.FeaturesLoadedListner;
import com.example.lenovo.carzz.callbacks.ModelLoadedListner;
import com.example.lenovo.carzz.extras.CarsUtils;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.pojo.Features;
import com.example.lenovo.carzz.pojo.Models;

import java.util.ArrayList;

/**
 * Created by lenovo on 2/29/2016.
 */
public class TaskLoadFeatures extends AsyncTask<Void, Void, Features> {
    private FeaturesLoadedListner featuresLoadedListner;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadFeatures(FeaturesLoadedListner featuresLoadedListner) {
        this.featuresLoadedListner = featuresLoadedListner;
        volleySingleton = VolleySingleton.getinstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected Features doInBackground(Void... params) {
        Features features = CarsUtils.getFeatures(requestQueue);
        return features;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Features features) {
        if (featuresLoadedListner != null) {
            featuresLoadedListner.onGalleryLoadedlistner(features);
        }
    }
}
