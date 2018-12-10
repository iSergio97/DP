
package domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Warranty extends DomainEntity {

	private String title;
	private String terms;
	private List<String> applicableLaws;

	@NotNull
	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@NotBlank
	public String getTerms() {
		return this.terms;
	}

	public void setTerms(final String terms) {
		this.terms = terms;
	}

	public List<String> getApplicableLaws() {
		return this.applicableLaws;
	}

	public void setApplicableLaws(final List<String> applicableLaws) {
		this.applicableLaws = applicableLaws;
	}
}
