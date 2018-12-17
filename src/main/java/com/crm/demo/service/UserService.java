package com.crm.demo.service;

import com.crm.demo.mapper.UserMapper;
import com.crm.demo.model.UserModel;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * 
 * @author jacy
 *
 */
@Service
public class UserService {

    @Autowired
    private  UserMapper dao;

    /***
     * 登录
     * @param name
     * @param pwd
     * @return
     */
    public UserModel login(String name,String pwd) {
        return dao.login(name,pwd);
    }

    /***
     * 根据id查询单挑记录
     * @param id
     * @return
     */
    public UserModel select(int id) {
        return dao.select(id);
    }

    // 写入
    public int insert(UserModel model){
    	 return dao.insert(model);
    }
 
    // 查询全部
    public List<UserModel> selectAll(){
   	 return dao.selectAll();
    }

    // 更新 value
    public int updateValue(UserModel model){
    	 return dao.updateValue(model);
    }

    // 根据 ID 删除
    public int delete(Integer id){
   	 return dao.delete(id);
    }
}
