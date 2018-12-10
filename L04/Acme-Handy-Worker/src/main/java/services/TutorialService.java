
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Tutorial;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class TutorialService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TutorialRepository	tutorialRepository;


	// Supporting services ----------------------------------------------------

	@Autowired
	private HandyWorkerService handyWorkerService;

	@Autowired
	private SponsorshipService sponsorshipService;

	// Constructors -----------------------------------------------------------

	public TutorialService() {
		super();
	}

	// Methods ----------------------------------------------------------------

	public Tutorial create() {
		Tutorial tutorial = new Tutorial();
		UserAccount login = LoginService.getPrincipal();
		tutorial.setHandyWorker(handyWorkerService.findById(login.getId()));
		tutorial.setLastUpdated(new Date());
		tutorial.setPictures(new ArrayList<String>());
		tutorial.setSections(new ArrayList<Section>());
		tutorial.setSummary("");
		tutorial.setSponsorship(sponsorshipService.create());
		tutorial.setTitle("");

		return this.save(tutorial);
	}

	public Tutorial save(final Tutorial tutorial) {
		Assert.isTrue(tutorial != null);
		return this.tutorialRepository.save(tutorial);
	}

	public Iterable<Tutorial> save(final Iterable<Tutorial> tutorials) {
		Assert.isTrue(tutorials != null);
		return this.tutorialRepository.save(tutorials);
	}

	public void delete(final Tutorial tutorial) {
		Assert.isTrue(tutorial != null);
		this.tutorialRepository.delete(tutorial);
	}

	public void delete(final Iterable<Tutorial> tutorials) {
		Assert.isTrue(tutorials != null);
		this.tutorialRepository.delete(tutorials);
	}

	public Tutorial findById(final int id) {
		return this.tutorialRepository.findOne(id);
	}

	public List<Tutorial> findAll() {
		return this.tutorialRepository.findAll();
	}

	// Specific Methods ----------------------------------------------------------------
}
