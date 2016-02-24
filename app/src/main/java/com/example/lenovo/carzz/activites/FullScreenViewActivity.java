package com.example.lenovo.carzz.activites;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.lenovo.carzz.MyApplication;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.adapters.GalleryAdapter;
import com.example.lenovo.carzz.extras.Constants;
import com.example.lenovo.carzz.extras.TouchImageView;
import com.example.lenovo.carzz.extras.Utils;
import com.example.lenovo.carzz.logging.L;
import com.example.lenovo.carzz.network.VolleySingleton;

import static com.example.lenovo.carzz.adapters.GalleryAdapter.ZOOM_URL;
import static com.example.lenovo.carzz.extras.Constants.NA;

public class FullScreenViewActivity extends AppCompatActivity {

    TouchImageView imageView;
    String url;
    private Toolbar toolbar;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_view);
        imageView = (TouchImageView) findViewById(R.id.fullImage);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url = extras.getString(ZOOM_URL);
            getZoomImage(url);
        }

    }

    private void getZoomImage(String url) {
        volleySingleton = VolleySingleton.getinstance();
        imageLoader = volleySingleton.getImageLoader();
        if (!url.equals(NA)) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }


}
