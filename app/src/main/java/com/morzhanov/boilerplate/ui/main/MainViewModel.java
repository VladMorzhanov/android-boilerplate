package com.morzhanov.boilerplate.ui.main;

import static com.morzhanov.boilerplate.device.MevicsDeviceManager.TRAINING_STATE_DONE;
import static com.morzhanov.boilerplate.device.MevicsDeviceManager.TRAINING_STATE_RUN;
import static com.morzhanov.boilerplate.device.MevicsDeviceManager.TRAINING_STATE_STOP;
import static com.morzhanov.boilerplate.device.MevicsDeviceManager.TRAINING_STATE_UNDONE;

import android.app.Application;
import android.support.v4.app.DialogFragment;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.device.MevicsDeviceManager;
import com.morzhanov.boilerplate.ui.base.BaseViewModel;
import com.morzhanov.boilerplate.ui.main.sample.SampleFragment;
import com.morzhanov.boilerplate.ui.main.mymevics.MyMevicsFragment;
import com.morzhanov.boilerplate.ui.main.progress.ProgressFragment;
import com.morzhanov.boilerplate.ui.main.settings.SettingsFragment;
import com.morzhanov.boilerplate.ui.main.trainingsystem.TrainingDoneDialog;
import com.morzhanov.boilerplate.ui.main.trainingsystem.TrainingUnoneDialog;

/**
 * Exposes the data to be used in the main screen.
 */
public class MainViewModel extends BaseViewModel<MainNavigator> {

    private MevicsDeviceManager mMevicsDeviceManager;

    public MainViewModel(Application application, RepositoryProvider provider, MevicsDeviceManager manager) {
        super(application, provider);
        mMevicsDeviceManager = manager;
    }

    public void dashboardClicked() {
        getNavigator().changeFragment(new SampleFragment(), false);
    }

    public void menuClicked() {
        getNavigator().openDrawer();
    }

    public void myMevicsClicked() {
        getNavigator().changeFragment(new MyMevicsFragment(), false);
    }

    public void settingsClicked() {
        getNavigator().changeFragment(new SettingsFragment(), false);
    }

    public void statsClicked() {
        getNavigator().changeFragment(new ProgressFragment(), false);
    }

    //// TODO: 10/24/17 heavy method
    void initTrainingSystem() {
        mMevicsDeviceManager.addOnTrainingDataListener((state, data) -> {
            switch (state) {
                case TRAINING_STATE_RUN:
                    //// TODO: 10/24/17 show timer
                    break;
                case TRAINING_STATE_DONE:
                    //// TODO: 10/24/17 show dialog and on click start new cycle
                    //// TODO: 10/24/17 compare dates. if date from device after data in db
                    //// TODO: 10/24/17 than add new day and change last day
                    DialogFragment d = TrainingDoneDialog.newInstance(res -> {
                        switch (res) {
                            case TrainingDoneDialog.RESULT_START:
                                mMevicsDeviceManager.startTrainingCycle("1000");
                                break;
                            case TrainingDoneDialog.RESULT_CANCEL:
                                //// TODO: 10/24/17 show message as point in menu
                                break;
                        }
                    });
                    String TAG = "TrainingDoneDialog";
                    getNavigator().showDialogFragment(d, TAG);
                    break;
                case TRAINING_STATE_UNDONE:
                    //// TODO: 10/24/17 show dialog and on click start new cycle
                    d = TrainingUnoneDialog.newInstance(res -> {
                        switch (res) {
                            case TrainingUnoneDialog.RESULT_START:
                                mMevicsDeviceManager.startTrainingCycle("1000");
                                break;
                            case TrainingUnoneDialog.RESULT_CANCEL:
                                //// TODO: 10/24/17 show message as point in menu
                                break;
                        }
                    });
                    TAG = "TrainingUnDoneDialog";
                    getNavigator().showDialogFragment(d, TAG);
                    break;
                case TRAINING_STATE_STOP:
                    //// TODO: 10/24/17 do nothing
                    break;
            }
        });
        mMevicsDeviceManager.getTrainingState();
    }
}
