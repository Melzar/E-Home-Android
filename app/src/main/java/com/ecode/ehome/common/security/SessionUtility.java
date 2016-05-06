package com.ecode.ehome.common.security;

import com.ecode.ehome.model.Authentication;

/**
 * Created by matuszewski on 06/05/16.
 */
public class SessionUtility {

    private Authentication authentication;

    private static SessionUtility sessionUtility = null;

    private SessionUtility(){

    }

    public static SessionUtility getInstance(){
        if(sessionUtility == null){
            sessionUtility = new SessionUtility();
        }
        return sessionUtility;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public void clearSession(){
        authentication = null;
    }
}

