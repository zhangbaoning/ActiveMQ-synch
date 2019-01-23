package me.zhangbaoning.system2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("me.zbn.ActiveMQsynch.entity")
@SpringBootApplication
public class SystemBApplication {
	public static void main(String[] args) {
		SpringApplication.run(SystemBApplication.class, args);
	}

}

