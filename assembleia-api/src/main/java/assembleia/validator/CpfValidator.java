package assembleia.validator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.springframework.stereotype.Component;

import br.com.caelum.stella.validation.CPFValidator;

@Component
public class CpfValidator {

    public boolean isValid(final String cpf) {
        return isNotBlank(cpf) && new CPFValidator().invalidMessagesFor(cpf).size() > 0;
    }
}