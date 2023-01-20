package assembleia.validator;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import assembleia.entity.PautaEntity;
import assembleia.exception.DomainException;
import assembleia.request.AberturaSessaoRequest;

@RunWith(MockitoJUnitRunner.class)
public class SessaoVotacaoServiceValidatorTest {

    private final SessaoVotacaoServiceValidator validator = new SessaoVotacaoServiceValidator();

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionRequestInvalido() {
        validator.accept(null, null);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionIdInvalido() {
        final AberturaSessaoRequest request = AberturaSessaoRequest.builder().build();
        validator.accept(request, null);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionTempoDuracaoInvalido() {
        final AberturaSessaoRequest request = AberturaSessaoRequest
            .builder()
            .id(RandomUtils.nextLong(1, 10))
            .tempoDuracao(null)
            .build();

        validator.accept(request, null);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionTempoDuracaoNegativo() {
        final AberturaSessaoRequest request = AberturaSessaoRequest
            .builder()
            .id(RandomUtils.nextLong(1, 10))
            .tempoDuracao(-3)
            .build();

        validator.accept(request, null);
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionQuandoExistirVotacaoAberta() {
        final AberturaSessaoRequest request = AberturaSessaoRequest
            .builder()
            .id(RandomUtils.nextLong(1, 10))
            .tempoDuracao(10)
            .build();

        final PautaEntity pauta = PautaEntity
            .builder()
            .dataHoraAberturaSessao(LocalDateTime.now().minusMinutes(10))
            .tempoDuracao(15)
            .build();

        validator.accept(request, pauta);
    }
}
