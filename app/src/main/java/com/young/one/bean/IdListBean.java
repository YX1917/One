package com.young.one.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yangxing on 2017/11/10.
 */

public class IdListBean {

    /**
     * res : 0
     * data : ["4588","4557","4587","4556","4585","4571","4555","4578","4554","4573"]
     */

    private int res;
    @SerializedName("data")
    private List<String> idList;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        return "IdListBean{" +
                "res=" + res +
                ", idList=" + idList +
                '}';
    }
}
