package com.ecode.ehome.datasource;

/**
 * Created by matuszewski on 04/05/16.
 */
public interface AuthenticationDataSource {

    void loginUser(String email, String password);
}
