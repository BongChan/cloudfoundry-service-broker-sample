# cloudfoundry-service-broker-sample
cloudfoundry-service-broker-sample is a sample project that implements the service-broker of cloudfoundry.

Service-broker sample provided by cloudfoundry is too simple to understand mechanism of service-broker.

But this project fully implements SaaS service, servcie-broker, consumer.

By running this project you can understand service-broker's mechanism easily.

## structure
cloudfoundry-service-broker-sample is composed of two projects.

- calculator

	The application that receives the operation and return operation result. It is service of the SaaS type
	`com.bong.calculator.broker` the contents of the sub- source implementation of the broker.

- consumer

	The application is calculator using the calculator api.

## How to run
Further information: http://docs.cloudfoundry.org/services/api.html

1. Build projects and deploy.

	- mvn install
	- cf push

2. Create service-broker
ID, PW is for http basic authentication, but calculator project is not use it.
Use dummy data for ID, PW parametsers. 
ref: `com.bong.calculator.broker.config.CatalogConfig.java'

	- cf create-service-broker BROKER_NAME ID PW URL
	- URL: URL of calculator project.

3. Set access
Set to allow access to the servcie-broker from particular org.
Related information is available at cf service-access.

	- cf enable-service-access SERVICE_NAME -p PLAN -o ORG

4. Create service-instance
ref: `com.bong.calculator.broker.service.ProvisionService.java'

	- cf create-service SERVICE_NAME PLAN SERVICE_INSTANCE_NAME

5. Bind service
Bind servcie-instance to consumer project.
ref: `com.bong.calculator.broker.service.BindService.java'

	- cf bind-service APP_NAME SERVCE_INSTANCE_NAME