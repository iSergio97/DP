
package domain;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import domain.DomainEntity;

public class PersonalRecord extends DomainEntity {

	private String fullName;
	private String photo;
	private String email;
	private String phoneNumber;
	private String linkedIn;

	private Curriculum curriculum;

	@NotBlank
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@Pattern(regexp = "^((([a-zA-Z0-9])+ )+<([a-zA-Z0-9])+@(([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)+)?>)|(([a-zA-Z0-9])+@(([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)+)?)$")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Pattern(regexp = "^(\\+\\d{1,3} (\\(\\d{1,3}\\) )?)?\\d{4,}$")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@URL
	// @NotBlank
	public String getLinkedIn() {
		return this.linkedIn;
	}

	public void setLinkedIn(final String linkedIn) {
		this.linkedIn = linkedIn;
	}

	@Valid
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
