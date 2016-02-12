package com.bong.calculator.controller;

import java.util.HashMap;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bong.calculator.model.UserManager;


/**
 * CalculationController.java - Controller for request about calculator user.
 * @author kimbongchan
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserManager userManager;
	private JSONObject result = new JSONObject();

	private static final String KEY_SUCCESS = "success";
	private static final String KEY_ID = "id";
	private static final String KEY_PASSWORD = "password";
	
	/**
	 * Create account.
	 * @param data ID, PW
	 * @return success or fail flag
	 */
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject createUser(@RequestBody HashMap<String, String> data) {
		result.clear();

		if (userManager.createUser(data.get(KEY_ID), data.get(KEY_PASSWORD))) {
			result.put(KEY_SUCCESS, true);
		} else {
			result.put(KEY_SUCCESS, false);
		}

		return result;
	}
	
	/**
	 * Create account.
	 * @param data ID
	 * @return success or fail flag
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public JSONObject delete(@RequestBody HashMap<String, String> data) {
		result.clear();
		userManager.deleteUser(data.get(KEY_ID));
		result.put(KEY_SUCCESS, true);

		return result;
	}
}
