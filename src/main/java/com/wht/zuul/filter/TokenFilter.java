package com.wht.zuul.filter;

import java.util.Date;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.wht.zuul.bean.User;
import com.wht.zuul.config.Constant;
import com.wht.zuul.util.JwtUtil;

@Component
public class TokenFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(TokenFilter.class);

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		try {
			String requestURL = request.getServletPath().toString();
			System.out.println("请求URL:"+requestURL);
			if (requestURL.indexOf("login")!=-1) {
				return null;
			}
			if (requestURL.indexOf("logout")!=-1) {
				response.setHeader("accessToken", "");
				return null;
			}
			logger.info("--------------------TOKENFILTER------------------------");
			String accessToken = request.getHeader("accessToken");
			logger.info("请求token:" + accessToken);
			if (StringUtils.isBlank(accessToken)) {
				logger.error(">>>>>>>>>>AccessToken is empty");
				// 过滤该请求，不往下级服务去转发请求，到此结束
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(401);
				ctx.setResponseBody("{\"status\":401,\"result\":\"accessToken为空,请登录!\"}");
				ctx.getResponse().setContentType(
						"application/json;charset=UTF-8");
				return null;
			}
			Claims claims = JwtUtil.parseJWT(accessToken);
			String refreshToken = tokenExpire(claims);
			response.setHeader("accessToken", refreshToken);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"status\":401,\"result\":\"accessToken已过期,请重新登录!\"}");
			ctx.getResponse().setContentType("application/json;charset=UTF-8");
			return null;
		}
		// 如果有token，则进行路由转发
		logger.info("access token ok");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	private String tokenExpire(Claims claims) throws Exception{
		Date date = claims.getExpiration();
		String refreshToken = null;
		if (date.getTime() < new Date().getTime()) {
			logger.info("--------------token已失效");
			String json = claims.getSubject();
			User user = JSONObject.parseObject(json, User.class);
			String subject = JwtUtil.generalSubject(user);
			refreshToken = JwtUtil.createJWT(Constant.JWT_ID, subject,
					Constant.JWT_APP_TTL);
			logger.info("用户信息:" + subject + "------刷新后的token:"
					+ refreshToken);
		} else {
			logger.info("--------------token未失效");
			long time = new Date().getTime() - date.getTime();
			String json = claims.getSubject();
			User user = JSONObject.parseObject(json, User.class);
			String subject = JwtUtil.generalSubject(user);
			refreshToken = JwtUtil
					.createJWT(Constant.JWT_ID, subject, time);
		}
		return refreshToken;
	}
	
	
}
