
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import security.Authority;
import security.UserAccount;

import javax.transaction.Transactional;

@Component
@Transactional
public class UserAccountToStringConverter implements Converter<UserAccount, String> {

	@Override
	public String convert(final UserAccount userAccount) {
		String result;

		if (userAccount == null)
			result = null;
		else
			result = userAccount.getUsername();

		return result;
	}

}
