package com.cdkj.wzcd.model.event;

/**
 * Created by cdkj on 2018/6/30.
 */

public class BudgetCheckModel {

    // 检查页面下标
    private int checkIndex;
    // 检查结果
    private boolean checkResult;
    // 未通过字段
    private String checkFail;

    public int getCheckIndex() {
        return checkIndex;
    }

    public BudgetCheckModel setCheckIndex(int checkIndex) {
        this.checkIndex = checkIndex;

        return this;
    }

    public boolean isCheckResult() {
        return checkResult;
    }

    public BudgetCheckModel setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;

        return this;
    }

    public String getCheckFail() {
        return checkFail;
    }

    public BudgetCheckModel setCheckFail(String checkFail) {
        this.checkFail = checkFail;

        return this;
    }
}
