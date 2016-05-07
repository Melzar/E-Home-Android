package com.ecode.ehome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.ecode.ehome.R;
import com.ecode.ehome.activity.accomodation.AccomodationActivity;
import com.ecode.ehome.activity.dashboard.DashboardActivity;
import com.ecode.ehome.activity.login.LoginActivity;
import com.ecode.ehome.common.security.SessionUtility;
import com.ecode.ehome.container.EHomeContainer;
import com.ecode.ehome.datasource.AuthenticationDataSource;
import com.ecode.ehome.eventbus.AuthenticationDataSourceEvents.OnLogoutSuccess;
import com.ecode.ehome.eventbus.AuthenticationDataSourceEvents.OnLogoutError;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

public class BaseDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Nullable
    @BindView(R.id.drawer_navigation)
    NavigationView navigationView;

    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Inject
    AuthenticationDataSource authenticationDataSource;

    private Map<Integer, Class> menuMapper  = new HashMap<Integer, Class>() {{
            put(R.id.menu_dashboard_item, DashboardActivity.class);
            put(R.id.menu_accomodations_item, AccomodationActivity.class);
            put(R.id.menu_spaces_item,DashboardActivity.class);
            put(R.id.menu_controls_item, DashboardActivity.class);
            put(R.id.menu_logout_item, null);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setDrawerContent(int activityLayout){
        setContentView(R.layout.content_with_drawer);
        setLayoutWithInjections(activityLayout, R.id.app_content);
        setDrawerToolbar();
        ((EHomeContainer) getApplication()).getNetworkComponent().inject(this);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setDrawerToolbar(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this,
                        drawerLayout,
                        toolbar,
                        R.string.drawer_open,
                        R.string.drawer_closed);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Subscribe
    public void onLogoutSuccess(OnLogoutSuccess onLogoutSuccess){
        navigationHelper.navigateTo(LoginActivity.class);
    }

    @Subscribe
    public void onLogoutError(OnLogoutError onLogoutError){
        alertHelper.showAlert(onLogoutError.getErrorResponse().getMessage(),
                getString(R.string.error));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_logout_item){
            authenticationDataSource.logoutUser();
            return true;
        }
        navigationHelper.navigateTo(menuMapper.get(item.getItemId()));
        return true;
    }
}
