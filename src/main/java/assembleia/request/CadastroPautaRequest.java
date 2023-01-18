package assembleia.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CadastroPautaRequest {

    @Schema(title = "Descrição da pauta", required = true, example = "Devemos tocar Taylor Swift nos elevadores?")
    private String descricao;
}