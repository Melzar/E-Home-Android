package com.ecode.ehome.activity.accomodation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ecode.ehome.BR;
import com.ecode.ehome.R;
import com.ecode.ehome.activity.BaseDrawerActivity;
import com.ecode.ehome.adapter.MenuAdapter;
import com.ecode.ehome.container.EHomeContainer;
import com.ecode.ehome.datasource.ControlDataSource;
import com.ecode.ehome.eventbus.ControlDataSourceEvents.OnGetControlsError;
import com.ecode.ehome.eventbus.ControlDataSourceEvents.OnGetControlsSuccess;
import com.ecode.ehome.model.Control;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;

public class ControlActivity extends BaseDrawerActivity {

    @Inject
    ControlDataSource controlDataSource;

    @BindView(R.id.control_list)
    ListView controlsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDrawerContent(R.layout.activity_control);
        ((EHomeContainer) getApplication()).getNetworkComponent().inject(this);
        controlDataSource.getControls();
    }

    @Subscribe
    public void onGetControlsSuccess(OnGetControlsSuccess success){
        MenuAdapter<Control> adapter = new MenuAdapter<>(getLayoutInflater(),
                success.getControls(),
                R.layout.list_view_control_row_item,
                BR.space);
        controlsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onGetControlsError(OnGetControlsError error){
        alertHelper.showAlert(error.getErrorResponse().getMessage(), getString(R.string.error));
    }
}
