package com.fist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * web读取配置的方式：
 *  /{application}/{profile}[/{label}]
 * 	/{application}-{profile}.yml
 * 	/{label}/{application}-{profile}.yml
 * 	/{application}-{profile}.properties
 * 	/{label}/{application}-{profile}.properties
 * application：应用名 ,也就是spring.application.name
 * label：分支名，默认master
 * profile：环境名，环境可以在配置文件的属性名是env,不设置也可以
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
