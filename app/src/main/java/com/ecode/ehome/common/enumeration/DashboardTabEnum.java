package com.ecode.ehome.common.enumeration;

import com.ecode.ehome.R;

/**
 * Created by Tomasz Matuszewski on 14/07/15.
 */
public enum DashboardTabEnum {

    DAILY(R.string.dashboard_tab_daily),
    WEEKLY(R.string.dashbaord_tab_weekly),
    MONTHLY(R.string.dashboard_tab_monthly),
    QUARTERLY(R.string.dashboard_tab_quarterly),
    YEARLY(R.string.dashboard_tab_yearly);

    private int stringId;

    DashboardTabEnum(int stringId) {
        this.stringId = stringId;
    }

    public int getStringId() {
        return stringId;
    }

    public static DashboardTabEnum getEnumFromIndex(int enumIndex){
        if(enumIndex < DashboardTabEnum.values().length){
            return DashboardTabEnum.values()[enumIndex];
        }
        return DAILY;
    };

}
