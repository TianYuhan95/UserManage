package com.tian.boot.entity;

import com.tian.boot.utils.DateUtil;

import java.util.Date;

public class FrontUser {
    private Long user_id;

    private String login_name;

    private String user_name;

    private String serial_number;

    private String password;

    private String company_name;

    private Byte status;

    private Long product_id;

    private Long develop_staff_id;

    private Long develop_department_id;

    private Date create_date;

    private Date end_date;

    private Date update_time;

    private String update_staff_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name == null ? null : login_name.trim();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number == null ? null : serial_number.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name == null ? null : company_name.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getDevelop_staff_id() {
        return develop_staff_id;
    }

    public void setDevelop_staff_id(Long develop_staff_id) {
        this.develop_staff_id = develop_staff_id;
    }

    public Long getDevelop_department_id() {
        return develop_department_id;
    }

    public void setDevelop_department_id(Long develop_department_id) {
        this.develop_department_id = develop_department_id;
    }

    public String getCreate_date() {
        if (create_date!=null) return DateUtil.getGMT(create_date);
        return null;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getEnd_date() {
        if(end_date!=null) return DateUtil.getGMT(end_date);
        return null;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getUpdate_time() {
        if(update_time!=null) return DateUtil.getGMT(update_time);
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