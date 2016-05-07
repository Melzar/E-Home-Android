package com.ecode.ehome.datasource.impl;

import com.ecode.ehome.datasource.AuthenticationDataSource;
import com.ecode.ehome.datasource.retrofit.RetrofitAuthenticationDataSource;
import com.ecode.ehome.eventbus.AuthenticationDataSourceEvents;
import com.ecode.ehome.model.Authentication;
import com.ecode.ehome.common.security.SessionUtility;
import com.ecode.ehome.module.ErrorResponse;

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
    public void loginUser(String email, String password) {
        retrofitAuthenticationDataSource.loginUser(email, password).enqueue(new Callback<Authentication>() {
            @Override
            public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                if(response.isSuccessful()){
                    Authentication authentication = response.body();
                    SessionUtility.getInstance().setAuthentication(authentication);
                    AuthenticationDataSourceEvents.OnAuthenticationSuccess success =
                            new AuthenticationDataSourceEvents.OnAuthenticationSuccess();
                    EventBus.getDefault().post(success);
                }else{
                    AuthenticationDataSourceEvents.OnAuthenticationError error =
                            new AuthenticationDataSourceEvents.OnAuthenticationError(
                                    new ErrorResponse(response.message(), response.code()));
                    EventBus.getDefault().post(error);
                }
            }

            @Override
            public void onFailure(Call<Authentication> call, Throwable t) {
                AuthenticationDataSourceEvents.OnAuthenticationError error =
                        new AuthenticationDataSourceEvents.OnAuthenticationError(new ErrorResponse(
                                t.getMessage(),t.hashCode())
                        );
                EventBus.getDefault().post(error);
            }
        });
    }

    @Override
    public void logoutUser() {
        retrofitAuthenticationDataSource.logoutUser().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    AuthenticationDataSourceEvents.OnLogoutSuccess success =
                            new AuthenticationDataSourceEvents.OnLogoutSuccess();
                    SessionUtility.getInstance().clearSession();
                    EventBus.getDefault().post(success);
                }else{
                    AuthenticationDataSourceEvents.OnLogoutError error =
                            new AuthenticationDataSourceEvents.OnLogoutError( new ErrorResponse(
                                    response.message(), response.code()
                            ));
                    EventBus.getDefault().post(error);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                AuthenticationDataSourceEvents.OnLogoutError error =
                        new AuthenticationDataSourceEvents.OnLogoutError( new ErrorResponse(
                                t.getMessage(), t.hashCode()
                        ));
                EventBus.getDefault().post(error);
            }
        });
    }
}
