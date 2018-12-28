
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView result;
		final Finder finder;

		finder = this.finderService.findByHandyWorkerId(LoginService.getPrincipal().getId());

		result = new ModelAndView("finder/list");
		result.addObject("finder", finder);
		result.addObject("requestURI", "finder/handy-worker/list.do");

		return result;
	}
}
