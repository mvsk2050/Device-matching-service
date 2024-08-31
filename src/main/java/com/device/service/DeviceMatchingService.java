package com.device.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.device.model.Device;
import com.device.vo.DeviceVO;

public interface DeviceMatchingService {

	public Device saveDeviceDetails(DeviceVO deviceVO, HttpServletRequest request);
	
	public Device getDevice(Map<String, String> map);
	
	public List<DeviceVO> getAllDevices();
	
	public DeviceVO getDeviceByDeviceId(int deviceId);
	
	public DeviceVO deleteByDeviceId(int deviceId);
	
}
