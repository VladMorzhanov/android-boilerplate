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

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import com.morzhanov.boilerplate.data.RepositoryProvider;

public abstract class BaseViewModel<N> extends AndroidViewModel {

    private N mNavigator;

    private final RepositoryProvider mRepositoryProvider;

    public BaseViewModel(Application application, RepositoryProvider repositoryProvider) {
        super(application);
        this.mRepositoryProvider = repositoryProvider;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public RepositoryProvider getRepositoryProvider() {
        return mRepositoryProvider;
    }

    public void onDestroyView() {
        mNavigator = null;
    }

    public void onViewAttached() {

    }
}
