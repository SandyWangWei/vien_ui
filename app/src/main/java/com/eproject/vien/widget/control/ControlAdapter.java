package com.eproject.vien.widget.control;

import android.view.View;
import android.view.ViewGroup;

public interface ControlAdapter {

    /**
     * 获取对应位置的数据对象
     * @param index 位置索引
     * @return 返回对应位置的数据对象
     */
    Object getItem(int index);

    /**
     * 当前适配中的数据元个数
     * @return 适配器数据个数
     */
    int getCount();

    /**
     * 设置标题的view样式
     * @param position item的位置信息
     * @param parent item的父布局
     */
    View getTitleView(int position, ViewGroup parent, boolean isSelected);

    /**
     * 设置标题下的二级菜单
     * @param position item的位置信息
     * @param parent 二级菜单的父布局
     */
    View getSubView(int position, ViewGroup parent);

}
