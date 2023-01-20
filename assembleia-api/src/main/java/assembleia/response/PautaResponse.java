package assembleia.response;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import assembleia.entity.PautaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public final class PautaResponse {

    private final Long id;

    private final String descricao;

    private final LocalDateTime dataHoraAberturaSessao;

    private final Integer tempoDuracao;

    private final int votoSim;

    private final int votoNao;

    public static PautaResponse fromEntity(PautaEntity entity) {
        return PautaResponse
            .builder()
            .id(entity.getId())
            .descricao(entity.getDescricao())
            .dataHoraAberturaSessao(entity.getDataHoraAberturaSessao())
            .tempoDuracao(entity.getTempoDuracao())
            .votoNao(entity.getVotos().stream().filter(voto -> !voto.getVoto()).collect(Collectors.toList()).size())
            .votoSim(entity.getVotos().stream().filter(voto -> voto.getVoto()).collect(Collectors.toList()).size())
            .build();
    }
}
