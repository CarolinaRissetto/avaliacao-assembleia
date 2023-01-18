package assembleia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import assembleia.entity.AssociadoEntity;

public interface AssociadoRepository extends CrudRepository<AssociadoEntity, Long> {

    Optional<AssociadoEntity> findByCpf(final String cpf);
}