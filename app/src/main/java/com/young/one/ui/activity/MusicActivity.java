package com.young.one.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.one.R;
import com.young.one.base.BaseActivity;
import com.young.one.bean.MusicBean;
import com.young.one.bean.MusicCommentBean;
import com.young.one.presenter.MusicContentPresent;
import com.young.one.utilcode.util.LogUtils;
import com.young.one.utilcode.util.ToastUtils;
import com.young.one.utils.GlideCircleTransform;
import com.young.one.utils.recyclerview.CommonAdapter;
import com.young.one.utils.recyclerview.base.ViewHolder;
import com.young.one.utils.recyclerview.wrapper.LoadMoreWrapper;
import com.young.one.view.MusicView;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangxing on 2018/1/30.
 */

public class MusicActivity extends BaseActivity implements MusicView {


    @BindView(R.id.essay_hp_title)
    TextView mEssayHpTitle;
    @BindView(R.id.essay_hp_author)
    TextView mEssayHpAuthor;
    @BindView(R.id.content_read_top)
    ConstraintLayout mContentReadTop;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_author_introduce)
    TextView mTvAuthorIntroduce;
    @BindView(R.id.img_author_photo)
    ImageView mImgAuthorPhoto;
    @BindView(R.id.tv_author_user_name)
    TextView mTvAuthorUserName;
    @BindView(R.id.tv_author_summary)
    TextView mTvAuthorSummary;
    @BindView(R.id.btn_author_cover)
    Button mBtnAuthorCover;
    @BindView(R.id.author)
    ConstraintLayout mAuthor;
    @BindView(R.id.recycle_comment)
    RecyclerView mRecycleComment;
    @BindView(R.id.bottom_like_num)
    TextView mBottomLikeNum;
    @BindView(R.id.bottom_like)
    ConstraintLayout mBottomLike;
    @BindView(R.id.bottom_comment_num)
    TextView mBottomCommentNum;
    @BindView(R.id.bottom_comment)
    ConstraintLayout mBottomComment;
    @BindView(R.id.bottom_share)
    ConstraintLayout mBottomShare;
    @BindView(R.id.content_bottom)
    ConstraintLayout mContentBottom;
    @BindView(R.id.music_toolbar)
    Toolbar mMusicToolbar;
    @BindView(R.id.img_loading)
    ImageView mImgLoading;
    @BindView(R.id.nsl_music)
    NestedScrollView mNslMusic;
    private MusicContentPresent mMusicContentPresent;
    private AnimationDrawable animationDrawable;

    private List<MusicCommentBean.DataBeanX.DataBean> mDataBeanXes = new ArrayList<>();
    private CommonAdapter<MusicCommentBean.DataBeanX.DataBean> mDataBeanXCommonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;

    @Override
    protected void initToolBar() {
        setSupportActionBar(mMusicToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navigation_back);
        getSupportActionBar().setTitle("音乐故事");


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_music;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mMusicContentPresent = new MusicContentPresent();
        mMusicContentPresent.attachView(this);
        mMusicContentPresent.getMusicContent(getIntent().getStringExtra(getString(R.string.Content_Id)));
        mMusicContentPresent.getMusicComment(getIntent().getStringExtra(getString(R.string.Content_Id)), "0");

    }


    private void initView() {
        mNslMusic.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    if (mDataBeanXes.size() > 0) {
                        mMusicContentPresent.getMusicComment(getIntent().getStringExtra(getString(R.string.Content_Id)), mDataBeanXes.get(mDataBeanXes.size() - 1).getId());
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
        mDataBeanXCommonAdapter = new CommonAdapter<MusicCommentBean.DataBeanX.DataBean>(this, R.layout.item_comment, mDataBeanXes) {
            @Override
            protected void convert(ViewHolder holder, MusicCommentBean.DataBeanX.DataBean dataBeanX, int position) {
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

    /**
     * 显示正在加载view
     */
    @Override
    public void showLoading() {
        mContentReadTop.setVisibility(View.GONE);
        mAuthor.setVisibility(View.GONE);
        mTvContent.setVisibility(View.GONE);
        mContentBottom.setVisibility(View.GONE);
        mRecycleComment.setVisibility(View.GONE);
        animationDrawable = (AnimationDrawable) mImgLoading.getDrawable();
        //开始动画
        animationDrawable.start();
        mImgLoading.setVisibility(View.VISIBLE);

    }

    /**
     * 关闭正在加载view
     */
    @Override
    public void hideLoading() {
        mContentReadTop.setVisibility(View.VISIBLE);
        mAuthor.setVisibility(View.VISIBLE);
        mTvContent.setVisibility(View.VISIBLE);
        mContentBottom.setVisibility(View.VISIBLE);
        mRecycleComment.setVisibility(View.VISIBLE);
        animationDrawable.stop();
        mImgLoading.setVisibility(View.GONE);

    }

    /**
     * 显示提示
     *
     * @param msg
     */
    @Override
    public void showToast(String msg) {

    }

    /**
     * 显示请求错误提示
     *
     * @param e
     */
    @Override
    public void showErr(Throwable e) {

    }

    @Override
    public void showContent(MusicBean musicBean) {
        RichText.fromHtml(musicBean.getData().getStory()).done(new Callback() {
            @Override
            public void done(boolean imageLoadDone) {
                hideLoading();
            }
        }).into(mTvContent);
        mEssayHpTitle.setText(musicBean.getData().getStory_title());
        mEssayHpAuthor.setText(String.format("文/%s",musicBean.getData().getAuthor().getUser_name()));
        mTvAuthorIntroduce.setText(musicBean.getData().getCharge_edt());
        mTvAuthorUserName.setText(String.format("%s",musicBean.getData().getAuthor().getUser_name()));
        mTvAuthorSummary.setText(musicBean.getData().getAuthor().getSummary());
        mBottomLikeNum.setText(String.valueOf(musicBean.getData().getPraisenum()));
        mBottomCommentNum.setText(String.valueOf(musicBean.getData().getCommentnum()));
        Glide.with(MusicActivity.this).load(musicBean.getData().getAuthor().getWeb_url()).transform(new GlideCircleTransform(MusicActivity.this)).into(mImgAuthorPhoto);

    }

    @Override
    public void showComment(MusicCommentBean musicBean) {

        //不足一页
        if (musicBean.getData().getData().size() == 0 || musicBean.getData().getData().size() < 28) {
            LogUtils.e(musicBean.getData().getData().size() == 0);
            LogUtils.e(musicBean.getData().getData().size());
            mLoadMoreWrapper.setLoadOver(false);
            ToastUtils.showShort("没有更多数据");
        }

        LogUtils.e(musicBean.getData().getData().size());
        for (MusicCommentBean.DataBeanX.DataBean dataBean : musicBean.getData().getData()) {
            mDataBeanXes.add(dataBean);
        }
        mLoadMoreWrapper.notifyDataSetChanged();


    }


}
