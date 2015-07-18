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

    @OnClick(R.id.english_language_button)
    public void englishClicked(){
        localeHelper.updateApplicationLocale(R.string.language_english);
    }

    @OnClick(R.id.polish_language_button)
    public void polishClicked(){
        localeHelper.updateApplicationLocale(R.string.language_polish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityContent(R.layout.activity_login);
        hideToolbar();
    }
}
