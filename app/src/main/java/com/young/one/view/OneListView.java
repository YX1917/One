package com.young.one.view;

import com.young.one.base.BaseView;
import com.young.one.bean.IdListBean;
import com.young.one.bean.OneListBean;

/**
 * Created by yangxing on 2017/11/10.
 */

public interface OneListView extends BaseView {
    void showData(OneListBean oneListBean);
    void initRecycleDate(IdListBean idListBean);
}
