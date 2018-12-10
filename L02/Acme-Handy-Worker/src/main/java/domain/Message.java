
package domain;

import java.sql.Date;
import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class Message extends DomainEntity {

	private Date date;
	private String subject;
	private String body;
	private String priority;
	private Collection<String> tags;

	private Collection<Actor> recipients;
	private Actor sender;
	private Collection<MessageBox> MessageBoxes;

	@NotNull
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	@NotNull
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotNull
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotNull
	@Pattern(regexp = "^HIGH|NEUTRAL|LOW$")
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(final String priority) {
		this.priority = priority;
	}

	@NotNull
	public Collection<String> getTags() {
		return this.tags;
	}

	public void setTags(final Collection<String> tags) {
		this.tags = tags;
	}

	@NotNull
	@Valid
	public Collection<Actor> getRecipients() {
		return this.recipients;
	}

	public void setRecipients(final Collection<Actor> recipient) {
		this.recipients = recipient;
	}

	@NotNull
	@Valid
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	@NotNull
	@Valid
	@NotEmpty
	public Collection<MessageBox> getMessageBox() {
		return this.MessageBoxes;
	}

	public void setMessageBox(final Collection<MessageBox> messageBox) {
		this.MessageBoxes = messageBox;
	}

}
