package com.bong.calculator.controller;

import java.util.HashMap;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bong.calculator.model.Calculator;
import com.bong.calculator.model.UserManager;

/**
 * CalculationController.java - Controller for request about calculator api.
 * @author kimbongchan
 *
 */
@RestController
@RequestMapping("/calculation")
public class CalculationController {

	@Autowired
	private Calculator calculator;
	@Autowired
	private UserManager userManager;
	
	/**
	 * Calculate a operation.
	 * @param data apikey, operation
	 * @return result of operation with success or fail flag
	 */
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject calculate(@RequestBody HashMap<String, String> param) {
		JSONObject result = new JSONObject();
		String apikey = param.get("apikey");
		if (userManager.isAvailable(apikey)) {
			result.put("result", calculator.calculate(param.get("operation")));
			result.put("success", true);
		} else {
			result.put("success", false);
		}

		return result;
	}
}
