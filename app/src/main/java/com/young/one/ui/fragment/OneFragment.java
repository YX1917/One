package com.young.one.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.young.one.R;
import com.young.one.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yangxing on 2017/11/9.
 */

public class OneFragment extends BaseFragment {



    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentPagerAdapter mFragmentPagerAdapter;

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
        initViewPager();

    }

    private void initViewPager() {
        if (mFragmentList.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                Bundle bundle = new Bundle();
                bundle.putInt("num", i);
                OneDetailFragment oneDetailFragment = new OneDetailFragment();
                oneDetailFragment.setArguments(bundle);
                mFragmentList.add(oneDetailFragment);
            }
        }

        mFragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };

        mVpOne.setAdapter(mFragmentPagerAdapter);
        mVpOne.setCurrentItem(0);
        mVpOne.setOffscreenPageLimit(0);
    }


}
