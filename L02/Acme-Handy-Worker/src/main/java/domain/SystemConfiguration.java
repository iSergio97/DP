package domain;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDateTime;

public class SystemConfiguration extends DomainEntity {

	private String name;
	private String banner;
	private String message;
	private List<String> spamWords;
	private double VAT;
	private String defaultCountryCode;
	private List<String> creditCardMakers;
	private List<String> positiveWords;
	private List<String> negativeWords;
	private int maximumFinderResults;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@URL
	@NotBlank
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSpamWords() {
		return spamWords;
	}

	public void setSpamWords(List<String> spamWords) {
		this.spamWords = spamWords;
	}

	public double getVAT() {
		return VAT;
	}

	public void setVAT(double VAT) {
		this.VAT = VAT;
	}

	@Pattern(regexp = "^\\+\\d{1,3}$")
	public String getDefaultCountryCode() {
		return defaultCountryCode;
	}

	public void setDefaultCountryCode(String defaultCountryCode) {
		this.defaultCountryCode = defaultCountryCode;
	}

	public String getCreditCardMakers() {
		return creditCardMakers;
	}

	public void setCreditCardMakers(List<String> creditCardMakers) {
		this.creditCardMakers = creditCardMakers;
	}

	public String getPositiveWords() {
		return positiveWords;
	}

	public void setPositiveWords(List<String> positiveWords) {
		this.positiveWords = positiveWords;
	}

	public String getNegativeWords() {
		return negativeWords;
	}

	public void setNegativeWords(List<String> negativeWords) {
		this.negativeWords = negativeWords;
	}

	@Range(min=0, max=100)
	public String getMaximumFinderResults() {
		return maximumFinderResults;
	}

	public void setMaximumFinderResults(int maximumFinderResults) {
		this.maximumFinderResults = maximumFinderResults;
	}

}
