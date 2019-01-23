package me.zhangbaoning.system1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("me.zbn.ActiveMQsynch.entity")
@SpringBootApplication
public class SystemAApplication {
	public static void main(String[] args) {
		SpringApplication.run(SystemAApplication.class, args);
	}

}

