
package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.MessageBoxService;
import services.MessageService;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService		messageService;

	@Autowired
	private MessageBoxService	messageBoxService;

	@Autowired
	private ActorService		actorService;


	public MessageController() {
		super();
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	public ModelAndView sendMessageGet() {
		ModelAndView result;
		List<Actor> lsActors;
		lsActors = this.actorService.findAll();
		Message domainMessage;
		domainMessage = this.messageService.create();
		result = new ModelAndView("message/sendMessage");
		result.addObject("domainMessage", domainMessage);
		result.addObject("actors", lsActors);

		return result;
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public ModelAndView sendMessagePost(final Message domainMessage, final BindingResult bindingResult) {

		ModelAndView result;
		if (!bindingResult.hasErrors()) {
			this.messageService.save(domainMessage);
			final int id = LoginService.getPrincipal().getId();
			final Actor a = this.actorService.findByUserAccountId(id);
			final Collection<Message> ms = a.getMessagesSent();
			ms.add(domainMessage);
			result = new ModelAndView("redirect:showMessage.do");
		} else {
			for (int i = 0; i < bindingResult.getErrorCount(); i++)
				System.out.println(bindingResult.getAllErrors().get(i));
			result = new ModelAndView("message/sendMessage");
			result.addObject("domainMessage", domainMessage);
			result.addObject("bindingResult", bindingResult);
			for (int i = 0; i < bindingResult.getAllErrors().size(); i++)
				System.out.println("Error " + i + bindingResult.getAllErrors().get(i));
		}
		return result;
	}

	@RequestMapping(value = "/showBox", method = RequestMethod.GET)
	public ModelAndView boxes() {
		final ModelAndView result;
		final int id = LoginService.getPrincipal().getId();
		Actor customer;
		customer = this.actorService.findByUserAccountId(id);
		if (customer.getMessageBoxes().size() == 0) {
			final List<MessageBox> mb = this.messageBoxService.createSystemBoxes();
			for (final MessageBox ms : mb) {
				ms.setActor(customer);
				this.messageBoxService.save(ms);
			}
		}

		final Collection<MessageBox> mb = customer.getMessageBoxes();
		result = new ModelAndView("customer/box");
		result.addObject("messageBoxes", mb);

		return result;
	}
}
