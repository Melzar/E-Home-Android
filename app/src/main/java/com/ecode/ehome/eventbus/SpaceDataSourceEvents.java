package com.ecode.ehome.eventbus;

import com.ecode.ehome.model.Space;
import com.ecode.ehome.module.ErrorResponse;

import java.util.List;

/**
 * Created by matuszewski on 08/05/16.
 */
public class SpaceDataSourceEvents {

    public static class OnGetSpacesSuccess{
        private List<Space> spaces;

        public OnGetSpacesSuccess(List<Space> spaces) {
            this.spaces = spaces;
        }

        public List<Space> getSpaces() {
            return spaces;
        }
    }

    public static class OnGetSpacesError{
        ErrorResponse errorResponse;

        public OnGetSpacesError(ErrorResponse errorResponse) {
            this.errorResponse = errorResponse;
        }

        public ErrorResponse getErrorResponse() {
            return errorResponse;
        }
    }
}
