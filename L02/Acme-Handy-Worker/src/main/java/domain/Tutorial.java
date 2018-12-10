package domain;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDateTime;

public class Tutorial extends DomainEntity {
	private String title;
	private LocalDateTime lastUpdated;
	private String summary;
	private List<String> pictures;

	private List<Section> sections;
	private HandyWorker handyWorker;
	private Sponsorship sponsorship;

	@Valid
	@NotBlank
	public HandyWorker getHandyWorker() {
		return handyWorker;
	}

	public void setHandyWorker(HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@NotBlank
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@URL
	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	@Valid
	public Sponsorship getSponsorship() {
		return sponsorship;
	}

	public void setSponsorship(Sponsorship sponsorship) {
		this.sponsorship = sponsorship;
	}
}
