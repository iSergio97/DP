package domain;

import java.util.List;

public class Sponsor extends Actor {
	List<Sponsorship> sponsorships;

	public List<Sponsorship> getSponsorships() {
		return sponsorships;
	}

	public void setSponsorships(List<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}
}
