package com.ecode.ehome.datasource.retrofit;

import com.ecode.ehome.model.Control;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by matuszewski on 08/05/16.
 */
public interface RetrofitControlDataSource {

    @GET("customers/controls.json")
    Call<List<Control>> getControls();
}
