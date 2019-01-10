/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import services.ActorService;
import services.CustomerService;
import services.HandyWorkerService;
import services.SponsorService;
import domain.Actor;
import domain.Customer;
import domain.HandyWorker;
import domain.Sponsor;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private SponsorService		sponsorService;


	// Show -------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView result;
		final int id = LoginService.getPrincipal().getId();
		final Actor actor = this.actorService.findByUserAccountId(id);

		result = new ModelAndView("profile/show");
		final List<Authority> au = (List<Authority>) actor.getUserAccount().getAuthorities();
		final Authority auth = au.get(0);
		if (auth.equals("HANDY_WORKER")) {
			final HandyWorker hw = this.handyWorkerService.findById(actor.getId());
			result.addObject("handyWorker", hw);
		}
		result.addObject("actor", actor);

		return result;
	}
	// Edit GET -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editGet() {
		ModelAndView result;

		final int id = LoginService.getPrincipal().getId();
		final Actor actor = this.actorService.findByUserAccountId(id);

		result = new ModelAndView("profile/edit");
		result.addObject("actor", actor);
		return result;
	}

	//Edit POST
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView editPost(@Valid final Actor a, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			final List<Authority> au = (List<Authority>) a.getUserAccount().getAuthorities();
			final String authority = au.get(0).getAuthority();
			final int id = a.getId();
			if (authority.equals("CUSTOMER")) {
				final Customer customer = this.customerService.findById(id);
				this.customerService.save(customer);
			} else if (authority.equals("HANDY_WORKER")) {
				final HandyWorker hw = this.handyWorkerService.findById(id);
				this.handyWorkerService.save(hw);
			} else {
				final Sponsor sp = this.sponsorService.findById(id);
				this.sponsorService.save(sp);
			}
			result = new ModelAndView("redirect:show");
		} else {
			result = new ModelAndView("redirect:edit");
			result.addObject("actor", a);
			result.addObject("errors", binding);
		}
		return result;
	}
}
