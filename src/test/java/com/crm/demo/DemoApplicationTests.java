package com.crm.demo;

import com.crm.demo.model.UserModel;
import com.crm.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
/***
 * 
 * @author jacy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserService service;

	@Test
	public void contextLoads() {

        UserModel model = new UserModel();
        model.setName("测试user");
        model.setPwd("测试pwd");

        // 记录数
        List<UserModel> all = service.selectAll();
        int size = all.size();

        // insert
        int result = service.insert(model);
        Assert.assertTrue(result==1);

        // select
        UserModel selectModel = service.select(model.getId());
        Assert.assertNotNull(selectModel);

        // selectAll
        all = service.selectAll();
        Assert.assertEquals(size + 1, all.size());

        // updateValue
        selectModel.setName("测试更改value1");
        result = service.updateValue(selectModel);
        Assert.assertTrue(result==1);

        // delete
        result = service.delete(selectModel.getId());
        Assert.assertTrue(result==1);
	}
}
