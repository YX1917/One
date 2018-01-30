package com.young.one.adapter;

import com.young.one.R;
import com.young.one.bean.OneListBean;
import com.young.one.utils.recyclerview.base.ItemViewDelegate;
import com.young.one.utils.recyclerview.base.ViewHolder;

/**
 * Created by yangxing on 2017/12/21.
 */

public class PhotoItem implements ItemViewDelegate<OneListBean.DataBean.ContentListBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_photo;
    }

    @Override
    public boolean isForViewType(OneListBean.DataBean.ContentListBean item, int position) {
        return item.getCategory().equals("0");
    }

    @Override
    public void convert(ViewHolder holder, OneListBean.DataBean.ContentListBean contentListBean, int position) {
        holder.setText(R.id.tv_photo_title, contentListBean.getTitle());
        holder.setText(R.id.tv_photo_pic_info, contentListBean.getPic_info());
        holder.setText(R.id.tv_photo_forward, contentListBean.getForward());
        holder.setText(R.id.tv_photo_words_info, contentListBean.getWords_info());
        holder.setText(R.id.tv_photo_like_num, String.valueOf(contentListBean.getLike_count()));
        holder.setImg(R.id.img_photo_picture,contentListBean.getImg_url());


    }
}
