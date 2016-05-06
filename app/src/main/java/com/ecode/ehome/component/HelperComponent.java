package com.ecode.ehome.component;

import android.content.Context;

import com.ecode.ehome.activity.BaseActivity;
import com.ecode.ehome.helper.AlertHelper;
import com.ecode.ehome.helper.NavigationHelper;
import com.ecode.ehome.module.HelperModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Tomasz Matuszewski on 12/07/15.
 */
@Singleton
@Component(modules = HelperModule.class)
public interface HelperComponent {

    NavigationHelper provideNavigationHelper();
    AlertHelper provideAlertHelper();
}
