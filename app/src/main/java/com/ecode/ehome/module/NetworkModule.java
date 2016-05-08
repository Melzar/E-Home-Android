package com.ecode.ehome.module;

import com.ecode.ehome.datasource.AccomodationDataSource;
import com.ecode.ehome.datasource.AuthenticationDataSource;
import com.ecode.ehome.datasource.ControlDataSource;
import com.ecode.ehome.datasource.SpaceDataSource;
import com.ecode.ehome.datasource.impl.AccomodationDataSourceImpl;
import com.ecode.ehome.datasource.impl.AuthenticationDataSourceImpl;
import com.ecode.ehome.datasource.impl.ControlDataSourceImpl;
import com.ecode.ehome.datasource.impl.SpaceDataSourceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by matuszewski on 04/05/16.
 */
@Module(includes = ApiModule.class)
public class NetworkModule {

    @Provides
    @Singleton
    public AuthenticationDataSource provideAuthenticationService(Retrofit retrofit){
        return new AuthenticationDataSourceImpl(retrofit);
    }

    @Provides
    @Singleton
    public AccomodationDataSource provideAccomodationService(Retrofit retrofit){
        return new AccomodationDataSourceImpl(retrofit);
    }

    @Provides
    @Singleton
    public SpaceDataSource provideSpaceService(Retrofit retrofit){
        return new SpaceDataSourceImpl(retrofit);
    }

    @Provides
    @Singleton
    public ControlDataSource provideControlService(Retrofit retrofit){
        return new ControlDataSourceImpl(retrofit);
    }
}
