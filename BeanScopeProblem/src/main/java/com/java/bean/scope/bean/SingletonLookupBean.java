package com.java.bean.scope.bean;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class SingletonLookupBean {
	
	public SingletonLookupBean() {
		System.out.println("Default constructor of SingletonLookupBean() executed");
	}
	
	@Lookup
	public PrototypeBean getPrototypeBean() {
		return null;
	}
}
