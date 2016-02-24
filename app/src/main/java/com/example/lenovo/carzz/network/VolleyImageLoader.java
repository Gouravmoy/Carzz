package com.example.lenovo.carzz.network;

import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.lenovo.carzz.MyApplication;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.logging.L;

import static com.example.lenovo.carzz.extras.Constants.NA;

/**
 * Created by lenovo on 2/25/2016.
 */
public class VolleyImageLoader {

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    ImageView imageView;
    NetworkImageView networkImageView;

    public VolleyImageLoader(ImageView imageView) {
        volleySingleton = VolleySingleton.getinstance();
        imageLoader = volleySingleton.getImageLoader();
        this.imageView = imageView;
        networkImageView = null;
    }

    public VolleyImageLoader(NetworkImageView networkImageView) {
        volleySingleton = VolleySingleton.getinstance();
        imageLoader = volleySingleton.getImageLoader();
        this.networkImageView = networkImageView;
    }

    public void getImage(String url) {
        volleySingleton = VolleySingleton.getinstance();
        imageLoader = volleySingleton.getImageLoader();
        if (!url.equals(NA)) {
            if (networkImageView != null) {
                imageLoader.get(url, ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
                networkImageView.setImageUrl(url, imageLoader);
            } else {
                imageLoader.get(url, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        imageView.setImageBitmap(response.getBitmap());
                        L.m("Zoom Image Fetched!");
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        L.t(MyApplication.getAppContext(), "Failed to Load Image");
                    }
                });
            }
        }
    }
}
