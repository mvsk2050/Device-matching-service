package com.device.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "aerospike")
public class AerospikeConfigProperties {

	private String host;
	private int port;
	private String nameSpace;
}
