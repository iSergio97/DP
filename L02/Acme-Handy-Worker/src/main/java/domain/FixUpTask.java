
package domain;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class FixUpTask extends DomainEntity {

	private String ticker;
	private Date moment;
	private String description;
	private String address;
	private int maximumPrice;
	private Date timeLimit;

	private FixUpTaskCategory category;
	private Customer publisher;
	private Collection<Application> applications;
	private Warranty warranty;
	private WorkPlan workPlan;
	private Collection<Complaint> complaints;

	@NotNull
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^([\\d]){6}-([A-Z\\d]) {6}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotNull
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
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
	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@NotNull
	public int getMaximumPrice() {
		return this.maximumPrice;
	}

	public void setMaximumPrice(final int maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	@NotNull
	public Date getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(final Date timeLimit) {
		this.timeLimit = timeLimit;
	}

	@NotNull
	@Valid
	public FixUpTaskCategory getCategory() {
		return this.category;
	}

	public void setCategory(final FixUpTaskCategory category) {
		this.category = category;
	}

	@NotNull
	@Valid
	public Customer getPublisher() {
		return this.publisher;
	}

	public void setPublisher(final Customer publisher) {
		this.publisher = publisher;
	}

	@Valid
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

	@Valid
	@NotNull
	public Warranty getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	@Valid
	public WorkPlan getWorkPlan() {
		return this.workPlan;
	}

	public void setWorkPlan(final WorkPlan workPlan) {
		this.workPlan = workPlan;
	}

	@Valid
	public Collection<Complaint> getComplaints() {
		return this.complaints;
	}

	public void setComplaints(final Collection<Complaint> complaints) {
		this.complaints = complaints;
	}
}
