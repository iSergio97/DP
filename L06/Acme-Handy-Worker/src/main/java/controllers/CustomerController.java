/*
 * CustomerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}


	@Autowired
	private CustomerService	customerService;


	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("customer/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;
		result = new ModelAndView("customer/action-2");

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerGet() {
		ModelAndView result;
		Customer customer;
		customer = this.customerService.create();

		result = new ModelAndView("customer/register");
		result.addObject("customer", customer);
		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerPost(@Valid final Customer customer, final BindingResult bindingResult) {

		ModelAndView result;

		if (!bindingResult.hasErrors()) {
			this.customerService.save(customer);
			result = new ModelAndView("redirect:login.do");
		} else {
			result = new ModelAndView("customer/register");
			result.addObject("customer", customer);
		}
		return result;
	}
}
