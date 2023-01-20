package assembleia.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import assembleia.exception.DomainException;
import assembleia.request.VotoRequest;

@RunWith(MockitoJUnitRunner.class)
public class VotoRequestValidatorTest {

    private CpfValidator cpfValidator = new CpfValidator();
    private VotoRequestValidator validator = new VotoRequestValidator(cpfValidator);

    @Test
    public void deveValidarRequest() {
        final VotoRequest request = VotoRequest.builder().voto(true).cpf("60002526093").idPauta(1l).build();

        validator.accept(request);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionIdPautaInvalido() {
        final VotoRequest request = VotoRequest.builder().voto(true).cpf("60274443040").build();

        validator.accept(request);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionVotoInvalido() {
        final VotoRequest request = VotoRequest.builder().cpf("60274443040").idPauta(1l).build();

        validator.accept(request);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionCpfInvalido() {
        final VotoRequest request = VotoRequest.builder().cpf("6027444344040").idPauta(1l).build();

        validator.accept(request);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionRequestInvalido() {
        validator.accept(null);
    }
}
