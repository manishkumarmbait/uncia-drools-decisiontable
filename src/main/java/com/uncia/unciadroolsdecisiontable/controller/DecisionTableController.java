package com.uncia.unciadroolsdecisiontable.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uncia.unciadroolsdecisiontable.model.Customer;

@RestController
public class DecisionTableController {

	private final KieContainer kieContainer;

	public DecisionTableController(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	@RequestMapping(value = "/getDiscount", method = RequestMethod.GET, produces = "application/json")
	public Customer getQuestions(@RequestParam(required = true) String name,
			@RequestParam(required = true) Integer age) {
		KieSession kieSession = kieContainer.newKieSession();
		Customer customer1 = new Customer(name);
		customer1.setAge(age);

		kieSession.insert(customer1);
		kieSession.fireAllRules();
		kieSession.dispose();
		return customer1;
	}
}
