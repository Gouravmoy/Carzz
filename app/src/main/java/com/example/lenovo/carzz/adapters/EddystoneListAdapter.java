package com.example.lenovo.carzz.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.estimote.sdk.Utils;
import com.estimote.sdk.eddystone.Eddystone;
import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.logging.L;
import com.example.lenovo.carzz.pojo.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 3/13/2016.
 */
public class EddystoneListAdapter extends BaseAdapter {
    private ArrayList<Eddystone> eddystones;
    private LayoutInflater inflater;

    public EddystoneListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.eddystones = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return eddystones.size();
    }

    @Override
    public Eddystone getItem(int position) {
        return eddystones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflateIfRequired(view, position, parent);
        bind(getItem(position), view);
        return view;
    }

    private void bind(Eddystone eddystone, View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.macTextView.setText(String.format("MAC: %s (%.2fm)", eddystone.macAddress.toStandardString(), Utils.computeAccuracy(eddystone)));
        holder.rssiTextView.setText("RSSI: " + eddystone.rssi);
        holder.eddystoneNamespaceTextView.setText("Namespace: " + (eddystone.namespace == null ? "-" : eddystone.namespace));
        holder.eddystoneInstanceIdTextView.setText("Instance ID: " + (eddystone.instance == null ? "-" : eddystone.instance));
        holder.eddystoneUrlTextView.setText("URL: " + (eddystone.url == null ? "-" : eddystone.url));
        L.m("EddyStone NameSpace - " + (eddystone.namespace == null ? "-" : eddystone.namespace));
        L.m("EddyStone InstanceId - " + (eddystone.instance == null ? "-" : eddystone.instance));
    }

    private View inflateIfRequired(View view, int position, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.eddystone_item, null);
            view.setTag(new ViewHolder(view));
        }
        return view;
    }

    public void replaceWith(List<Eddystone> eddystones) {
        this.eddystones.clear();
        this.eddystones.addAll(eddystones);
        notifyDataSetChanged();
    }
}
