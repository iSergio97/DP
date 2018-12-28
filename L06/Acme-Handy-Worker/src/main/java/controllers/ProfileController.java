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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import domain.Actor;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService	actorService;


	// Show -------------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView result;
		final int id = LoginService.getPrincipal().getId();
		final Actor actor = this.actorService.findByUserAccountId(id);

		result = new ModelAndView("profile/show");

		result.addObject("actor", actor);

		return result;
	}
	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@RequestParam(value = "id") final int id) {
		ModelAndView result;

		final Actor actor = this.actorService.findByUserAccountId(id);

		result = new ModelAndView("profile/edit");

		result.addObject("actor", actor);

		return result;
	}

	// Action-3 ---------------------------------------------------------------		

	@RequestMapping("/action-3")
	public ModelAndView action3() {
		throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
	}

}
