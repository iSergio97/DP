
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
	private ActorService			actorService;


	//Constructors
	public MessageBoxService() {
		super();
	}

	// CRUD Methods ----------------------------------------------------------------

	public List<MessageBox> createSystemBoxes() {
		final List<MessageBox> mbls = new ArrayList<>();
		final MessageBox inBox = new MessageBox();
		inBox.setName("InBox");
		inBox.setMessages(new ArrayList<Message>());
		inBox.setActor(this.actorService.findPrincipal());
		mbls.add(inBox);

		final MessageBox outBox = new MessageBox();
		outBox.setName("OutBox");
		outBox.setMessages(new ArrayList<Message>());
		outBox.setActor(this.actorService.findPrincipal());
		mbls.add(outBox);

		final MessageBox trashBox = new MessageBox();
		trashBox.setName("SpamBox");
		trashBox.setMessages(new ArrayList<Message>());
		trashBox.setActor(this.actorService.findPrincipal());
		mbls.add(trashBox);

		final MessageBox spamBox = new MessageBox();
		spamBox.setName("TrashBox");
		spamBox.setMessages(new ArrayList<Message>());
		spamBox.setActor(this.actorService.findPrincipal());
		mbls.add(spamBox);

		return mbls;
	}
	public MessageBox createNormalBoxes() {
		final MessageBox mb = new MessageBox();
		mb.setActor(this.actorService.findPrincipal());
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
		Assert.isTrue(!messageBox.getName().equals("inBox"));
		Assert.isTrue(!messageBox.getName().equals("outBox"));
		Assert.isTrue(!messageBox.getName().equals("trashBox"));
		Assert.isTrue(!messageBox.getName().equals("spamBox"));

		this.messageBoxRepository.delete(messageBox);
	}

	public void delete(final Iterable<MessageBox> messageBoxes) {
		Assert.isTrue(messageBoxes != null);
		for (final MessageBox b : messageBoxes) {
			Assert.isTrue(!b.getName().equals("inBox"));
			Assert.isTrue(!b.getName().equals("outBox"));
			Assert.isTrue(!b.getName().equals("trashBox"));
			Assert.isTrue(!b.getName().equals("spamBox"));
		}
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
