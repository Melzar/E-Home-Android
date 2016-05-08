package com.ecode.ehome.datasource.impl;

import com.ecode.ehome.datasource.SpaceDataSource;
import com.ecode.ehome.datasource.retrofit.RetrofitSpaceDataSource;
import com.ecode.ehome.eventbus.SpaceDataSourceEvents.OnGetSpacesError;
import com.ecode.ehome.eventbus.SpaceDataSourceEvents.OnGetSpacesSuccess;
import com.ecode.ehome.model.Space;
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
public class SpaceDataSourceImpl implements SpaceDataSource {

    private RetrofitSpaceDataSource retrofitSpaceDataSource;

    public SpaceDataSourceImpl(Retrofit retrofit) {
        this.retrofitSpaceDataSource = retrofit.create(RetrofitSpaceDataSource.class);
    }

    @Override
    public void getSpaces() {
        retrofitSpaceDataSource.getSpaces().enqueue(new Callback<List<Space>>() {
            @Override
            public void onResponse(Call<List<Space>> call, Response<List<Space>> response) {
                if(response.isSuccessful()){
                    OnGetSpacesSuccess success = new OnGetSpacesSuccess(response.body());
                    EventBus.getDefault().post(success);
                }else{
                    OnGetSpacesError error = new OnGetSpacesError(new ErrorResponse(
                            response.message(), response.code()
                    ));
                    EventBus.getDefault().post(error);
                }
            }

            @Override
            public void onFailure(Call<List<Space>> call, Throwable t) {
                OnGetSpacesError error = new OnGetSpacesError(new ErrorResponse(
                        t.getMessage(), t.hashCode()
                ));
                EventBus.getDefault().post(error);
            }
        });
    }
}
