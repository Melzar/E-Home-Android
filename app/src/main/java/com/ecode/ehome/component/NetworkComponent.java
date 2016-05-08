package com.ecode.ehome.component;

import com.ecode.ehome.activity.BaseActivity;
import com.ecode.ehome.activity.BaseDrawerActivity;
import com.ecode.ehome.activity.accomodation.AccomodationActivity;
import com.ecode.ehome.activity.accomodation.SpaceActivity;
import com.ecode.ehome.activity.login.LoginActivity;
import com.ecode.ehome.datasource.AuthenticationDataSource;
import com.ecode.ehome.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by matuszewski on 05/05/16.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {

    void inject(BaseActivity baseActivity);
    void inject(BaseDrawerActivity baseDrawerActivity);
    void inject(LoginActivity loginActivity);
    void inject(AccomodationActivity accomodationActivity);
    void inject(SpaceActivity spaceActivity);

}
