
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.WarrantyRepository;
import domain.Warranty;

@Component
@Transactional
public class StringToCustomerConverter implements Converter<String, Warranty> {

	@Autowired
	WarrantyRepository	warrantyRepository;


	@Override
	public Warranty convert(final String text) {
		Warranty result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToCustomerConverter IF: " + text);
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.warrantyRepository.findOne(id);
				System.out.println("Error en StringToCustomerConverter ELSE: " + result);
			}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToCustomerConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
