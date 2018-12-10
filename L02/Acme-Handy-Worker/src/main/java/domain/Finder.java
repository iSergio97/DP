
package domain;

import java.util.Collection;

import javax.validation.Valid;

import org.joda.time.LocalDateTime;

public class Finder extends DomainEntity {

	private String keyword;
	private int minimunPrice;
	private int maximumPrice;
	private LocalDateTime minimunDate;
	private LocalDateTime maximumDate;

	private HandyWorker handyWorker;
	private Collection<FixUpTask> fixUpTasks;
	private Warranty warranty;
	private FixUpTaskCategory fixUpTaskCategory;

	@Valid
	public HandyWorker getHandyWorker() {
		return handyWorker;
	}

	public void setHandyWorker(HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

	@Valid
	public Collection<FixUpTask> getFixUpTask() {
		return fixUpTasks;
	}

	public void setFixUpTask(Collection<FixUpTask> fixUpTask) {
		this.fixUpTasks = fixUpTask;
	}

	@Valid
	public Warranty getWarranty() {
		return warranty;
	}

	public void setWarranty(Warranty warranty) {
		this.warranty = warranty;
	}

	@Valid
	public FixUpTaskCategory getFixUpTaskCategory() {
		return fixUpTaskCategory;
	}

	public void setFixUpTaskCategory(FixUpTaskCategory fixUpTaskCategory) {
		this.fixUpTaskCategory = fixUpTaskCategory;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public int getMinimunPrice() {
		return this.minimunPrice;
	}

	public int getMaximumPrice() {
		return this.maximumPrice;
	}

	public LocalDateTime getMinimunDate() {
		return this.minimunDate;
	}

	public LocalDateTime getMaximumDate() {
		return this.maximumDate;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	public void setMinimunPrice(final int minimunPrice) {
		this.minimunPrice = minimunPrice;
	}

	public void setMaximumPrice(final int maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	public void setMinimunDate(final LocalDateTime minimunDate) {
		this.minimunDate = minimunDate;
	}

	public void setMaximumDate(final LocalDateTime maximumDate) {
		this.maximumDate = maximumDate;
	}

}
