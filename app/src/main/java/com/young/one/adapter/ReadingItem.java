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
        String tag_title  = "";
        switch (contentListBean.getCategory()) {
            case "1":
                tag_title = "文章";
                break;
            case "2":
                tag_title = "连载";
                break;
            case "3":
                tag_title = "问答";
                break;

        }
        holder.setText(R.id.tv_movie_tag_title, contentListBean.getTag_list().size()>0?contentListBean.getTag_list().get(0).getTitle():tag_title);
        holder.setText(R.id.tv_movie_content_title, contentListBean.getTitle());
        holder.setText(R.id.tv_movie_author, contentListBean.getAuthor().getUser_name());
        holder.setText(R.id.tv_movie_forward, contentListBean.getForward());
        holder.setText(R.id.tv_read_like_num, String.valueOf(contentListBean.getLike_count()));
        holder.setimg(R.id.img_movie_picture,contentListBean.getImg_url());

    }
}
