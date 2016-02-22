package com.example.lenovo.carzz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.lenovo.carzz.MyApplication;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.extras.Constants;
import com.example.lenovo.carzz.logging.L;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.pojo.Image;

import static com.example.lenovo.carzz.extras.Constants.NA;

/**
 * Created by lenovo on 2/22/2016.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolderGallery> {

    private LayoutInflater inflater;
    private Gallery gallery = new Gallery();
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
        notifyDataSetChanged();
    }

    public GalleryAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getinstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
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
    }

    @Override
    public int getItemCount() {
        return gallery.getImageList().size();
    }

    public static class ViewHolderGallery extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolderGallery(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.galleryImage);
        }
    }
}
