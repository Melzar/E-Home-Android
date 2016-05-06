package com.ecode.ehome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.ecode.ehome.R;
import com.ecode.ehome.component.DaggerHelperComponent;
import com.ecode.ehome.component.HelperComponent;
import com.ecode.ehome.helper.AlertHelper;
import com.ecode.ehome.helper.NavigationHelper;
import com.ecode.ehome.module.HelperModule;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.content_toolbar)
    protected Toolbar toolbar;

    protected NavigationHelper navigationHelper;
    protected AlertHelper alertHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_with_toolbar);
        HelperComponent helperComponent = DaggerHelperComponent.builder()
                .helperModule(new HelperModule(this)).build();
        navigationHelper = helperComponent.provideNavigationHelper();
        alertHelper = helperComponent.provideAlertHelper();
    }

    protected void setActivityContent(int actvityLayout){
        setLayoutWithInjections(actvityLayout, R.id.app_content);
    }

    protected void setLayoutWithInjections(int activityLayout, int contentLayout){
        View layout = getLayoutInflater().inflate(activityLayout, null);
        ((ViewGroup)ButterKnife.findById(this,contentLayout)).addView(layout);
        ButterKnife.bind(this);
    }

    protected void hideToolbar(){
        toolbar.setVisibility(View.GONE);
    }

    protected void setExtendedToolbar(int extendedContentLayout){
        toolbar.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.toolbar_extended);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View extendedLayout = getLayoutInflater().inflate(extendedContentLayout,null);
        ViewGroup extendedContainer = ButterKnife.findById(this, R.id.extended_toolbar_content);
        extendedContainer.addView(extendedLayout);
        extendedContainer.setVisibility(View.VISIBLE);

    }

    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
