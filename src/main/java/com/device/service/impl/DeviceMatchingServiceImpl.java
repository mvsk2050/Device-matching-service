package com.device.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.device.model.Device;
import com.device.repository.DeviceRepository;
import com.device.service.DeviceMatchingService;
import com.device.vo.DeviceVO;

import lombok.AllArgsConstructor;
import ua_parser.Client;
import ua_parser.Parser;

@Service
@AllArgsConstructor
public class DeviceMatchingServiceImpl implements DeviceMatchingService{

	private static final Logger logger = LoggerFactory.getLogger(DeviceMatchingServiceImpl.class);
	
	private DeviceRepository deviceRepository;

	@Override
	public Device saveDeviceDetails(DeviceVO deviceVO, HttpServletRequest request) {

		logger.info("In DeviceMatchingServiceImpl :: saveDeviceDetails() method : {}", deviceVO.getDeviceName());
		Device device = null;
		try {
			Parser uaParser = new Parser();
			Client c = uaParser.parse(request.getHeader("User-Agent"));
			device = new Device();
			device.setDeviceId(deviceVO.getDeviceId());
			device.setBrowserName(c.userAgent.family);
			device.setBrowserVersion(Integer.parseInt(c.userAgent.major));
			device.setDeviceName(c.device.family);
			device.setHitCount(deviceVO.getHitCount());
			device.setOsVersion(c.os.family);
			device = deviceRepository.save(device);
		} catch (Exception e) {
			logger.error("Error in saving device info : {}", e.getMessage());
		}
		return device;
	}

	@Override
	public Device getDevice(Map<String, String> map) {
		
		
		return null;
	}

	@Override
	public List<DeviceVO> getAllDevices() {
		
		List<DeviceVO> deviceVOs = new ArrayList<>();
		Iterable<Device> devices = deviceRepository.findAll();
		Iterator it = devices.iterator();
		List<Device> devicesList = new ArrayList<>();
		while(it.hasNext()) {
			Device device = (Device)it.next();
			devicesList.add(device);
		}
		for (Device d : devicesList) {
			DeviceVO deviceVO = new DeviceVO();
			deviceVO.setDeviceId(d.getDeviceId());
			deviceVO.setBrowserName(d.getBrowserName());
			deviceVO.setBrowserVersion(d.getBrowserVersion());
			deviceVO.setDeviceName(d.getDeviceName());
			deviceVO.setOsVersion(d.getOsVersion());
			deviceVO.setHitCount(d.getHitCount());
			deviceVOs.add(deviceVO);
		}
		return deviceVOs;
	}

	@Override
	public DeviceVO getDeviceByDeviceId(int deviceId) {
		
		DeviceVO vo = new DeviceVO();
		try {
		Optional<Device> device = deviceRepository.findById(deviceId);
		if(device.isPresent()) {
			Device d = device.get();
			vo.setDeviceId(d.getDeviceId());
			vo.setBrowserName(d.getBrowserName());
			vo.setBrowserVersion(d.getBrowserVersion());
			vo.setDeviceName(d.getDeviceName());
			vo.setOsVersion(d.getOsVersion());
			vo.setHitCount(d.getHitCount());
		} 
		if(!device.isPresent()) {
			vo.setErrorMessage("Data not found");
		}
		}catch(Exception e) {
			logger.error("Exception in fetching device info : {}", e.getMessage());
			throw e;
		}
		return vo;
	}
	
	@Override
	public DeviceVO deleteByDeviceId(int deviceId) {
		
		DeviceVO vo = new DeviceVO();
		try {
			deviceRepository.deleteById(deviceId);
			vo.setMessage("success");
		}catch(Exception e) {
			logger.error("Exception in fetching device info : {}", e.getMessage());
			throw e;
		}
		return vo;

	}

}
