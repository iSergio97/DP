
package domain;

import java.util.Collection;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

public class FixUpTaskCategory extends DomainEntity {

	private Collection<FixUpTask> fixUpTasks;
	private Set<FixUpTaskCategory> fixUpTaskCategoryChildren;
	private FixUpTaskCategory fixUpTaskCategoryParent;

	private String name;

	public Set<FixUpTaskCategory> getFixUpTaskCategoryChildren() {
		return this.fixUpTaskCategoryChildren;
	}

	public void setFixUpTaskCategoryChildren(Set<FixUpTaskCategory> fixUpTaskCategoryChildren) {
		this.fixUpTaskCategoryChildren = fixUpTaskCategoryChildren;
	}

	@Valid
	public FixUpTaskCategory getFixUpTaskCategoryParent() {
		return this.fixUpTaskCategoryParent;
	}

	public void setFixUpTaskCategoryChildren(final FixUpTaskCategory fixUpTaskCategoryChildren) {
		this.fixUpTaskCategoryParent = fixUpTaskCategoryChildren;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setFixUpTaskCategoryParent(final FixUpTaskCategory fixUpTaskCategoryParent) {
		this.fixUpTaskCategoryParent = fixUpTaskCategoryParent;
	}

	@Valid
	public Collection<FixUpTask> getFixUpTasks() {
		return fixUpTasks;
	}

	public void setFixUpTasks(Collection<FixUpTask> fixUpTasks) {
		this.fixUpTasks = fixUpTasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final FixUpTaskCategory other = (FixUpTaskCategory) obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FixUpTaskCategory [name=" + this.name + "]";
	}

}
