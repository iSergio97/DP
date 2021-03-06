
package domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

public class SystemConfiguration extends DomainEntity {

	// Fields -----------------------------------------------------------------

	private String				name;
	private String				banner;
	private String				message;
	private Collection<String>	spamWords;
	private double				VAT;
	private String				defaultCountryCode;
	private Collection<String>	creditCardMakers;
	private Collection<String>	positiveWords;
	private Collection<String>	negativeWords;
	private int					maximumFinderResults;


	// Field access methods ---------------------------------------------------

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@URL
	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public Collection<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final List<String> spamWords) {
		this.spamWords = spamWords;
	}

	public double getVAT() {
		return this.VAT;
	}

	public void setVAT(final double VAT) {
		this.VAT = VAT;
	}

	@Pattern(regexp = "^\\+\\d{1,3}$")
	public String getDefaultCountryCode() {
		return this.defaultCountryCode;
	}

	public void setDefaultCountryCode(final String defaultCountryCode) {
		this.defaultCountryCode = defaultCountryCode;
	}

	public Collection<String> getCreditCardMakers() {
		return this.creditCardMakers;
	}

	public void setCreditCardMakers(final List<String> creditCardMakers) {
		this.creditCardMakers = creditCardMakers;
	}

	@ElementCollection
	public Collection<String> getPositiveWords() {
		return this.positiveWords;
	}

	public void setPositiveWords(final List<String> positiveWords) {
		this.positiveWords = positiveWords;
	}

	@ElementCollection
	public Collection<String> getNegativeWords() {
		return this.negativeWords;
	}

	public void setNegativeWords(final List<String> negativeWords) {
		this.negativeWords = negativeWords;
	}

	@Range(min = 0, max = 100)
	public Integer getMaximumFinderResults() {
		return this.maximumFinderResults;
	}

	public void setMaximumFinderResults(final int maximumFinderResults) {
		this.maximumFinderResults = maximumFinderResults;
	}

}
