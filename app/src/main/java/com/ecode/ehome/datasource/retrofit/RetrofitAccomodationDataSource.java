package com.ecode.ehome.datasource.retrofit;

import com.ecode.ehome.model.Accomodation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by matuszewski on 07/05/16.
 */
public interface RetrofitAccomodationDataSource {

    @GET("customers/accomodations.json")
    Call<List<Accomodation>> getAccomodations();
}
