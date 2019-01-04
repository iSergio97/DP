
package converters;

import domain.Phase;
import domain.ProfessionalRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ProfessionalRecordToStringConverter implements Converter<ProfessionalRecord, String> {

	@Override
	public String convert(final ProfessionalRecord professionalRecord) {
		String result;

		if (professionalRecord== null)
			result = null;
		else
			result = String.valueOf(professionalRecord.getId());
		return result;
	}
}
