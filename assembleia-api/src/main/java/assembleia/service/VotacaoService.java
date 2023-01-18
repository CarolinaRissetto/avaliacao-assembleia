package assembleia.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import assembleia.entity.AssociadoEntity;
import assembleia.entity.PautaEntity;
import assembleia.entity.VotoEntity;
import assembleia.exception.DomainException;
import assembleia.repository.AssociadoRepository;
import assembleia.repository.PautaRepository;
import assembleia.repository.VotoRepository;
import assembleia.request.VotoRequest;
import assembleia.validator.VotacaoAbertaValidator;
import assembleia.validator.VotoRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotacaoService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    private final AssociadoRepository associadoRepository;
    private final VotoRequestValidator votoRequestValidator;
    private final VotacaoAbertaValidator votacaoAbertaValidator;

    public void votar(final VotoRequest request) {

        votoRequestValidator.accept(request);

        log.info("Registrando voto {}", request);

        final Optional<AssociadoEntity> associadoRegistrado = associadoRepository.findByCpf(request.getCpf());

        AssociadoEntity associadoEntity;

        if (associadoRegistrado.isEmpty()) {
            associadoEntity = AssociadoEntity.builder().cpf(request.getCpf()).build();

            associadoRepository.save(associadoEntity);
        } else {
            associadoEntity = associadoRegistrado.get();
        }

        final PautaEntity pautaEntity = pautaRepository.findById(request.getIdPauta()).orElseThrow(() -> {
            log.warn("Pauta não encontrado.");

            return new DomainException("Pauta não encontrado.");
        });

        votacaoAbertaValidator.accept(pautaEntity, request.getCpf());

        final VotoEntity votoEntity = VotoEntity.builder()
            .voto(request.getVoto())
            .associado(associadoEntity)
            .pauta(pautaEntity)
            .build();

        votoRepository.save(votoEntity);
    }
}