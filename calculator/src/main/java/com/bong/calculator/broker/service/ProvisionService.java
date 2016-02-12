package com.bong.calculator.broker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationResponse;
import org.springframework.cloud.servicebroker.model.OperationState;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

import com.bong.calculator.model.UserManager;

/**
 * ProvisionService.java - a class that response for create-service.
 * Cloud Foundry Create-service using this class(/v2/service_instance/:id).
 * @author kimbongchan
 *
 */
@Service
public class ProvisionService implements ServiceInstanceService {

	@Autowired
	private UserManager userManager;

	/**
	 * This method is called when Cloud Foundry try to create service.
	 * Create account serviceID to use Calculator SaaS.
	 */
	@Override
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
		String id = request.getServiceInstanceId();
		String pwd = "password";

		if (!userManager.createUser(id, pwd)) {
			throw new ServiceInstanceExistsException(request.getServiceInstanceId(),
					request.getServiceDefinitionId());
		}

		return new CreateServiceInstanceResponse();
	}

	/**
	 * This method is called when Cloud Foundry try to remove service.
	 * Delete account from Calculator SaaS.
	 */
	@Override
	public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
		userManager.deleteUser(request.getServiceInstanceId());

		return new DeleteServiceInstanceResponse();
	}

	@Override
	public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest arg0) {
		return new GetLastServiceOperationResponse(OperationState.SUCCEEDED);
	}

	@Override
	public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest arg0) {
		return new UpdateServiceInstanceResponse();
	}

}
