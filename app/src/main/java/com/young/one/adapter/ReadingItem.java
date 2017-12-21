package com.young.one.adapter;

import com.young.one.R;
import com.young.one.bean.OneListBean;
import com.young.one.utils.recyclerview.base.ItemViewDelegate;
import com.young.one.utils.recyclerview.base.ViewHolder;

/**
 * Created by yangxing on 2017/12/21.
 */

public class ReadingItem implements ItemViewDelegate<OneListBean.DataBean.ContentListBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_reading;
    }

    @Override
    public boolean isForViewType(OneListBean.DataBean.ContentListBean item, int position) {
        return item.getCategory().equals("1")
                || item.getCategory().equals("2")
                || item.getCategory().equals("3");
    }

    @Override
    public void convert(ViewHolder holder, OneListBean.DataBean.ContentListBean contentListBean, int position) {
        holder.setText(R.id.tv_read_forward, contentListBean.getForward());

    }
}
