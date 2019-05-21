package com.java.bean.scope.bean;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonObjectFactoryBean {
	
	@Autowired
	private ObjectFactory<PrototypeBean> prototypeBeanObjectFactory;
	
	public PrototypeBean getPrototypeBean() {
		return prototypeBeanObjectFactory.getObject();
	}
}
