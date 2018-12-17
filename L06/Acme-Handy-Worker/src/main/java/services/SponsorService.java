
package services;

import java.util.ArrayList;
import java.util.List;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class SponsorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SponsorRepository	sponsorRepository;


	// Supporting services ----------------------------------------------------

	@Autowired
	private MessageBoxService messageBoxService;

	@Autowired
	private UserAccountRepository userAccountRepository;

	// Constructors -----------------------------------------------------------

	public SponsorService() {
		super();
	}

	// Methods ----------------------------------------------------------------

	public Sponsor create() {
		Sponsor sponsor = new Sponsor();
		List<Authority> ls = new ArrayList<>();
		Authority authority = new Authority();
		authority.setAuthority(Authority.SPONSOR);
		ls.add(authority);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(ls);
		sponsor.setMessageBoxes(messageBoxService.createSystemBoxes());
		UserAccount saved = userAccountRepository.save(userAccount);
		sponsor.setUserAccount(saved);


		return sponsor;
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
