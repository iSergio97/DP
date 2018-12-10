
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.LoginService;
import security.UserAccount;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;

@Service
@Transactional
public class FinderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FinderRepository			finderRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private HandyWorkerService			handyWorkerService;

	@Autowired
	private FixUpTaskService			fixUpTaskService;

	@Autowired
	private WarrantyService				warrantyService;

	@Autowired
	private FixUpTaskCategoryService	fixUpTaskCategoryService;


	// Constructors -----------------------------------------------------------

	public FinderService() {

		super();
	}

	// Methods ----------------------------------------------------------------

	public Finder create() {
		final Finder finder = new Finder();

		finder.setKeyword("");

		final Date dateMin = new Date();
		dateMin.setTime(dateMin.getMonth() + 1);
		finder.setMinimumDate(dateMin);

		Date dateMax;
		dateMax = new Date();
		dateMax.setTime(dateMax.getMonth() + 3);
		finder.setMaximumDate(dateMax);

		UserAccount login = LoginService.getPrincipal();
		HandyWorker handyWorker;
		handyWorker = this.handyWorkerService.findById(login.getId());
		finder.setHandyWorker(handyWorker);

		finder.setFixUpTasks(new ArrayList<FixUpTask>());

		finder.setWarranty(this.warrantyService.create());

		return finder;
	}

	public Finder save(final Finder finder) {
		Assert.isTrue(finder != null);
		return this.finderRepository.save(finder);
	}

	public Iterable<Finder> save(final Iterable<Finder> finders) {
		Assert.isTrue(finders != null);
		return this.finderRepository.save(finders);
	}

	public void delete(final Finder finder) {
		Assert.isTrue(finder != null);
		this.finderRepository.delete(finder);
	}

	public void delete(final Iterable<Finder> finders) {
		Assert.isTrue(finders != null);
		this.finderRepository.delete(finders);
	}

	public Finder findById(final int id) {
		return this.finderRepository.findOne(id);
	}

	public List<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	// Specific Methods ----------------------------------------------------------------

}
