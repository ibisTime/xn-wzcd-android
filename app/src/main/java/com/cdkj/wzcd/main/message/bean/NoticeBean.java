package com.cdkj.wzcd.main.message.bean;

import java.io.Serializable;

/**
 * Created by lei on 2020/3/17.
 */

public class NoticeBean implements Serializable {


    /**
     * code : XX202003111456340963701
     * type : 2
     * title : 业务员提交准入申请通知
     * content : 您团队的业务员徐翔于2020-03-11 14:56:34提交了准入申请,准入单编号为:Hr332020031104113B
     * notifier : U201912301631306849320
     * status : 1
     * createDatetime : Mar 11, 2020 2:56:34 PM
     * updater : U202001021708593372460
     * updateDatetime : Mar 11, 2020 2:56:34 PM
     * sysUser : {"userId":"U202001021708593372460","type":"P","loginName":"18606676922","mobile":"18606676922","realName":"徐翔","createDatetime":"Jan 2, 2020 5:08:59 PM","companyCode":"DP201909171118539192759","departmentCode":"DP202003111550030323206","postCode":"DP201909171119479292849","roleCode":"SR201800000000000000YWY","jobNo":"041","deviceToken":"97604d6c276cd2e332b9b46249febd5fc7224458b06eec8ed93dcc29677db8a1","updater":"USYS201800000000001","status":"1","teamCode":"BT202001031015493328494","postName":"温州业务员","departmentName":"浙江业务部","companyName":"浙江分部","teamName":"汪俊团队","roleName":"业务员"}
     */

    private String code;
    private String type;
    private String title;
    private String content;
    private String notifier;
    private String status;
    private String createDatetime;
    private String updater;
    private String updateDatetime;
    private SysUserBean sysUser;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNotifier() {
        return notifier;
    }

    public void setNotifier(String notifier) {
        this.notifier = notifier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public SysUserBean getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUserBean sysUser) {
        this.sysUser = sysUser;
    }

    public static class SysUserBean implements Serializable {
        /**
         * userId : U202001021708593372460
         * type : P
         * loginName : 18606676922
         * mobile : 18606676922
         * realName : 徐翔
         * createDatetime : Jan 2, 2020 5:08:59 PM
         * companyCode : DP201909171118539192759
         * departmentCode : DP202003111550030323206
         * postCode : DP201909171119479292849
         * roleCode : SR201800000000000000YWY
         * jobNo : 041
         * deviceToken : 97604d6c276cd2e332b9b46249febd5fc7224458b06eec8ed93dcc29677db8a1
         * updater : USYS201800000000001
         * status : 1
         * teamCode : BT202001031015493328494
         * postName : 温州业务员
         * departmentName : 浙江业务部
         * companyName : 浙江分部
         * teamName : 汪俊团队
         * roleName : 业务员
         */

        private String userId;
        private String type;
        private String loginName;
        private String mobile;
        private String realName;
        private String createDatetime;
        private String companyCode;
        private String departmentCode;
        private String postCode;
        private String roleCode;
        private String jobNo;
        private String deviceToken;
        private String updater;
        private String status;
        private String teamCode;
        private String postName;
        private String departmentName;
        private String companyName;
        private String teamName;
        private String roleName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(String createDatetime) {
            this.createDatetime = createDatetime;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getDepartmentCode() {
            return departmentCode;
        }

        public void setDepartmentCode(String departmentCode) {
            this.departmentCode = departmentCode;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(String roleCode) {
            this.roleCode = roleCode;
        }

        public String getJobNo() {
            return jobNo;
        }

        public void setJobNo(String jobNo) {
            this.jobNo = jobNo;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        public String getUpdater() {
            return updater;
        }

        public void setUpdater(String updater) {
            this.updater = updater;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTeamCode() {
            return teamCode;
        }

        public void setTeamCode(String teamCode) {
            this.teamCode = teamCode;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
}
