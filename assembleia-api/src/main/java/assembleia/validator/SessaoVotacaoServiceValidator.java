package assembleia.validator;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.function.BiConsumer;

import org.springframework.stereotype.Service;

import assembleia.entity.PautaEntity;
import assembleia.exception.DomainException;
import assembleia.request.AberturaSessaoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessaoVotacaoServiceValidator implements BiConsumer<AberturaSessaoRequest, PautaEntity> {

    @Override
    public void accept(final AberturaSessaoRequest request, PautaEntity pautaEntity) {

        if (isNull(request)) {
            throw new DomainException("Request inválida.");
        }

        if (isNull(request.getId())) {
            throw new DomainException("Pauta Inválida.");
        }

        if (isNull(request.getTempoDuracao()) || request.getTempoDuracao() <= 0) {
            throw new DomainException("Tempo de duração inválido.");
        }

        if (nonNull(pautaEntity.getTempoDuracao())) {

            final LocalDateTime horaExpiracaoVotacao = pautaEntity.getDataHoraAberturaSessao()
                .plusMinutes(pautaEntity.getTempoDuracao());

            if (LocalDateTime.now().isBefore(horaExpiracaoVotacao)) {

                throw new DomainException("Ja existe uma sessão de votação aberta para esta pauta.");
            }
        }
    }
}