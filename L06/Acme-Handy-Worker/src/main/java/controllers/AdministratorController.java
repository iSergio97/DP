/*
 * AdministratorController.java
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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccountRepository;
import services.ActorService;
import services.AdminService;
import services.ApplicationService;
import services.CustomerService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.MessageBoxService;
import services.RefereeService;
import services.ReportService;
import services.SystemConfigurationService;
import domain.Actor;
import domain.Admin;
import domain.MessageBox;
import domain.Referee;
import domain.SystemConfiguration;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService				actorService;
	@Autowired
	private AdminService				adminService;
	@Autowired
	private ApplicationService			applicationService;
	@Autowired
	private CustomerService				customerService;
	@Autowired
	private FixUpTaskService			fixUpTaskService;
	@Autowired
	private HandyWorkerService			handyWorkerService;
	@Autowired
	private MessageBoxService			messageBoxService;
	@Autowired
	private RefereeService				refereeService;
	@Autowired
	private ReportService				reportService;
	@Autowired
	private SystemConfigurationService	systemConfigurationService;
	@Autowired
	private UserAccountRepository		userAccountRepository;


	// Constructors ----------------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Utility methods -------------------------------------------------------------

	private static String join(final Collection<String> strings) {
		String result = "";
		for (final String string : strings)
			result = result + string + ",";
		return result.substring(0, result.length() - 1);
	}

	private static List<String> breakUp(final String string) {
		final List<String> result = new ArrayList<>();
		for (final String s : string.split(","))
			result.add(s);
		return result;
	}

	// Dashboard -------------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView result;

		result = new ModelAndView("administrator/dashboard");

		// QUERY C.1
		// The average, the minimum, the maximum, and the standard deviation of
		// the number of fix-up tasks per user.

		final Double[] fixUpTaskStatistics = this.customerService.getFixUpTaskStatistics();
		result.addObject("fixUpTaskStatisticsMinimum", fixUpTaskStatistics[0]);
		result.addObject("fixUpTaskStatisticsMaximum", fixUpTaskStatistics[1]);
		result.addObject("fixUpTaskStatisticsAverage", fixUpTaskStatistics[2]);
		result.addObject("fixUpTaskStatisticsStandardDeviation", fixUpTaskStatistics[3]);

		// QUERY C.2
		// The average, the minimum, the maximum, and the standard deviation of
		// the number of applications per fix-up task.

		final Double[] applicationStatistics = this.fixUpTaskService.getApplicationStatistics();
		result.addObject("applicationStatisticsMinimum", applicationStatistics[0]);
		result.addObject("applicationStatisticsMaximum", applicationStatistics[1]);
		result.addObject("applicationStatisticsAverage", applicationStatistics[2]);
		result.addObject("applicationStatisticsStandardDeviation", applicationStatistics[3]);

		// QUERY C.3
		// The average, the minimum, the maximum, and the standard deviation of
		// the maximum price of the fix-up tasks.

		final Double[] maximumPriceStatistics = this.fixUpTaskService.getMaximumPriceStatistics();
		result.addObject("maximumPriceStatisticsMinimum", maximumPriceStatistics[0]);
		result.addObject("maximumPriceStatisticsMaximum", maximumPriceStatistics[1]);
		result.addObject("maximumPriceStatisticsAverage", maximumPriceStatistics[2]);
		result.addObject("maximumPriceStatisticsStandardDeviation", maximumPriceStatistics[3]);

		// QUERY C.4
		// The average, the minimum, the maximum, and the standard deviation of
		// the price offered in the applications.

		final Double[] offeredPriceStatistics = this.applicationService.getOfferedPriceStatistics();
		result.addObject("offeredPriceStatisticsMinimum", offeredPriceStatistics[0]);
		result.addObject("offeredPriceStatisticsMaximum", offeredPriceStatistics[1]);
		result.addObject("offeredPriceStatisticsAverage", offeredPriceStatistics[2]);
		result.addObject("offeredPriceStatisticsStandardDeviation", offeredPriceStatistics[3]);

		// QUERY C.5
		// The ratio of pending applications.

		result.addObject("pendingRatio", this.applicationService.getPendingRatio());

		// QUERY C.6
		// The ratio of accepted applications.

		result.addObject("acceptedRatio", this.applicationService.getAcceptedRatio());

		// QUERY C.7
		// The ratio of rejected applications.

		result.addObject("rejectedRatio", this.applicationService.getRejectedRatio());

		// QUERY C.8
		// The ratio of pending applications that cannot change its status because their time period's elapsed.

		result.addObject("expiredRatio", this.applicationService.getExpiredRatio());

		// QUERY C.9
		// The listing of customers who have published at least 10% more fix-up tasks than the average,
		// ordered by number of applications.

		result.addObject("topFixUpTasks", this.customerService.getTopFixUpTasks());

		// QUERY C.10
		// The listing of handy workers who have got accepted at least 10% more applications than the average,
		// ordered by number of applications.

		result.addObject("topApplications", this.handyWorkerService.getTopApplications());

		// QUERY B.1
		// The minimum, the maximum, the average, and the standard deviation of
		// the number of complaints per fix-up task.

		final Double[] complaintStatistics = this.fixUpTaskService.getComplaintStatistics();
		result.addObject("complaintStatisticsMinimum", complaintStatistics[0]);
		result.addObject("complaintStatisticsMaximum", complaintStatistics[1]);
		result.addObject("complaintStatisticsAverage", complaintStatistics[2]);
		result.addObject("complaintStatisticsStandardDeviation", complaintStatistics[3]);

		// QUERY B.2
		// The minimum, the maximum, the average, and the standard deviation of
		// the number of notes per referee report.

		final Double[] noteStatistics = this.reportService.getNoteStatistics();
		result.addObject("noteStatisticsMinimum", noteStatistics[0]);
		result.addObject("noteStatisticsMaximum", noteStatistics[1]);
		result.addObject("noteStatisticsAverage", noteStatistics[2]);
		result.addObject("noteStatisticsStandardDeviation", noteStatistics[3]);

		// QUERY B.3
		// The ratio of fix-up tasks with a complaint.

		result.addObject("complaintRatio", this.fixUpTaskService.getComplaintRatio());

		// QUERY B.4
		// The top 3 customers in terms of complaints.

		result.addObject("topComplaintsCustomer", this.customerService.getTopComplaints().subList(0, 3));

		// QUERY B.5
		// The top-three handy workers in terms of complaints.

		result.addObject("topComplaintsHandyWorker", this.handyWorkerService.getTopComplaints().subList(0, 3));

		return result;
	}

	// System configuration --------------------------------------------------------

	@RequestMapping(value = "/systemconfiguration", method = RequestMethod.GET)
	public ModelAndView systemConfiguration() {
		final ModelAndView result;
		final SystemConfiguration systemConfiguration;

		systemConfiguration = this.systemConfigurationService.getSystemConfiguration();

		result = new ModelAndView("administrator/systemconfiguration");
		result.addObject("message", this.systemConfigurationService.getMessage());
		result.addObject("systemconfigurationname", systemConfiguration.getName());
		result.addObject("systemconfigurationbanner", systemConfiguration.getBanner());
		result.addObject("systemconfigurationmessage", systemConfiguration.getMessage());
		result.addObject("systemconfigurationspamwords", AdministratorController.join(systemConfiguration.getSpamWords()));
		result.addObject("systemconfigurationvat", systemConfiguration.getVAT());
		result.addObject("systemconfigurationdefaultcountrycode", systemConfiguration.getDefaultCountryCode());
		result.addObject("systemconfigurationcreditcardmakers", AdministratorController.join(systemConfiguration.getCreditCardMakers()));
		result.addObject("systemconfigurationpositivewords", AdministratorController.join(systemConfiguration.getPositiveWords()));
		result.addObject("systemconfigurationnegativewords", AdministratorController.join(systemConfiguration.getNegativeWords()));
		result.addObject("systemconfigurationmaximumfinderresults", systemConfiguration.getMaximumFinderResults());

		return result;
	}

	@RequestMapping(value = "/systemconfiguration", method = RequestMethod.POST)
	public ModelAndView systemConfiguration(@RequestParam(value = "systemconfigurationname") final String name, @RequestParam(value = "systemconfigurationbanner") final String banner,
		@RequestParam(value = "systemconfigurationmessage") final String message, @RequestParam(value = "systemconfigurationspamwords") final String spamWords, @RequestParam(value = "systemconfigurationvat") final double VAT, @RequestParam(
			value = "systemconfigurationdefaultcountrycode") final String defaultCountryCode, @RequestParam(value = "systemconfigurationcreditcardmakers") final String creditCardMakers,
		@RequestParam(value = "systemconfigurationpositivewords") final String positiveWords, @RequestParam(value = "systemconfigurationnegativewords") final String negativeWords,
		@RequestParam(value = "systemconfigurationmaximumfinderresults") final int maximumFinderResults) {
		final ModelAndView result;
		final SystemConfiguration systemConfiguration;

		systemConfiguration = this.systemConfigurationService.getSystemConfiguration();
		systemConfiguration.setName(name);
		systemConfiguration.setBanner(banner);
		systemConfiguration.setMessage(message);
		systemConfiguration.setSpamWords(AdministratorController.breakUp(spamWords));
		systemConfiguration.setVAT(VAT);
		systemConfiguration.setDefaultCountryCode(defaultCountryCode);
		systemConfiguration.setCreditCardMakers(AdministratorController.breakUp(creditCardMakers));
		systemConfiguration.setPositiveWords(AdministratorController.breakUp(positiveWords));
		systemConfiguration.setNegativeWords(AdministratorController.breakUp(negativeWords));
		systemConfiguration.setMaximumFinderResults(maximumFinderResults);
		this.systemConfigurationService.save(systemConfiguration);

		result = this.systemConfiguration();

		return result;
	}

	// Register administrator ------------------------------------------------------

	@RequestMapping(value = "/registeradmin", method = RequestMethod.GET)
	public ModelAndView registerAdmin() {
		final ModelAndView result;
		final Admin admin;

		admin = this.adminService.create();
		result = new ModelAndView("administrator/registeradmin");
		result.addObject("admin", admin);

		return result;
	}

	@RequestMapping(value = "/registeradmin", method = RequestMethod.POST)
	public ModelAndView registerAdmin(Admin admin, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			admin = this.adminService.save(admin);
			final String password = new Md5PasswordEncoder().encodePassword(admin.getUserAccount().getPassword(), null);
			admin.getUserAccount().setPassword(password);
			this.userAccountRepository.save(admin.getUserAccount());
			admin.setMessageBoxes(this.messageBoxService.createSystemBoxes());
			for (final MessageBox mb : admin.getMessageBoxes())
				mb.setActor(admin);
			this.messageBoxService.save(admin.getMessageBoxes());
			admin = this.adminService.save(admin);
			result = new ModelAndView("redirect:show.do");
		} else {
			result = new ModelAndView("administrator/registeradmin");
			result.addObject("admin", admin);
			result.addObject("showError", binding);
			result.addObject("erroresBinding", binding.getAllErrors());
			for (int i = 0; i < binding.getAllErrors().size(); i++)
				System.out.println("Error " + i + binding.getAllErrors().get(i));
		}

		return result;
	}

	// Register referee ------------------------------------------------------------

	@RequestMapping(value = "/registerreferee", method = RequestMethod.GET)
	public ModelAndView registerReferee() {
		final ModelAndView result;
		final Referee referee;

		referee = this.refereeService.create();
		result = new ModelAndView("administrator/registerreferee");
		result.addObject("referee", referee);

		return result;
	}

	@RequestMapping(value = "/registerreferee", method = RequestMethod.POST)
	public ModelAndView registerReferee(Referee referee, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			referee = this.refereeService.save(referee);
			final String password = new Md5PasswordEncoder().encodePassword(referee.getUserAccount().getPassword(), null);
			referee.getUserAccount().setPassword(password);
			this.userAccountRepository.save(referee.getUserAccount());
			referee.setMessageBoxes(this.messageBoxService.createSystemBoxes());
			for (final MessageBox mb : referee.getMessageBoxes())
				mb.setActor(referee);
			this.messageBoxService.save(referee.getMessageBoxes());
			referee = this.refereeService.save(referee);
			result = new ModelAndView("redirect:show.do");
		} else {
			result = new ModelAndView("administrator/registerreferee");
			result.addObject("referee", referee);
			result.addObject("showError", binding);
			result.addObject("erroresBinding", binding.getAllErrors());
			for (int i = 0; i < binding.getAllErrors().size(); i++)
				System.out.println("Error " + i + binding.getAllErrors().get(i));
		}

		return result;
	}

	// Suspicious ------------------------------------------------------------------

	@RequestMapping(value = "/suspicious", method = RequestMethod.GET)
	public ModelAndView suspicious() {
		final ModelAndView result;
		final List<Actor> suspiciousActors;

		suspiciousActors = this.actorService.findSuspicious();
		result = new ModelAndView("administrator/suspicious");
		result.addObject("suspiciousActors", suspiciousActors);

		return result;
	}

	@RequestMapping(value = "/suspicious", method = RequestMethod.POST, params = "ban")
	public ModelAndView suspiciousBan(@RequestParam(value = "id") final int id) {
		final Actor actor;

		actor = this.actorService.findById(id);
		actor.setIsBanned(true);
		this.actorService.save(actor);

		return this.suspicious();
	}

	@RequestMapping(value = "/suspicious", method = RequestMethod.POST, params = "unban")
	public ModelAndView suspiciousUnban(@RequestParam(value = "id") final int id) {
		final Actor actor;

		actor = this.actorService.findById(id);
		actor.setIsBanned(false);
		this.actorService.save(actor);

		return this.suspicious();
	}

}
