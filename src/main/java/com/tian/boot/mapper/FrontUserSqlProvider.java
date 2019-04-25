package com.tian.boot.mapper;

import com.tian.boot.entity.FrontUser;
import org.apache.ibatis.jdbc.SQL;

public class FrontUserSqlProvider {

    public String insertSelective(FrontUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tf_f_user");
        
        if (record.getUser_id() != null) {
            sql.VALUES("user_id", "#{user_id,jdbcType=BIGINT}");
        }
        
        if (record.getLogin_name() != null) {
            sql.VALUES("login_name", "#{login_name,jdbcType=VARCHAR}");
        }
        
        if (record.getUser_name() != null) {
            sql.VALUES("user_name", "#{user_name,jdbcType=VARCHAR}");
        }
        
        if (record.getSerial_number() != null) {
            sql.VALUES("serial_number", "#{serial_number,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getCompany_name() != null) {
            sql.VALUES("company_name", "#{company_name,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        
        if (record.getProduct_id() != null) {
            sql.VALUES("product_id", "#{product_id,jdbcType=BIGINT}");
        }
        
        if (record.getDevelop_staff_id() != null) {
            sql.VALUES("develop_staff_id", "#{develop_staff_id,jdbcType=BIGINT}");
        }
        
        if (record.getDevelop_department_id() != null) {
            sql.VALUES("develop_department_id", "#{develop_department_id,jdbcType=BIGINT}");
        }
        
        if (record.getCreate_date() != null) {
            sql.VALUES("create_date", "#{create_date,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEnd_date() != null) {
            sql.VALUES("end_date", "#{end_date,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdate_time() != null) {
            sql.VALUES("update_time", "#{update_time,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdate_staff_id() != null) {
            sql.VALUES("update_staff_id", "#{update_staff_id,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FrontUser record) {
        SQL sql = new SQL();
        sql.UPDATE("tf_f_user");

        if (record.getLogin_name() != null) {
            sql.SET("login_name = #{login_name,jdbcType=VARCHAR}");
        }
        
        if (record.getUser_name() != null) {
            sql.SET("user_name = #{user_name,jdbcType=VARCHAR}");
        }
        
        if (record.getSerial_number() != null) {
            sql.SET("serial_number = #{serial_number,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getCompany_name() != null) {
            sql.SET("company_name = #{company_name,jdbcType=VARCHAR}");
        }

        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }

        if (record.getProduct_id() != null) {
            sql.SET("product_id = #{product_id,jdbcType=BIGINT}");
        }
        
        if (record.getDevelop_staff_id() != null) {
            sql.SET("develop_staff_id = #{develop_staff_id,jdbcType=BIGINT}");
        }
        
        if (record.getDevelop_department_id() != null) {
            sql.SET("develop_department_id = #{develop_department_id,jdbcType=BIGINT}");
        }

        if (record.getCreate_date() != null) {
            sql.SET("create_date = #{create_date,jdbcType=TIMESTAMP}");
        }

        if (record.getEnd_date() != null) {
            sql.SET("end_date = #{end_date,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdate_time() != null) {
            sql.SET("update_time = #{update_time,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdate_staff_id() != null) {
            sql.SET("update_staff_id = #{update_staff_id,jdbcType=VARCHAR}");
        }

        sql.WHERE("user_id = #{user_id,jdbcType=BIGINT}");

        return sql.toString();
    }
}