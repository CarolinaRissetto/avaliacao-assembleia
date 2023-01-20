package assembleia.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import assembleia.exception.DomainException;
import assembleia.request.CadastroPautaRequest;

@RunWith(MockitoJUnitRunner.class)
public class CadastroPautaRequestValidatorTest {

    private final CadastroPautaRequestValidator validator = new CadastroPautaRequestValidator();

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionRequestInvalido() {
        validator.accept(null);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionDescricaoInvalido() {
        validator.accept(CadastroPautaRequest.builder().build());
    }
}
