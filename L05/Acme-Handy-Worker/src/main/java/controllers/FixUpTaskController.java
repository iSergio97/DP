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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.FixUpTaskCategoryService;
import services.WarrantyService;
import services.WorkPlanService;
import domain.Application;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTaskCategory;
import domain.Warranty;
import domain.WorkPlan;

@Controller
@RequestMapping("/fix-up-task")
public class FixUpTaskController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CustomerService				customerService;

	@Autowired
	private FixUpTaskCategoryService	fixUpTaskCategoryService;

	@Autowired
	private WarrantyService				warrantyService;

	@Autowired
	private WorkPlanService				workPlanService;


	// Constructors -----------------------------------------------------------

	public FixUpTaskController() {
		super();
	}

	// Create/Edit Fix-Up Task ------------------------------------------------

	@RequestMapping("/customer_edit")
	public ModelAndView action1() {
		// Create result object
		final ModelAndView result;
		result = new ModelAndView("fix-up-task/customer_edit");

		// Add logged customer
		final Customer customer = this.customerService.findPrincipal();
		result.addObject("customer", customer);

		// Add all fix-up task categories
		final List<FixUpTaskCategory> fixUpTaskCategories = this.fixUpTaskCategoryService.findAll();
		result.addObject("fixUpTaskCategories", fixUpTaskCategories);

		// Add an empty collection of Application
		final List<Application> applications = new ArrayList<>();
		result.addObject("applications", applications);

		// Add all warranties
		final List<Warranty> warranties = this.warrantyService.findAll();
		result.addObject("warranties", warranties);

		// Add a built-from-scratch work plan
		final WorkPlan workPlan = this.workPlanService.create();
		result.addObject("workPlan", workPlan);

		// Add an empty list of complaints
		final List<Complaint> complaints = new ArrayList<>();
		result.addObject("complaints", complaints);

		return result;
	}
}
