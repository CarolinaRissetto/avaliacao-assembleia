package assembleia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import assembleia.entity.PautaEntity;

public interface PautaRepository extends CrudRepository<PautaEntity, Long> {

}
