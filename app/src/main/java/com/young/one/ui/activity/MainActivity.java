package com.young.one.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.young.one.R;
import com.young.one.base.BaseActivity;
import com.young.one.ui.fragment.AllFragment;
import com.young.one.ui.fragment.MeFragment;
import com.young.one.ui.fragment.OneFragment;
import com.young.one.weight.BottomNavigationViewEx;

import butterknife.BindView;

public class MainActivity extends BaseActivity{
    private static final String TAG = MainActivity.class.getSimpleName();
    private OneFragment mOneFragment;
    private AllFragment mAllFragment;
    private MeFragment mMeFragment;

    @BindView(R.id.main_frameLayout)
    FrameLayout mMainFrameLayout;
    @BindView(R.id.main_bottom)
    BottomNavigationViewEx mMainBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBottomNav();
        initFragment();

    }

    private void initFragment() {
        mOneFragment = new OneFragment();
        mAllFragment = new AllFragment();
        mMeFragment = new MeFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frameLayout, mOneFragment);
        fragmentTransaction.commit();
    }


    /**
     * 设置底部导航栏
     */
    private void initBottomNav() {
        mMainBottom.enableAnimation(false);
        mMainBottom.enableShiftingMode(false);
        mMainBottom.enableItemShiftingMode(false);
        mMainBottom.setTextVisibility(false);
        mMainBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.menu_one:
                        if (mOneFragment == null) mOneFragment = new OneFragment();
                        fragmentTransaction.replace(R.id.main_frameLayout, mOneFragment);
                        break;
                    case R.id.menu_all:
                        if (mAllFragment == null) mAllFragment = new AllFragment();
                        fragmentTransaction.replace(R.id.main_frameLayout, mAllFragment);
                        break;
                    case R.id.menu_me:
                        if (mMeFragment == null) mMeFragment = new MeFragment();
                        fragmentTransaction.replace(R.id.main_frameLayout, mMeFragment);
                        break;

                }
                fragmentTransaction.commit();
                return true;
            }
        });
    }

    @Override
    protected void initToolBar() {
        hideToolBar();

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

}
