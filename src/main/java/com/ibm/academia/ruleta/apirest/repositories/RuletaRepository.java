package com.ibm.academia.ruleta.apirest.repositories;

import com.ibm.academia.ruleta.apirest.models.entities.Ruleta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RuletaRepository extends CrudRepository<Ruleta, Long> {

}
