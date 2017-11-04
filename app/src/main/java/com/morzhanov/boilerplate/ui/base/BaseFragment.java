/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.morzhanov.boilerplate.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment
        implements BaseNavigator {

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    private BaseActivity mActivity;

    private View mRootView;

    private T mViewDataBinding;

    private V mViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        ((BaseActivity) getActivity()).setCurrentFragmentClass(this.getClass());
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getViewModel();
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.setNavigator(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.setNavigator(null);
    }

    @Override
    public void onDestroyView() {
        mViewModel.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return getBaseActivity().hasPermission(permission);
    }

    @Override
    public void hideKeyboard() {
        getBaseActivity().hideKeyboard();
    }

    @Override
    public void invokeIntent(@Nullable Intent intent, @Nullable final Bundle b, @Nullable final Class<?> c,
            final boolean forResult, final int requestCode) {
        getBaseActivity().invokeIntent(intent, b, c, forResult, requestCode);
    }

    @Override
    public void replaceFragment(final Fragment fragment, final boolean addToBackStack) {
        getBaseActivity().replaceFragment(fragment, addToBackStack);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        getBaseActivity().requestPermissionsSafely(permissions, requestCode);
    }

    @Override
    public void showDialogFragment(@Nullable final DialogFragment fragment, @Nullable final String tag) {
        getBaseActivity().showDialogFragment(fragment, tag);
    }

    @Override
    public void showToast(final String message) {
        getBaseActivity().showToast(message);
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }
}
