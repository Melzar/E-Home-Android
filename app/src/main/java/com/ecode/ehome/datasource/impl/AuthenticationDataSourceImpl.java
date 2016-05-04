package com.ecode.ehome.datasource.impl;

import com.ecode.ehome.datasource.AuthenticationDataSource;
import com.ecode.ehome.datasource.retrofit.RetrofitAuthenticationDataSource;
import com.ecode.ehome.eventbus.AuthenticationDataSourceEvents;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matuszewski on 04/05/16.
 */
public class AuthenticationDataSourceImpl implements AuthenticationDataSource {

    RetrofitAuthenticationDataSource retrofitAuthenticationDataSource;

    public AuthenticationDataSourceImpl(Retrofit retrofit) {
        retrofitAuthenticationDataSource = retrofit.create(RetrofitAuthenticationDataSource.class);
    }

    @Override
    public void loginUser() {
        retrofitAuthenticationDataSource.loginUser().enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                AuthenticationDataSourceEvents.OnAuthenticationSuccess success =
                        new AuthenticationDataSourceEvents.OnAuthenticationSuccess();
                EventBus.getDefault().post(success);
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                AuthenticationDataSourceEvents.OnAuthenticationError error =
                        new AuthenticationDataSourceEvents.OnAuthenticationError();
                EventBus.getDefault().post(error);
            }
        });
    }
}
