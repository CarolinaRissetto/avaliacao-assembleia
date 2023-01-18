package assembleia.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import assembleia.entity.PautaEntity;
import assembleia.exception.DomainException;
import assembleia.repository.PautaRepository;
import assembleia.request.AberturaSessaoRequest;
import assembleia.validator.SessaoVotacaoServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessaoVotacaoService {

    private final PautaRepository repository;
    private final SessaoVotacaoServiceValidator validator;

    public void abrirSessao(final AberturaSessaoRequest request) {

        final PautaEntity pautaEntity = repository.findById(request.getId())
            .orElseThrow(() -> {
                log.warn("Pauta não encontrado.");

                return new DomainException("Pauta não encontrada.");
            });

        validator.accept(request, pautaEntity);

        log.info("Abrindo sessão de votação na pauta {}", request.getId());

        pautaEntity.setDataHoraAberturaSessao(LocalDateTime.now());
        pautaEntity.setTempoDuracao(request.getTempoDuracao());

        repository.save(pautaEntity);
    }
}