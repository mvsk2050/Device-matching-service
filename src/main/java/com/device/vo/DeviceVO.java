package com.device.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DeviceVO {

	@NotNull
	private long deviceId;
	@NotNull
	private String deviceName;
	@NotNull
	private String osVersion;
	@NotNull
	private String browserName;
	@NotNull
	private int browserVersion;
	@NotNull
	private int hitCount;
	
	private String errorMessage;
	
	private String message;
}
