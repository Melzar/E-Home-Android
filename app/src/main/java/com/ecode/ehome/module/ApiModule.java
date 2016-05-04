package com.ecode.ehome.module;

import com.ecode.ehome.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matuszewski on 04/05/16.
 */
@Module
public class ApiModule {

    private static final String API_URL = BuildConfig.API_URL;


    @Provides
    @Singleton
    public Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
