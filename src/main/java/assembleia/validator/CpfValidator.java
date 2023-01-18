package assembleia.validator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import br.com.caelum.stella.validation.CPFValidator;

@Component
public class CpfValidator{

    public boolean isValid(final String cpf) {
        var errors = new CPFValidator().invalidMessagesFor(cpf);
        return isNotBlank(cpf) && errors.size() > 0;
    }
}