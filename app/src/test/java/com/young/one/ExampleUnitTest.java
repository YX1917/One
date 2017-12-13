package com.young.one;

import com.young.one.bean.IdListBean;
import com.young.one.net.ApiCall;
import com.young.one.net.RetrofitManger;
import com.young.one.utilcode.util.LogUtils;

import org.junit.Test;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        RetrofitManger.getInstance().createReq(ApiCall.class)
                .getIdList().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<IdListBean>() {
                    @Override
                    public void call(IdListBean idListBean) {
                        LogUtils.e(idListBean.getRes());
                    }
                });
    }


}