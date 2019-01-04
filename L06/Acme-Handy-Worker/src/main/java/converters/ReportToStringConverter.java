
package converters;

import domain.Referee;
import domain.Report;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class ReportToStringConverter implements Converter<Report, String> {

	@Override
	public String convert(final Report report) {
		String result;
		if (report == null)
			result = null;
		else
			result = String.valueOf(report.getId());

		return result;
	}

}
