package com.tian.boot.entity;

import com.tian.boot.utils.DateUtil;

import java.util.Date;

public class FrontUserItem {
    private Long item_id;

    private Long user_id;

    private Byte item_type;

    private String item_value;

    private String item_remark;

    private Date start_date;

    private Date end_date;

    private Date update_time;

    private String update_staff_id;

    public FrontUserItem(Long item_id, Long user_id, Byte item_type, String item_value, String item_remark, Date start_date, Date end_date, String update_staff_id) {
        this.item_id = item_id;
        this.user_id = user_id;
        this.item_type = item_type;
        this.item_value = item_value;
        this.item_remark = item_remark;
        this.start_date = start_date;
        this.end_date = end_date;
        this.update_staff_id = update_staff_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Byte getItem_type() {
        return item_type;
    }

    public void setItem_type(Byte item_type) {
        this.item_type = item_type;
    }

    public String getItem_value() {
        return item_value;
    }

    public void setItem_value(String item_value) {
        this.item_value = item_value == null ? null : item_value.trim();
    }

    public String getItem_remark() {
        return item_remark;
    }

    public void setItem_remark(String item_remark) {
        this.item_remark = item_remark == null ? null : item_remark.trim();
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getUpdate_time() {
        return update_time;
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