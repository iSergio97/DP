
package domain;

import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDateTime;

public class EducationalRecord extends DomainEntity {

	private String diplomaTitle;
	private LocalDateTime startingTime;
	private LocalDateTime endingTime;
	private String institution;
	private String attachment;
	private String comments;

	private Curriculum curriculum;

	@NotBlank
	public String getDiplomaTitle() {
		return this.diplomaTitle;
	}

	@Past
	public LocalDateTime getStartingTime() {
		return this.startingTime;
	}

	public LocalDateTime getEndingTime() {
		return this.endingTime;
	}

	@NotBlank
	public String getInstitution() {
		return this.institution;
	}

	@URL
	public String getAttachment() {
		return this.attachment;
	}

	public String getComments() {
		return this.comments;
	}

	public void setDiplomaTitle(final String diplomaTitle) {
		this.diplomaTitle = diplomaTitle;
	}

	public void setStartingTime(final LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}

	public void setEndingTime(final LocalDateTime endingTime) {
		this.endingTime = endingTime;
	}

	public void setInstitution(final String institution) {
		this.institution = institution;
	}

	public void setAttachament(final String attachment) {
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
