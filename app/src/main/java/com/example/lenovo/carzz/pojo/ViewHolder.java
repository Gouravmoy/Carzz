package com.example.lenovo.carzz.pojo;

import android.view.View;
import android.widget.TextView;

/**
 * Created by lenovo on 3/14/2016.
 */
public class ViewHolder {
    final public TextView macTextView;
    final public TextView rssiTextView;
    final public TextView eddystoneNamespaceTextView;
    final public TextView eddystoneInstanceIdTextView;
    final public TextView eddystoneUrlTextView;

    public ViewHolder(View view) {
        macTextView = (TextView) view.findViewWithTag("mac");
        rssiTextView = (TextView) view.findViewWithTag("rssi");
        eddystoneNamespaceTextView = (TextView) view.findViewWithTag("eddystone_namespace");
        eddystoneInstanceIdTextView = (TextView) view.findViewWithTag("eddystone_instance_id");
        eddystoneUrlTextView = (TextView) view.findViewWithTag("eddystone_url");
    }
}
