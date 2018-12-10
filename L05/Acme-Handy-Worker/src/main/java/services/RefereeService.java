
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

@Service
@Transactional
public class RefereeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RefereeRepository	refereeRepository;


	// Supporting services ----------------------------------------------------

	@Autowired
	private MessageBoxService messageBoxService;
	// Constructors -----------------------------------------------------------

	public RefereeService() {
		super();
	}

	// Methods ----------------------------------------------------------------

	public Referee create() {
		Referee referee = new Referee();
		referee.setName("");
		referee.setMiddleName("");
		referee.setSurname("");
		referee.setSocialProfiles(new ArrayList<SocialProfile>());
		referee.setPhoto("");
		referee.setEmail("");
		referee.setPhoneNumber("");
		referee.setAddress("");
		final UserAccount cuenta = new UserAccount();
		final List<Authority> ls = new ArrayList<>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.REFEREE);
		ls.add(authority);
		cuenta.setAuthorities(ls);
		cuenta.setPassword("");
		cuenta.setUsername("");
		referee.setUserAccount(cuenta);
		referee.setMessageBoxes(this.messageBoxService.createSystemBoxes());
		referee.setEndorsedBy(new ArrayList<Endorsement>());
		referee.setEndorses(new ArrayList<Endorsement>());
		referee.setMessagesSent(new ArrayList<Message>());
		referee.setMessagesReceived(new ArrayList<Message>());
		referee.setNotes(new ArrayList<Note>());
		referee.setReports(new ArrayList<Report>());

		return this.refereeRepository.save(referee);
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
