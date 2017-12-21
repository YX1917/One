package com.young.one.adapter;

import android.content.Context;

import com.young.one.bean.OneListBean;
import com.young.one.utils.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by yangxing on 2017/12/21.
 */

public class OneTypeItem extends MultiItemTypeAdapter<OneListBean.DataBean.ContentListBean> {
    public OneTypeItem(Context context, List<OneListBean.DataBean.ContentListBean> datas) {
        super(context, datas);
        addItemViewDelegate(new PhotoItem());
        addItemViewDelegate(new ReadingItem());
        addItemViewDelegate(new MovisItem());
        addItemViewDelegate(new MusicItem());
    }
}
