package com.ecode.ehome.activity.dashboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.ecode.ehome.R;
import com.ecode.ehome.activity.BaseDrawerActivity;
import com.ecode.ehome.common.enumeration.DashboardTabEnum;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class DashboardActivity extends BaseDrawerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDrawerContent(R.layout.activity_dashboard);
    }

    @Subscribe
    public void placeholder(Object o){

    }
}
