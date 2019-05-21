package com.java.bean.scope.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SingletonAppContextBean implements ApplicationContextAware {
	
	public SingletonAppContextBean() {
		System.out.println("SingletonAppContextBean Constructor is executed");
	}

	private ApplicationContext applicationContext;
	
	public PrototypeBean getPrototypeBean() {
		return applicationContext.getBean(PrototypeBean.class);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
