package assembleia.validator;

import static java.util.Objects.isNull;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import assembleia.exception.DomainException;
import assembleia.request.VotoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class VotoRequestValidator implements Consumer<VotoRequest> {

    private final CpfValidator cpfValidator;

    @Override
    public void accept(final VotoRequest request) {

        if (isNull(request)) {
            throw new DomainException("Request inválida.");
        }

        if (isNull(request.getIdPauta())) {
            throw new DomainException("Pauta inválida.");
        }

        if (!cpfValidator.isValid(request.getCpf())) {
            throw new DomainException("CPF inválido.");
        }

        if (isNull(request.getVoto())) {
            throw new DomainException("Voto inválido.");
        }
    }
}