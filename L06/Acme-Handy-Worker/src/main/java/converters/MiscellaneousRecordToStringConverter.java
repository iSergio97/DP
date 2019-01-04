
package converters;

import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MiscellaneousRecordToStringConverter implements Converter<MiscellaneousRecord, String> {

	@Override
	public String convert(final MiscellaneousRecord miscellaneousRecord) {
		String result;

		if (miscellaneousRecord == null)
			result = null;
		else
			result = String.valueOf(miscellaneousRecord.getId());
		return result;
	}
}
