
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		final Actor a = this.actorService.findByUserAccountId(LoginService.getPrincipal().getId());
		lsActors.remove(a);
		Message mesage;
		mesage = this.messageService.create();
		result = new ModelAndView("message/sendMessage");
		result.addObject("domainMessage", mesage);
		result.addObject("actors", lsActors);

		return result;
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public ModelAndView sendMessagePost(@Valid final Message mesage, final BindingResult bindingResult) {

		ModelAndView result;
		if (!bindingResult.hasErrors()) {
			final int id = LoginService.getPrincipal().getId();
			final Actor a = this.actorService.findByUserAccountId(id);

			final Actor a2 = this.actorService.findByUserAccountId(32768);
			final Collection<Message> mSend = a.getMessagesSent();
			final Collection<Message> mRecieved = a2.getMessagesReceived();
			mSend.add(mesage);
			mRecieved.add(mesage);
			final MessageBox inBox = (MessageBox) a.getMessageBoxes().toArray()[0];
			inBox.setMessages(mSend);
			this.messageBoxService.save(inBox);

			mesage.setSender(a);
			this.messageService.save(mesage);
			a.getMessagesSent().add(mesage);
			result = new ModelAndView("redirect:showMessage.do");
		} else {
			for (int i = 0; i < bindingResult.getErrorCount(); i++)
				System.out.println(bindingResult.getAllErrors().get(i));
			result = new ModelAndView("message/sendMessage");
			List<Actor> lsActors;
			lsActors = this.actorService.findAll();
			result.addObject("domainMessage", mesage);
			result.addObject("actors", lsActors);
			result.addObject("bindingResult", bindingResult);
			for (int i = 0; i < bindingResult.getAllErrors().size(); i++)
				System.out.println("Error " + i + bindingResult.getAllErrors().get(i));
		}
		return result;
	}

	@RequestMapping(value = "/listboxes", method = RequestMethod.GET)
	public ModelAndView boxes() {
		final ModelAndView result;
		final int id = LoginService.getPrincipal().getId();
		Actor customer;
		customer = this.actorService.findByUserAccountId(id);
		if (customer.getMessageBoxes().size() == 0) {
			final List<MessageBox> messageBoxes = new ArrayList<MessageBox>();
			for (final MessageBox messageBox : this.messageBoxService.createSystemBoxes()) {
				messageBox.setActor(customer);
				messageBoxes.add(this.messageBoxService.save(messageBox));
			}
			customer.setMessageBoxes(messageBoxes);
			customer = this.actorService.save(customer);
		}

		final Collection<MessageBox> messageBoxes = customer.getMessageBoxes();
		result = new ModelAndView("customer/listboxes");
		result.addObject("messageBoxes", messageBoxes);

		return result;
	}

	@RequestMapping(value = "/displaybox", method = RequestMethod.GET)
	public ModelAndView displayBox(@RequestParam(value = "id") final int id) {
		ModelAndView result;
		MessageBox messageBox;

		messageBox = this.messageBoxService.findById(id);
		Assert.isTrue(LoginService.getPrincipal().equals(messageBox.getActor().getUserAccount()));
		result = new ModelAndView("message/displaybox");
		result.addObject("messageBox", messageBox);

		return result;
	}

	@RequestMapping(value = "/move", method = RequestMethod.GET)
	public ModelAndView move(@RequestParam(value = "messageId") final int messageId, @RequestParam(value = "fromMessageBoxId") final int fromMessageBoxId, @RequestParam(value = "toMessageBoxId") final int toMessageBoxId) {
		Message message;
		MessageBox fromMessageBox;
		MessageBox toMessageBox;

		message = this.messageService.findById(messageId);
		fromMessageBox = this.messageBoxService.findById(fromMessageBoxId);
		toMessageBox = this.messageBoxService.findById(toMessageBoxId);

		Assert.isTrue(LoginService.getPrincipal().equals(fromMessageBox.getActor().getUserAccount()));
		Assert.isTrue(LoginService.getPrincipal().equals(toMessageBox.getActor().getUserAccount()));

		final Collection<MessageBox> messageBoxes = message.getMessageBoxes();
		messageBoxes.remove(fromMessageBox);
		messageBoxes.add(toMessageBox);
		message.setMessageBoxes(messageBoxes);
		message = this.messageService.save(message);

		final Collection<Message> fromMessages = fromMessageBox.getMessages();
		fromMessages.remove(message);
		fromMessageBox = this.messageBoxService.save(fromMessageBox);

		final Collection<Message> toMessages = toMessageBox.getMessages();
		toMessages.add(message);
		toMessageBox = this.messageBoxService.save(toMessageBox);

		return this.displayBox(toMessageBox.getId());
	}

}
