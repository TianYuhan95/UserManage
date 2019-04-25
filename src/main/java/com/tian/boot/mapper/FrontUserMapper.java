package com.tian.boot.mapper;

import com.tian.boot.entity.FrontUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

public interface FrontUserMapper {
    @Delete({
        "delete from tf_f_user",
        "where user_id = #{user_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long user_id);

    @Insert({
        "insert into tf_f_user (user_id, login_name, ",
        "user_name, serial_number, ",
        "password, company_name, ",
        "status, product_id, ",
        "develop_staff_id, develop_department_id, ",
        "create_date, end_date, ",
        "update_time, update_staff_id)",
        "values (#{user_id,jdbcType=BIGINT}, #{login_name,jdbcType=VARCHAR}, ",
        "#{user_name,jdbcType=VARCHAR}, #{serial_number,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{company_name,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=TINYINT}, #{product_id,jdbcType=BIGINT}, ",
        "#{develop_staff_id,jdbcType=BIGINT}, #{develop_department_id,jdbcType=BIGINT}, ",
        "#{create_date,jdbcType=TIMESTAMP}, #{end_date,jdbcType=TIMESTAMP}, ",
        "#{update_time,jdbcType=TIMESTAMP}, #{update_staff_id,jdbcType=VARCHAR})"
    })
    int insert(FrontUser record);

    @InsertProvider(type=FrontUserSqlProvider.class, method="insertSelective")
    int insertSelective(FrontUser record);

    @Select({
        "select",
        "user_id, login_name, user_name, serial_number, password, company_name, status, ",
        "product_id, develop_staff_id, develop_department_id, create_date, end_date, ",
        "update_time, update_staff_id",
        "from tf_f_user",
        "where user_id = #{user_id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="user_id", property="user_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="login_name", property="login_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="user_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="serial_number", property="serial_number", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_name", property="company_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="product_id", property="product_id", jdbcType=JdbcType.BIGINT),
        @Result(column="develop_staff_id", property="develop_staff_id", jdbcType=JdbcType.BIGINT),
        @Result(column="develop_department_id", property="develop_department_id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_date", property="create_date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_date", property="end_date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_staff_id", property="update_staff_id", jdbcType=JdbcType.VARCHAR)
    })
    FrontUser selectByPrimaryKey(Long user_id);

    @UpdateProvider(type=FrontUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FrontUser record);

    @Update({
        "update tf_f_user",
        "set login_name = #{login_name,jdbcType=VARCHAR},",
          "user_name = #{user_name,jdbcType=VARCHAR},",
          "serial_number = #{serial_number,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "company_name = #{company_name,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=TINYINT},",
          "product_id = #{product_id,jdbcType=BIGINT},",
          "develop_staff_id = #{develop_staff_id,jdbcType=BIGINT},",
          "develop_department_id = #{develop_department_id,jdbcType=BIGINT},",
          "create_date = #{create_date,jdbcType=TIMESTAMP},",
          "end_date = #{end_date,jdbcType=TIMESTAMP},",
          "update_time = #{update_time,jdbcType=TIMESTAMP},",
          "update_staff_id = #{update_staff_id,jdbcType=VARCHAR}",
        "where user_id = #{user_id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FrontUser record);

    @Select({
            "select",
            "user_id,login_name,user_name,serial_number,company_name,status,create_date,end_date,update_time,update_staff_id",
            "from tf_f_user"
    })
    List<FrontUser> getAllUsers();

    @Update({
            "update tf_f_user",
            "set status=#{b},update_staff_id=#{update_staff_id}",
            "where user_id=#{userId}"
    })
    int updateStatusByLoginId(Long userId, byte b, String update_staff_id);

    @Update("update tf_f_user set end_date=#{currentEndDate} where user_id=#{userId}")
    Integer updateEndDateByUserId(Long userId, Date currentEndDate);

    @Select({
            "select",
            "user_id,login_name,user_name,serial_number,company_name,status,create_date,end_date,update_time,update_staff_id",
            "from tf_f_user",
            "where status=#{status}"
    })
    List<FrontUser> selectByStatus(byte status);
}