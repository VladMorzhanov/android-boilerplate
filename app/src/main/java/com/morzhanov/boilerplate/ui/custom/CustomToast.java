package com.morzhanov.boilerplate.ui.custom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Class to create customized Mevics Toast message
 */
public class CustomToast {

    public static Toast makeText(Activity context, String text, int duration) {

        LayoutInflater inflater = context.getLayoutInflater();
        // Inflate the Layout
        //// TODO: 10/6/17 try to use databinding if needed
        View layout = inflater.inflate(R.layout.mevics_toast,
                context.findViewById(R.id.custom_toast_layout));

        //// TODO: 10/6/17 try to use databinding if needed
        TextView textView = layout.findViewById(R.id.textToShow);
        // Set the Text to show in TextView
        textView.setText(text);

        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(layout);

        return toast;
    }
}