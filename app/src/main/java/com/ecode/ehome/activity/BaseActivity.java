package com.ecode.ehome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ecode.ehome.R;
import com.ecode.ehome.component.DaggerHelperComponent;
import com.ecode.ehome.helper.LocaleHelper;
import com.ecode.ehome.helper.NavigationHelper;
import com.ecode.ehome.module.HelperModule;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Bind(R.id.content_toolbar)
    protected Toolbar toolbar;

    @Inject
    protected NavigationHelper navigationHelper;

    @Inject
    protected LocaleHelper localeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_with_toolbar);
        DaggerHelperComponent.builder().helperModule(new HelperModule(this)).build().inject(this);
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
}
