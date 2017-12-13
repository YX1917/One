package com.young.one.bean;

import java.util.List;

/**
 * Created by yangxing on 2017/12/12.
 */

public class StringBean {
    /**
     * 0 摄影
     * 1 文章
     * 2 连载
     * 3 问答
     * 4 音乐
     * 5 影视
     */
    private List<String> date;

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }
}
