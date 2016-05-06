package com.ecode.ehome.eventbus;

import com.ecode.ehome.model.Authentication;
import com.ecode.ehome.module.ErrorResponse;

/**
 * Created by matuszewski on 04/05/16.
 */
public class AuthenticationDataSourceEvents {

    public static class OnAuthenticationSuccess {
    }

    public static class OnAuthenticationError {
        ErrorResponse errorResponse;

        public OnAuthenticationError(ErrorResponse errorResponse) {
            this.errorResponse = errorResponse;
        }

        public ErrorResponse getErrorResponse() {
            return errorResponse;
        }
    }
}
