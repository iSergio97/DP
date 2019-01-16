
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.ComplaintService;
import services.CustomerService;
import services.RefereeService;
import domain.Actor;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;

@Controller
@RequestMapping("/complaint")
public class ComplaintController extends AbstractController {

	//-- Services --

	@Autowired
	private ComplaintService	complaintService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private RefereeService		refereeService;


	//-- Constructors --

	public ComplaintController() {
		super();
	}

	// Display --------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(value = "id") final int id) {
		final ModelAndView result;
		Complaint complaint;
		Actor actor;

		complaint = this.complaintService.findById(id);
		actor = this.actorService.findPrincipal();

		Assert.isTrue(actor.getUserAccount().getAuthorities().contains("REFEREE") || actor.getUserAccount().getAuthorities().contains("CUSTOMER"));

		result = new ModelAndView("complaint/display");
		result.addObject("complaint", complaint);

		return result;
	}

	//Create----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Complaint complaint;

		complaint = this.complaintService.create();

		result = this.createEditModelAndView(complaint);

		return result;
	}

	//Save-----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Complaint complaint, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(complaint);
		else
			try {
				this.complaintService.save(complaint);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(complaint, "complaint.commit.error");
			}
		return result;
	}

	// List-----------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Complaint> complaints;
		Actor a;
		Customer c;

		a = this.actorService.findPrincipal();
		Assert.isTrue(a.getUserAccount().getAuthorities().contains(Authority.CUSTOMER));
		c = this.customerService.findPrincipal();

		complaints = this.complaintService.getComplaints(c);

		result = new ModelAndView("complaint/list");
		result.addObject("complaints", complaints);

		return result;
	}

	//	@RequestMapping(value = "/list", method = RequestMethod.GET)
	//	public ModelAndView listReferee() {
	//		ModelAndView result;
	//		Collection<Complaint> complaints;
	//		Actor a;
	//		Referee r;
	//		a = this.actorService.findPrincipal();
	//		Assert.isTrue(a.getUserAccount().getAuthorities().contains(Authority.REFEREE));
	//		r = this.refereeService.findById(a.getId());
	//
	//		complaints = this.complaintService.getComplaints(r);
	//
	//		result = new ModelAndView("complaint/list");
	//		result.addObject("complaints", complaints);
	//
	//		return result;
	//	}

	//Ancillary Methods-----------------------------------

	protected ModelAndView createEditModelAndView(final Complaint complaint) {

		ModelAndView result;

		result = this.createEditModelAndView(complaint, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint, final String messageCode) {
		ModelAndView result;
		FixUpTask fut;

		fut = complaint.getFixUpTask();

		result = new ModelAndView("complaint/edit");
		result.addObject("complaint", complaint);
		result.addObject("fixUpTask", fut);
		result.addObject("message", messageCode);

		return result;

	}
}
