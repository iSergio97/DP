
package converters;

import domain.HandyWorker;
import domain.MiscellaneousRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.HandyWorkerRepository;
import repositories.MiscellaneousRecordRepository;

@Component
@Transactional
public class StringToMiscellaneousRecordConverter implements Converter<String, MiscellaneousRecord> {

	@Autowired
	private MiscellaneousRecordRepository miscellaneousRecordRepository;


	@Override
	public MiscellaneousRecord convert(final String text) {
		MiscellaneousRecord result;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				final int id = Integer.valueOf(text);
				result = this.miscellaneousRecordRepository.findOne(id);
			}
		} catch (final Throwable izipizelemonezcuici) {
			throw new IllegalArgumentException(izipizelemonezcuici);
		}
		return result;
	}

}
