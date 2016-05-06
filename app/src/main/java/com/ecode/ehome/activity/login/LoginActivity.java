package com.ecode.ehome.activity.login;

import android.os.Bundle;
import android.widget.EditText;

import com.ecode.ehome.R;
import com.ecode.ehome.activity.BaseActivity;
import com.ecode.ehome.activity.dashboard.DashboardActivity;
import com.ecode.ehome.container.EHomeContainer;
import com.ecode.ehome.datasource.AuthenticationDataSource;
import com.ecode.ehome.eventbus.AuthenticationDataSourceEvents.OnAuthenticationSuccess;
import com.ecode.ehome.eventbus.AuthenticationDataSourceEvents.OnAuthenticationError;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @Inject
    AuthenticationDataSource authenticationDataSource;

    @BindView(R.id.input_login)
    EditText loginInput;

    @BindView(R.id.input_password)
    EditText passwordInput;

    @OnClick(R.id.button_signin)
    public void onClick(){
        authenticationDataSource.loginUser(
                loginInput.getText().toString(),
                passwordInput.getText().toString()
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityContent(R.layout.activity_login);
        ((EHomeContainer) getApplication()).getNetworkComponent().inject(this);
        hideToolbar();
    }

    @Subscribe
    public void onLoginSuccess(OnAuthenticationSuccess onAuthenticationSuccess){
        navigationHelper.navigateTo(DashboardActivity.class);
    }

    @Subscribe
    public void onLoginError(OnAuthenticationError onAuthenticationError){
        alertHelper.showAlert(onAuthenticationError.getErrorResponse().getMessage(), getString(R.string.error));
    }
}
