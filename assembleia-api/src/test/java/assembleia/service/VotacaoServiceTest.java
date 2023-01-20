package assembleia.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import assembleia.entity.AssociadoEntity;
import assembleia.entity.PautaEntity;
import assembleia.exception.DomainException;
import assembleia.repository.AssociadoRepository;
import assembleia.repository.PautaRepository;
import assembleia.repository.VotoRepository;
import assembleia.request.VotoRequest;
import assembleia.validator.VotacaoAbertaValidator;
import assembleia.validator.VotoRequestValidator;

@RunWith(MockitoJUnitRunner.class)
public class VotacaoServiceTest {

    @InjectMocks
    private VotacaoService votacaoService;
    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private VotoRepository votoRepository;
    @Mock
    private AssociadoRepository associadoRepository;
    @Mock
    private VotoRequestValidator votoRequestValidator;
    @Mock
    private VotacaoAbertaValidator votacaoAbertaValidator;


    @Test
    public void deveSalvarAssociadoCasoNaoExista()
    {
        final VotoRequest votoRequest = VotoRequest
            .builder()
            .cpf("72207630005")
            .build();

        when(associadoRepository.findByCpf(votoRequest.getCpf()))
            .thenReturn(Optional.empty());

        when(pautaRepository.findById(votoRequest.getIdPauta()))
            .thenReturn(Optional.of(PautaEntity.builder().build()));

        votacaoService.votar(votoRequest);

        verify(associadoRepository).save(AssociadoEntity.builder().cpf(votoRequest.getCpf()).build());
    }

    @Test(expected = DomainException.class)
    public void deveRetornarExceptionQuandoPautaNaoExistir()
    {
        final VotoRequest votoRequest = VotoRequest
            .builder()
            .cpf("72207630005")
            .build();

        when(associadoRepository.findByCpf(votoRequest.getCpf()))
            .thenReturn(Optional.of(AssociadoEntity.builder().cpf(votoRequest.getCpf()).build()));

        when(pautaRepository.findById(votoRequest.getIdPauta()))
            .thenReturn(Optional.empty());

        votacaoService.votar(votoRequest);

        verify(associadoRepository, times(0)).save(AssociadoEntity.builder().cpf(votoRequest.getCpf()).build());
        verifyNoInteractions(votoRepository);
    }

    @Test
    public void deveSalvarVoto()
    {
        final VotoRequest votoRequest = VotoRequest
            .builder()
            .cpf("72207630005")
            .build();

        when(associadoRepository.findByCpf(votoRequest.getCpf()))
            .thenReturn(Optional.of(AssociadoEntity.builder().cpf(votoRequest.getCpf()).build()));

        when(pautaRepository.findById(votoRequest.getIdPauta()))
            .thenReturn(Optional.of(PautaEntity.builder().build()));

        votacaoService.votar(votoRequest);

        verify(associadoRepository, times(0)).save(AssociadoEntity.builder().cpf(votoRequest.getCpf()).build());
        verify(votoRepository, times(1)).save(any());
    }
}
