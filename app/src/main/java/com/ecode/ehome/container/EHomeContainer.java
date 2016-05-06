package com.ecode.ehome.container;

import android.app.Application;

import com.ecode.ehome.component.DaggerNetworkComponent;
import com.ecode.ehome.component.NetworkComponent;
import com.ecode.ehome.module.NetworkModule;

/**
 * Created by Tomasz Matuszewski on 12/07/15.
 */
public class EHomeContainer extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        networkComponent = DaggerNetworkComponent
                .builder()
                .networkModule(new NetworkModule())
                .build();

    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
