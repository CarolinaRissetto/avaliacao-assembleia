package assembleia.service;


import org.springframework.stereotype.Service;

import assembleia.entity.PautaEntity;
import assembleia.repository.PautaRepository;
import assembleia.request.CadastroPautaRequest;
import assembleia.validator.CadastroPautaRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastraPautaService {

    private  final PautaRepository repository;
    private final CadastroPautaRequestValidator validator;

    public void cadastrar(final CadastroPautaRequest request) {

        log.info("Cadastrando pauta {}", request);

        validator.accept(request);

        final PautaEntity entity = PautaEntity.builder()
            .descricao(request.getDescricao())
            .build();

        repository.save(entity);
    }
}