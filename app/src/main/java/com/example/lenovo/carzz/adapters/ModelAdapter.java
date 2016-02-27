package com.example.lenovo.carzz.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.logging.L;
import com.example.lenovo.carzz.network.VolleyImageLoader;
import com.example.lenovo.carzz.network.VolleySingleton;
import com.example.lenovo.carzz.pojo.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2/27/2016.
 */
public class ModelAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context mContext;
    VolleyImageLoader volleyImageLoader;
    List<Models> modelsList = new ArrayList<Models>();
    RecyclerView descriptionView;
    DescriptionAdapter descriptionAdapter;

    public void setModelsList(List<Models> modelsList) {
        this.modelsList = modelsList;
        notifyDataSetChanged();
    }

    public ModelAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getinstance();
        imageLoader = volleySingleton.getImageLoader();

    }

    @Override
    public int getCount() {
        return modelsList.size();
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

        /*ViewHolder viewHolder;
        if (convertView == null) {
            convertView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.models_adapter, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Models model = modelsList.get(position);
        final DescriptionAdapter descriptionAdapter = new DescriptionAdapter(mContext);
        descriptionAdapter.setDescriptionList(model.getDescription());
        viewHolder.modelname.setText(model.getModel_name());
        viewHolder.cost.setText(Double.toString(model.getCost()));
        volleyImageLoader = new VolleyImageLoader(viewHolder.modelImage);
        volleyImageLoader.getImage(model.getImage().getProfile());
        viewHolder.descriptions.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder.descriptions.setAdapter(descriptionAdapter);

        return convertView;*/


        TextView descripTV;
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        View view = inflater.inflate(R.layout.models_adapter, null);
        ViewHolder viewHolder = new ViewHolder(view);
        if (position == 2) {
            L.m("Debug Point");
        }
        Models model = modelsList.get(position);

        descriptionAdapter = new DescriptionAdapter(mContext);
        descriptionAdapter.setDescriptionList(model.getDescription());

        viewHolder.modelname.setText(model.getModel_name());
        viewHolder.cost.setText(Double.toString(model.getCost()));
        volleyImageLoader = new VolleyImageLoader(viewHolder.modelImage);
        volleyImageLoader.getImage(model.getImage().getProfile());
        linearLayout.addView(view);

        for (String descriprion : model.getDescription()) {
            descripTV = new TextView(mContext);
            descripTV.setText("\u2022 " + descriprion);
            descripTV.setGravity(2);
            descripTV.setPadding(8,8,8,8);
            linearLayout.addView(descripTV);
        }
        return linearLayout;
    }

    public class ViewHolder {
        public ImageView modelImage;
        public TextView modelname;
        public TextView cost;
        //public RecyclerView descriptions;

        public ViewHolder(View view) {
            this.modelImage = (ImageView) view.findViewById(R.id.modelImage);
            this.modelname = (TextView) view.findViewById(R.id.modelName);
            this.cost = (TextView) view.findViewById(R.id.modelCost);
            //this.descriptions = (RecyclerView) view.findViewById(R.id.listDescription);
        }
    }
}
