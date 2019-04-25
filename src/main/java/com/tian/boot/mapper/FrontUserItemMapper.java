package com.tian.boot.mapper;

import com.tian.boot.entity.FrontUserItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface FrontUserItemMapper {
    @Delete({
        "delete from tf_f_user_item",
        "where item_id = #{item_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long item_id);

    @Insert({
        "insert into tf_f_user_item (item_id, user_id, ",
        "item_type, item_value, ",
        "item_remark, start_date, ",
        "end_date, update_time, ",
        "update_staff_id)",
        "values (#{item_id,jdbcType=BIGINT}, #{user_id,jdbcType=BIGINT}, ",
        "#{item_type,jdbcType=TINYINT}, #{item_value,jdbcType=VARCHAR}, ",
        "#{item_remark,jdbcType=VARCHAR}, #{start_date,jdbcType=TIMESTAMP}, ",
        "#{end_date,jdbcType=TIMESTAMP}, #{update_time,jdbcType=TIMESTAMP}, ",
        "#{update_staff_id,jdbcType=VARCHAR})"
    })
    int insert(FrontUserItem record);

    @InsertProvider(type=FrontUserItemSqlProvider.class, method="insertSelective")
    int insertSelective(FrontUserItem record);

    @Select({
        "select",
        "item_id, user_id, item_type, item_value, item_remark, start_date, end_date, ",
        "update_time, update_staff_id",
        "from tf_f_user_item",
        "where item_id = #{item_id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="item_id", property="item_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="user_id", jdbcType=JdbcType.BIGINT),
        @Result(column="item_type", property="item_type", jdbcType=JdbcType.TINYINT),
        @Result(column="item_value", property="item_value", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_remark", property="item_remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="start_date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_date", property="end_date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_staff_id", property="update_staff_id", jdbcType=JdbcType.VARCHAR)
    })
    FrontUserItem selectByPrimaryKey(Long item_id);

    @UpdateProvider(type=FrontUserItemSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FrontUserItem record);

    @Update({
        "update tf_f_user_item",
        "set user_id = #{user_id,jdbcType=BIGINT},",
          "item_type = #{item_type,jdbcType=TINYINT},",
          "item_value = #{item_value,jdbcType=VARCHAR},",
          "item_remark = #{item_remark,jdbcType=VARCHAR},",
          "start_date = #{start_date,jdbcType=TIMESTAMP},",
          "end_date = #{end_date,jdbcType=TIMESTAMP},",
          "update_time = #{update_time,jdbcType=TIMESTAMP},",
          "update_staff_id = #{update_staff_id,jdbcType=VARCHAR}",
        "where item_id = #{item_id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FrontUserItem record);

    @Select("select * from tf_f_user_item where user_id=#{userId} and item_type=#{i}")
    FrontUserItem getItemByUserIdAndItemType(Long userId, byte i);

    @Select("select count(*) from tf_f_user_item where item_value=#{item_value} and end_date>NOW()")
    int authenticateKey(@Param("item_value") String item_value);
}