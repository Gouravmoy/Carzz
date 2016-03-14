package com.example.lenovo.carzz.tasks;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.example.lenovo.carzz.callbacks.CarIdFetchedListener;
import com.example.lenovo.carzz.extras.BeaconsUtil;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.pojo.Gallery;

/**
 * Created by lenovo on 3/14/2016.
 */
public class TaskLoadCarFromBeacon extends AsyncTask<Void, Void, String> {

    public CarIdFetchedListener carIdFetchedListener;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private String namespace;
    private String instance;

    public TaskLoadCarFromBeacon(CarIdFetchedListener carIdFetchedListener, String namespace, String instance) {
        this.carIdFetchedListener = carIdFetchedListener;
        this.namespace = namespace;
        this.instance = instance;
        volleySingleton = VolleySingleton.getinstance();
        requestQueue = volleySingleton.getRequestQueue();
    }


    @Override
    protected String doInBackground(Void... params) {
        String carId = BeaconsUtil.getCarIdForBeacon(namespace, instance,requestQueue);
        return carId;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String carId) {
        if (carIdFetchedListener != null) {
            carIdFetchedListener.onCarIdFetchedListener(carId);
        }
    }
}
