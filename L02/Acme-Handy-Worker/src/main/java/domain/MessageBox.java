
package domain;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MessageBox {

	private String name;
	private int isSystem;

	private Actor actor;
	private Collection<Message> messages;

	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotNull
	@Valid
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

	@NotNull
	@Valid
	public Collection<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final Collection<Message> messages) {
		this.messages = messages;
	}

	public int getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(int isSystem) {
		this.isSystem = isSystem;
	}

}
