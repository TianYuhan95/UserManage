package com.tian.boot.mapper;

import com.tian.boot.entity.FrontUserPayLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FrontUserPayLogMapper {
    @Delete({
        "delete from tf_b_paylog",
        "where pay_id = #{pay_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long pay_id);

    @Insert({
        "insert into tf_b_paylog (pay_id, user_id, ",
        "pay_money, pay_time, ",
        "pay_type, lastEndDate, ",
        "currentEndDate, remark, ",
        "update_time, update_staff_id)",
        "values (#{pay_id,jdbcType=BIGINT}, #{user_id,jdbcType=BIGINT}, ",
        "#{pay_money,jdbcType=REAL}, #{pay_time,jdbcType=TIMESTAMP}, ",
        "#{pay_type,jdbcType=TINYINT}, #{lastEndDate,jdbcType=TIMESTAMP}, ",
        "#{currentEndDate,jdbcType=TIMESTAMP}, #{remark,jdbcType=VARCHAR}, ",
        "#{update_time,jdbcType=TIMESTAMP}, #{update_staff_id,jdbcType=VARCHAR})"
    })
    int insert(FrontUserPayLog record);

    @InsertProvider(type=FrontUserPayLogSqlProvider.class, method="insertSelective")
    int insertSelective(FrontUserPayLog record);

    @Select({
        "select",
        "pay_id, user_id, pay_money, pay_time, pay_type, lastEndDate, currentEndDate, ",
        "remark, update_time, update_staff_id",
        "from tf_b_paylog",
        "where pay_id = #{pay_id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="pay_id", property="pay_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="user_id", jdbcType=JdbcType.BIGINT),
        @Result(column="pay_money", property="pay_money", jdbcType=JdbcType.REAL),
        @Result(column="pay_time", property="pay_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_type", property="pay_type", jdbcType=JdbcType.TINYINT),
        @Result(column="lastEndDate", property="lastEndDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="currentEndDate", property="currentEndDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_staff_id", property="update_staff_id", jdbcType=JdbcType.VARCHAR)
    })
    FrontUserPayLog selectByPrimaryKey(Long pay_id);

    @UpdateProvider(type=FrontUserPayLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FrontUserPayLog record);

    @Update({
        "update tf_b_paylog",
        "set user_id = #{user_id,jdbcType=BIGINT},",
          "pay_money = #{pay_money,jdbcType=REAL},",
          "pay_time = #{pay_time,jdbcType=TIMESTAMP},",
          "pay_type = #{pay_type,jdbcType=TINYINT},",
          "lastEndDate = #{lastEndDate,jdbcType=TIMESTAMP},",
          "currentEndDate = #{currentEndDate,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "update_time = #{update_time,jdbcType=TIMESTAMP},",
          "update_staff_id = #{update_staff_id,jdbcType=VARCHAR}",
        "where pay_id = #{pay_id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FrontUserPayLog record);

    List<FrontUserPayLog> selectPayLogListByLoginName(String login_name);

    @Select("select t1.*,t2.login_name from tf_b_paylog t1,tf_f_user t2 where t1.user_id=t2.user_id order by user_id asc")
    List<FrontUserPayLog> selectAllPayLogList();
}