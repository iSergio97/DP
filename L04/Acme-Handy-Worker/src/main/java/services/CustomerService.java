
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Customer;
import domain.Endorsement;
import domain.FixUpTask;
import domain.Message;
import domain.Note;
import domain.SocialProfile;

@Service
@Transactional
public class CustomerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CustomerRepository		customerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private MessageBoxService		messageBoxService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


	// Constructors -----------------------------------------------------------

	public CustomerService() {
		super();
	}

	// CRUD methods -----------------------------------------------------------

	public Customer create() {

		final Customer customer;
		customer = new Customer();
		UserAccount cuenta;
		cuenta = new UserAccount();
		List<Authority> ls;
		ls = new ArrayList<>();
		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.CUSTOMER);
		ls.add(authority);
		cuenta.setAuthorities(ls);
		UserAccount cuentaSaved;
		cuentaSaved = this.userAccountRepository.save(cuenta);
		customer.setUserAccount(cuentaSaved);
		Customer customerSaved;
		customerSaved = this.save(customer);
		customerSaved.setName("");
		customerSaved.setMiddleName("");
		customerSaved.setSurname("");
		customerSaved.setSocialProfiles(new ArrayList<SocialProfile>());
		customerSaved.setPhoto("");
		customerSaved.setEmail("");
		customerSaved.setPhoneNumber("");
		customerSaved.setAddress("");
		customerSaved.setMessageBoxes(this.messageBoxService.createSystemBoxes());
		customerSaved.setEndorsedBy(new ArrayList<Endorsement>());
		customerSaved.setEndorses(new ArrayList<Endorsement>());
		customerSaved.setMessagesSent(new ArrayList<Message>());
		customerSaved.setMessagesReceived(new ArrayList<Message>());
		customerSaved.setNotes(new ArrayList<Note>());
		customerSaved.setFixUpTasks(new ArrayList<FixUpTask>());

		return this.save(customerSaved);
	}

	public Customer update(final Customer customer) {
		int id;
		id = customer.getId();
		Customer customer1;
		customer1 = this.create();
		customer1.setId(id);
		customer1.setName(customer.getName());
		customer1.setMiddleName(customer.getMiddleName());
		final UserAccount userAccount = new UserAccount();
		userAccount.setUsername(customer.getUserAccount().getUsername());
		userAccount.setPassword(customer.getUserAccount().getPassword());
		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.CUSTOMER);
		List<Authority> ls;
		ls = new ArrayList<>();
		ls.add(authority);
		userAccount.setAuthorities(ls);
		customer1.setUserAccount(userAccount);
		customer1.setSurname(customer.getSurname());
		customer1.setSocialProfiles(customer.getSocialProfiles());
		customer1.setPhoto(customer.getPhoto());
		customer1.setEmail(customer.getEmail());
		customer1.setPhoneNumber(customer.getPhoneNumber());
		customer1.setAddress(customer.getAddress());
		customer1.setMessageBoxes(customer.getMessageBoxes());
		customer1.setEndorsedBy(customer.getEndorsedBy());
		customer1.setEndorses(customer.getEndorses());
		customer1.setMessagesSent(customer.getMessagesSent());
		customer1.setMessagesReceived(customer.getMessagesReceived());
		customer1.setNotes(customer.getNotes());
		customer1.setFixUpTasks(customer.getFixUpTasks());

		return this.customerRepository.save(customer1);
	}

	public Customer save(final Customer customer) {
		Assert.isTrue(customer != null);
		return this.customerRepository.save(customer);
	}

	public Iterable<Customer> save(final Iterable<Customer> customers) {
		Assert.isTrue(customers != null);
		return this.customerRepository.save(customers);
	}

	public void delete(final Customer customer) {
		Assert.isTrue(customer != null);
		this.customerRepository.delete(customer);
	}

	public void delete(final List<Customer> customers) {
		Assert.isTrue(customers != null);
		this.customerRepository.delete(customers);
	}

	public Customer findById(final Integer id) {
		//Assert.isInstanceOf(Integer.class, id);
		Assert.isTrue(id != null);
		return this.customerRepository.findOne(id);
	}

	public Collection<Customer> findAll() {
		return this.customerRepository.findAll();
	}

	public Customer findByUserAccountId(final int id) {
		return this.customerRepository.findByUserAccountId(id);
	}

	public Customer findPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		return this.findByUserAccountId(userAccount.getId());
	}

	// Other methods ----------------------------------------------------------

	public List<Customer> getTopFixUpTasks() {
		return this.customerRepository.getTopFixUpTasks();
	}

	public List<Customer> getTopComplaints() {
		return this.customerRepository.getTopComplaints();
	}

	public Double[] getFixUpTaskStatistics() {
		return this.customerRepository.getFixUpTaskStatistics();
	}

}
