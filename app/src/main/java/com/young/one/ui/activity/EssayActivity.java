package com.young.one.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.one.R;
import com.young.one.base.BaseActivity;
import com.young.one.bean.EssayBean;
import com.young.one.bean.EssayCommentBean;
import com.young.one.presenter.EssayContentPresenter;
import com.young.one.utilcode.util.LogUtils;
import com.young.one.utilcode.util.ToastUtils;
import com.young.one.utils.GlideCircleTransform;
import com.young.one.utils.recyclerview.CommonAdapter;
import com.young.one.utils.recyclerview.base.ViewHolder;
import com.young.one.utils.recyclerview.wrapper.LoadMoreWrapper;
import com.young.one.view.EssayContentView;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangxing on 2018/1/9.
 */

public class EssayActivity extends BaseActivity implements EssayContentView {
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
    @BindView(R.id.essay_wv)
    TextView mEssayWv;
    @BindView(R.id.recycle_comment)
    RecyclerView mRecycleComment;
    @BindView(R.id.bottom_like_num)
    TextView mBottomLikeNum;
    @BindView(R.id.bottom_comment_num)
    TextView mBottomCommentNum;
    @BindView(R.id.img_loading)
    ImageView mImgLoading;
    private EssayContentPresenter mEssayContentPresenter;
    private List<EssayCommentBean.DataBeanX.DataBean> mDataBeanXes = new ArrayList<>();
    private CommonAdapter<EssayCommentBean.DataBeanX.DataBean> mDataBeanXCommonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    private AnimationDrawable animationDrawable;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_essay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
        mEssayContentPresenter = new EssayContentPresenter();
        mEssayContentPresenter.attachView(this);
        mEssayContentPresenter.getEssayContent(getIntent().getStringExtra(getString(R.string.Content_Id)));
        mEssayContentPresenter.getEssayComment(getIntent().getStringExtra(getString(R.string.Content_Id)), "0");

    }

    private void initView() {
        mScrollContent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    if (mDataBeanXes.size() > 0) {
                        mEssayContentPresenter.getEssayComment(getIntent().getStringExtra(getString(R.string.Content_Id)), mDataBeanXes.get(mDataBeanXes.size() - 1).getId());
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
        mDataBeanXCommonAdapter = new CommonAdapter<EssayCommentBean.DataBeanX.DataBean>(this, R.layout.item_comment, mDataBeanXes) {
            @Override
            protected void convert(ViewHolder holder, EssayCommentBean.DataBeanX.DataBean dataBeanX, int position) {
                holder.setText(R.id.comment_name, dataBeanX.getUser().getUser_name());
                holder.setText(R.id.comment_data, dataBeanX.getInput_date());
                holder.setText(R.id.comment_content, dataBeanX.getContent());
                holder.setCircleImg(R.id.comment_img_head, dataBeanX.getUser().getWeb_url());
                holder.setText(R.id.comment_like_num, String.valueOf(dataBeanX.getPraisenum()));
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

    @Override
    public void showEssayContent(EssayBean data) {
        RichText.fromHtml(data.getData().getHp_content()).done(new Callback() {
            @Override
            public void done(boolean imageLoadDone) {
                hideLoading();
            }
        }).into(mEssayWv);

        mEssayHpTitle.setText(data.getData().getHp_title());
        mEssayHpAuthor.setText(data.getData().getHp_author());
        mTvAuthorIntroduce.setText(data.getData().getHp_author_introduce());
        mTvAuthorUserName.setText(data.getData().getAuthor().get(0).getUser_name() + data.getData().getAuthor().get(0).getWb_name());
        mTvAuthorSummary.setText(data.getData().getAuthor().get(0).getSummary());
        mBottomLikeNum.setText(String.valueOf(data.getData().getPraisenum()));
        mBottomCommentNum.setText(String.valueOf(data.getData().getCommentnum()));
        Glide.with(EssayActivity.this).load(data.getData().getAuthor().get(0).getWeb_url()).transform(new GlideCircleTransform(EssayActivity.this)).into(mImgAuthorPhoto);
    }


    @Override
    public void showEssayRecycle(EssayCommentBean essayCommentBean) {
        //不足一页
        if (essayCommentBean.getData().getData().size() == 0 || essayCommentBean.getData().getData().size() < 28) {
            LogUtils.e(essayCommentBean.getData().getData().size() == 0);
            LogUtils.e(essayCommentBean.getData().getData().size());
            mLoadMoreWrapper.setLoadOver(false);
            ToastUtils.showShort("没有更多数据");
        }

        LogUtils.e(essayCommentBean.getData().getData().size());
        for (EssayCommentBean.DataBeanX.DataBean dataBean : essayCommentBean.getData().getData()) {
            mDataBeanXes.add(dataBean);
        }
        mLoadMoreWrapper.notifyDataSetChanged();

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


}
