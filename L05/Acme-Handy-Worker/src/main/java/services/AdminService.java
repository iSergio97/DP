
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.Authority;
import security.UserAccount;
import domain.Admin;
import domain.Endorsement;
import domain.Message;
import domain.MessageBox;
import domain.Note;
import domain.SocialProfile;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdminRepository		adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private MessageBoxService	messageBoxService;


	// Constructors -----------------------------------------------------------

	public AdminService() {
		super();
	}

	// Methods ----------------------------------------------------------------

	public Admin create() {
		final Admin admin = new Admin();

		final UserAccount userAccount = new UserAccount();
		final List<Authority> authorities = new ArrayList<>();
		final Authority authority = new Authority();
		final Collection<MessageBox> messageBoxes = this.messageBoxService.createSystemBoxes();

		authority.setAuthority(Authority.ADMIN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		admin.setName("");
		admin.setMiddleName("");
		admin.setSurname("");
		admin.setEmail("");
		admin.setPhoneNumber("");
		admin.setAddress("");

		admin.setUserAccount(userAccount);
		admin.setMessageBoxes(messageBoxes);
		admin.setSocialProfiles(new ArrayList<SocialProfile>());
		admin.setEndorsedBy(new ArrayList<Endorsement>());
		admin.setEndorses(new ArrayList<Endorsement>());
		admin.setMessagesSent(new ArrayList<Message>());
		admin.setMessagesReceived(new ArrayList<Message>());
		admin.setNotes(new ArrayList<Note>());

		return admin;
	}
	public Admin save(final Admin admin) {
		Assert.isTrue(admin != null);
		return this.adminRepository.save(admin);
	}

	public Iterable<Admin> save(final Iterable<Admin> admins) {
		Assert.isTrue(admins != null);
		return this.adminRepository.save(admins);
	}

	public void delete(final Admin admin) {
		Assert.isTrue(admin != null);
		this.adminRepository.delete(admin);
	}

	public void delete(final Iterable<Admin> admins) {
		Assert.isTrue(admins != null);
		this.adminRepository.delete(admins);
	}

	public Admin findById(final int id) {
		return this.adminRepository.findOne(id);
	}

	public List<Admin> findAll() {
		return this.adminRepository.findAll();
	}

	// Specific Methods ----------------------------------------------------------------

}
