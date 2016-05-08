package com.ecode.ehome.activity.accomodation;

import android.os.Bundle;
import android.widget.ListView;


import com.ecode.ehome.BR;
import com.ecode.ehome.R;
import com.ecode.ehome.activity.BaseDrawerActivity;
import com.ecode.ehome.adapter.MenuAdapter;
import com.ecode.ehome.container.EHomeContainer;
import com.ecode.ehome.datasource.AccomodationDataSource;
import com.ecode.ehome.eventbus.AccomodationDataSourceEvents.OnGetAccomodationsSuccess;
import com.ecode.ehome.eventbus.AccomodationDataSourceEvents.OnGetAccomodationsError;
import com.ecode.ehome.model.Accomodation;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;

public class AccomodationActivity extends BaseDrawerActivity {

    @Inject
    AccomodationDataSource accomodationDataSource;

    @BindView(R.id.accomodation_list)
    ListView accomodationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDrawerContent(R.layout.activity_accomodation);
        ((EHomeContainer) getApplication()).getNetworkComponent().inject(this);
        accomodationDataSource.getAccomodations();
    }


    @Subscribe
    public void onAccomodationsGetSuccess(OnGetAccomodationsSuccess success){
        MenuAdapter<Accomodation> adapter =  new MenuAdapter<>(getLayoutInflater(),
                success.getAccomodations(),
                R.layout.list_view_accomodation_row_item,
                BR.accomodation);
        accomodationsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onAccomodationsGetError(OnGetAccomodationsError error){
        alertHelper.showAlert(error.getErrorResponse().getMessage(), getString(R.string.error));
    }

}
