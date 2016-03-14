package com.example.lenovo.carzz.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.eddystone.Eddystone;
import com.example.lenovo.carzz.MyApplication;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.adapters.EddystoneListAdapter;
import com.example.lenovo.carzz.callbacks.CarIdFetchedListener;
import com.example.lenovo.carzz.logging.L;
import com.example.lenovo.carzz.tasks.TaskLoadCarFromBeacon;

import static com.example.lenovo.carzz.extras.Keys.EndpointBoxOffice.KEY_BEACON_CAR_ID;

import java.util.Collections;
import java.util.List;

/**
 * Created by lenovo on 3/13/2016.
 */
public class EddyStoneActivity extends AppCompatActivity implements CarIdFetchedListener {

    private static final String TAG = EddyStoneActivity.class.toString();
    private BeaconManager beaconManager;
    private EddystoneListAdapter adapter;
    ListView eddyStoneListView;
    String carId = "";
    Eddystone selectedEddyStone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_eddystone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i(TAG, "Inside onCreate ");

        adapter = new EddystoneListAdapter(MyApplication.getAppContext());
        eddyStoneListView = (ListView) findViewById(R.id.device_list_eddystone);
        eddyStoneListView.setAdapter(adapter);
        eddyStoneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.m("Inside On Click Listener");
                selectedEddyStone = (Eddystone) eddyStoneListView.getItemAtPosition(position);
                fetchCarIdFromEddyStone(selectedEddyStone);
            }
        });
        beaconManager = new BeaconManager(MyApplication.getAppContext());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void fetchCarIdFromEddyStone(Eddystone selectedEddyStone) {
        new TaskLoadCarFromBeacon(this, selectedEddyStone.namespace, selectedEddyStone.instance).execute();
    }

    @Override
    protected void onDestroy() {
        beaconManager.disconnect();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            startScanning();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        beaconManager.disconnect();
    }

    private void startScanning() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Log.i(TAG, "Inside srart running");
        toolbar.setSubtitle("Scanning...");
        adapter.replaceWith(Collections.<Eddystone>emptyList());
        beaconManager.setEddystoneListener(new BeaconManager.EddystoneListener() {
            @Override
            public void onEddystonesFound(List<Eddystone> eddystones) {
                toolbar.setSubtitle("Found beacons with Eddystone protocol: " + eddystones.size());
                adapter.replaceWith(eddystones);
            }
        });
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startEddystoneScanning();
            }
        });
    }

    @Override
    public void onCarIdFetchedListener(String carId) {
        this.carId = carId;
        Intent carIntent = new Intent(this, CarsActivity.class);
        carIntent.putExtra(KEY_BEACON_CAR_ID, carId);
        startActivity(carIntent);

    }
}
