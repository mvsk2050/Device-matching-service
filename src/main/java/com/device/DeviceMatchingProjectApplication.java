package com.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//import com.device.config.AerospikeConfigProperties;

@SpringBootApplication
@ComponentScan("com.device.*")
public class DeviceMatchingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceMatchingProjectApplication.class, args);
	}

}
