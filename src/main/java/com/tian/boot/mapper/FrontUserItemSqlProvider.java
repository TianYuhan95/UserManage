package com.tian.boot.mapper;

import com.tian.boot.entity.FrontUserItem;
import org.apache.ibatis.jdbc.SQL;

public class FrontUserItemSqlProvider {

    public String insertSelective(FrontUserItem record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tf_f_user_item");
        
        if (record.getItem_id() != null) {
            sql.VALUES("item_id", "#{item_id,jdbcType=BIGINT}");
        }
        
        if (record.getUser_id() != null) {
            sql.VALUES("user_id", "#{user_id,jdbcType=BIGINT}");
        }
        
        if (record.getItem_type() != null) {
            sql.VALUES("item_type", "#{item_type,jdbcType=TINYINT}");
        }
        
        if (record.getItem_value() != null) {
            sql.VALUES("item_value", "#{item_value,jdbcType=VARCHAR}");
        }
        
        if (record.getItem_remark() != null) {
            sql.VALUES("item_remark", "#{item_remark,jdbcType=VARCHAR}");
        }
        
        if (record.getStart_date() != null) {
            sql.VALUES("start_date", "#{start_date,jdbcType=TIMESTAMP}");
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

    public String updateByPrimaryKeySelective(FrontUserItem record) {
        SQL sql = new SQL();
        sql.UPDATE("tf_f_user_item");
        
        if (record.getUser_id() != null) {
            sql.SET("user_id = #{user_id,jdbcType=BIGINT}");
        }
        
        if (record.getItem_type() != null) {
            sql.SET("item_type = #{item_type,jdbcType=TINYINT}");
        }
        
        if (record.getItem_value() != null) {
            sql.SET("item_value = #{item_value,jdbcType=VARCHAR}");
        }
        
        if (record.getItem_remark() != null) {
            sql.SET("item_remark = #{item_remark,jdbcType=VARCHAR}");
        }
        
        if (record.getStart_date() != null) {
            sql.SET("start_date = #{start_date,jdbcType=TIMESTAMP}");
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
        
        sql.WHERE("item_id = #{item_id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}