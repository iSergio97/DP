
package domain;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDateTime;

public class ProfessionalRecord extends DomainEntity {

	private String companyName;
	private LocalDateTime startingTime;
	private LocalDateTime endingTime;
	private String role;
	private String attachment;
	private String comments;

	private Curriculum curriculum;

	@URL
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@NotBlank
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}

	@Past
	public LocalDateTime getStartingTime() {
		return this.startingTime;
	}

	public void setStartingTime(final LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}

	public LocalDateTime getEndingTime() {
		return this.endingTime;
	}

	public void setEndingTime(final LocalDateTime endingTime) {
		this.endingTime = endingTime;
	}

	@NotBlank
	public String getRole() {
		return this.role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

}
