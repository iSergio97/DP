
package services;

import java.util.ArrayList;
import java.util.List;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.UserAccount;

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



    //Methods

    public HandyWorker create() {
        final HandyWorker handyWorker = new HandyWorker();
        handyWorker.setName("");
        handyWorker.setMiddleName("");
        handyWorker.setSurname("");
        handyWorker.setSocialProfiles(new ArrayList<SocialProfile>());
        handyWorker.setPhoto("");
        handyWorker.setEmail("");
        handyWorker.setPhoneNumber("");
        handyWorker.setAddress("");
        final UserAccount cuenta = new UserAccount();
        final List<Authority> ls = new ArrayList<>();
        final Authority authority = new Authority();
        authority.setAuthority(Authority.HANDY_WORKER);
        ls.add(authority);
        cuenta.setAuthorities(ls);
        cuenta.setPassword("");
        cuenta.setUsername("");
        handyWorker.setUserAccount(cuenta);
        handyWorker.setMessageBoxes(this.messageBoxService.createSystemBoxes());
        handyWorker.setEndorsedBy(new ArrayList<Endorsement>());
        handyWorker.setEndorses(new ArrayList<Endorsement>());
        handyWorker.setMessagesSent(new ArrayList<Message>());
        handyWorker.setMessagesReceived(new ArrayList<Message>());
        handyWorker.setNotes(new ArrayList<Note>());
        List<Application> applications = new ArrayList<>();
        handyWorker.setApplications(applications);
        handyWorker.setFinder(new Finder());
        handyWorker.setCurriculum(new Curriculum());
        handyWorker.setTutorials(new ArrayList<Tutorial>());
        return this.handyWorkerRepository.save(handyWorker);
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

	public HandyWorker getHandyWorkerByUserAccountId(final int userAccountId) {
		HandyWorker hw;
		hw = this.handyWorkerRepository.findByUserAccountId(userAccountId);
		return hw;
	}

}
