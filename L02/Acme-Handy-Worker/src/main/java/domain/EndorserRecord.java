
package domain;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class EndorserRecord extends DomainEntity {

	private String endorserFullName;
	private String endorserEmail;
	private String endorserPhoneNumber;
	private String endorserLinkedIn;
	private String comments;

	private Curriculum curriculum;

	@NotBlank
	public String getEndorserFullName() {
		return this.endorserFullName;
	}

	@Pattern(regexp = "^((([a-zA-Z0-9])+ )+<([a-zA-Z0-9])+@(([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)+)?>)|(([a-zA-Z0-9])+@(([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)+)?)$")
	public String getEndorserEmail() {
		return this.endorserEmail;
	}

	@Pattern(regexp = "^(\\+\\d{1,3} (\\(\\d{1,3}\\) )?)?\\d{4,}$")
	public String getEndorserPhoneNumber() {
		return this.endorserPhoneNumber;
	}

	@URL
	public String getEndorserLinkedIn() {
		return this.endorserLinkedIn;
	}

	public String getComments() {
		return this.comments;
	}

	public void setEndorserFullName(final String endorserFullName) {
		this.endorserFullName = endorserFullName;
	}

	public void setEndorserEmail(final String endorserEmail) {
		this.endorserEmail = endorserEmail;
	}

	public void setEndorserPhoneNumber(final String endorserPhoneNumber) {
		this.endorserPhoneNumber = endorserPhoneNumber;
	}

	public void setEndorserLinkedIn(final String endorserLinkedIn) {
		this.endorserLinkedIn = endorserLinkedIn;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	@Valid
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
