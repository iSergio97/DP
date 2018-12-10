package domain;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;

public class Note extends DomainEntity {
	private LocalDateTime moment;
	private String comment;
	private List<String> additionalComments;

	private Report report;
	private Actor actor;

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<String> getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(List<String> additionalComments) {
		this.additionalComments = additionalComments;
	}

	@Valid
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@Valid
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
}
