package assembleia.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import assembleia.request.AberturaSessaoRequest;
import assembleia.request.CadastroPautaRequest;
import assembleia.request.VotoRequest;
import assembleia.service.CadastraPautaService;
import assembleia.service.SessaoVotacaoService;
import assembleia.service.VotacaoService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class AssembleiaController implements AssembleiaApi{

    private final CadastraPautaService cadastraPautaService;
    private final SessaoVotacaoService sessaoVotacaoService;
    private final VotacaoService votacaoService;

    @Override
    @PostMapping("/cadastrar-pauta")
    @ResponseStatus(HttpStatus.OK)
    public void cadastrarPauta(final CadastroPautaRequest request) {
        cadastraPautaService.cadastrar(request);
    }

    @Override
    @PostMapping("/abrir-sessao")
    @ResponseStatus(HttpStatus.OK)
    public void abrirSessao(final AberturaSessaoRequest request) {
        sessaoVotacaoService.abrirSessao(request);
    }

    @Override
    @PostMapping("/votar")
    @ResponseStatus(HttpStatus.OK)
    public void votar(final VotoRequest request) {
        votacaoService.votar(request);
    }
}