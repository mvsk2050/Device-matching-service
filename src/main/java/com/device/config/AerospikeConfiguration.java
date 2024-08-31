package com.device.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.config.AerospikeDataSettings;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

import com.aerospike.client.Host;
import com.device.repository.DeviceRepository;

@Configuration
@EnableConfigurationProperties(AerospikeConfigProperties.class)
@EnableAerospikeRepositories(basePackageClasses = DeviceRepository.class)
public class AerospikeConfiguration extends AbstractAerospikeDataConfiguration {

	/*
	 * @Autowired private AerospikeConfigProperties configProperties;
	 */

	   @Override
	   protected Collection<Host> getHosts() {
	       return Collections.singleton(new Host("localhost", 3000));
	   }

	   @Override
	   protected String nameSpace() {
	       return "test";
	   }

		/*
		 * @Bean public AerospikeDataSettings aerospikeDataSettings() { return
		 * AerospikeDataSettings.builder().scansEnabled(true).build(); }
		 */

}
