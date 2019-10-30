package com.eproject.vien.widget.control;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ControlAdapterImpl implements ControlAdapter{


    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     */
    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    @Override
    public Object getItem(int index) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public View getTitleView(int position, ViewGroup parent, boolean isSelected) {
        return null;
    }

    @Override
    public View getSubView(int position, ViewGroup parent) {
        return null;
    }
}
