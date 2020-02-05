package com.netapp.zapi;

import com.netapp.common.zapi.annotation.APIRequest;
import com.netapp.common.zapi.annotation.Api;
import com.netapp.common.zapi.annotation.Typedef;
import org.reflections.Reflections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
public class ZapiServiceApplication {

	public static Map<String, Class> APIMAP = new HashMap<>();
	public static Map<String, Class> TYPEDEFMAP = new HashMap<>();

	public static void main(String[] args) {
		System.out.println("Scanning using Reflections:");

		Reflections ref = new Reflections("com.netapp.ontap.api");
		for (Class<?> cl : ref.getTypesAnnotatedWith(Api.class)) {
			Api findable = cl.getAnnotation(Api.class);
			if(APIRequest.class.isAssignableFrom(cl)) {
				APIMAP.put(findable.value(), cl);
			}
		}
		for (Class<?> cl : ref.getTypesAnnotatedWith(Typedef.class)) {
			Typedef findable = cl.getAnnotation(Typedef.class);
			TYPEDEFMAP.put(findable.value(), cl);
		}
		SpringApplication.run(ZapiServiceApplication.class, args);
	}

}
