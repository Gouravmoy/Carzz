package com.example.lenovo.carzz.tasks;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.example.lenovo.carzz.callbacks.ModelLoadedListner;
import com.example.lenovo.carzz.extras.CarsUtils;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.pojo.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2/28/2016.
 */
public class TaskLoadModelsGallery extends AsyncTask<Void, Void, ArrayList<Models>> {
    private ModelLoadedListner modelLoadedListner;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadModelsGallery(ModelLoadedListner modelLoadedListner) {
        this.modelLoadedListner = modelLoadedListner;
        volleySingleton = VolleySingleton.getinstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected ArrayList<Models> doInBackground(Void... params) {
        ArrayList<Models> modelsList = CarsUtils.getModels(requestQueue);
        return modelsList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Models> modelsList) {
        if (modelLoadedListner != null) {
            modelLoadedListner.onModelsLoadedlistner(modelsList);
        }
    }
}
