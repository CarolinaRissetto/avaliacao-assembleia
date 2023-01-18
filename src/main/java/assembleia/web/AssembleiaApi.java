package assembleia.web;

import org.springframework.web.bind.annotation.RequestBody;

import assembleia.request.AberturaSessaoRequest;
import assembleia.request.CadastroPautaRequest;
import assembleia.request.VotoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AssembleiaApi")
public interface AssembleiaApi {

    @Operation(summary = "Operação cadastrar pauta.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
        @ApiResponse(responseCode = "400", description = "<ul>"
            + "<li>Request inválida.</li>"
            + "<li>Descrição inválida.</li>"
            + "</ul>"),
        @ApiResponse(responseCode = "500", description = "Falha inesperada."),
    })
    void cadastrarPauta(
        @Parameter(description = "Envia os dados necessários para cadastrar a pauta.", required = true)
        @RequestBody final CadastroPautaRequest request);

    @Operation(summary = "Operação para abrir sessão de votação.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
        @ApiResponse(responseCode = "400", description = "<ul>"
            + "<li>Request inválida.</li>"
            + "<li>Pauta Inválida.</li>"
            + "<li>Tempo de duração inválido.</li>"
            + "<li>Ja existe uma sessão de votação aberta para esta pauta.</li>"
            + "</ul>"),
        @ApiResponse(responseCode = "500", description = "Falha inesperada."),
    })
    void abrirSessao(
        @Parameter(description = "Envia os dados necessários abrir sessão.", required = true)
        @RequestBody final AberturaSessaoRequest request);

    @Operation(summary = "Operação para registrar o voto do associado.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
        @ApiResponse(responseCode = "400", description = "<ul>"
            + "<li>Request inválida.</li>"
            + "<li>Pauta Inválida.</li>"
            + "<li>CPF inválido.</li>"
            + "<li>Voto inválido.</li>"
            + "<li>A sessão de votação ja foi fechada.</li>"
            + "<li>O associado ja votou nesta pauta.</li>"
            + "</ul>"),
        @ApiResponse(responseCode = "500", description = "Falha inesperada."),
    })
    void votar(
        @Parameter(description = "Envia os dados necessários para votar.", required = true)
        @RequestBody final VotoRequest request);
}