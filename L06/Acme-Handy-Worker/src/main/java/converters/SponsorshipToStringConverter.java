
package converters;

import domain.Sponsor;
import domain.Sponsorship;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SponsorshipToStringConverter implements Converter<Sponsorship, String> {

	@Override
	public String convert(final Sponsorship sponsorShip) {
		String result;
		if (sponsorShip == null)
			result = null;
		else
			result = String.valueOf(sponsorShip.getId());

		return result;
	}

}
