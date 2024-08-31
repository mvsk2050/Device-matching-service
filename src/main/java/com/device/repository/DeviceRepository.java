package com.device.repository;

import org.springframework.data.aerospike.repository.AerospikeRepository;

import com.device.model.Device;

public interface DeviceRepository extends AerospikeRepository<Device, Integer>{


}
