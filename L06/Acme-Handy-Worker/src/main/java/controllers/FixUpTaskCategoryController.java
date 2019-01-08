
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.FixUpTaskCategoryService;
import services.FixUpTaskService;
import domain.Actor;
import domain.FixUpTaskCategory;

@Controller
@RequestMapping("/fixUpTaskCategory")
public class FixUpTaskCategoryController extends AbstractController {

	//Services----------------------------------------------

	@Autowired
	private FixUpTaskCategoryService	futCatService;

	@Autowired
	private FixUpTaskService			futService;

	@Autowired
	private ActorService				actorService;


	//List-------------------------------------------------

	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<FixUpTaskCategory> categories;
		result = new ModelAndView("fixUpTaskCategory/list");

		// Add logged admin
		final Actor admin = this.actorService.findPrincipal();
		Assert.isTrue(admin.getUserAccount().getAuthorities().contains(Authority.ADMIN));
		categories = this.futCatService.findAll();
		result.addObject("categories", categories);
		result.addObject("admin", admin);
		result.addObject("requestURI", "fixUpTaskCategory/admin/list.do");

		return result;
	}

}
