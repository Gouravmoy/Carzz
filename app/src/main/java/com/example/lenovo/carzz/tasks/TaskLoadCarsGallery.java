package com.example.lenovo.carzz.tasks;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.example.lenovo.carzz.callbacks.GalleryLoadedListner;
import com.example.lenovo.carzz.extras.CarsUtils;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.pojo.Gallery;

/**
 * Created by lenovo on 2/22/2016.
 */
public class TaskLoadCarsGallery extends AsyncTask<Void, Void, Gallery> {
    private GalleryLoadedListner galleryLoadedListner;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadCarsGallery(GalleryLoadedListner galleryLoadedListner) {
        this.galleryLoadedListner = galleryLoadedListner;
        volleySingleton = VolleySingleton.getinstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected Gallery doInBackground(Void... params) {
        Gallery gallery = CarsUtils.loadGalleryImages(requestQueue);
        return gallery;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Gallery gallery) {
        if (galleryLoadedListner != null) {
            galleryLoadedListner.onGalleryLoadedlistner(gallery);
        }
    }
}
