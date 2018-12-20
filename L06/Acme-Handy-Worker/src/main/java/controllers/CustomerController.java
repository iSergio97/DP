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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.CustomerService;
import domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CustomerService	customerService;


	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Register ---------------------------------------------------------------

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
	public ModelAndView registerPost(@RequestParam(value = "id") final int id, @RequestParam(value = "version") final int version, @RequestParam(value = "name") final String name, @RequestParam(value = "middleName") final String middleName, @RequestParam(
		value = "surname") final String surname, @RequestParam(value = "email") final String email, @RequestParam(value = "phoneNumber") final String phoneNumber, @RequestParam(value = "address") final String address,
		@RequestParam(value = "photo") final String photo, @RequestParam(value = "username") final String username, @RequestParam(value = "password") final String password) {
		ModelAndView result;
		Customer customer;
		UserAccount userAccount;
		boolean error;

		error = false;

		if (id == 0) {
			customer = this.customerService.create();
			userAccount = customer.getUserAccount();
			userAccount.setUsername(username);
			if (password.isEmpty())
				error = true;
			else
				userAccount.setPassword(password);
		} else {
			customer = this.customerService.findById(id);
			userAccount = customer.getUserAccount();
			userAccount.setPassword(password);
			if (!password.isEmpty())
				userAccount.setPassword(password);
		}

		customer.setName(name);
		customer.setMiddleName(middleName);
		customer.setSurname(surname);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		customer.setAddress(address);
		customer.setPhoto(photo);

		if (!error) {
			this.customerService.save(customer);
			result = new ModelAndView("redirect:login.do");
		} else {
			result = new ModelAndView("customer/register");
			result.addObject("customer", customer);
		}
		return result;
	}

}
