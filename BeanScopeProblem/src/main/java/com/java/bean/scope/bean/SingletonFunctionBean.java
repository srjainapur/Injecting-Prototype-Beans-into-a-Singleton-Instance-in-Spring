package com.java.bean.scope.bean;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SingletonFunctionBean {
	
	@Autowired
	private Function<String, PrototypeWithParam> beanFactory;
	
	public PrototypeWithParam getPrototypeInstance(String name) {
		return beanFactory.apply(name);
	}
}
