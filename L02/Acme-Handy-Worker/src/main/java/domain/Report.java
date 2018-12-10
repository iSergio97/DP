package domain;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDateTime;

public class Report extends DomainEntity {
	private LocalDateTime moment;
	private String description;
	private List<String> attachements;

	private List<Note> notes;
	private Complaint complaint;
	private Referee referee;

	@NotNull
	@Valid
	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	@NotNull
	@Valid
	public Referee getReferee() {
		return referee;
	}

	public void setReferee(Referee referee) {
		this.referee = referee;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@URL // Preguntar
	public List<String> getAttachements() {
		return attachements;
	}

	public void setAttachements(List<String> attachements) {
		this.attachements = attachements;
	}

	@Valid
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}
