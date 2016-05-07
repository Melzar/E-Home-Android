package com.ecode.ehome.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecode.ehome.BR;
import com.ecode.ehome.R;
import com.ecode.ehome.databinding.AccomodationListViewRowItemBinding;
import com.ecode.ehome.model.Accomodation;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by matuszewski on 07/05/16.
 */
public class AccomodationAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Accomodation> accomodations;

    public AccomodationAdapter(LayoutInflater layoutInflater, List<Accomodation> accomodations) {
        this.layoutInflater = layoutInflater;
        this.accomodations = accomodations;
        }

    @Override
    public int getCount() {
        return accomodations.size();
    }

    @Override
    public Object getItem(int position) {
        return accomodations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AccomodationListViewRowItemBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.accomodation_list_view_row_item,parent,false);
        binding.setAccomodation(accomodations.get(position));
        return binding.getRoot();
    }
}
