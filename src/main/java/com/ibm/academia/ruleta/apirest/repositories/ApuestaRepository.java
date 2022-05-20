package com.ibm.academia.ruleta.apirest.repositories;

import com.ibm.academia.ruleta.apirest.models.entities.Apuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ApuestaRepository extends CrudRepository<Apuesta, Long> {
    // Seleccionar apuesta por Id
    @Query("select a from Apuesta a where a.ruleta.id=?1")
    Iterable<Apuesta> buscarApuestaPorRuletaId(Integer idRuleta);
}
