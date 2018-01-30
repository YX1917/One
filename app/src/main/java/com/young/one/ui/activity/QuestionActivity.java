package com.young.one.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.one.R;
import com.young.one.base.BaseActivity;
import com.young.one.bean.QuestionBean;
import com.young.one.bean.QuestionCommentBean;
import com.young.one.presenter.QuestionContentPresenter;
import com.young.one.utilcode.util.ToastUtils;
import com.young.one.utils.GlideCircleTransform;
import com.young.one.utils.recyclerview.CommonAdapter;
import com.young.one.utils.recyclerview.base.ViewHolder;
import com.young.one.utils.recyclerview.wrapper.LoadMoreWrapper;
import com.young.one.view.QuestionContentView;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangxing on 2018/1/29.
 */

public class QuestionActivity extends BaseActivity implements QuestionContentView {
    @BindView(R.id.question_content)
    TextView mQuestionContent;
    @BindView(R.id.question_asker)
    TextView mQuestionAsker;
    @BindView(R.id.tv_html_content)
    TextView mTvHtmlContent;
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
    @BindView(R.id.content_question_top)
    ConstraintLayout mContentTop;
    @BindView(R.id.author)
    ConstraintLayout mAuthor;
    @BindView(R.id.content_bottom)
    ConstraintLayout mContentBottom;
    @BindView(R.id.img_loading)
    ImageView mImgLoading;
    @BindView(R.id.nsl_question)
    NestedScrollView mNslQuestion;
    @BindView(R.id.question_title)
    TextView mQuestionTitle;
    @BindView(R.id.question_answerer)
    TextView mQuestionAnswerer;

    private QuestionContentPresenter mQuestionContentPresenter;
    private List<QuestionCommentBean.DataBeanX.DataBean> mDataBeanList = new ArrayList<>();
    private CommonAdapter<QuestionCommentBean.DataBeanX.DataBean> mDataBeanXCommonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    private AnimationDrawable animationDrawable;

    /**
     * 显示正在加载view
     */
    @Override
    public void showLoading() {
        mContentTop.setVisibility(View.GONE);
        mAuthor.setVisibility(View.GONE);
        mTvHtmlContent.setVisibility(View.GONE);
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
        mContentTop.setVisibility(View.VISIBLE);
        mAuthor.setVisibility(View.VISIBLE);
        mTvHtmlContent.setVisibility(View.VISIBLE);
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
        ToastUtils.showShort(msg);

    }

    /**
     * 显示请求错误提示
     *
     * @param e
     */
    @Override
    public void showErr(Throwable e) {
        ToastUtils.showShort(e.getMessage());
    }


    @Override
    public void showQuestionContent(QuestionBean data) {
        RichText.fromHtml(data.getData().getAnswer_content()).done(new Callback() {
            @Override
            public void done(boolean imageLoadDone) {
                hideLoading();
            }
        }).into(mTvHtmlContent);
        mQuestionTitle.setText(data.getData().getQuestion_title());
        mQuestionContent.setText(data.getData().getQuestion_content());
        mQuestionAsker.setText(data.getData().getAsker().getUser_name()+"问：");
        mQuestionAnswerer.setText(data.getData().getAnswerer().getUser_name()+"答：");
        mTvAuthorIntroduce.setText(data.getData().getCharge_edt());
        mTvAuthorUserName.setText(data.getData().getAsker().getUser_name());
        mTvAuthorSummary.setText(data.getData().getAnswerer().getSummary());
        mBottomLikeNum.setText(String.valueOf(data.getData().getPraisenum()));
        mBottomCommentNum.setText(String.valueOf(data.getData().getCommentnum()));
        Glide.with(QuestionActivity.this).load(data.getData().getAsker().getWeb_url()).transform(new GlideCircleTransform(QuestionActivity.this)).into(mImgAuthorPhoto);


    }


    @Override
    public void showQuestionRecycle(QuestionCommentBean questionCommentBean) {
        //不足一页
        if (questionCommentBean.getData().getData().size() == 0 || questionCommentBean.getData().getData().size() < 28) {
            mLoadMoreWrapper.setLoadOver(false);
        }
        for (QuestionCommentBean.DataBeanX.DataBean dataBean : questionCommentBean.getData().getData()) {
            mDataBeanList.add(dataBean);
        }
        mLoadMoreWrapper.notifyDataSetChanged();

    }

    @Override
    protected void initToolBar() {
        setTitle("问答");
        setIsShowBack(true);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_question;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mQuestionContentPresenter = new QuestionContentPresenter();
        mQuestionContentPresenter.attachView(this);
        mQuestionContentPresenter.getQuestionContent(getIntent().getStringExtra(getString(R.string.Content_Id)));
        mQuestionContentPresenter.getQuestionComment(getIntent().getStringExtra(getString(R.string.Content_Id)), "0");
    }

    private void initView() {

        mNslQuestion.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    if (mDataBeanList.size() > 0) {
                        mQuestionContentPresenter.getQuestionComment(getIntent().getStringExtra(getString(R.string.Content_Id)), mDataBeanList.get(mDataBeanList.size() - 1).getId());
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

        mDataBeanXCommonAdapter = new CommonAdapter<QuestionCommentBean.DataBeanX.DataBean>(this, R.layout.item_comment, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, QuestionCommentBean.DataBeanX.DataBean dataBeanX, int position) {
                holder.setText(R.id.comment_name, dataBeanX.getUser().getUser_name());
                holder.setText(R.id.comment_data, dataBeanX.getInput_date());
                holder.setText(R.id.comment_content, dataBeanX.getContent());
                holder.setCircleImg(R.id.comment_img_head, dataBeanX.getUser().getWeb_url());
                holder.setText(R.id.comment_like_num, String.valueOf(dataBeanX.getPraisenum()));
            }
        };
        mLoadMoreWrapper = new LoadMoreWrapper(mDataBeanXCommonAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading_recycle);
        mRecycleComment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecycleComment.setAdapter(mLoadMoreWrapper);
    }

    @OnClick({R.id.btn_author_cover, R.id.bottom_like, R.id.bottom_comment, R.id.bottom_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_author_cover:
                break;
            case R.id.bottom_like:
                break;
            case R.id.bottom_comment:
                break;
            case R.id.bottom_share:
                break;
        }
    }
}
