
package domain;

import java.sql.Date;
import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Endorsement extends DomainEntity {

	private Date date;
	private Collection<String> comment;
	
	private Actor endorsed;
	private Actor endorser;

	@NotNull
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Collection<String> getComment() {
		return this.comment;
	}

	public void setComment(final Collection<String> comment) {
		this.comment = comment;
	}

	@NotNull
	@Valid
	public Actor getEndorsed() {
		return this.endorsed;
	}

	public void setEndorsed(final Actor endorsed) {
		this.endorsed = endorsed;
	}

	@NotNull
	@Valid
	public Actor getEndorser() {
		return this.endorser;
	}

	public void setEndorser(final Actor endorser) {
		this.endorser = endorser;
	}

}
