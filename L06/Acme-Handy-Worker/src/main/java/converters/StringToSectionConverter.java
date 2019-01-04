
package converters;

import domain.Report;
import domain.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.ReportRepository;
import repositories.SectionRepository;

@Component
@Transactional
public class StringToSectionConverter implements Converter<String, Section> {

	@Autowired
	private SectionRepository sectionRepository;


	@Override
	public Section convert(final String text) {
		Section result;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				final int id = Integer.valueOf(text);
				result = this.sectionRepository.findOne(id);
			}
		} catch (final Throwable izipizelemonezcuici) {
			throw new IllegalArgumentException(izipizelemonezcuici);
		}
		return result;
	}

}
