package com.eproject.vien.widget.control;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eproject.vien.vienhome.R;

import java.util.ArrayList;

public class ControlMenuAdapter extends ControlAdapterImpl {

    private ArrayList<ControlMenuInfo> mMenuInfoList = new ArrayList<>();

    @Override
    public Object getItem(int index) {
        return null;
    }

    @Override
    public int getCount() {
        return mMenuInfoList.size();
    }

    @Override
    public View getTitleView(int position, ViewGroup parent, boolean isSelected) {

        TextView view = new TextView(parent.getContext());

        view.setText(mMenuInfoList.get(position).title);

        view.setTextSize(40);

        view.setGravity(Gravity.CENTER);

        view.setTextColor(parent.getContext().getResources().getColor(R.color.colorPrimary));

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                parent.getContext().getResources().getDimensionPixelSize(R.dimen.karaoke_control_text_size));
        view.setLayoutParams(params);

        return view;
    }

    @Override
    public View getSubView(int position, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.control_item, parent, false);

        return view;
    }

    public void setData(ArrayList<ControlMenuInfo> menuInfoList){
        this.mMenuInfoList = menuInfoList;
    }
}
