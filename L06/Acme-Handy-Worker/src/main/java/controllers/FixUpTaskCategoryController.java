
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
import services.FixUpTaskCategoryService;
import services.FixUpTaskService;
import domain.Actor;
import domain.FixUpTask;
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

	//Show------------------------------------------------

	@RequestMapping(value = "/admin/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int fixUpTaskCategoryId) {
		ModelAndView result;
		FixUpTaskCategory futCat;
		result = new ModelAndView("fixUpTaskCategory/show");
		futCat = this.futCatService.findById(fixUpTaskCategoryId);
		result.addObject("fixUpTaskCategory", futCat);

		return result;
	}

	//Create---------------------------------------------

	@RequestMapping(value = "/admin/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		FixUpTaskCategory futCat;
		futCat = this.futCatService.create();
		result = this.createEditModelAndView(futCat);

		return result;
	}

	//Edit---------------------------------------------

	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int fixUpTaskCategoryId) {
		ModelAndView result;
		FixUpTaskCategory futCat;
		futCat = this.futCatService.findById(fixUpTaskCategoryId);
		Assert.notNull(futCat);
		result = this.createEditModelAndView(futCat);

		return result;
	}

	//Save--------------------------------------------

	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FixUpTaskCategory futCat, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(futCat);
		else
			try {
				this.futCatService.save(futCat);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(futCat, "fixUpTaskCategory.commit.error");
			}
		return result;
	}

	//Delete----------------------------------------

	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int fixUpTaskCategoryId) {
		ModelAndView result;
		FixUpTaskCategory futCat;
		futCat = this.futCatService.findById(fixUpTaskCategoryId);
		try {
			this.futCatService.delete(futCat);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(futCat, "fixUpTaskCategory.commit.error");
		}

		return result;
	}

	//Ancillary Methods-----------------------------

	protected ModelAndView createEditModelAndView(final FixUpTaskCategory fixUpTaskCategory) {
		final ModelAndView result;
		result = this.createEditModelAndView(fixUpTaskCategory, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final FixUpTaskCategory fixUpTaskCategory, final String messageCode) {
		ModelAndView result;
		Collection<FixUpTask> futs;

		futs = this.futService.findAll();
		result = new ModelAndView("fixUpTaskCategory/edit");
		result.addObject("fixUpTaskCategory", fixUpTaskCategory);
		result.addObject("fixUpTasks", futs);
		result.addObject("message", messageCode);

		return result;
	}

}
