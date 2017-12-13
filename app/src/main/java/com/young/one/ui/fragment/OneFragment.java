package com.young.one.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.young.one.R;
import com.young.one.base.BaseFragment;
import com.young.one.bean.IdListBean;
import com.young.one.bean.OneListBean;
import com.young.one.presenter.OneListPresenter;
import com.young.one.utilcode.util.LogUtils;
import com.young.one.utils.ComparatorDate;
import com.young.one.utils.recyclerview.CommonAdapter;
import com.young.one.utils.recyclerview.base.ViewHolder;
import com.young.one.view.OneListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yangxing on 2017/11/9.
 */

public class OneFragment extends BaseFragment implements OneListView {
    private CommonAdapter<OneListBean.DataBean.ContentListBean> mContentListBeanCommonAdapter;
    private List<OneListBean.DataBean.ContentListBean> mContentListBeen = new ArrayList<>();
    private RecyclerView recyclerView;
    private OneListPresenter mOneListPresenter;
    private List<String> mIdList;
    private List<OneListBean> mOneList = new ArrayList<>();

    @BindView(R.id.tv_one_year)
    TextView mTvOneYear;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.tv_one_month)
    TextView mTvOneMonth;
    @BindView(R.id.textView6)
    TextView mTextView6;
    @BindView(R.id.tv_one_day)
    TextView mTvOneDay;
    @BindView(R.id.tv_one_weather)
    TextView mTvOneWeather;
    @BindView(R.id.cl_one_date)
    ConstraintLayout mClOneDate;
    @BindView(R.id.vp_one)
    ViewPager mVpOne;
    Unbinder unbinder;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_one;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initPresenter();

        recyclerView = new RecyclerView(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        test();
        mVpOne.setAdapter(new PagerAdapter() {


            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                recyclerView = new RecyclerView(getActivity());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(mContentListBeanCommonAdapter);
//                recyclerView.setAdapter(mStringCommonAdapter);
                container.addView(recyclerView);
                return recyclerView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        mVpOne.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mOneList.size() == 0)
                    return;
                if (mOneList.size() <= position) {
                    mOneListPresenter.getOneList(mIdList.get(position));
                } else {
                    mTvOneYear.setText(mOneList.get(position).getData().getDate());
                    mContentListBeen.clear();
                    for (OneListBean.DataBean.ContentListBean contentListBean : mOneList.get(position).getData().getContent_list()) {
                        mContentListBeen.add(contentListBean);
                    }
                    mContentListBeanCommonAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void test() {
        mContentListBeanCommonAdapter = new CommonAdapter<OneListBean.DataBean.ContentListBean>(getActivity(), R.layout.item_string, mContentListBeen) {
            @Override
            protected void convert(ViewHolder holder, OneListBean.DataBean.ContentListBean contentListBean, int position) {
                holder.setText(R.id.text1, contentListBean.getTitle());
            }
        };
        recyclerView.setAdapter(mContentListBeanCommonAdapter);
    }

    private void initPresenter() {
        mOneListPresenter = new OneListPresenter();
        mOneListPresenter.attachView(this);
        mOneListPresenter.getIdList();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {
//        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
//        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showErr() {

    }

    @Override
    public void showData(OneListBean oneListBean) {
        LogUtils.e(oneListBean.getData().getContent_list());
        mTvOneYear.setText(oneListBean.getData().getDate());
        mOneList.add(oneListBean);
        Collections.sort(mOneList,new ComparatorDate());
        mContentListBeen.clear();
        for (OneListBean.DataBean.ContentListBean contentListBean : oneListBean.getData().getContent_list()) {
            mContentListBeen.add(contentListBean);
        }
        mContentListBeanCommonAdapter.notifyDataSetChanged();


    }

    @Override
    public void initRecycleDate(IdListBean idListBean) {
        LogUtils.e(idListBean.toString());
        mIdList = idListBean.getIdList();
        mOneListPresenter.getOneList(mIdList.get(0));
    }
}
