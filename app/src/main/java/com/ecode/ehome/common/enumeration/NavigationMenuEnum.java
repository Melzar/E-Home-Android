package com.ecode.ehome.common.enumeration;

import com.ecode.ehome.R;
import com.ecode.ehome.activity.dashboard.DashboardActivity;
import com.ecode.ehome.activity.login.LoginActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomasz Matuszewski on 18/07/15.
 */
public enum NavigationMenuEnum {

    DASHBOARD(R.id.menu_dashboard_item, DashboardActivity.class),
    LOGOUT(R.id.menu_logout_item, LoginActivity.class);

    private int menuIndex;
    private Class<?> activityClass;

    private static Map<Class<?>, NavigationMenuEnum> valueKeyMapping = new HashMap<Class<?>, NavigationMenuEnum>(){{
        put(DashboardActivity.class,DASHBOARD);
        put(LoginActivity.class,LOGOUT);
    }};

    private static Map<Integer, NavigationMenuEnum> keyValueMapping = new HashMap<Integer, NavigationMenuEnum>(){{
        put(R.id.menu_dashboard_item, DASHBOARD);
        put(R.id.menu_logout_item,LOGOUT);
    }};

    NavigationMenuEnum(int menuItemId, Class<?> activityClass) {
        this.menuIndex = menuItemId;
        this.activityClass = activityClass;
    }

    public static NavigationMenuEnum getActivityByMenuIndex(int index){
        if(index < NavigationMenuEnum.values().length){
            return NavigationMenuEnum.values()[index];
        }
        return DASHBOARD;
    }

    public static NavigationMenuEnum getActivityByMenuId(int id){
        return keyValueMapping.get(id);
    }

    public static NavigationMenuEnum getMenuIdFromActivityClass(Class<?> activity){
      return valueKeyMapping.get(activity);
    };

    public int getMenuItemId() {
        return menuIndex;
    }

    public Class<?> getActivityClass() {
        return activityClass;
    }
}
