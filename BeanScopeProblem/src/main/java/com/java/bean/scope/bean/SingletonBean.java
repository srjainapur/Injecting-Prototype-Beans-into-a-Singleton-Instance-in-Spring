package com.java.bean.scope.bean;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
	
	@Autowired
	private PrototypeBean prototypeBean;
	
	public SingletonBean() {
		System.out.println("SingletonBean default Constructor executed");
	}
	
	public PrototypeBean getPrototypeBean() {
		System.out.println(String.valueOf(LocalTime.now()));
		return prototypeBean;
	}
}
