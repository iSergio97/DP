
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.FixUpTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FixUpTaskServiceTest extends AbstractTest {

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	@Test
	public void test() {
		FixUpTask fut, saved;
		Collection<FixUpTask> futs;

		super.authenticate("customer3");

		fut = this.fixUpTaskService.create();
		Assert.isTrue(fut.getTicker().equals(""));
		Assert.isNull(fut.getMoment());
		Assert.isTrue(fut.getDescription().equals(""));
		Assert.isTrue(fut.getAddress().equals(""));
		Assert.isTrue(fut.getCustomer().getUserAccount().getUsername().equals("customer3"));
		Assert.isTrue(fut.getApplications().isEmpty());
		Assert.isNull(fut.getWarranty());
		Assert.isNull(fut.getWorkPlan());
		Assert.isTrue(fut.getComplaints().isEmpty());

		//Falta inicializarlo
		saved = this.fixUpTaskService.save(fut);
		futs = this.fixUpTaskService.findAll();
		Assert.isTrue(futs.contains(saved));

		super.authenticate(null);
	}

}
