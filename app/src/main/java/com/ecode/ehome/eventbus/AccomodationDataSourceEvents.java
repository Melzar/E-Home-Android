package com.ecode.ehome.eventbus;

import com.ecode.ehome.model.Accomodation;
import com.ecode.ehome.module.ErrorResponse;

import java.util.List;

/**
 * Created by matuszewski on 07/05/16.
 */
public class AccomodationDataSourceEvents {


    public static class OnGetAccomodationsSuccess{
        private List<Accomodation> accomodations;

        public OnGetAccomodationsSuccess(List<Accomodation> accomodations) {
            this.accomodations = accomodations;
        }

        public List<Accomodation> getAccomodations() {
            return accomodations;
        }
    }

    public static class OnGetAccomodationsError{
        private ErrorResponse errorResponse;

        public OnGetAccomodationsError(ErrorResponse errorResponse) {
            this.errorResponse = errorResponse;
        }

        public ErrorResponse getErrorResponse() {
            return errorResponse;
        }
    }
}
