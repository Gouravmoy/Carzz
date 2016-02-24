package com.example.lenovo.carzz.activites;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.volley.toolbox.ImageLoader;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.extras.TouchImageView;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.network.VolleyImageLoader;

import static com.example.lenovo.carzz.adapters.GalleryAdapter.ZOOM_URL;

public class FullScreenViewActivity extends AppCompatActivity {

    TouchImageView imageView;
    String url;
    private Toolbar toolbar;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private VolleyImageLoader volleyImageLoader;

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
            volleyImageLoader = new VolleyImageLoader(imageView);
            volleyImageLoader.getImage(url);
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
