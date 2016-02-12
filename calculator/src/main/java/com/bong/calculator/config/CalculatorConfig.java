package com.bong.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bong.calculator.model.Calculator;
import com.bong.calculator.model.UserManager;

/**
 * CalculatorConfig.java - a class that create bean.
 * @author kimbongchan
 *
 */
@Configuration
public class CalculatorConfig {
	/**
	 * Create Calculator bean.
	 * @return Calcularor bean
	 */
	@Bean
	public Calculator calculator() {
		return new Calculator();
	}
	
	/**
	 * Create UserManager bean.
	 * @return UserManager bean
	 */
	@Bean
	public UserManager userManager() {
		return new UserManager();
	}
}
