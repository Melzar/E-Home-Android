package com.ecode.ehome.activity.accomodation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ecode.ehome.BR;
import com.ecode.ehome.R;
import com.ecode.ehome.activity.BaseDrawerActivity;
import com.ecode.ehome.adapter.MenuAdapter;
import com.ecode.ehome.container.EHomeContainer;
import com.ecode.ehome.datasource.SpaceDataSource;
import com.ecode.ehome.eventbus.SpaceDataSourceEvents.OnGetSpacesError;
import com.ecode.ehome.eventbus.SpaceDataSourceEvents.OnGetSpacesSuccess;
import com.ecode.ehome.model.Space;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SpaceActivity extends BaseDrawerActivity {

    @Inject
    SpaceDataSource spaceDataSource;

    @BindView(R.id.space_list)
    ListView spacesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDrawerContent(R.layout.activity_space);
        ((EHomeContainer) getApplication()).getNetworkComponent().inject(this);
        spaceDataSource.getSpaces();
    }

    @Subscribe
    public void onGetSpacesSuccess(OnGetSpacesSuccess success){
        MenuAdapter<Space> spaces = new MenuAdapter<>(
                getLayoutInflater(),
                success.getSpaces(),
                R.layout.list_view_space_row_item,
                BR.space);
        spacesList.setAdapter(spaces);
        spaces.notifyDataSetChanged();
    }

    @Subscribe
    public void onGetSpacesError(OnGetSpacesError error){
        alertHelper.showAlert(error.getErrorResponse().getMessage(), getString(R.string.error));
    }
}
