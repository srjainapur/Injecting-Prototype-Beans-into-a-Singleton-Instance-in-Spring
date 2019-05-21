package com.java.bean.scope;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.java.bean.scope.bean.PrototypeBean;
import com.java.bean.scope.bean.PrototypeWithParam;
import com.java.bean.scope.bean.SingletonAppContextBean;
import com.java.bean.scope.bean.SingletonBean;
import com.java.bean.scope.bean.SingletonFunctionBean;
import com.java.bean.scope.bean.SingletonLookupBean;
import com.java.bean.scope.bean.SingletonObjectFactoryBean;
import com.java.bean.scope.bean.SingletonProviderBean;

@SpringBootApplication
public class BeanScopeProblemApplication {
	
	@Bean
	public Function<String, PrototypeWithParam> beanFactory() {
		return name -> prototypeBeanWithParam(name);
	}
	
	@Bean
	@Scope(value = "prototype")
	public PrototypeWithParam prototypeBeanWithParam(String name) {
	       return new PrototypeWithParam(name);
	}
	
	@Bean
    public SingletonFunctionBean singletonFunctionBean() {
        return new SingletonFunctionBean();
    }
	
	public static void main(String[] args) {
		ConfigurableApplicationContext configAppCtx = SpringApplication.run(BeanScopeProblemApplication.class, args);
		
		//System.out.println("Get singleton bean instance first time");
		SingletonBean firstSbean = (SingletonBean)configAppCtx.getBean(SingletonBean.class);
		PrototypeBean firstPBean = firstSbean.getPrototypeBean();
		
		// System.out.println("Get singleton bean instance second time");
		SingletonBean secondSbean = (SingletonBean)configAppCtx.getBean(SingletonBean.class);
		PrototypeBean secondPBean = secondSbean.getPrototypeBean();
		System.out.println("Hashcode of first Prototype Bean " + secondPBean.hashCode());
		System.out.println("Hashcode of first Prototype Bean " + secondPBean.hashCode());
		
		
		if(firstPBean.equals(secondPBean)) {
			System.out.println("The same instance should be returned");
		} else {
			System.out.println("The DIFFERENT instance should be returned");
		}
		
		System.out.println("\nInjecting ApplicationContext Demo");
		SingletonAppContextBean firstAppCtxBean = configAppCtx.getBean(SingletonAppContextBean.class);
		SingletonAppContextBean secondAppCtxBean = configAppCtx.getBean(SingletonAppContextBean.class);

		PrototypeBean firstPrototypeBean = firstAppCtxBean.getPrototypeBean();
		PrototypeBean secondPrototypeBean = secondAppCtxBean.getPrototypeBean();
		
		System.out.println(firstPrototypeBean.hashCode());
		System.out.println(secondPrototypeBean.hashCode());
		
		if (firstPrototypeBean.equals(secondPrototypeBean)) {
			System.out.println("firstPrototypeBean & secondPrototypeBean refer to same bean");
		} else {
			System.out.println("firstPrototypeBean & secondPrototypeBean refer to different bean");
		}
		
		System.out.println("\nMethod Injection using @Lookup annotation");
		SingletonLookupBean firstSLookupBean = configAppCtx.getBean(SingletonLookupBean.class);
		SingletonLookupBean secondSLookupBean = configAppCtx.getBean(SingletonLookupBean.class);
		
		PrototypeBean firstLookupPBean = firstSLookupBean.getPrototypeBean();
		PrototypeBean secondLookupPBean = secondSLookupBean.getPrototypeBean();
		
		if(firstLookupPBean.equals(secondLookupPBean)) {
			System.out.println("Both firstLookupPBean & secondLookupPBean beans are same");
		} else {
			System.out.println("Both firstLookupPBean & secondLookupPBean beans are DIFFERENT!!!!");
			System.out.println(firstLookupPBean.hashCode());
			System.out.println(secondLookupPBean.hashCode());
		}
		
		System.out.println("\njavax.inject API Provider Interface Demo");
		SingletonProviderBean firstSProviderBean = configAppCtx.getBean(SingletonProviderBean.class);
		SingletonProviderBean secondSProviderBean = configAppCtx.getBean(SingletonProviderBean.class);
		
		PrototypeBean firstProtoPBean = firstSProviderBean.getPrototypeBean();
		PrototypeBean secondProtoPBean = secondSProviderBean.getPrototypeBean();
		
		if(firstProtoPBean.equals(secondProtoPBean)) {
			System.out.println("Both firstProtoPBean & secondProtoPBean are same");
		} else {
			System.out.println("Both firstProtoPBean & secondProtoPBean are DIFFERENT !!!!!!!!!!");
			System.out.println(firstProtoPBean.hashCode());
			System.out.println(secondProtoPBean.hashCode());
		}
		
		System.out.println("\nObjectFactory Interface");
		SingletonObjectFactoryBean firstOFSBean = configAppCtx.getBean(SingletonObjectFactoryBean.class);
		SingletonObjectFactoryBean secondOFSBean = configAppCtx.getBean(SingletonObjectFactoryBean.class);
		
		PrototypeBean firstOFProtoBean = firstOFSBean.getPrototypeBean();
		PrototypeBean secondOFProtoBean = secondOFSBean.getPrototypeBean();
		if(firstOFProtoBean.equals(secondOFProtoBean)) {
			System.out.println("Both firstOFProtoBean & secondOFProtoBean instances are same");
		} else {
			System.out.println("Both firstOFProtoBean & secondOFProtoBean instances are Different !!!!");
			System.out.println(firstOFProtoBean.hashCode());
			System.out.println(secondOFProtoBean.hashCode());
		}
		
		System.out.println("\nCreate a Bean at Runtime Using java.util.Function");
		SingletonFunctionBean firstSFuncBean = configAppCtx.getBean(SingletonFunctionBean.class);
		SingletonFunctionBean secondSFuncBean = configAppCtx.getBean(SingletonFunctionBean.class);
		
		PrototypeWithParam firstProtoParam = firstSFuncBean.getPrototypeInstance("Avishka");
		PrototypeWithParam secondProtoParam = secondSFuncBean.getPrototypeInstance("Avishka");
		if(firstProtoParam.equals(secondProtoParam)) {
			System.out.println("Both firstProtoParam & secondProtoParam instance are same");
		} else {
			System.out.println("Both firstProtoParam & secondProtoParam instance are Different !!!!!");
			System.out.println(firstProtoParam.hashCode());
			System.out.println(secondProtoParam.hashCode());
		}
	}

}
