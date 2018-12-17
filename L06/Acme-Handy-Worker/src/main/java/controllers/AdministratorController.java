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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SystemConfigurationService;
import domain.SystemConfiguration;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SystemConfigurationService	systemConfigurationService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Utility methods --------------------------------------------------------		

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

	// System configuration ---------------------------------------------------		

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

}
