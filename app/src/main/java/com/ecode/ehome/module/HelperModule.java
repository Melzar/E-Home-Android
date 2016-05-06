package com.ecode.ehome.module;

import android.content.Context;

import com.ecode.ehome.helper.AlertHelper;
import com.ecode.ehome.helper.NavigationHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomasz Matuszewski on 12/07/15.
 */

@Module
public class HelperModule {

    private Context context;

    public HelperModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideActivityContext(){
        return context;
    }

    @Provides
    @Singleton
    public NavigationHelper provideNavigationHelper(){
        return new NavigationHelper(context);
    }

    @Provides
    @Singleton
    public AlertHelper provideAlertHelper() { return new AlertHelper(context); }
}
