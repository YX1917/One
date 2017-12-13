package com.young.one.utils;

import com.young.one.bean.OneListBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yangxing on 2017/10/31.
 */

public class ComparatorDate implements Comparator {


    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public int compare(Object obj1, Object obj2) {
        OneListBean t1 = (OneListBean) obj1;
        OneListBean t2 = (OneListBean) obj2;
        Date d1, d2;
        try {
            d1 = format.parse(t1.getData().getDate());
            d2 = format.parse(t2.getData().getDate());
        } catch (ParseException e) {
            // 解析出错，则不进行排序
            return 0;
        }
        if (d1.before(d2)) {
            return 1;
        } else {
            return -1;
        }
    }
}
