package com.bong.calculator.model;

import java.util.HashMap;
import java.util.Set;

/**
 * Calculator.java - class that manage account.
 * @author kimbongchan
 */
public class UserManager {

	public static HashMap<String, String> accountDB;
	public static HashMap<String, HashMap<String, String>> apikeyDB;

	public UserManager() {
		accountDB = new HashMap<String, String>();
		apikeyDB = new HashMap<String, HashMap<String, String>>();
	}
	
	/**
	 * Login.
	 * @param id
	 * @param pwd
	 * @return success or fail flag
	 */
	public boolean login(String id, String pwd) {
		if (accountDB.containsKey(id)) {
			if (accountDB.get(id).equals(pwd)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Create account.
	 * @param id
	 * @param pwd
	 * @return success or fail flag
	 */
	public boolean createUser(String id, String pwd) {
		if (accountDB.containsKey(id)) {
			return false;
		}

		accountDB.put(id, pwd);
		apikeyDB.put(id, new HashMap<String, String>());

		return true;
	}
	
	/**
	 * Create api key.
	 * @param id
	 * @param appId
	 */
	public void createAPIKey(String id, String appId) {
		String apikey = String.valueOf(System.currentTimeMillis());
		apikeyDB.get(id).put(appId, apikey);
	}
	
	/**
	 * Return api key.
	 * @param id
	 * @param appId
	 * @return apikey.
	 */
	public String getAPIKey(String id, String appId) {
		createAPIKey(id, appId);
		return apikeyDB.get(id).get(appId);
	}
	
	/**
	 * Delete account.
	 * @param id
	 */
	public void deleteUser(String id) {
		accountDB.remove(id);
		apikeyDB.remove(id);
	}
	
	/**
	 * Check apikey.
	 * @param apikey
	 * @return available
	 */
	public boolean isAvailable(String apikey) {
		Set<String> keySet = apikeyDB.keySet();
		System.out.println("bong : param apikey = " + apikey);
		System.out.println("bong : param apikeyDB = " + apikeyDB.toString());
		for (String key : keySet) {
			if (apikeyDB.get(key).containsValue(apikey)) {
				return true;
			}
		}
		return false;
	}
}
