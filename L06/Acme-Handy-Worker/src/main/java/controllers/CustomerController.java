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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccountRepository;
import services.ActorService;
import services.CustomerService;
import domain.Actor;
import domain.Customer;
import domain.MessageBox;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


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
	public ModelAndView registerPost(final Customer customer, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			//			final UserAccount userAccount = new UserAccount();
			//			userAccount.setUsername(customer.getName());
			//			userAccount.setPassword(customer.getName());
			//			final List<Authority> authority = new ArrayList<>();
			//			final Authority au = new Authority();
			//			au.setAuthority(Authority.CUSTOMER);
			//			authority.add(au);
			//			userAccount.setAuthorities(authority);
			//			final UserAccount uaSaved = this.userAccountRepository.save(userAccount);
			//			customer.setUserAccount(uaSaved);
			this.customerService.save(customer);
			result = new ModelAndView("redirect:show.do");
		} else {
			result = new ModelAndView("customer/register");
			result.addObject("customer", customer);
			result.addObject("showError", binding);
		}

		return result;
	}

	// Esto no quedará así... (enviar a ActorController)
	@RequestMapping(value = "/box", method = RequestMethod.GET)
	public ModelAndView boxes() {
		final ModelAndView result;
		final int id = LoginService.getPrincipal().getId();
		Actor customer;
		customer = this.actorService.findByUserAccountId(id);
		final Collection<MessageBox> mb = customer.getMessageBoxes();
		result = new ModelAndView("customer/box");

		result.addObject("messageBoxes", mb);

		return result;
	}
}
