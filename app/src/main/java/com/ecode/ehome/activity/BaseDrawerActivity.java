package com.ecode.ehome.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ecode.ehome.R;
import com.ecode.ehome.common.enumeration.NavigationMenuEnum;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Nullable
    @Bind(R.id.drawer_navigation)
    NavigationView navigationView;

    @Nullable
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setDrawerContent(int activityLayout){
        setContentView(R.layout.content_with_drawer);
        setLayoutWithInjections(activityLayout, R.id.app_content);
        setDrawerToolbar();
        highlightMenuItem();
    }

    private void setDrawerToolbar(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        drawerLayout.closeDrawers();
        menuItem.setChecked(true);
        navigateToMenuItem(NavigationMenuEnum.getActivityByMenuId(menuItem.getItemId()));
        return true;
    }

    private void navigateToMenuItem(NavigationMenuEnum menu){
        navigationHelper.navigateTo(menu.getActivityClass());
    }

    private void highlightMenuItem(){
        MenuItem menuItem = navigationView.getMenu().findItem(NavigationMenuEnum.getMenuIdFromActivityClass(this.getClass()).getMenuItemId());
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
    }
}
