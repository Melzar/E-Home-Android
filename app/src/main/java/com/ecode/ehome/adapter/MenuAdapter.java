package com.ecode.ehome.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecode.ehome.BR;
import com.ecode.ehome.R;
import com.ecode.ehome.model.Space;

import java.util.List;

/**
 * Created by matuszewski on 08/05/16.
 */
public class MenuAdapter<T> extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<T> menuItems;
    private int rowLayoutId;
    private int bindingVariableId;

    public MenuAdapter(LayoutInflater layoutInflater, List<T> menuItems,
                       int rowLayoutId, int bindingVariableId) {
        this.layoutInflater = layoutInflater;
        this.menuItems = menuItems;
        this.rowLayoutId = rowLayoutId;
        this.bindingVariableId = bindingVariableId;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater,
                rowLayoutId, parent, false );
        binding.setVariable(bindingVariableId, menuItems.get(position));
        binding.executePendingBindings();
        return binding.getRoot();
    }
}
