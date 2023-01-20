package assembleia.validator;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.springframework.stereotype.Component;

import br.com.caelum.stella.validation.CPFValidator;

@Component
public class CpfValidator {

    public boolean isValid(final String cpf) {
        return nonNull(cpf) && cpf.length() > 0 && new CPFValidator().invalidMessagesFor(cpf).size() == 0;
    }
}