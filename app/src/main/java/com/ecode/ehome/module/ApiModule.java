package com.ecode.ehome.module;

import com.ecode.ehome.BuildConfig;
import com.ecode.ehome.common.security.AuthenticationTokenUtility;
import com.ecode.ehome.common.security.SessionUtility;
import com.ecode.ehome.compatibility.JSONAPIConverterFactory;
import com.ecode.ehome.model.Accomodation;
import com.ecode.ehome.model.AccomodationType;
import com.ecode.ehome.model.Authentication;
import com.ecode.ehome.model.Control;
import com.ecode.ehome.model.ControlType;
import com.ecode.ehome.model.Space;
import com.ecode.ehome.model.SpaceType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by matuszewski on 04/05/16.
 */
@Module
public class ApiModule {

    private static final String API_URL = BuildConfig.API_URL;


    @Provides
    @Singleton
    public Retrofit provideRetrofit(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Authentication authentication = SessionUtility.getInstance().getAuthentication();
                if ( SessionUtility.getInstance().getAuthentication() != null ){
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization",
                                    AuthenticationTokenUtility.generateAuthenticationTokenHeader())
                            .method(original.method(), original.body());
                    original = requestBuilder.build();
                }
                return chain.proceed(original);
            }
        });

        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(httpClient.build())
                .addConverterFactory(new JSONAPIConverterFactory(
                        objectMapper,
                        Accomodation.class,
                        AccomodationType.class,
                        Space.class,
                        SpaceType.class,
                        Control.class,
                        ControlType.class))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
