package com.crm.demo.controller;
import com.crm.common.BaqiException;
import com.crm.common.CustomExceptionEnum;
import com.crm.common.MyResEntity;
import com.crm.common.RSAUtil;
import com.crm.common.SQLFilter;
import com.crm.demo.model.UserModel;
import com.crm.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/***
 * 
 * @author jacy
 *
 */
@Controller
public class UserController {
	
    @Autowired
    private UserService userService;
    
    /**
     * 登录页面
     * @return index 视图
     */
    @RequestMapping("/index")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    
    /**
     * center
     * @return userModel
     */
    @RequestMapping("/center")
    public ModelAndView center(HttpSession session) {
        ModelAndView mv = new ModelAndView("center");
		Integer userId= (Integer)session.getAttribute("user");
		mv.addObject("user", userService.select(userId));
        return mv;
    }
    
    /**
     * 登录
     * ResponseBody 
     * @return JSON 字符串
     * @throws BaqiException 
     */
    @RequestMapping(value = "/api/login",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public MyResEntity login(HttpServletRequest request,@RequestParam("name") String name,@RequestParam("pwd") String pwd) throws BaqiException {
    	try {
    		pwd=new String(RSAUtil.decrypt(Base64.getDecoder().decode(pwd), RSAUtil.getPrivateKey(RSAUtil.privateKeyStr)));
    	} catch (Exception e) {
			throw new BaqiException(CustomExceptionEnum.RSA_DECODE_ERROR);
		}
    	UserModel user = userService.login( SQLFilter.sqlInject(name),  SQLFilter.sqlInject(pwd));
		if(user==null){
			throw new BaqiException(CustomExceptionEnum.LOGIN_ERROR);
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user.getId());
        return new MyResEntity("登录成功");
    }

}
