package assembleia.validator;

import static java.util.Objects.isNull;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import assembleia.exception.DomainException;
import assembleia.request.CadastroPautaRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CadastroPautaRequestValidator implements Consumer<CadastroPautaRequest> {

    @Override
    public void accept(final CadastroPautaRequest request) {

        if (isNull(request)) {
            throw new DomainException("Request inválida.");
        }

        if (isNull(request.getDescricao())) {
            throw new DomainException("Descrição inválida.");
        }
    }
}
