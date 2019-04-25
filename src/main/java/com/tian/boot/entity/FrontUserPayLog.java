package com.tian.boot.entity;

import com.tian.boot.utils.DateUtil;

import java.util.Date;

public class FrontUserPayLog {
    private Long pay_id;

    private Long user_id;

    private String login_name;

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name == null?null:login_name.trim();
    }

    private Float pay_money;

    private Date pay_time;

    private Byte pay_type;

    private Date lastEndDate;

    private Date currentEndDate;

    private String remark;

    private Date update_time;

    private String update_staff_id;

    public Long getPay_id() {
        return pay_id;
    }

    public void setPay_id(Long pay_id) {
        this.pay_id = pay_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Float getPay_money() {
        return pay_money;
    }

    public void setPay_money(Float pay_money) {
        this.pay_money = pay_money;
    }

    public String getPay_time() {
        if (pay_time!=null) return DateUtil.getGMT(pay_time);
        return null;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Byte getPay_type() {
        return pay_type;
    }

    public void setPay_type(Byte pay_type) {
        this.pay_type = pay_type;
    }

    public String getLastEndDate() {
        if (lastEndDate!=null) return DateUtil.getGMT(lastEndDate);
        return null;
    }

    public void setLastEndDate(Date lastEndDate) {
        this.lastEndDate = lastEndDate;
    }

    public String  getCurrentEndDate() {
        if (currentEndDate!=null) return DateUtil.getGMT(currentEndDate);
        return null;
    }

    public void setCurrentEndDate(Date currentEndDate) {
        this.currentEndDate = currentEndDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUpdate_time() {
        if (update_time!=null) return DateUtil.getGMT(update_time);
        return null;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_staff_id() {
        return update_staff_id;
    }

    public void setUpdate_staff_id(String update_staff_id) {
        this.update_staff_id = update_staff_id == null ? null : update_staff_id.trim();
    }
}