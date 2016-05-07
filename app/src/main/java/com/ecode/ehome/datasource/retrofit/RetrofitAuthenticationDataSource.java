package com.ecode.ehome.datasource.retrofit;

import com.ecode.ehome.model.Authentication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by matuszewski on 04/05/16.
 */
public interface RetrofitAuthenticationDataSource {

    @Multipart
    @POST("users/sign_in")
    Call<Authentication> loginUser(@Part("user[email]") String email,
                                   @Part("user[password]") String password);

    @GET("/users/sign_out")
    Call<Void> logoutUser();
}
