package domain;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.joda.time.LocalDateTime;

public class Application extends DomainEntity {
	private LocalDateTime moment;
	private String status;
	private int offeredPrice;

	private HandyWorker handyWorker;
	private FixUpTask fixUpTask;

	@Valid
	public HandyWorker getHandyWorker() {
		return handyWorker;
	}

	public void setHandyWorker(HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

	@Valid
	public FixUpTask getFixUpTask() {
		return fixUpTask;
	}

	public void setFixUpTask(FixUpTask fixUpTask) {
		this.fixUpTask = fixUpTask;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	@Pattern(regexp = "^ACCEPTED|REJECTED|PENDING$")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOfferedPrice() {
		return offeredPrice;
	}

	public void setOfferedPrice(int offeredPrice) {
		this.offeredPrice = offeredPrice;
	}
}
