package domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDateTime;

public class Complaint {
	private String ticker;
	private LocalDateTime moment;
	private String description;
	private List<String> attachments;

	private Collection<Report> reports;
	private FixUpTask fixUpTask;

	@Valid
	public FixUpTask getFixUpTask() {
		return fixUpTask;
	}

	public void setFixUpTask(FixUpTask fixUpTask) {
		this.fixUpTask = fixUpTask;
	}

	@Column(unique = true)
	@Pattern(regexp = "^([\\d]){6}-([A-Z\\d]) {6}$")
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
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

	@URL
	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	@Valid
	public Collection<Report> getReports() {
		return reports;
	}

	public void setReports(Collection<Report> reports) {
		this.reports = reports;
	}
}
