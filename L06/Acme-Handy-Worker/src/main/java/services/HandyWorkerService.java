
package services;

import java.util.ArrayList;
import java.util.List;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import repositories.HandyWorkerRepository;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;


	//Constructors
	public HandyWorkerService() {
		super();
	}


	// SuportingServices

	@Autowired
	private FixUpTaskService	fixUpTaskService;

	@Autowired
	private ApplicationService	applicationService;

    @Autowired
    private MessageBoxService	messageBoxService;

    @Autowired
	private UserAccountRepository userAccountRepository;

    @Autowired
	private FinderService finderService;

     @Autowired
	 private CurriculumService curriculumService;


    //Methods

	public HandyWorker create() {
		HandyWorker handyWorker = new HandyWorker();
		List<Authority> ls = new ArrayList<>();
		Authority authority = new Authority();
		authority.setAuthority(Authority.HANDY_WORKER);
		ls.add(authority);
		UserAccount ua = new UserAccount();
		ua.setAuthorities(ls);
		UserAccount saved = userAccountRepository.save(ua);
		handyWorker.setUserAccount(saved);
		handyWorker.setMessageBoxes(messageBoxService.createSystemBoxes());
		handyWorker.setApplications(new ArrayList<Application>());
		handyWorker.setFinder(finderService.create());
		handyWorker.setCurriculum(curriculumService.create());
		handyWorker.setTutorials(new ArrayList<Tutorial>());
		handyWorker.setNotes(new ArrayList<Note>());
		handyWorker.setMessagesSent(new ArrayList<Message>());
		handyWorker.setMessagesReceived(new ArrayList<Message>());
		handyWorker.setEndorses(new ArrayList<Endorsement>());
		handyWorker.setEndorsedBy(new ArrayList<Endorsement>());
		handyWorker.setSocialProfiles(new ArrayList<SocialProfile>());
		handyWorker.setIsBanned(false);
		handyWorker.setAddress("");
		handyWorker.setPhoneNumber("");
		handyWorker.setEmail("");
		handyWorker.setPhoto("");
		handyWorker.setMiddleName("");
		handyWorker.setName("");
		handyWorker.setSurname("");
		handyWorker.setMake("");

		return handyWorker;
	}

    public HandyWorker save(final HandyWorker handyWorker) {
		Assert.isTrue(handyWorker != null);
		return this.handyWorkerRepository.save(handyWorker);
	}

	public Iterable<HandyWorker> save(final Iterable<HandyWorker> handyWorkers) {
		Assert.isTrue(handyWorkers != null);
		return this.handyWorkerRepository.save(handyWorkers);
	}

	public void delete(final HandyWorker handyWorker) {
		Assert.isTrue(handyWorker != null);
		this.handyWorkerRepository.delete(handyWorker);
	}

	public void delete(final Iterable<HandyWorker> handyWorkers) {
		Assert.isTrue(handyWorkers != null);
		this.handyWorkerRepository.delete(handyWorkers);
	}

	public HandyWorker findById(final int id) {
		return this.handyWorkerRepository.findOne(id);
	}

	public List<HandyWorker> findAll() {
		return this.handyWorkerRepository.findAll();
	}

	public List<HandyWorker> getTopApplications() {
		return this.handyWorkerRepository.getTopApplications();
	}

	public List<HandyWorker> getTopComplaints() {
		return this.handyWorkerRepository.getTopComplaints();
	}

//	public HandyWorker getHandyWorkerByUserAccountId(final int userAccountId) {
//		HandyWorker hw;
//		hw = this.handyWorkerRepository.findByUserAccountId(userAccountId);
//		return hw;
//	}

}
