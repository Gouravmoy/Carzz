package com.example.lenovo.carzz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.lenovo.carzz.MyApplication;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.callbacks.FeaturesLoadedListner;
import com.example.lenovo.carzz.network.VolleyImageLoader;
import com.example.lenovo.carzz.pojo.Catagory;
import com.example.lenovo.carzz.pojo.Features;
import com.example.lenovo.carzz.pojo.SubFeatures;
import com.example.lenovo.carzz.tasks.TaskLoadFeatures;

import java.util.ArrayList;

public class featuresFragment extends Fragment implements FeaturesLoadedListner, SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_FEATURES = "state_features";

    public static final int FIRST_LEVEL_COUNT = 6;
    public static final int SECOND_LEVEL_COUNT = 4;
    public static final int THIRD_LEVEL_COUNT = 20;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView onVolleyError;
    private ExpandableListView expandableListView;
    public Features features = new Features();
    SwipeRefreshLayout swipeRefreshLayout;


    public featuresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment featuresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static featuresFragment newInstance(String param1, String param2) {
        featuresFragment fragment = new featuresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_features, container, false);
        onVolleyError = (TextView) view.findViewById(R.id.textVolleyError);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeGallery);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (savedInstanceState != null) {
            //featuresArrayList = savedInstanceState.getParcelableArrayList(STATE_FEATURES);
        } else {
            new TaskLoadFeatures(this).execute();
        }
        expandableListView = (ExpandableListView) view.findViewById(R.id.mainList);
        expandableListView.setAdapter(new ParentLevel(MyApplication.getAppContext(), features));
        return view;
    }

    private void handelVolleyError(VolleyError error) {
        onVolleyError.setVisibility(View.VISIBLE);
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            onVolleyError.setText(R.string.errorTimeOut);
        } else if (error instanceof AuthFailureError) {
            onVolleyError.setText(R.string.authFailure);
        } else if (error instanceof ServerError) {
            onVolleyError.setText(R.string.serverError);
        } else if (error instanceof NetworkError) {
            onVolleyError.setText(R.string.netConError);
        } else if (error instanceof ParseError) {
            onVolleyError.setText(R.string.parseError);
        }
    }

    @Override
    public void onGalleryLoadedlistner(Features features) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        this.features = features;
        expandableListView.setAdapter(new ParentLevel(MyApplication.getAppContext(), features));
        //to add adapter
    }

    @Override
    public void onRefresh() {
        new TaskLoadFeatures(this).execute();
    }

    public class ParentLevel extends BaseExpandableListAdapter {

        private Context context;
        private Features features;
        //SecondLevelExpandableListView secondLevelELV;

        public ParentLevel(Context context, Features features) {
            this.context = context;
            this.features = features;
            //secondLevelELV = new SecondLevelExpandableListView(context);
        }

        @Override
        public int getGroupCount() {
            return features.getCatagoryArrayList().size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            Catagory catagory = features.getCatagoryArrayList().get(groupPosition);
            return catagory.getSubFeaturesArrayList().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            Catagory catagory = features.getCatagoryArrayList().get(groupPosition);
            return catagory.getSubFeaturesArrayList().get(childPosition);
            //return childPosition;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.features_first_level, null);
                TextView text = (TextView) convertView.findViewById(R.id.eventsListEventRowText);
                Catagory catagory = features.getCatagoryArrayList().get(groupPosition);
                text.setText(catagory.getCatagoryName());
            }
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);
            Catagory catagory = features.getCatagoryArrayList().get(groupPosition);
            SecondLevelAdapter adapter = new SecondLevelAdapter(context, catagory);
            secondLevelELV.setAdapter(adapter);
            secondLevelELV.setGroupIndicator(null);
            secondLevelELV.setSelectedGroup(childPosition);
            return secondLevelELV;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class SecondLevelExpandableListView extends ExpandableListView {

        public SecondLevelExpandableListView(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class SecondLevelAdapter extends BaseExpandableListAdapter {

        private Context context;
        Catagory catagory;

        public SecondLevelAdapter(Context context, Catagory catagory) {
            this.context = context;
            this.catagory = catagory;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.features_second_level, null);
                SubFeatures subFeatures = catagory.getSubFeaturesArrayList().get(groupPosition);
                TextView text = (TextView) convertView.findViewById(R.id.eventsListEventRowText);
                text.setText(subFeatures.getTitle());
            }
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            VolleyImageLoader volleyImageLoader;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.features_third_level, null);
                SubFeatures subFeatures = catagory.getSubFeaturesArrayList().get(childPosition);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.featuresImage);
                volleyImageLoader = new VolleyImageLoader(imageView);
                volleyImageLoader.getImage(subFeatures.getImage().getProfile());
                TextView text = (TextView) convertView.findViewById(R.id.eventsListEventRowText);
                text.setText(subFeatures.getSynopsis());
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

}
