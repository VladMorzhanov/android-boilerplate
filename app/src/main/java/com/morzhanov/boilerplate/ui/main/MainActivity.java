package com.morzhanov.boilerplate.ui.main;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import com.morzhanov.boilerplate.BR;
import com.morzhanov.boilerplate.R;
import com.morzhanov.boilerplate.databinding.ActivityMainBinding;
import com.morzhanov.boilerplate.ui.base.BaseActivity;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

/**
 * Activity class for main screen that uses as container screen for main screens fragments
 */

//// TODO: 10/28/17 mainactivity may not work because it must extend FragmentActivity
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements MainNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    MainViewModel mViewModel;

    @Override
    public int getBindingVariable() {
        return BR.ViewModel;
    }

    @Override
    public FrameLayout getFragmentContainer() {
        return getViewDataBinding().mainFragmentContainer;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void sampleCall() {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
