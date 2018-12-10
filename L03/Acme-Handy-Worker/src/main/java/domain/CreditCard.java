
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

// <<Datatype>>
@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

	// Fields -----------------------------------------------------------------

	private String	holder;
	private String	brand;
	private String	number;
	private int		expirationMonth;
	private int		expirationYear;
	private int		CVV;


	// Field access methods ---------------------------------------------------

	@NotBlank
	public String getHolder() {
		return this.holder;
	}

	public void setHolder(final String holder) {
		this.holder = holder;
	}

	@NotBlank
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(final String brand) {
		this.brand = brand;
	}

	@Pattern(regexp = "^([\\d]){16}$")
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@Range(min = 1, max = 12)
	public int getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	//@Future
	public int getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final int expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	public int getCVV() {
		return this.CVV;
	}

	public void setCVV(final int cVV) {
		this.CVV = cVV;
	}
}
