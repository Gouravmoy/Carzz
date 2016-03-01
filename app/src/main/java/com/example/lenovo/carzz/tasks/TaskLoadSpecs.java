package com.example.lenovo.carzz.tasks;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.example.lenovo.carzz.callbacks.FeaturesLoadedListner;
import com.example.lenovo.carzz.callbacks.SpecsLoadedListner;
import com.example.lenovo.carzz.extras.CarsUtils;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.pojo.Features;
import com.example.lenovo.carzz.pojo.Specs;

/**
 * Created by lenovo on 3/2/2016.
 */
public class TaskLoadSpecs extends AsyncTask<Void, Void, Specs> {

    private SpecsLoadedListner specsLoadedListner;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadSpecs(SpecsLoadedListner specsLoadedListner) {
        this.specsLoadedListner = specsLoadedListner;
        volleySingleton = VolleySingleton.getinstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected Specs doInBackground(Void... params) {
        Specs specs = CarsUtils.getSpecs(requestQueue);
        return specs;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Specs specs) {
        if (specsLoadedListner != null) {
            specsLoadedListner.onSpecLoadedlistner(specs);
        }
    }
}
