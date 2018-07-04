package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/6/18.
 */

public class AdvanceReplaceModel {

    private int position;
    private NodeListModel.RepointDetailListBean rePointModel;

    public int getPosition() {
        return position;
    }

    public AdvanceReplaceModel setPosition(int position) {
        this.position = position;

        return this;
    }

    public NodeListModel.RepointDetailListBean getRePointModel() {
        return rePointModel;
    }

    public AdvanceReplaceModel setRePointModel(NodeListModel.RepointDetailListBean rePointModel) {
        this.rePointModel = rePointModel;

        return this;
    }
}
