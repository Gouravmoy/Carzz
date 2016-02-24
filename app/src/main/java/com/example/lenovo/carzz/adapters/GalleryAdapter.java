package com.example.lenovo.carzz.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.activites.FullScreenViewActivity;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.pojo.Image;

/**
 * Created by lenovo on 2/22/2016.
 */
public class GalleryAdapter extends BaseAdapter {

    public static String ZOOM_URL="url";
    private LayoutInflater inflater;
    private Gallery gallery = new Gallery();
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context mContext;

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
        notifyDataSetChanged();
    }

    public GalleryAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getinstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    /*@Override
    public ViewHolderGallery onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_gallery, parent, false);
        ViewHolderGallery viewHolderGallery = new ViewHolderGallery(view);
        return viewHolderGallery;
    }

    @Override
    public void onBindViewHolder(ViewHolderGallery holder, int position) {
        Image image = gallery.getImageList().get(position);
        String imageUrl = image.getProfile();
        loadImage(holder, imageUrl);
    }

    private void loadImage(final ViewHolderGallery holder, String imageUrl) {
        if (!imageUrl.equals(NA)) {
            imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.imageView.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    L.t(MyApplication.getAppContext(), "Failed to Load Image");
                }
            });
        }
    }*/

    /*@Override
    public int getItemCount() {
        return gallery.getImageList().size();
    }*/

    @Override
    public int getCount() {
        return gallery.getImageList().size();
        //return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Image image = gallery.getImageList().get(position);
        String profileUrl = image.getThumnail();
        String zoomUrl = image.getProfile();


        NetworkImageView networkImageView = getImage(profileUrl);
        networkImageView.setTag(profileUrl);
        networkImageView.setOnClickListener(new OnImageClickListener(zoomUrl));
        /*networkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (Integer) v.getTag();
                zoomImageFromThumb(arg0, id);
            }
        });*/

        /*TextView textView = new TextView(mContext);
        textView.setText("Gourav");*/

        //linearLayout.addView(textView);
        linearLayout.addView(networkImageView);

        return linearLayout;


        /*if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Image image = gallery.getImageList().get(position);
        String imageUrl = image.getProfile();
        if (imageView != null) {
            imageView.setImageBitmap(loadGridImage(imageUrl, imageView));
            //imageView.setImageResource(mThumbIds[position]);
        }
        return imageView;*/
    }

    class OnImageClickListener implements View.OnClickListener {

        String zoomUrl;
        //Gallery gallery;

        // constructor
        public OnImageClickListener(String url) {
            this.zoomUrl = url;
        }

        @Override
        public void onClick(View v) {
            // on selecting grid view image
            // launch full screen activity
            Intent i = new Intent(mContext, FullScreenViewActivity.class);
            i.putExtra(ZOOM_URL, zoomUrl);
            mContext.startActivity(i);
        }

    }

    @NonNull
    private NetworkImageView getImage(String imageUrl) {
        NetworkImageView networkImageView = new NetworkImageView(mContext);

        imageLoader.get(imageUrl, ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        networkImageView.setImageUrl(imageUrl, imageLoader);

        networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        networkImageView.setLayoutParams(new GridView.LayoutParams(200, 200));
        return networkImageView;
    }

    /*private Bitmap loadGridImage(String imageUrl, ImageView imageView) {
        final Bitmap[] bitmap = new Bitmap[1];
        if (!imageUrl.equals(NA)) {
            imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    L.m("Image Fetched");
                    bitmap[0] = response.getBitmap();
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    L.t(MyApplication.getAppContext(), "Failed to Load Image");
                }
            });
        }
        return bitmap[0];
    }*/

    public Integer[] mThumbIds = {
            R.mipmap.dog,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };


    /*public static class ViewHolderGallery extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolderGallery(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.galleryImage);
        }
    }*/
}
