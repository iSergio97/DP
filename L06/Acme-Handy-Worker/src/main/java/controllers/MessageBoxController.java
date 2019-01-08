
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.MessageBoxService;
import domain.Actor;
import domain.MessageBox;

@Controller
@RequestMapping("/message-box")
public class MessageBoxController {

	@Autowired
	private MessageBoxService	messageBoxService;

	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam(value = "name") final String name) {
		ModelAndView result;
		MessageBox messageBox;

		final int id = LoginService.getPrincipal().getId();
		final Actor actor = this.actorService.findByUserAccountId(id);

		//messageBox = this.messageBoxService.findByPrincipalAndName(actor.getId(), name);
		messageBox = (MessageBox) actor.getMessageBoxes().toArray()[1];
		result = new ModelAndView("message-box/show");
		result.addObject("messageBox", messageBox);

		return result;
	}
}
