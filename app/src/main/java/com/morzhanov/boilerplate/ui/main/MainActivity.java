package com.morzhanov.boilerplate.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.morzhanov.boilerplate.App;
import com.morzhanov.boilerplate.BR;
import com.morzhanov.boilerplate.R;
import com.morzhanov.boilerplate.databinding.ActivityMainBinding;
import com.morzhanov.boilerplate.ui.base.BaseActivity;
import com.morzhanov.boilerplate.ui.main.sample.SampleFragment;
import com.morzhanov.boilerplate.ui.main.instruction.origin.InstructionOriginFragment;
import com.morzhanov.boilerplate.ui.main.instruction.straight.InstructionStraightFragment;
import com.morzhanov.boilerplate.ui.main.instruction.training.InstructionTrainingFragment;
import com.morzhanov.boilerplate.ui.main.profile.ProfileFragment;
import com.morzhanov.boilerplate.ui.main.support.faq.FaqFragment;
import com.morzhanov.boilerplate.ui.main.support.info.InfoFragment;
import com.morzhanov.boilerplate.ui.main.support.privacy.PrivacyFragment;
import com.morzhanov.boilerplate.ui.main.support.terms.TermsFragment;
import com.morzhanov.boilerplate.util.DatabaseUtils;
import com.morzhanov.boilerplate.util.NetworkUtils;
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
    App mApp;

    @Inject
    DatabaseUtils mDatabaseUtils;

    @Inject
    NetworkUtils mNetworkUtils;

    @Inject
    MainViewModel mViewModel;

//    @Inject
//    ViewModelProvider.Factory mViewModelFactory;

    private byte mBackExitCounter = 0;

    private DrawerLayout mDrawer;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.setNavigator(this);

        Log.wtf(".", mNetworkUtils.toString());

        mDatabaseUtils.clearRepos();
        mDatabaseUtils.initState();

        mFragmentManager = getSupportFragmentManager();
        mCurrentFragmentClass = SampleFragment.class;
        mDrawer = getViewDataBinding().drawerLayout;

        //// TODO: 10/14/17 YOU NEED TO CREATE SOME KIND OF APP LIFECYCLE
        //// TODO: 10/14/17 WHERE YOU SHOW/HIDE TOOLBAR, OPERATE INSTR. FRAGMENTS AND ETC
        //// TODO: 10/14/17 figure out how to hide/show toolbars on begin
        //// TODO: 10/14/17 add toolbars only when last instruction screen showed
//        hideToolbars();
        replaceFragment(new SampleFragment(), false);

        //// TODO: 10/24/17 this logic must be somewhere else
        mViewModel.initTrainingSystem();
    }

    @Override
    public void changeFragment(Fragment fragment, boolean addToBackStack) {
        MainActivity.this.replaceFragment(fragment, addToBackStack);
    }

    @Override
    public int getBindingVariable() {
        return BR.ViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

//    @Override
//    public MainViewModel getViewModel() {
//        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
//        return mViewModel;
//    }

    @Override
    public MainViewModel getViewModel() {
        return mViewModel;
    }

    public void hideToolbars() {
        getViewDataBinding().topToolbar.setVisibility(View.GONE);
        getViewDataBinding().bottomToolbar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        //// TODO: 10/12/17 refactor this method
        if (mCurrentFragmentClass == SampleFragment.class) {
            if (mBackExitCounter == 1) {
                this.finish();
            } else {
                Toast.makeText(this, "Press back one more time to exit app", Toast.LENGTH_SHORT).show();
                ++mBackExitCounter;
                new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    --mBackExitCounter;
                }).start();
            }
        }

        // if user in profile view that remove edit mode
        if (mCurrentFragmentClass == ProfileFragment.class) {
            ProfileFragment profile = (ProfileFragment) mCurrentFragment;
            if (profile.isInEditMode()) {
                profile.onBackPressed();
                return;
            }
        }

        if (mApp.isUserInstructed()) {
            // if user instructed check is we are in support fragments stack
            if (mCurrentFragmentClass == FaqFragment.class ||
                    mCurrentFragmentClass == InstructionOriginFragment.class ||
                    mCurrentFragmentClass == InstructionStraightFragment.class ||
                    mCurrentFragmentClass == InstructionTrainingFragment.class ||
                    mCurrentFragmentClass == PrivacyFragment.class ||
                    mCurrentFragmentClass == TermsFragment.class ||
                    mCurrentFragmentClass == InfoFragment.class) {
                // if we are in support fragments stack than go to support fragment
                super.onBackPressed();
                return;
            }
        } else {
            super.onBackPressed();
            return;
        }

        mCurrentFragmentClass = SampleFragment.class;
        replaceFragment(new SampleFragment(), true);
    }

    @Override
    public void openDrawer() {
        mDrawer.openDrawer(Gravity.START);
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackstack) {
        FrameLayout container = getViewDataBinding().mainFragmentContainer;
        container.removeAllViewsInLayout();
        mCurrentFragmentClass = fragment.getClass();
        mCurrentFragment = fragment;
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, fragment);
        if (addToBackstack) {
            mTransaction.addToBackStack(null);
        }
        mTransaction.commit();
    }

    public void showToolbars() {
        getViewDataBinding().topToolbar.setVisibility(View.VISIBLE);
        getViewDataBinding().bottomToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    public void toggleDrawer(boolean show) {
        if (show) {
            mDrawer.openDrawer(Gravity.START);
        } else {
            mDrawer.closeDrawer(Gravity.START);
        }
    }
}
