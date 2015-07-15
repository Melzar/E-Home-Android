package com.ecode.ehome.helper;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Tomasz Matuszewski on 12/07/15.
 */
public class NavigationHelper extends ContextWrapper {

    public NavigationHelper(Context base) {
        super(base);
    }

    public void navigateTo(Bundle bundle, Class<?> activityClass){
        Intent intent = new Intent(this, activityClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void navigateTo(Class<?> activityClass){
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
