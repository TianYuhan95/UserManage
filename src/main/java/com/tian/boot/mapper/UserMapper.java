package com.tian.boot.mapper;

import com.tian.boot.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


public interface UserMapper<T> {
    @Delete({
        "delete from sys_user",
        "where user_id = #{user_id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String user_id);

    @Insert({
        "insert into sys_user (user_id, user_name, ",
        "password, role_name)",
        "values (#{user_id,jdbcType=VARCHAR}, #{user_name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{role_name,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "user_id, user_name, password, role_name",
        "from sys_user",
        "where user_id = #{user_id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="user_id", property="user_id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_name", property="user_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_name", property="role_name", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(String user_id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update sys_user",
        "set user_name = #{user_name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "role_name = #{role_name,jdbcType=VARCHAR}",
        "where user_id = #{user_id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(User record);

   @Select({
           "select",
           "user_id, user_name, password, role_name",
           "from sys_user",
           "where user_name = #{user_name,jdbcType=VARCHAR}"
   })
   User findByUserName(@Param("user_name") String username);
}