package assembleia.validator;

import java.time.LocalDateTime;
import java.util.function.BiConsumer;

import org.springframework.stereotype.Component;

import assembleia.entity.PautaEntity;
import assembleia.exception.DomainException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VotacaoAbertaValidator implements BiConsumer<PautaEntity, String> {

    @Override
    public void accept(final PautaEntity pautaEntity, String cpf) {

        final LocalDateTime horaExpiracaoVotacao = pautaEntity.getDataHoraAberturaSessao()
            .plusMinutes(pautaEntity.getTempoDuracao());

        if (LocalDateTime.now().isAfter(horaExpiracaoVotacao)) {
            throw new DomainException("A sessão de votação ja foi fechada.");
        }

        final Boolean associadoJaVotou = pautaEntity.getVotos().stream()
            .anyMatch(voto -> voto.getAssociado().getCpf().equals(cpf));

        if (associadoJaVotou) {
            throw new DomainException("O associado ja votou nesta pauta.");
        }
    }
}