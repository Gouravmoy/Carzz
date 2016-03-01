package com.example.lenovo.carzz.fragments;

import android.content.Context;
import android.net.Uri;
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
import com.example.lenovo.carzz.callbacks.SpecsLoadedListner;
import com.example.lenovo.carzz.network.VolleyImageLoader;
import com.example.lenovo.carzz.pojo.Catagory;
import com.example.lenovo.carzz.pojo.Features;
import com.example.lenovo.carzz.pojo.SpecCatagory;
import com.example.lenovo.carzz.pojo.Specs;
import com.example.lenovo.carzz.tasks.TaskLoadFeatures;
import com.example.lenovo.carzz.tasks.TaskLoadSpecs;

public class specsFragment extends Fragment implements SpecsLoadedListner, SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_SPEC = "state_specs";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private TextView onVolleyError;
    private ExpandableListView expandableListView;
    public Specs specs = new Specs();
    SwipeRefreshLayout swipeRefreshLayout;
    VolleyImageLoader volleyImageLoader;
    ImageView specImageView;


    public specsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment specsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static specsFragment newInstance(String param1, String param2) {
        specsFragment fragment = new specsFragment();
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
        View view = inflater.inflate(R.layout.fragment_specs, container, false);
        specImageView = (ImageView) view.findViewById(R.id.specImage);

        onVolleyError = (TextView) view.findViewById(R.id.textVolleyError);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeSpecs);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (savedInstanceState != null) {
            specs = savedInstanceState.getParcelable(STATE_SPEC);
            volleyImageLoader = new VolleyImageLoader(specImageView);
            volleyImageLoader.getImage(specs.getImage().getProfile());
        } else {
            new TaskLoadSpecs(this).execute();
        }
        expandableListView = (ExpandableListView) view.findViewById(R.id.mainList);
        expandableListView.setAdapter(new ParentLevel(MyApplication.getAppContext(), specs));
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_SPEC, specs);
    }

    @Override
    public void onRefresh() {
        new TaskLoadSpecs(this).execute();

    }

    @Override
    public void onSpecLoadedlistner(Specs specs) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        this.specs = specs;
        expandableListView.setAdapter(new ParentLevel(MyApplication.getAppContext(), specs));
        volleyImageLoader = new VolleyImageLoader(specImageView);
        volleyImageLoader.getImage(specs.getImage().getProfile());
    }

    public class ParentLevel extends BaseExpandableListAdapter {

        private Context context;
        private Specs specs;

        public ParentLevel(Context context, Specs specs) {
            this.context = context;
            this.specs = specs;
        }

        @Override
        public int getGroupCount() {
            return specs.getSpecCatagoryList().size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            SpecCatagory specCatagory = specs.getSpecCatagoryList().get(groupPosition);
            return specCatagory.getSpecDetailsList().size();
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
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.specs_first_level, null);
                TextView text = (TextView) convertView.findViewById(R.id.specCatagory);
                SpecCatagory specCatagory = specs.getSpecCatagoryList().get(groupPosition);
                text.setText(specCatagory.getCatagoryName());
            }
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.specs_second_level, null);
                TextView desc = (TextView) convertView.findViewById(R.id.specDesc);
                TextView specname = (TextView) convertView.findViewById(R.id.specName);
                //TextView hint = (TextView) convertView.findViewById(R.id.specCatagory);
                SpecCatagory specCatagory = specs.getSpecCatagoryList().get(groupPosition);
                desc.setText(specCatagory.getSpecDetailsList().get(childPosition).getDescription());
                specname.setText(specCatagory.getSpecDetailsList().get(childPosition).getSpecName());
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
