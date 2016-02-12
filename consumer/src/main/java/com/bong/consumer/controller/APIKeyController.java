package com.bong.consumer.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * APIKeyController.java - class that send apikey from cloud foundry env.
 * @author kimbongchan
 *
 */
@RestController
@RequestMapping("apikey")
public class APIKeyController {
	
	/**
	 * Parse apikey form cloud foundry env.
	 * @return apikey
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getAPIKey() {
		System.out.println(System.getenv("VCAP_SERVICES"));
		JSONObject credential = new JSONObject(System.getenv("VCAP_SERVICES"));
		JSONObject calcaulatorInfo = (JSONObject) ((JSONArray) credential.get("Calculator")).get(0);
		String apikey = (String) ((JSONObject) calcaulatorInfo.get("credentials")).get("APIKEY");
		
		return apikey;
	}
}
