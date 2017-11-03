package com.morzhanov.boilerplate.ui.main.sample;

import com.morzhanov.boilerplate.App;
import com.morzhanov.boilerplate.BR;
import com.morzhanov.boilerplate.R;
import com.morzhanov.boilerplate.databinding.FragmentSampleBinding;
import com.morzhanov.boilerplate.ui.base.BaseFragment;
import javax.inject.Inject;

public class SampleFragment extends BaseFragment<FragmentSampleBinding, SampleViewModel>
        implements SampleNavigator {

    @Inject
    App mApp;

    @Inject
    SampleViewModel mViewModel;

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
    public void sampleCall() {

    }
}
