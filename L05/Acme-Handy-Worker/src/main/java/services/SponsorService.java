
package services;

import java.util.ArrayList;
import java.util.List;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class SponsorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SponsorRepository	sponsorRepository;


	// Supporting services ----------------------------------------------------

	@Autowired
	private MessageBoxService messageBoxService;

	// Constructors -----------------------------------------------------------

	public SponsorService() {
		super();
	}

	// Methods ----------------------------------------------------------------

	public Sponsor create() {
		Sponsor sponsor = new Sponsor();
		sponsor.setName("");
		sponsor.setMiddleName("");
		sponsor.setSurname("");
		sponsor.setSocialProfiles(new ArrayList<SocialProfile>());
		sponsor.setPhoto("");
		sponsor.setEmail("");
		sponsor.setPhoneNumber("");
		sponsor.setAddress("");
		final UserAccount cuenta = new UserAccount();
		final List<Authority> ls = new ArrayList<>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.SPONSOR);
		ls.add(authority);
		cuenta.setAuthorities(ls);
		cuenta.setPassword("");
		cuenta.setUsername("");
		sponsor.setUserAccount(cuenta);
		sponsor.setMessageBoxes(this.messageBoxService.createSystemBoxes());
		sponsor.setEndorsedBy(new ArrayList<Endorsement>());
		sponsor.setEndorses(new ArrayList<Endorsement>());
		sponsor.setMessagesSent(new ArrayList<Message>());
		sponsor.setMessagesReceived(new ArrayList<Message>());
		sponsor.setNotes(new ArrayList<Note>());
		sponsor.setSponsorships(new ArrayList<Sponsorship>());

		return this.sponsorRepository.save(sponsor);
	}

	public Sponsor save(final Sponsor sponsor) {
		Assert.isTrue(sponsor != null);
		return this.sponsorRepository.save(sponsor);
	}

	public Iterable<Sponsor> save(final Iterable<Sponsor> sponsors) {
		Assert.isTrue(sponsors != null);
		return this.sponsorRepository.save(sponsors);
	}

	public void delete(final Sponsor sponsor) {
		Assert.isTrue(sponsor != null);
		this.sponsorRepository.delete(sponsor);
	}

	public void delete(final Iterable<Sponsor> sponsors) {
		Assert.isTrue(sponsors != null);
		this.sponsorRepository.delete(sponsors);
	}

	public Sponsor findById(final int id) {
		return this.sponsorRepository.findOne(id);
	}

	public List<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}

	// Specific Methods ----------------------------------------------------------------
}
