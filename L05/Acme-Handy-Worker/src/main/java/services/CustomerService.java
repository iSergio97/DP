
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

		Customer customer;
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
		customer.setMessageBoxes(messageBoxService.createSystemBoxes());
		return customer;
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
