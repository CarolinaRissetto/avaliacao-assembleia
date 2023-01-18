package assembleia.request;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VotoRequest {

    @Schema(title = "Id da pauta a receber voto.", required = true, example = "8")
    private final Long idPauta;

    @Schema(title = "Cpf do associado.", required = true, example = "00011122233")
    private final String cpf;

    @Schema(title = "Voto do associado.", required = true, example = "true/false")
    private final Boolean voto;

    @Override
    public String toString() {
        return "cpf: " + md5Hex(cpf);
    }
}