package com.example.lenovo.carzz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.lenovo.carzz.adapters.GalleryAdapter;
import com.example.lenovo.carzz.callbacks.GalleryLoadedListner;
import com.example.lenovo.carzz.pojo.Gallery;
import com.example.lenovo.carzz.tasks.TaskLoadCarsGallery;

public class galleryFragment extends Fragment implements GalleryLoadedListner, SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_GALLERY = "state_gallery";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView onVolleyError;
    private Gallery gallery;
    //private RecyclerView galleryList;
    private GridView galleryGridView;
    private GalleryAdapter galleryAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    public galleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment galleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static galleryFragment newInstance(String param1, String param2) {
        galleryFragment fragment = new galleryFragment();
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
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        onVolleyError = (TextView) view.findViewById(R.id.textVolleyError);
        //galleryList = (RecyclerView) view.findViewById(R.id.listGalleryImages);
        galleryGridView = (GridView) view.findViewById(R.id.gridview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeGallery);
        swipeRefreshLayout.setOnRefreshListener(this);
        //galleryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        galleryAdapter = new GalleryAdapter(getActivity());
        //galleryList.setAdapter(galleryAdapter);
        galleryGridView.setAdapter(galleryAdapter);
        if (savedInstanceState != null) {
            gallery = savedInstanceState.getParcelable(STATE_GALLERY);
        } else {
            new TaskLoadCarsGallery(this).execute();
        }
        if (gallery == null)
            gallery = new Gallery();
        galleryAdapter.setGallery(gallery);
        /*galleryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MyApplication.getAppContext(), SingleViewActivity.class);
                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });*/
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_GALLERY, gallery);
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
    public void onRefresh() {
        new TaskLoadCarsGallery(this).execute();
    }

    @Override
    public void onGalleryLoadedlistner(Gallery gallery) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        this.gallery = new Gallery();
        this.gallery.setId(gallery.getId());
        this.gallery.setCarName(gallery.getCarName());
        this.gallery.setImageList(gallery.getImageList());
        galleryAdapter.setGallery(this.gallery);
    }
}
