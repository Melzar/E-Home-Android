package com.ecode.ehome.activity.dashboard;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ecode.ehome.R;
import com.ecode.ehome.activity.BaseActivity;
import com.ecode.ehome.activity.BaseDrawerActivity;
import com.ecode.ehome.common.enumeration.DashboardTabEnum;

import butterknife.BindView;

public class DashboardActivity extends BaseDrawerActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.dashboard_tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDrawerContent(R.layout.activity_dashboard);
        prepareTabs();
        setExtendedToolbar(R.layout.dashboard_toolbar_content);
    }

    private void prepareTabs(){
        tabLayout.addTab(tabLayout.newTab().setText(DashboardTabEnum.DAILY.getStringId()));
        tabLayout.addTab(tabLayout.newTab().setText(DashboardTabEnum.WEEKLY.getStringId()));
        tabLayout.addTab(tabLayout.newTab().setText(DashboardTabEnum.MONTHLY.getStringId()));
        tabLayout.addTab(tabLayout.newTab().setText(DashboardTabEnum.QUARTERLY.getStringId()));
        tabLayout.addTab(tabLayout.newTab().setText(DashboardTabEnum.YEARLY.getStringId()));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        DashboardTabEnum tabEnum = DashboardTabEnum.getEnumFromIndex(tab.getPosition());
        //TODO add implementation for fetching data for selected tab
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
