package com.fist;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableHystrix
@EnableHystrixDashboard
/**
 * 关于@EnableHystrix和@EnableCircuitBreaker的区别：
 * 查看@EnableHystrix的源码可以发现，它继承了@EnableCircuitBreaker，并对它进行了在封装。
 * 这两个注解都是激活hystrix的功能，我们根据上面代码得出来结论，只需要在服务启动类加入@EnableHystrix
 * 注解即可，无须增加@EnableCircuitBreaker注解，本身@EnableHystrix注解已经涵盖了
 * EnableCircuitBreaker的功能。
 */
//@EnableCircuitBreaker
public class ServiceHiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHiApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/hi")
	@HystrixCommand(fallbackMethod = "hiError")
	public String home(@RequestParam(value = "name", defaultValue = "Li yang") String name) {
		return "hi " + name + " ,i am from port:" + port;
	}

	public String hiError(String name) {
		return "hi " + name + "sorry,error!";
	}

	//这个bean主要是解决/hystrix.stream视图无法打开的问题；
	@Bean
	public ServletRegistrationBean hystrixMetricsStreamServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
		registration.addUrlMappings("/actuator/hystrix.stream");
		return registration;
	}
}
