
package domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tutorial extends DomainEntity {

	// Fields -----------------------------------------------------------------

	private String				title;
	private Date				lastUpdated;
	private String				summary;
	private Collection<String>	pictures;

	// Relationships ----------------------------------------------------------

	private Collection<Section>	sections;
	private HandyWorker			handyWorker;
	private Sponsorship			sponsorship;


	// Field access methods ---------------------------------------------------

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(final Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@NotBlank
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@ElementCollection
	public Collection<String> getPictures() {
		return this.pictures;
	}

	public void setPictures(final List<String> pictures) {
		this.pictures = pictures;
	}

	// Relationship access methods --------------------------------------------

	@OneToMany(mappedBy = "tutorial", cascade = CascadeType.ALL)
	public Collection<Section> getSections() {
		return this.sections;
	}

	public void setSections(final Collection<Section> sections) {
		this.sections = sections;
	}

	@Valid
	@ManyToOne
	public HandyWorker getHandyWorker() {
		return this.handyWorker;
	}

	public void setHandyWorker(final HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

	@Valid
	@OneToOne
	public Sponsorship getSponsorship() {
		return this.sponsorship;
	}

	public void setSponsorship(final Sponsorship sponsorship) {
		this.sponsorship = sponsorship;
	}
}
