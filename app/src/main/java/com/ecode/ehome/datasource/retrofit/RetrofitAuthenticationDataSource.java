package com.ecode.ehome.datasource.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by matuszewski on 04/05/16.
 */
public interface RetrofitAuthenticationDataSource {

    @POST("user/sign_in")
    Call<List<Object>> loginUser();
}
