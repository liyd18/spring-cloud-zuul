package com.wht.zuul.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wht.zuul.bean.ResultData;
import com.wht.zuul.bean.User;
import com.wht.zuul.config.Constant;
import com.wht.zuul.util.JwtUtil;

@RestController
public class AccessToken {

	@RequestMapping("getToken")
	public String getToken(HttpServletResponse res,@RequestBody User user) throws Exception{
		String subject = JwtUtil.generalSubject(user);
		String refreshToken = JwtUtil.createJWT(Constant.JWT_ID, subject, Constant.JWT_APP_TTL);
		return refreshToken;
	}
	
	@RequestMapping("login")
	public ResultData<User> login(){
		ResultData<User> data = new ResultData<User>();
		User u = new User();
		u.setMobile("13012346578");
		u.setRole("0");
		data.setData(u);
		data.setStatus(data.STATUS_NORMAL);
		return data;
	}
	
	
	@RequestMapping("getVersion")
	public String getVersion(HttpServletRequest request){
		String agent=request.getHeader("User-Agent").toLowerCase();
        System.out.println(agent);
        System.out.println("浏览器版本："+getBrowserName(agent));
		return agent;
		
	}
	/**
	  * 获取浏览器版本信息
	  * @Title: getBrowserName
	  * @data:2015-1-12下午05:08:49
	  * @author:wolf
	  *
	  * @param agent
	  * @return
	  */

	public String getBrowserName(String agent) {
	  if(agent.indexOf("msie 7")>0){
	   return "ie7";
	  }else if(agent.indexOf("msie 8")>0){
	   return "ie8";
	  }else if(agent.indexOf("msie 9")>0){
	   return "ie9";
	  }else if(agent.indexOf("msie 10")>0){
	   return "ie10";
	  }else if(agent.indexOf("msie")>0){
	   return "ie";
	  }else if(agent.indexOf("opera")>0){
	   return "opera";
	  }else if(agent.indexOf("opera")>0){
	   return "opera";
	  }else if(agent.indexOf("firefox")>0){
	   return "firefox";
	  }else if(agent.indexOf("webkit")>0){
	   return "webkit";
	  }else if(agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){
	   return "ie11";
	  }else{
	   return "Others";
	  }
	 }
}
