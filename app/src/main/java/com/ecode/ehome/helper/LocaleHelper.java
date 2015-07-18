package com.ecode.ehome.helper;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;

import com.ecode.ehome.activity.BaseActivity;

import java.util.Locale;

/**
 * Created by Tomasz Matuszewski on 18/07/15.
 */
public class LocaleHelper extends ContextWrapper {

    public LocaleHelper(Context base) {
        super(base);
    }

    public void updateApplicationLocale(int locale){
        Locale language = new Locale(getString(locale));
        language.setDefault(language);
        Configuration configuration = new Configuration();
        configuration.locale = language;
        getResources().updateConfiguration(configuration, null);
        ((BaseActivity)getBaseContext()).recreate();
    }
}
