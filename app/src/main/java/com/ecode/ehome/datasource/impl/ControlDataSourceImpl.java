package com.ecode.ehome.datasource.impl;

import com.ecode.ehome.datasource.ControlDataSource;
import com.ecode.ehome.datasource.retrofit.RetrofitControlDataSource;
import com.ecode.ehome.eventbus.ControlDataSourceEvents.OnGetControlsSuccess;
import com.ecode.ehome.eventbus.ControlDataSourceEvents.OnGetControlsError;
import com.ecode.ehome.model.Control;
import com.ecode.ehome.module.ErrorResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matuszewski on 08/05/16.
 */
public class ControlDataSourceImpl implements ControlDataSource {

    private RetrofitControlDataSource retrofitControlDataSource;

    public ControlDataSourceImpl(Retrofit retrofit) {
        this.retrofitControlDataSource = retrofit.create(RetrofitControlDataSource.class);
    }

    @Override
    public void getControls() {
        retrofitControlDataSource.getControls().enqueue(new Callback<List<Control>>() {
            @Override
            public void onResponse(Call<List<Control>> call, Response<List<Control>> response) {
                if(response.isSuccessful()){
                    OnGetControlsSuccess success = new OnGetControlsSuccess(response.body());
                    EventBus.getDefault().post(success);
                }else {
                    OnGetControlsError error = new OnGetControlsError(new ErrorResponse(
                            response.message(), response.code()
                    ));
                    EventBus.getDefault().post(error);
                }
            }

            @Override
            public void onFailure(Call<List<Control>> call, Throwable t) {
                OnGetControlsError error = new OnGetControlsError(new ErrorResponse(
                        t.getMessage(), t.hashCode()
                ));
                EventBus.getDefault().post(error);
            }
        });
    }
}
