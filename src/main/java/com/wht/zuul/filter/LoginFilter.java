package com.wht.zuul.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class LoginFilter extends ZuulFilter {


	@Override
	public Object run() {
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "post";
	}
}
