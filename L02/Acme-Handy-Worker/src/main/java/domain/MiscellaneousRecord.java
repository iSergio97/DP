
package domain;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class MiscellaneousRecord extends DomainEntity {

	private String tittle;
	private String attachment;
	private String comments;

	private Curriculum curriculum;

	@NotBlank
	public String getTittle() {
		return this.tittle;
	}

	@URL
	public String getAttachment() {
		return this.attachment;
	}

	public String getComments() {
		return this.comments;
	}

	public void setTittle(final String tittle) {
		this.tittle = tittle;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
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
