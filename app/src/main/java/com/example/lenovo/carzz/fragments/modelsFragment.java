package com.example.lenovo.carzz.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.adapters.ModelAdapter;
import com.example.lenovo.carzz.callbacks.ModelLoadedListner;
import com.example.lenovo.carzz.extras.Constants;
import com.example.lenovo.carzz.extras.Utils;
import com.example.lenovo.carzz.logging.L;
import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.pojo.Models;
import com.example.lenovo.carzz.tasks.TaskLoadCarsGallery;
import com.example.lenovo.carzz.tasks.TaskLoadModelsGallery;

import java.util.ArrayList;
import java.util.List;

import static android.widget.GridView.NO_STRETCH;
import static com.example.lenovo.carzz.extras.Constants.MODEL_NUM_OF_COLUMNS;


public class modelsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ModelLoadedListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_MODEL = "state_model";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView onVolleyError;
    private ArrayList<Models> modelList;
    private GridView modelGridView;
    private ModelAdapter modelAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int columnWidth;

    public modelsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment modelsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static modelsFragment newInstance(String param1, String param2) {
        modelsFragment fragment = new modelsFragment();
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
        View view = inflater.inflate(R.layout.fragment_models, container, false);
        onVolleyError = (TextView) view.findViewById(R.id.textVolleyError);
        modelGridView = (GridView) view.findViewById(R.id.gridModelView);

        InitilizeGridLayout();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeGallery);
        swipeRefreshLayout.setOnRefreshListener(this);
        modelAdapter = new ModelAdapter(getActivity());
        modelGridView.setAdapter(modelAdapter);
        if (savedInstanceState != null) {
            modelList = savedInstanceState.getParcelableArrayList(STATE_MODEL);
        } else {
            new TaskLoadModelsGallery(this).execute();
        }

        if (modelList == null)
            modelList = new ArrayList<Models>();
        modelAdapter.setModelsList(modelList);
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
        outState.putParcelableArrayList(STATE_MODEL,modelList);
        //outState.putParcelable(STATE_MODEL, (Parcelable) modelList);
    }

    @Override
    public void onRefresh() {
        new TaskLoadModelsGallery(this).execute();
    }

    @Override
    public void onModelsLoadedlistner(ArrayList<Models> modelList) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        this.modelList = modelList;
        modelAdapter.setModelsList(this.modelList);
    }

    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                Constants.GRID_PADDING, r.getDisplayMetrics());

        columnWidth = (int) ((Utils.getScreenWidth() - ((Constants.GALLERY_NUM_OF_COLUMNS + 1) * padding)) / Constants.GALLERY_NUM_OF_COLUMNS);
        L.m("Coloumn Width = " + columnWidth);
        columnWidth=500;
        modelGridView.setNumColumns(MODEL_NUM_OF_COLUMNS);
        modelGridView.setColumnWidth(columnWidth);
        modelGridView.setMinimumHeight(600);
        modelGridView.setStretchMode(NO_STRETCH);
        modelGridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        modelGridView.setHorizontalSpacing((int) padding);
        modelGridView.setVerticalSpacing((int) padding);
    }
}
