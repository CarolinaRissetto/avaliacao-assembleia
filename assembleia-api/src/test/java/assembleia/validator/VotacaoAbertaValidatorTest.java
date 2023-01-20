package assembleia.validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import assembleia.entity.AssociadoEntity;
import assembleia.entity.PautaEntity;
import assembleia.entity.VotoEntity;
import assembleia.exception.DomainException;

@RunWith(MockitoJUnitRunner.class)
public class VotacaoAbertaValidatorTest {

    @InjectMocks
    private VotacaoAbertaValidator validator;

    @Test(expected = DomainException.class)
    public void deveValidarSessaoExpirada() {
        final LocalDateTime dataAnterior = LocalDateTime.of(1993, 01, 01, 01, 01);
        final String cpf = "60002526093";
        final PautaEntity pauta = PautaEntity
            .builder()
            .dataHoraAberturaSessao(dataAnterior)
            .tempoDuracao(0)
            .build();

        validator.accept(pauta, cpf);
    }

    @Test(expected = DomainException.class)
    public void deveValidarAssociadoJaVotou() {
        final String cpf = "60002526093";
        final PautaEntity pauta = PautaEntity
            .builder()
            .dataHoraAberturaSessao(LocalDateTime.now())
            .tempoDuracao(60)
            .votos(List.of(VotoEntity.builder().associado(AssociadoEntity.builder().cpf(cpf).build()).build()))
            .build();

        validator.accept(pauta, cpf);
    }

    @Test()
    public void devePassarNasValidacoes() {
        final String cpf = "60002526093";
        final PautaEntity pauta = PautaEntity
            .builder()
            .dataHoraAberturaSessao(LocalDateTime.now())
            .tempoDuracao(60)
            .votos(new ArrayList<>())
            .build();

        validator.accept(pauta, cpf);
    }
}
