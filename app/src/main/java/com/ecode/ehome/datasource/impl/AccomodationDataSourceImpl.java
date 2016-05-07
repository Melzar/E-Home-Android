package com.ecode.ehome.datasource.impl;

import com.ecode.ehome.datasource.AccomodationDataSource;
import com.ecode.ehome.datasource.retrofit.RetrofitAccomodationDataSource;
import com.ecode.ehome.eventbus.AccomodationDataSourceEvents.OnGetAccomodationsSuccess;
import com.ecode.ehome.eventbus.AccomodationDataSourceEvents.OnGetAccomodationsError;
import com.ecode.ehome.model.Accomodation;
import com.ecode.ehome.module.ErrorResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by matuszewski on 07/05/16.
 */
public class AccomodationDataSourceImpl implements AccomodationDataSource {

    RetrofitAccomodationDataSource retrofitAccomodationDataSource;

    public AccomodationDataSourceImpl(Retrofit retrofit) {
        this.retrofitAccomodationDataSource = retrofit.create(RetrofitAccomodationDataSource.class);
    }

    @Override
    public void getAccomodations() {

        retrofitAccomodationDataSource.getAccomodations().enqueue(new Callback<List<Accomodation>>() {
            @Override
            public void onResponse(Call<List<Accomodation>> call, Response<List<Accomodation>> response) {
                if (response.isSuccessful()) {
                    OnGetAccomodationsSuccess success =
                            new OnGetAccomodationsSuccess(response.body());
                    EventBus.getDefault().post(success);
                } else {
                    OnGetAccomodationsError error = new OnGetAccomodationsError(new ErrorResponse(
                            response.message(), response.code()
                    ));
                    EventBus.getDefault().post(error);
                }
            }

            @Override
            public void onFailure(Call<List<Accomodation>> call, Throwable t) {
                OnGetAccomodationsError error = new OnGetAccomodationsError(new ErrorResponse(
                        t.getMessage(), t.hashCode()
                ));
                EventBus.getDefault().post(error);
            }
        });

    }
}
