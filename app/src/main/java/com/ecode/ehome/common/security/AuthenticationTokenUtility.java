package com.ecode.ehome.common.security;

/**
 * Created by matuszewski on 07/05/16.
 */
public class AuthenticationTokenUtility {

    public static String generateAuthenticationTokenHeader(){
        return String.format("Token token=\"%s\", email=\"%s\"",
                SessionUtility.getInstance().getAuthentication().getToken(),
                SessionUtility.getInstance().getAuthentication().getEmail());
    }
}
