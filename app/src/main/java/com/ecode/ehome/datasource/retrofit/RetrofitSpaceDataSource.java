package com.ecode.ehome.datasource.retrofit;

import com.ecode.ehome.model.Space;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by matuszewski on 08/05/16.
 */
public interface RetrofitSpaceDataSource {

    @GET("customers/spaces.json")
    Call<List<Space>> getSpaces();
}
