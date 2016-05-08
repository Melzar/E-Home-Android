package com.ecode.ehome.eventbus;

import com.ecode.ehome.model.Control;
import com.ecode.ehome.module.ErrorResponse;

import java.util.List;

/**
 * Created by matuszewski on 08/05/16.
 */
public class ControlDataSourceEvents {

    public static class OnGetControlsSuccess{
        private List<Control> controls;

        public OnGetControlsSuccess(List<Control> controls) {
            this.controls = controls;
        }

        public List<Control> getControls() {
            return controls;
        }
    }

    public static class OnGetControlsError{
        private ErrorResponse errorResponse;

        public OnGetControlsError(ErrorResponse errorResponse) {
            this.errorResponse = errorResponse;
        }

        public ErrorResponse getErrorResponse() {
            return errorResponse;
        }
    }
}
