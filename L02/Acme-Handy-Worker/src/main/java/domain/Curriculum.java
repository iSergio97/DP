
package domain;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import domain.DomainEntity;

public class Curriculum extends DomainEntity {

	private String ticker;

	private Set<MiscellaneousRecord> miscellaneousRecord;
	private Set<EndorserRecord> endorserRecord;
	private Set<ProfessionalRecord> professionalRecord;
	private Set<EducationalRecord> educationalRecord;
	private Set<PersonalRecord> personalRecord;
	private HandyWorker handyWorker;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^([\\d]){6}-([A-Z\\d]) {6}$")
	public String getTicker() {
		return this.ticker;
	}

	public Set<MiscellaneousRecord> getMiscellaneousRecord() {
		return this.miscellaneousRecord;
	}

	public Set<EndorserRecord> getEndorserRecord() {
		return this.endorserRecord;
	}

	public Set<ProfessionalRecord> getProfessionalRecord() {
		return this.professionalRecord;
	}

	public Set<EducationalRecord> getEducationalRecord() {
		return this.educationalRecord;
	}

	public Set<PersonalRecord> getPersonalRecord() {
		return this.personalRecord;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	public void setMiscellaneousRecord(final Set<MiscellaneousRecord> miscellaneousRecord) {
		this.miscellaneousRecord = miscellaneousRecord;
	}

	public void setEndorserRecord(final Set<EndorserRecord> endorserRecord) {
		this.endorserRecord = endorserRecord;
	}

	public void setProfessionalRecord(final Set<ProfessionalRecord> professionalRecord) {
		this.professionalRecord = professionalRecord;
	}

	public void setEducationalRecord(final Set<EducationalRecord> educationalRecord) {
		this.educationalRecord = educationalRecord;
	}

	public void setPersonalRecord(final Set<PersonalRecord> personalRecord) {
		this.personalRecord = personalRecord;
	}

	@Valid
	public HandyWorker getHandyWorker() {
		return handyWorker;
	}

	public void setHandyWorker(HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

}
