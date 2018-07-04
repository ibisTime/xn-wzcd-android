package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/6/18.
 */

public class RePointReplaceModel {

    private int position;
    private NodeListModel.RepointDetailListBean rePointModel;

    public int getPosition() {
        return position;
    }

    public RePointReplaceModel setPosition(int position) {
        this.position = position;

        return this;
    }

    public NodeListModel.RepointDetailListBean getRePointModel() {
        return rePointModel;
    }

    public RePointReplaceModel setRePointModel(NodeListModel.RepointDetailListBean rePointModel) {
        this.rePointModel = rePointModel;

        return this;
    }
}
