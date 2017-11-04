package com.morzhanov.boilerplate.ui.main.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.morzhanov.boilerplate.R;
import com.morzhanov.boilerplate.ui.base.BaseDialog;
import com.morzhanov.boilerplate.ui.base.BaseDialogCallback;

public class SampleDialog extends BaseDialog implements View.OnClickListener {

    public static SampleDialog newInstance(BaseDialogCallback callback) {
        Bundle args = new Bundle();
        SampleDialog fragment = new SampleDialog();
        fragment.mCallback = callback;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_sample, container);
        v.findViewById(R.id.btnOk).setOnClickListener(this);
        v.findViewById(R.id.btnCancel).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk:
                mCallback.onResult(BaseDialogCallback.RESULT_POSITIVE);
                break;
            case R.id.btnCancel:
                mCallback.onResult(BaseDialogCallback.RESULT_NEGATIVE);
                break;
        }
        dismiss();
    }
}
