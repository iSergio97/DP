
package domain;

import java.sql.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Phase extends DomainEntity {

	private String title;
	private String description;
	private Date startMoment;
	private Date endMoment;

	private WorkPlan workplan;

	@NotNull
	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	public Date getStartMoment() {
		return this.startMoment;
	}

	public void setStartMoment(final Date startMoment) {
		this.startMoment = startMoment;
	}

	@NotNull
	public Date getEndMoment() {
		return this.endMoment;
	}

	public void setEndMoment(final Date endMoment) {
		this.endMoment = endMoment;
	}

	@Valid
	public WorkPlan getWorkplan() {
		return workplan;
	}

	public void setWorkplan(WorkPlan workplan) {
		this.workplan = workplan;
	}
}
