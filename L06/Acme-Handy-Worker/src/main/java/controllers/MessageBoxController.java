
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
		MessageBox messageBox;

		final int id = LoginService.getPrincipal().getId();
		final Actor actor = this.actorService.findByUserAccountId(id);

		final MessageBox[] messageBoxes = this.messageBoxService.findByPrincipalAndName(actor.getId(), name);
		if (messageBoxes.length == 0)
			messageBox = null;
		else
			messageBox = messageBoxes[0];

		//messageBox = (MessageBox) actor.getMessageBoxes().toArray()[1];

		final ModelAndView result = new ModelAndView("message-box/show");
		result.addObject("messageBox", messageBox);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delete(@RequestParam(value = "name") final String name) {

		final int id = LoginService.getPrincipal().getId();
		final Actor actor = this.actorService.findByUserAccountId(id);

		final MessageBox[] messageBoxes = this.messageBoxService.findByPrincipalAndName(actor.getId(), name);
		final MessageBox messageBox = messageBoxes[0];

		this.messageBoxService.delete(messageBox);
	}
}
