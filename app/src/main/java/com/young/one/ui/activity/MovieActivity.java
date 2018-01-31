package com.young.one.ui.activity;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wenld.wenldbanner.DefaultPageIndicator;
import com.wenld.wenldbanner.WenldBanner;
import com.wenld.wenldbanner.helper.Holder;
import com.young.one.R;
import com.young.one.base.BaseActivity;
import com.young.one.bean.MovieBean;
import com.young.one.bean.MovieComment;
import com.young.one.bean.MovieDetailBean;
import com.young.one.presenter.MovieContentPresenter;
import com.young.one.utilcode.util.LogUtils;
import com.young.one.utilcode.util.ToastUtils;
import com.young.one.utils.recyclerview.CommonAdapter;
import com.young.one.utils.recyclerview.base.ViewHolder;
import com.young.one.utils.recyclerview.wrapper.LoadMoreWrapper;
import com.young.one.view.MovieContentView;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangxing on 2018/1/31.
 */

public class MovieActivity extends BaseActivity implements MovieContentView{
    @BindView(R.id.essay_hp_title)
    TextView mEssayHpTitle;
    @BindView(R.id.essay_hp_author)
    TextView mEssayHpAuthor;
    @BindView(R.id.tv_author_introduce)
    TextView mTvAuthorIntroduce;
    @BindView(R.id.img_author_photo)
    ImageView mImgAuthorPhoto;
    @BindView(R.id.tv_author_user_name)
    TextView mTvAuthorUserName;
    @BindView(R.id.tv_author_summary)
    TextView mTvAuthorSummary;
    @BindView(R.id.author)
    ConstraintLayout mAuthor;
    @BindView(R.id.content_read_top)
    ConstraintLayout mContentReadTop;
    @BindView(R.id.content_bottom)
    ConstraintLayout mContentBottom;
    @BindView(R.id.scroll_content)
    NestedScrollView mScrollContent;
    @BindView(R.id.tv_movie_content)
    TextView mEssayWv;
    @BindView(R.id.recycle_comment)
    RecyclerView mRecycleComment;
    @BindView(R.id.bottom_like_num)
    TextView mBottomLikeNum;
    @BindView(R.id.bottom_comment_num)
    TextView mBottomCommentNum;
    @BindView(R.id.img_loading)
    ImageView mImgLoading;
    @BindView(R.id.commonBanner)
    WenldBanner mWenldBanner;
    private MovieContentPresenter mMovieContentPresenter;
    private List<MovieComment.DataBeanX.DataBean> mDataBeanXes = new ArrayList<>();
    private CommonAdapter<MovieComment.DataBeanX.DataBean> mDataBeanXCommonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    private AnimationDrawable animationDrawable;
    private Holder<String> mDetailBeanHolder;
    private List<String> mImgUrlList = new ArrayList<>();

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_moive;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
        initBanner();
        mMovieContentPresenter = new MovieContentPresenter();
        mMovieContentPresenter.attachView(this);
        mMovieContentPresenter.getMovieContent(getIntent().getStringExtra(getString(R.string.Content_Id)));
        mMovieContentPresenter.getMovieComment(getIntent().getStringExtra(getString(R.string.Content_Id)), "0");
        mMovieContentPresenter.getMovieDetail(getIntent().getStringExtra(getString(R.string.Content_Id)));

    }

    private void initBanner() {
        mDetailBeanHolder = new Holder<String>() {
            @Override
            public com.wenld.wenldbanner.helper.ViewHolder createView(Context context, ViewGroup parent, int position, int viewType) {
                return com.wenld.wenldbanner.helper.ViewHolder.createViewHolder(context, parent, R.layout.item_moive_banner,getViewType(position));
            }

            @Override
            public void UpdateUI(Context context, com.wenld.wenldbanner.helper.ViewHolder viewHolder, int position, String data) {
                ImageView view = viewHolder.getView(R.id.imageView);
                Glide.with(context).load(data).error(R.drawable.loading_error).placeholder(R.drawable.loading).into(view);

            }

            @Override
            public int getViewType(int position) {
                return 0;
            }
        };




    }

    private void initView() {
        mScrollContent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    if (mDataBeanXes.size() > 0) {
                        mMovieContentPresenter.getMovieComment(getIntent().getStringExtra(getString(R.string.Content_Id)), mDataBeanXes.get(mDataBeanXes.size() - 1).getId());
                    }
                }
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        mRecycleComment.setHasFixedSize(true);
        mRecycleComment.setNestedScrollingEnabled(false);
        mRecycleComment.setLayoutManager(linearLayoutManager);
        mDataBeanXCommonAdapter = new CommonAdapter<MovieComment.DataBeanX.DataBean>(this, R.layout.item_comment, mDataBeanXes) {
            @Override
            protected void convert(ViewHolder holder, MovieComment.DataBeanX.DataBean dataBean, int position) {
                holder.setText(R.id.comment_name, dataBean.getUser().getUser_name());
                holder.setText(R.id.comment_data, dataBean.getInput_date());
                holder.setText(R.id.comment_content, dataBean.getContent());
                holder.setCircleImg(R.id.comment_img_head, dataBean.getUser().getWeb_url());
                holder.setText(R.id.comment_like_num, String.valueOf(dataBean.getPraisenum()));
            }
        };
        mLoadMoreWrapper = new LoadMoreWrapper(mDataBeanXCommonAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading_recycle);
//        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//
//            }
//        });
        mRecycleComment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecycleComment.setAdapter(mLoadMoreWrapper);
    }


    @Override
    public void showLoading() {
        mContentReadTop.setVisibility(View.GONE);
        mAuthor.setVisibility(View.GONE);
        mEssayWv.setVisibility(View.GONE);
        mContentBottom.setVisibility(View.GONE);
        mRecycleComment.setVisibility(View.GONE);
        animationDrawable = (AnimationDrawable) mImgLoading.getDrawable();
        //开始动画
        animationDrawable.start();
        mImgLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        mContentReadTop.setVisibility(View.VISIBLE);
        mAuthor.setVisibility(View.VISIBLE);
        mEssayWv.setVisibility(View.VISIBLE);
        mContentBottom.setVisibility(View.VISIBLE);
        mRecycleComment.setVisibility(View.VISIBLE);
        animationDrawable.stop();
        mImgLoading.setVisibility(View.GONE);

    }

    @Override
    public void showToast(String msg) {
        LogUtils.e(msg);
    }

    @Override
    public void showErr(Throwable e) {
        LogUtils.e(e.getMessage());

    }



    @OnClick({R.id.bottom_like, R.id.bottom_comment, R.id.bottom_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_like:
                ToastUtils.showShort("like");
                break;
            case R.id.bottom_comment:
                mScrollContent.scrollTo(0, mAuthor.getHeight() + mEssayWv.getHeight() + mContentReadTop.getHeight() + getToolBar().getHeight());
                break;
            case R.id.bottom_share:
                ToastUtils.showShort("share");
                break;
        }
    }

    @Override
    public void showMovieContent(MovieBean data) {
        RichText.fromHtml(data.getData().getData().get(0).getContent()).done(new Callback() {
            @Override
            public void done(boolean imageLoadDone) {
                hideLoading();
            }
        }).into(mEssayWv);

        mEssayHpTitle.setText(data.getData().getData().get(0).getTitle());
        mEssayHpAuthor.setText(String.format("文/%s",data.getData().getData().get(0).getUser().getUser_name()));
        mTvAuthorIntroduce.setText(data.getData().getData().get(0).getCharge_edt());
        mTvAuthorUserName.setText(data.getData().getData().get(0).getAuthor_list().get(0).getUser_name());
        mTvAuthorSummary.setText(data.getData().getData().get(0).getAuthor_list().get(0).getSummary());



    }

    @Override
    public void showMovieRecycle(MovieComment movieComment) {
        //不足一页
        if (movieComment.getData().getData().size() == 0 || movieComment.getData().getData().size() < 28) {
            LogUtils.e(movieComment.getData().getData().size() == 0);
            LogUtils.e(movieComment.getData().getData().size());
            mLoadMoreWrapper.setLoadOver(false);
            ToastUtils.showShort("没有更多数据");
        }

        LogUtils.e(movieComment.getData().getData().size());
        for (MovieComment.DataBeanX.DataBean dataBean : movieComment.getData().getData()) {
            mDataBeanXes.add(dataBean);
        }
        mLoadMoreWrapper.notifyDataSetChanged();

    }

    @Override
    public void showMovieDetail(MovieDetailBean detailBean) {
        mBottomLikeNum.setText(String.valueOf(detailBean.getData().getSharenum()));
        mBottomCommentNum.setText(String.valueOf(detailBean.getData().getCommentnum()));

        //设置 view 与 数据
        mImgUrlList.add(detailBean.getData().getDetailcover());
        for (String url:detailBean.getData().getPhoto()) {
            mImgUrlList.add(url);
        }
        LogUtils.e(mImgUrlList.toString());
        DefaultPageIndicator defaultPageIndicator = new DefaultPageIndicator(this);
        //设置指示器样式  选中图标与未选中图标
        defaultPageIndicator.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});


        mWenldBanner.setPages(mDetailBeanHolder, mImgUrlList);
        mWenldBanner
                .setPageIndicatorListener(defaultPageIndicator)  //设置指示器监听
                .setIndicatorView(defaultPageIndicator)  //设置指示器VIew
                .setPageIndicatorAlign(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.CENTER_HORIZONTAL);    //设置指示器位置
    }
}
