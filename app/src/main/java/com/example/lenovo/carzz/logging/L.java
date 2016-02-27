package com.example.lenovo.carzz.logging;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lenovo on 1/2/2016.
 */
public class L {
    public static void m(String message) {
        Log.d("Carzz ", "" + message);
    }

    public static void t(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
    }
}
