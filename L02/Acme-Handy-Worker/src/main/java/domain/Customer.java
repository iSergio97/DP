
package domain;

import java.util.Collection;

import javax.validation.Valid;

public class Customer extends Actor {

	Collection<FixUpTask> fixUpTasks;

	@Valid
	public Collection<FixUpTask> getFixUpTasks() {
		return this.fixUpTasks;
	}

	public void setFixUpTasks(final Collection<FixUpTask> fixUpTasks) {
		this.fixUpTasks = fixUpTasks;
	}
}
