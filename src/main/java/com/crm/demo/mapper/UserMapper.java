package com.crm.demo.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import com.crm.demo.model.UserModel;

import java.util.List;
/***
 * 
 * @author jacy
 *
 */
@Mapper
@Component
public interface UserMapper
{

    // 插入 并返回自增ID
    @Insert("INSERT INTO tb_user(name, pwd) VALUES(#{name}, #{pwd})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'tb_user')", before = false, keyProperty = "id", resultType = int.class)
    int insert(UserModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM tb_user WHERE id=#{id}")
    UserModel select(int id);

    // login
    @Select("SELECT * FROM tb_user WHERE name=#{name} and pwd=#{pwd}")
    UserModel login(@Param("name")String name,@Param("pwd")String pwd);
    
    // 查询全部
    @Select("SELECT * FROM tb_user")
    List<UserModel> selectAll();

    // 更新 value
    @Update("UPDATE tb_user SET pwd=#{pwd} WHERE id=#{id}")
    int updateValue(UserModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM tb_user WHERE id=#{id}")
    int delete(Integer id);
}
