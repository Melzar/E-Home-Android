package com.ecode.ehome.activity.login;

import android.os.Bundle;

import com.ecode.ehome.R;
import com.ecode.ehome.activity.BaseActivity;
import com.ecode.ehome.activity.dashboard.DashboardActivity;

import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @OnClick(R.id.button_signin)
    public void onClick(){
        navigationHelper.navigateTo(DashboardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityContent(R.layout.activity_login);
        hideToolbar();
    }
}
