
package domain;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

public abstract class Actor extends DomainEntity {

	private String name;
	private String middleName;
	private String surname;
	private String photo;
	private String email;
	private String phoneNumber;
	private String address;
	private UserAccount userAccount;
	private Collection<MessageBox> boxes;
	private Collection<SocialProfile> socialProfiles;
	private Collection<Endorsement> endorsedBy;
	private Collection<Endorsement> endorses;
	private Collection<Message> messagesSent;
	private Collection<Message> messagesRecieved;
	private Collection<Note> notes;

	@NotNull
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotNull
	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@NotNull
	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@NotNull
	@NotBlank
	@Pattern(regexp = "^((([a-zA-Z0-9])+ )+<([a-zA-Z0-9])+@(([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)+)?>)|(([a-zA-Z0-9])+@(([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)+)?)$")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@NotNull
	@Pattern(regexp = "^(\\+\\d{1,3} (\\(\\d{1,3}\\) )?)?\\d{4,}$")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@NotNull
	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@NotNull
	@Valid
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Valid
	@NotNull
	@NotEmpty
	public Collection<MessageBox> getBoxes() {
		return this.boxes;
	}

	public void setBoxes(final Collection<MessageBox> boxes) {
		this.boxes = boxes;
	}

	@NotNull
	@NotEmpty
	@Valid
	public Collection<SocialProfile> getSocialProfiles() {
		return this.socialProfiles;
	}

	public void setSocialProfiles(final Collection<SocialProfile> socialProfile) {
		this.socialProfiles = socialProfile;
	}

	@Valid
	public Collection<Endorsement> getEndorsedBy() {
		return this.endorsedBy;
	}

	public void setEndorsedBy(final Collection<Endorsement> endorsedBy) {
		this.endorsedBy = endorsedBy;
	}

	@Valid
	public Collection<Endorsement> getEndorses() {
		return this.endorses;
	}

	public void setEndorses(final Collection<Endorsement> endorses) {
		this.endorses = endorses;
	}

	@Valid
	public Collection<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(Collection<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	@Valid
	public Collection<Message> getMessagesRecieved() {
		return messagesRecieved;
	}

	public void setMessagesRecieved(Collection<Message> messagesRecieved) {
		this.messagesRecieved = messagesRecieved;
	}

	@Valid
	public Collection<Note> getNotes() {
		return notes;
	}

	public void setNotes(Collection<Note> notes) {
		this.notes = notes;
	}

}
