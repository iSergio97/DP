
package services;

import java.util.ArrayList;
import java.util.List;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class RefereeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RefereeRepository	refereeRepository;


	// Supporting services ----------------------------------------------------

	@Autowired
	private MessageBoxService messageBoxService;

	 @Autowired
	 private UserAccountRepository userAccountRepository;
	// Constructors -----------------------------------------------------------

	public RefereeService() {
		super();
	}

	// Methods ----------------------------------------------------------------

	public Referee create(){
		Referee referee = new Referee();
		referee.setMessageBoxes(messageBoxService.createSystemBoxes());
		UserAccount userAccount = new UserAccount();
		List<Authority> ls = new ArrayList<>();
		Authority a = new Authority();
		a.setAuthority(Authority.REFEREE);
		ls.add(a);
		userAccount.setAuthorities(ls);
		UserAccount saved = userAccountRepository.save(userAccount);
		referee.setUserAccount(saved);

		return referee;
	}

	public Referee save(final Referee referee) {
		Assert.isTrue(referee != null);
		return this.refereeRepository.save(referee);
	}

	public Iterable<Referee> save(final Iterable<Referee> referees) {
		Assert.isTrue(referees != null);
		return this.refereeRepository.save(referees);
	}

	public void delete(final Referee referee) {
		Assert.isTrue(referee != null);
		this.refereeRepository.delete(referee);
	}

	public void delete(final Iterable<Referee> referees) {
		Assert.isTrue(referees != null);
		this.refereeRepository.delete(referees);
	}

	public Referee findById(final int id) {
		return this.refereeRepository.findOne(id);
	}

	public List<Referee> findAll() {
		return this.refereeRepository.findAll();
	}

	// Specific Methods ----------------------------------------------------------------
}
