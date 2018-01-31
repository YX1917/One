package com.young.one.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.young.one.R;
import com.young.one.adapter.OneTypeItem;
import com.young.one.base.BaseFragment;
import com.young.one.bean.IdListBean;
import com.young.one.bean.OneListBean;
import com.young.one.presenter.OneListPresenter;
import com.young.one.ui.activity.EssayActivity;
import com.young.one.ui.activity.MusicActivity;
import com.young.one.ui.activity.QuestionActivity;
import com.young.one.utilcode.util.ActivityUtils;
import com.young.one.utilcode.util.LogUtils;
import com.young.one.utils.recyclerview.MultiItemTypeAdapter;
import com.young.one.view.OneListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yangxing on 2017/12/27.
 */

public class OneDetailFragment extends BaseFragment implements OneListView {
    @BindView(R.id.recycle_one_detail)
    RecyclerView mRecycleOneDetail;
    @BindView(R.id.pb_0ne_detail_one)
    ProgressBar mPb0neDetailOne;
    Unbinder unbinder;

    private List<OneListBean.DataBean.ContentListBean> mContentListBeen = new ArrayList<>();
    private OneTypeItem mOneTypeItem;

    private OneListPresenter mOneListPresenter;
    private List<String> mIdList = new ArrayList<>();
    private int mPosition;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_one_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPosition = getArguments().getInt("num");
        initRecycleView();
        initOneListPresenter();

    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleOneDetail.setLayoutManager(linearLayoutManager);
        mOneTypeItem = new OneTypeItem(getActivity(),mContentListBeen);
        mRecycleOneDetail.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecycleOneDetail.setAdapter(mOneTypeItem);
    }

    private void initOneListPresenter() {
        mOneListPresenter = new OneListPresenter();
        mOneListPresenter.attachView(this);
        if (mIdList.size()==0) {
            mOneListPresenter.getIdList();
        } else {
            if (mContentListBeen.isEmpty()) {
                mOneListPresenter.getOneList(mIdList.get(mPosition));
            } else {
                LogUtils.e(123456789);
                mOneTypeItem.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void showLoading() {
        mPb0neDetailOne.setVisibility(View.VISIBLE);
        mRecycleOneDetail.setVisibility(View.GONE);

    }

    @Override
    public void hideLoading() {
        mPb0neDetailOne.setVisibility(View.GONE);
        mRecycleOneDetail.setVisibility(View.VISIBLE);


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
    public void showData(OneListBean oneListBean) {
        for (OneListBean.DataBean.ContentListBean contentListBean:oneListBean.getData().getContent_list()) {
            mContentListBeen.add(contentListBean);
        }

        mContentListBeen = oneListBean.getData().getContent_list();
        mOneTypeItem.notifyDataSetChanged();
    }

    @Override
    public void initRecycleDate(final IdListBean idListBean) {
        if (idListBean.getRes() == 0) {
            mIdList = idListBean.getIdList();
            mOneListPresenter.getOneList(mIdList.get(mPosition));
        }

        mOneTypeItem.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent();
                switch (mContentListBeen.get(position).getCategory()) {
                    case "1":
                        intent.setClass(getActivity(),EssayActivity.class);
                        break;
                    case "3":
                        intent.setClass(getActivity(),QuestionActivity.class);
                        break;
                    case "4":
                        intent.setClass(getActivity(),MusicActivity.class);
                        break;
                }
                intent.putExtra(getString(R.string.Content_Id),mContentListBeen.get(position).getContent_id());
                ActivityUtils.startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
