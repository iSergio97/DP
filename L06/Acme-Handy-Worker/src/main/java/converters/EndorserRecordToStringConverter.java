
package converters;

import domain.EducationalRecord;
import domain.EndorserRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EndorserRecordToStringConverter implements Converter<EndorserRecord, String> {

	@Override
	public String convert(final EndorserRecord endorserRecord) {
		String result;

		if (endorserRecord == null)
			result = null;
		else
			result = String.valueOf(endorserRecord.getId());
		return result;
	}
}
