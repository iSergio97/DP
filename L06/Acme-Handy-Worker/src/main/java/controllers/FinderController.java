
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.FinderService;
import domain.Finder;

@Controller
//Se especifica que esta clase es un controlador	
@RequestMapping("/finder/handy-worker")
//Un contrlador que sirve para las llamadas relacionadas con esta url
public class FinderController extends AbstractController {

	@Autowired
	private FinderService	finderService;	//Vamos a requerir del servicio de finders


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView result;
		final Finder finder;

		finder = this.finderService.findByHandyWorkerId(LoginService.getPrincipal().getId());

		result = new ModelAndView("finder/show");
		result.addObject("finder", finder);
		result.addObject("requestURI", "finder/handy-worker/show.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int finderId) {
		final ModelAndView result;
		final Finder finder;

		finder = this.finderService.findById(finderId);
		Assert.notNull(finder);
		result = this.createEditModelAndView(finder);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(finder);
		else
			try {
				this.finderService.save(finder);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder, "finder.commit.error");
			}
		return result;
	}

	/* Los finder no tienen metodo delete -------------------------------------- */

	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final Finder finder, final BindingResult binding) {
	//
	//		ModelAndView result;
	//
	//		try {
	//			this.finderService.delete(finder);
	//			result = new ModelAndView("redirect:list.do");
	//		} catch (final Throwable oops) {
	//			result = this.createEditModelAndView(finder, "finder.commit.error");
	//		}
	//
	//		return result;
	//	}

	//Este metodo es un bypass a un segundo meodo con 2 valores de entrada
	protected ModelAndView createEditModelAndView(final Finder finder) {

		ModelAndView result;
		result = this.createEditModelAndView(finder, null);

		return result;
	}

	//Este metodo construye el objeto modelandview en funcion del finder de entrada y los mensajes de error
	protected ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {

		final ModelAndView result;

		result = new ModelAndView("finder/edit");
		result.addObject("message", messageCode);

		return result;
	}
}
