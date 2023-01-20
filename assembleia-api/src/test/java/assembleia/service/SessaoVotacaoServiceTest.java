package assembleia.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import assembleia.entity.PautaEntity;
import assembleia.exception.DomainException;
import assembleia.repository.PautaRepository;
import assembleia.request.AberturaSessaoRequest;
import assembleia.validator.SessaoVotacaoServiceValidator;

@RunWith(MockitoJUnitRunner.class)
public class SessaoVotacaoServiceTest {

    @InjectMocks
    public SessaoVotacaoService service;
    @Mock
    private PautaRepository repository;
    @Mock
    private SessaoVotacaoServiceValidator validator;

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionPautaNaoEncontrada() {
        final AberturaSessaoRequest request = AberturaSessaoRequest.builder().build();

        when(repository.findById(request.getId())).thenReturn(Optional.empty());

        service.abrirSessao(request);
    }

    @Test()
    public void deveIniciarSessao() {
        final AberturaSessaoRequest request = AberturaSessaoRequest.builder().tempoDuracao(5).build();
        final PautaEntity pautaEntity = PautaEntity.builder().build();
        when(repository.findById(request.getId())).thenReturn(Optional.of(pautaEntity));

        service.abrirSessao(request);

        verify(repository).save(pautaEntity);

        Assert.assertNotNull(pautaEntity.getDataHoraAberturaSessao());
        Assert.assertNotNull(pautaEntity.getTempoDuracao());
    }

}
