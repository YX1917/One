package com.young.one.adapter;

import com.young.one.R;
import com.young.one.bean.OneListBean;
import com.young.one.utils.recyclerview.base.ItemViewDelegate;
import com.young.one.utils.recyclerview.base.ViewHolder;

/**
 * Created by yangxing on 2017/12/21.
 */

public class MovieItem implements ItemViewDelegate<OneListBean.DataBean.ContentListBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_movie;
    }

    @Override
    public boolean isForViewType(OneListBean.DataBean.ContentListBean item, int position) {
        return item.getCategory().equals("5");
    }

    @Override
    public void convert(ViewHolder holder, OneListBean.DataBean.ContentListBean contentListBean, int position) {
        holder.setText(R.id.tv_movie_forward, contentListBean.getForward());
        holder.setText(R.id.tv_movie_content_title, contentListBean.getTitle());
        holder.setText(R.id.tv_movie_film_name, contentListBean.getSubtitle().split(":")[1]);
        holder.setText(R.id.tv_movie_author, contentListBean.getShare_list().getWx().getDesc().split(" ")[0]);
        holder.setText(R.id.tv_movie_like_num, String.valueOf(contentListBean.getLike_count()));
        holder.setimg(R.id.img_movie_picture,contentListBean.getImg_url());



    }
}
