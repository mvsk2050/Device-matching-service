package com.device.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.device.model.Device;
import com.device.service.DeviceMatchingService;
import com.device.vo.DeviceVO;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DeviceMatchingController {
	

	private static final Logger logger = LoggerFactory.getLogger(DeviceMatchingController.class);
	
	@Autowired
	DeviceMatchingService deviceMatchingService;

	@PostMapping("/save")
	public ResponseEntity<DeviceVO> saveDeviceDetails(HttpServletRequest request, @RequestBody DeviceVO  deviceVO) {

		logger.info("In DeviceMatchingController::saveDeviceDetails() method : {}", deviceVO.getDeviceName());
		
		ResponseEntity<DeviceVO> responseEntity = null;
		logger.info("user-agent :: "+request.getHeader("User-Agent"));
		
		try {
		Device dev = deviceMatchingService.saveDeviceDetails(deviceVO, request);
		if(deviceVO.getErrorMessage() != null) {
			deviceVO.setMessage("FAILED");
			responseEntity = new ResponseEntity(deviceVO, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			deviceVO.setMessage("SUCCESS");
			deviceVO.setDeviceId(dev.getDeviceId());
			responseEntity =  new ResponseEntity(deviceVO, HttpStatus.OK);		
			}
		} catch (Exception e) {
			logger.error("Error in saving device info : {}", e.getMessage());
		}
		return responseEntity;

	}
	
	
	@GetMapping("/getDeviceById/{deviceId}")
	public ResponseEntity<DeviceVO> getDeviceById(@PathVariable int deviceId) {

		logger.info("In DeviceMatchingController::getDeviceById() method : {}", deviceId);
		
		ResponseEntity<DeviceVO> responseEntity = null;
		
		try {
		DeviceVO deviceVO = deviceMatchingService.getDeviceByDeviceId(deviceId);
		if(deviceVO.getErrorMessage() != null) {
			deviceVO.setMessage("FAILED");
			responseEntity = new ResponseEntity(deviceVO, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			deviceVO.setMessage("SUCCESS");
			responseEntity = new ResponseEntity(deviceVO, HttpStatus.OK);
		}
		} catch (Exception e) {
			logger.error("Error in fetching device info : {}", e.getMessage());
		}
		return responseEntity;

	}
	
	@GetMapping("/getAllDevices")
	public Map<String, Object> getAlldevices() {

		/*
		 * logger.info("In DeviceMatchingController::getDeviceById() method : {}",
		 * deviceId);
		 */		
		Map<String, Object> map = new HashMap<>();
		List<DeviceVO> devices = new ArrayList<>();
		try {
			devices = deviceMatchingService.getAllDevices();
			if(devices != null) { 
		 		map.put("devices", devices);
		 	} 
		} catch (Exception e) {
			logger.error("Error in fetching device info : {}", e.getMessage());
		}
		return map;

	}
	
	@DeleteMapping("deleteByDeviceId/{deviceId}")
	public ResponseEntity deleteByDeviceId(@PathVariable int deviceId) {
		
		DeviceVO vo = new DeviceVO();
		try {
			vo  = deviceMatchingService.deleteByDeviceId(deviceId);
			vo.setMessage("success");
		}catch(Exception e) {
			logger.error("Exception in fetching device info : {}", e.getMessage());
			throw e;
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);

	}
}
