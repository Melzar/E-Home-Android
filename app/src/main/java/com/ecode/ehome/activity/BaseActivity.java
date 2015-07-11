package com.ecode.ehome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.ecode.ehome.R;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_with_toolbar);
    }

    protected void setActivityContent(int actvityLayout){
        View layout = getLayoutInflater().inflate(actvityLayout, null);
        ((ViewGroup)ButterKnife.findById(this,R.id.app_content)).addView(layout);
    }
}
