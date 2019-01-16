/*
 * ApplicationController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ApplicationService;
import services.CustomerService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import domain.Actor;
import domain.Application;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService		actorService;
	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private MessageBoxService	messageBoxService;
	@Autowired
	private MessageService		messageService;


	// Constructors -----------------------------------------------------------

	public ApplicationController() {
		super();
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(value = "id") final int id) {
		final ModelAndView result;
		final Application application;
		final Actor actor;

		application = this.applicationService.findById(id);
		actor = this.actorService.findPrincipal();

		// Check principal is either the customer or the handy worker of this application
		Assert.isTrue(application.getHandyWorker().getId() == actor.getId() || application.getFixUpTask().getCustomer().getId() == actor.getId());

		result = new ModelAndView("application/display");
		result.addObject("application", application);

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.POST, params = "addcomment")
	public ModelAndView addComment(@RequestParam(value = "id") final int id, @RequestParam(value = "text") final String text) {
		final ModelAndView result;
		Application application;
		final Actor actor;

		application = this.applicationService.findById(id);
		actor = this.actorService.findPrincipal();

		// Check principal is either the customer or the handy worker of this application
		Assert.isTrue(application.getHandyWorker().getId() == actor.getId() || application.getFixUpTask().getCustomer().getId() == actor.getId());

		final List<String> comments = application.getComments();
		comments.add(text);
		application.setComments(comments);
		application = this.applicationService.save(application);

		result = new ModelAndView("application/display");
		result.addObject("application", application);

		return result;
	}

	// List customer applications ---------------------------------------------

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ModelAndView customer() {
		final ModelAndView result;
		final Collection<Application> applications;
		final Customer customer;

		customer = this.customerService.findPrincipal();
		applications = new ArrayList<Application>();
		for (final FixUpTask f : customer.getFixUpTasks())
			applications.addAll(f.getApplications());
		result = new ModelAndView("application/customer");
		result.addObject("applications", applications);
		result.addObject("currentDate", new Date());

		return result;
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST, params = "accept")
	public ModelAndView customerAccept(@RequestParam(value = "id") final int id) {
		Application application;
		Customer customer;

		application = this.applicationService.findById(id);
		customer = this.customerService.findPrincipal();

		Assert.isTrue(application.getFixUpTask().getCustomer().equals(customer));
		Assert.isTrue(customer.getCreditCard() != null);
		Assert.isTrue(application.getStatus().equals("PENDING"));
		Assert.isTrue(application.getFixUpTask().getTimeLimit().before(new Date()));

		application.setStatus("ACCEPTED");
		application = this.applicationService.save(application);
		
		Message message = this.messageService.create();
		message.setPriority("HIGH");
		message.setSubject("Application accepted");
		message.setBody("Application accepted");
		message.setSender(customer);
		List<Actor> recipients = new ArrayList<>();
		recipients.add(application.getHandyWorker());
		message.setRecipients(recipients);
		List<MessageBox> messageBoxes = new ArrayList<>();
		messageBoxes.add(this.messageBoxService.findByPrincipalAndName(customer.getId(), "OutBox"));
		messageBoxes.add(this.messageBoxService.findByPrincipalAndName(application.getHandyWorker().getId(), "InBox"));
		message.setMessageBoxes(messageBoxes);
		message = this.messageService.save(message);
		for (final MessageBox messageBox : messageBoxes) {
			final Collection<Message> messages = messageBox.getMessages();
			messages.add(message);
			messageBox.setMessages(messages);
			this.messageBoxService.save(messageBox);

		return this.customer();
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST, params = "reject")
	public ModelAndView customerReject(@RequestParam(value = "id") final int id) {
		Application application;
		Customer customer;

		application = this.applicationService.findById(id);
		customer = this.customerService.findPrincipal();

		Assert.isTrue(application.getFixUpTask().getCustomer().equals(customer));
		Assert.isTrue(application.getStatus().equals("PENDING"));
		Assert.isTrue(application.getFixUpTask().getTimeLimit().before(new Date()));

		application.setStatus("REJECTED");
		application = this.applicationService.save(application);

		Message message = this.messageService.create();
		message.setPriority("HIGH");
		message.setSubject("Application rejected");
		message.setBody("Application rejected");
		message.setSender(customer);
		List<Actor> recipients = new ArrayList<>();
		recipients.add(application.getHandyWorker());
		message.setRecipients(recipients);
		List<MessageBox> messageBoxes = new ArrayList<>();
		messageBoxes.add(this.messageBoxService.findByPrincipalAndName(customer.getId(), "OutBox"));
		messageBoxes.add(this.messageBoxService.findByPrincipalAndName(application.getHandyWorker().getId(), "InBox"));
		message.setMessageBoxes(messageBoxes);
		message = this.messageService.save(message);
		for (final MessageBox messageBox : messageBoxes) {
			final Collection<Message> messages = messageBox.getMessages();
			messages.add(message);
			messageBox.setMessages(messages);
			this.messageBoxService.save(messageBox);

		return this.customer();
	}

	// List handy worker applications -----------------------------------------

	@RequestMapping(value = "/handyworker", method = RequestMethod.GET)
	public ModelAndView handyWorker() {
		final ModelAndView result;
		final Collection<Application> applications;
		final HandyWorker handyWorker;

		handyWorker = this.handyWorkerService.findById(this.actorService.findPrincipal().getId());
		applications = handyWorker.getApplications();

		result = new ModelAndView("application/handyworker");
		result.addObject("applications", applications);
		result.addObject("currentDate", new Date());

		return result;
	}

	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(value = "fixuptaskid") final int fixUpTaskId) {
		final ModelAndView result;

		result = new ModelAndView("application/create");
		result.addObject("fixuptaskid", fixUpTaskId);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView create(@RequestParam(value = "fixuptaskid") final int fixUpTaskId, @RequestParam(value = "offeredprice") final int offeredPrice, @RequestParam(value = "comment") final String comment) {
		final ModelAndView result;
		final Application application;
		final HandyWorker handyWorker;
		final FixUpTask fixUpTask;

		handyWorker = this.handyWorkerService.findById(this.actorService.findPrincipal().getId());
		fixUpTask = this.fixUpTaskService.findById(fixUpTaskId);
		application = this.applicationService.create();
		// Set fix-up task
		application.setFixUpTask(fixUpTask);
		final Collection<Application> fixUpTaskApplications = fixUpTask.getApplications();
		fixUpTaskApplications.add(application);
		fixUpTask.setApplications(fixUpTaskApplications);
		this.fixUpTaskService.save(fixUpTask);
		// Set handy worker
		application.setHandyWorker(handyWorker);
		final Collection<Application> handyWorkerApplications = handyWorker.getApplications();
		handyWorkerApplications.add(application);
		handyWorker.setApplications(handyWorkerApplications);
		this.handyWorkerService.save(handyWorker);
		//
		application.setStatus("PENDING");
		final ArrayList<String> comments = new ArrayList<>();
		comments.add(comment);
		application.setComments(comments);
		this.applicationService.save(application);

		result = new ModelAndView("application/create");
		result.addObject("fixuptaskid", fixUpTaskId);

		return result;
	}

}
