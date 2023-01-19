package assembleia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import assembleia.entity.PautaEntity;
import assembleia.repository.PautaRepository;
import assembleia.response.PautaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListarPautasService {
    private final PautaRepository pautaRepository;

    public List<PautaResponse> listar() {
        List<PautaResponse> pautas = new ArrayList<>();
        pautaRepository.findAll().forEach(pauta -> pautas.add(PautaResponse.fromEntity(pauta)));
        return pautas;
    }
}
