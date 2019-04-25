package com.tian.boot.mapper;

import com.tian.boot.entity.FrontUserPayLog;
import org.apache.ibatis.jdbc.SQL;

public class FrontUserPayLogSqlProvider {

    public String insertSelective(FrontUserPayLog record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tf_b_paylog");
        
        if (record.getPay_id() != null) {
            sql.VALUES("pay_id", "#{pay_id,jdbcType=BIGINT}");
        }
        
        if (record.getUser_id() != null) {
            sql.VALUES("user_id", "#{user_id,jdbcType=BIGINT}");
        }
        
        if (record.getPay_money() != null) {
            sql.VALUES("pay_money", "#{pay_money,jdbcType=REAL}");
        }
        
        if (record.getPay_time() != null) {
            sql.VALUES("pay_time", "#{pay_time,jdbcType=TIMESTAMP}");
        }
        
        if (record.getPay_type() != null) {
            sql.VALUES("pay_type", "#{pay_type,jdbcType=TINYINT}");
        }
        
        if (record.getLastEndDate() != null) {
            sql.VALUES("lastEndDate", "#{lastEndDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCurrentEndDate() != null) {
            sql.VALUES("currentEndDate", "#{currentEndDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdate_time() != null) {
            sql.VALUES("update_time", "#{update_time,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdate_staff_id() != null) {
            sql.VALUES("update_staff_id", "#{update_staff_id,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FrontUserPayLog record) {
        SQL sql = new SQL();
        sql.UPDATE("tf_b_paylog");
        
        if (record.getUser_id() != null) {
            sql.SET("user_id = #{user_id,jdbcType=BIGINT}");
        }
        
        if (record.getPay_money() != null) {
            sql.SET("pay_money = #{pay_money,jdbcType=REAL}");
        }
        
        if (record.getPay_time() != null) {
            sql.SET("pay_time = #{pay_time,jdbcType=TIMESTAMP}");
        }
        
        if (record.getPay_type() != null) {
            sql.SET("pay_type = #{pay_type,jdbcType=TINYINT}");
        }
        
        if (record.getLastEndDate() != null) {
            sql.SET("lastEndDate = #{lastEndDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCurrentEndDate() != null) {
            sql.SET("currentEndDate = #{currentEndDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdate_time() != null) {
            sql.SET("update_time = #{update_time,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdate_staff_id() != null) {
            sql.SET("update_staff_id = #{update_staff_id,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("pay_id = #{pay_id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}