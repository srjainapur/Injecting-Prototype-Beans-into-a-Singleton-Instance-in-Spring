package com.java.bean.scope.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeWithParam {
	
	private String name;
	
	public PrototypeWithParam(String name) {
		this.name=name;
		System.out.println("PrototypeWithParam Default Constructor executed !! name : " + name);
	}
}
