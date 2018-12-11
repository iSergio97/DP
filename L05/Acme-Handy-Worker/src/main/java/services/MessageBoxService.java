
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageBoxRepository;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageBoxService {

	//Managed Repository
	@Autowired
	private MessageBoxRepository	messageBoxRepository;

	//SuportingServices

	@Autowired
	ActorService					actorService;


	//Constructors
	public MessageBoxService() {
		super();
	}

	// CRUD Methods ----------------------------------------------------------------

	public List<MessageBox> createSystemBoxes() {
		final List<MessageBox> mbls = new ArrayList<>();
		final MessageBox inBox = new MessageBox();
		inBox.setName("inBox");
		inBox.setMessages(new ArrayList<Message>());
		mbls.add(inBox);

		final MessageBox outBox = new MessageBox();
		outBox.setName("outBox");
		outBox.setMessages(new ArrayList<Message>());
		mbls.add(outBox);

		final MessageBox trashBox = new MessageBox();
		trashBox.setName("outBox");
		trashBox.setMessages(new ArrayList<Message>());
		mbls.add(trashBox);

		final MessageBox spamBox = new MessageBox();
		spamBox.setName("outBox");
		spamBox.setMessages(new ArrayList<Message>());
		mbls.add(spamBox);

		return mbls;
	}

	public MessageBox createNormalBoxes() {
		final MessageBox mb = new MessageBox();
		mb.setName("");
		mb.setMessages(new ArrayList<Message>());

		this.messageBoxRepository.save(mb);

		return mb;
	}

	public MessageBox save(final MessageBox messageBox) {
		Assert.isTrue(messageBox != null);
		return this.messageBoxRepository.save(messageBox);
	}

	public Iterable<MessageBox> save(final Iterable<MessageBox> messageBoxes) {
		Assert.isTrue(messageBoxes != null);
		return this.messageBoxRepository.save(messageBoxes);
	}

	public void delete(final MessageBox messageBox) {
		Assert.isTrue(messageBox != null);
		this.messageBoxRepository.delete(messageBox);
	}

	public void delete(final Iterable<MessageBox> messageBoxes) {
		Assert.isTrue(messageBoxes != null);
		this.messageBoxRepository.delete(messageBoxes);
	}

	public MessageBox findById(final int id) {
		return this.messageBoxRepository.findOne(id);
	}

	public List<MessageBox> findAll() {
		return this.messageBoxRepository.findAll();
	}

	// Other Methods ----------------------------------------------------------------

	MessageBox[] getMessageBoxes(final Actor a) {
		return this.messageBoxRepository.getMessageBoxes(a.getId());
	}
}
