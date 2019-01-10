
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PhaseService;
import services.WorkPlanService;
import domain.Phase;
import domain.WorkPlan;

@Controller
@RequestMapping("/Workplan/HandyWorker")
public class WorkplanController extends AbstractController {

	@Autowired
	private WorkPlanService	workplanService;

	@Autowired
	private PhaseService	phaseService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int workplanId) {
		ModelAndView result;

		WorkPlan workplan;

		workplan = this.workplanService.findById(workplanId);

		result = this.createEditModelAndView(workplan);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		WorkPlan workplan;

		workplan = this.workplanService.create();
		result = this.createEditModelAndView(workplan);

		return result;
	}

	//Este metodo es un bypass a un segundo meodo con 2 valores de entrada
	protected ModelAndView createEditModelAndView(final WorkPlan workplan) {

		ModelAndView result;
		result = this.createEditModelAndView(workplan, null);

		return result;
	}

	//Este metodo construye el objeto modelandview en funcion del finder de entrada y los mensajes de error
	protected ModelAndView createEditModelAndView(final WorkPlan workplan, final String messageCode) {

		ModelAndView result;

		Collection<Phase> phases;

		if (workplan.getPhases() == null)
			phases = null;
		else
			phases = workplan.getPhases();

		result = new ModelAndView("phase/show");
		result.addObject("workplan", workplan);
		result.addObject("phases", phases);

		return result;
	}

}
