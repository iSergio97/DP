
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.MessageBoxService;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Controller
@RequestMapping("/message-box")
public class MessageBoxController {

	// Services ---------------------------------------------------------------

	@Autowired
	private MessageBoxService	messageBoxService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public MessageBoxController() {
		super();
	}

	// List ------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
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

		final Collection<MessageBox> messageBoxes = actor.getMessageBoxes();
		final Collection<MessageBox> systemBoxes = this.messageBoxService.getSystemBoxes(actor);
		messageBoxes.removeAll(systemBoxes);

		result = new ModelAndView("message-box/list");
		result.addObject("messageBoxes", messageBoxes);

		return result;
	}

	// Create ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final MessageBox messageBox;

		messageBox = this.messageBoxService.create();
		result = this.createEditModelAndView(messageBox);

		return result;
	}

	// Edit ------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "id") final int id) {
		ModelAndView result;
		MessageBox messageBox;

		messageBox = messageBoxService.findById(id);
		Assert.notNull(messageBox);
		
		result = createEditModelAndView(messageBox);

//		result = new ModelAndView("message-box/edit");
//		result.addObject("messageBox", messageBox);

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

	protected ModelAndView createEditModelAndView(final MessageBox messageBox) {
		ModelAndView result;

		result = this.createEditModelAndView(messageBox, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MessageBox messageBox, final String messageCode) {
		final ModelAndView result;
		final String name;
		final Actor actor;
		final Collection<Message> messages;

		name = messageBox.getName();
		final int principalId = LoginService.getPrincipal().getId();
		actor = this.actorService.findByUserAccountId(principalId);
		messages = messageBox.getMessages();

		result = new ModelAndView("message-box/edit");
		result.addObject("messageBox", messageBox);
		result.addObject("name", name);
		result.addObject("actor", actor);
		result.addObject("messages", messages);

		result.addObject("messageCode", messageCode);

		return result;
	}
}
