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
 * APIKeyController.java - Controller for request about APIkey.  
 * @author kimbongchan
 *
 */
@RestController
@RequestMapping("/key")
public class APIKeyController {

	@Autowired
	private UserManager userManager;
	private JSONObject result = new JSONObject();

	private static final String KEY_SUCCESS = "success";
	private static final String KEY_API = "apikey";
	private static final String KEY_ID = "id";
	private static final String KEY_PASSWORD = "password";
	
	/**
	 * Create APIKEY using id, pw.
	 * @param data id, pw
	 * @return success or fail flag
	 */
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject createKey(@RequestBody HashMap<String, String> data) {
		result.clear();
		userManager.createAPIKey(data.get(KEY_ID), data.get(KEY_PASSWORD));
		result.put(KEY_SUCCESS, true);

		return result;
	}
	
	/**
	 * Get APIKEY using id, pw.
	 * @param data id, pw
	 * @return apikey with success or fail flag 
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public JSONObject getKey(@RequestBody HashMap<String, String> data) {
		result.clear();

		String key = userManager.getAPIKey(data.get(KEY_ID), data.get(KEY_PASSWORD));

		result.put(KEY_SUCCESS, true);
		result.put(KEY_API, key);

		return result;
	}
}
