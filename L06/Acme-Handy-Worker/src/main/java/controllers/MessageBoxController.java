
package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MessageBox messageBox, final BindingResult binding) {
		ModelAndView result = null;

		try {
			this.messageBoxService.delete(messageBox);
			result = new ModelAndView("redirect::list.do");
		} catch (final Throwable oops) {

		}

		return result;

		//		final MessageBox[] messageBoxes = this.messageBoxService.findByPrincipalAndId(actor.getId(), messageBoxId);
		//		final MessageBox messageBox = messageBoxes[0];
		//
		//		this.messageBoxService.delete(messageBox);
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView boxes() {
		final ModelAndView result;
		final int id = LoginService.getPrincipal().getId();
		Actor actor;
		actor = this.actorService.findByUserAccountId(id);
		if (actor.getMessageBoxes().size() == 0) {
			final List<MessageBox> messageBoxes = new ArrayList<MessageBox>();
			for (final MessageBox messageBox : this.messageBoxService.createSystemBoxes()) {
				messageBox.setActor(actor);
				messageBoxes.add(this.messageBoxService.save(messageBox));
			}
			actor.setMessageBoxes(messageBoxes);
			actor = this.actorService.save(actor);
		}

		final MessageBox[] messageBoxesArray = this.messageBoxService.getMessageBoxes(actor);
		final List<MessageBox> messageBoxes = Arrays.asList(messageBoxesArray);
		final Collection<MessageBox> systemBoxes = this.messageBoxService.getSystemBoxes(actor);
		messageBoxes.removeAll(systemBoxes);

		result = new ModelAndView("message-box/list");
		result.addObject("messageBoxes", messageBoxes);

		return result;
	}
}
