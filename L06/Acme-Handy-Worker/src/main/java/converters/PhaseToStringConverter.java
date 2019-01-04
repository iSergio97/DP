
package converters;

import domain.PersonalRecord;
import domain.Phase;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PhaseToStringConverter implements Converter<Phase, String> {

	@Override
	public String convert(final Phase phase) {
		String result;

		if (phase== null)
			result = null;
		else
			result = String.valueOf(phase.getId());
		return result;
	}
}
