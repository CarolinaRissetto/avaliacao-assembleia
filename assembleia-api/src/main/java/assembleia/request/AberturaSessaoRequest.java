package assembleia.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AberturaSessaoRequest {

    @Schema(title = "Tempo de duração de uma sessão em minutos.", required = true, example = "8")
    private final Integer tempoDuracao;

    @Schema(title = "Id da pauta que será aberta a sessão de votação.", required = true, example = "8")
    private final Long id;
}