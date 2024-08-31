package com.device.model;

import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Document
public class Device {

	@Id
	private long deviceId;
	private String deviceName;
	private	String osVersion;
	private String browserName;
	private int browserVersion;
	private int hitCount;

}
