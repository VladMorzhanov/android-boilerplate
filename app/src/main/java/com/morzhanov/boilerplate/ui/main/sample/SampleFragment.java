package com.morzhanov.boilerplate.ui.main.sample;

import android.os.Bundle;
import android.view.View;
import com.morzhanov.boilerplate.App;
import com.morzhanov.boilerplate.BR;
import com.morzhanov.boilerplate.R;
import com.morzhanov.boilerplate.databinding.FragmentDashboardBinding;
import com.morzhanov.boilerplate.ui.base.BaseFragment;
import javax.inject.Inject;

public class SampleFragment extends BaseFragment<FragmentDashboardBinding, SampleViewModel>
        implements SampleNavigator {

    @Inject
    App mApp;

    @Inject
    SampleViewModel mViewModel;

    private SpineView mSpineView;

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApp.setUserInstructed(true);
        mSpineView = view.findViewById(R.id.spineView);
        mSpineView.setOnClickListener(mViewModel::onSpineClicked);
    }

    @Override
    public int getBindingVariable() {
        return BR.ViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public SampleViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void redrawSpine(boolean state) {
        mSpineView.redrawSpine(state);
    }
}
