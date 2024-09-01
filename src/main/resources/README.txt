 Need to install docker first to install Aerospike DB (https://docs.docker.com/desktop/install/windows-install/)
 Install Aerospike once docker is installed - https://aerospike.com/docs/server/operations/install/docker-desktop
 Run aerospike DB using the command - docker run -ti --name aerospike_admin_aql --rm aerospike/aerospike-tools aql -h {ipAddress} aerospike/aerospike-server
 Create spring boot project with spring dependencies and aerospike dependency in pom.xml file.
 		<dependency>
           <groupId>com.aerospike</groupId>
           <artifactId>spring-data-aerospike</artifactId>
           <version>2.4.0.RELEASE</version>
       	</dependency>
 Create save api to save the device matching information for the browsers.
 Fetch apis to get the devices info.
 
 List of apis below
 ==================
 
 http://{service-api}/device-project//save
 http://{service-api}/getDeviceById/{deviceId}
 http://{service-api}/getAllDevices
 http://{service-api}/deleteByDeviceId/{deviceId}