package com.tian.boot.mapper;

import com.tian.boot.entity.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_user");
        
        if (record.getUser_id() != null) {
            sql.VALUES("user_id", "#{user_id,jdbcType=VARCHAR}");
        }
        
        if (record.getUser_name() != null) {
            sql.VALUES("user_name", "#{user_name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getRole_name() != null) {
            sql.VALUES("role_name", "#{role_name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_user");
        
        if (record.getUser_name() != null) {
            sql.SET("user_name = #{user_name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getRole_name() != null) {
            sql.SET("role_name = #{role_name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("user_id = #{user_id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}