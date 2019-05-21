package com.java.bean.scope.bean;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonProviderBean {
	
	@Autowired
	private Provider<PrototypeBean> prototypeBeanProvider;
	
	public PrototypeBean getPrototypeBean() {
		return prototypeBeanProvider.get();
	}
}
