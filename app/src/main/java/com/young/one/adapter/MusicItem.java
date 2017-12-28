package com.young.one.adapter;

import com.young.one.R;
import com.young.one.bean.OneListBean;
import com.young.one.utils.recyclerview.base.ItemViewDelegate;
import com.young.one.utils.recyclerview.base.ViewHolder;

/**
 * Created by yangxing on 2017/12/21.
 */

public class MusicItem implements ItemViewDelegate<OneListBean.DataBean.ContentListBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_music;
    }

    @Override
    public boolean isForViewType(OneListBean.DataBean.ContentListBean item, int position) {
        return item.getCategory().equals("4");
    }

    @Override
    public void convert(ViewHolder holder, OneListBean.DataBean.ContentListBean contentListBean, int position) {
        holder.setText(R.id.tv_music_forward, contentListBean.getForward());
        holder.setText(R.id.tv_music_content_title, contentListBean.getTitle());
        holder.setText(R.id.tv_music_singer, contentListBean.getShare_info().getTitle().split(": ")[1] + "|" + contentListBean.getSubtitle().split(":")[1]);
        holder.setText(R.id.tv_music_author, contentListBean.getShare_list().getWx().getDesc().split(" ")[0]);
        holder.setText(R.id.tv_music_like_num, String.valueOf(contentListBean.getLike_count()));
        holder.setimg(R.id.img_music_picture,contentListBean.getImg_url());


    }
}
