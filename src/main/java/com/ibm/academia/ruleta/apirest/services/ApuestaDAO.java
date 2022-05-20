package com.ibm.academia.ruleta.apirest.services;

import com.ibm.academia.ruleta.apirest.enums.TipoApuesta;
import com.ibm.academia.ruleta.apirest.models.entities.Apuesta;
import com.ibm.academia.ruleta.apirest.models.entities.Ruleta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface ApuestaDAO extends GenericoDAO<Apuesta>{
    public ResponseEntity<?> apostar(Apuesta apuesta, TipoApuesta tipoApuesta, String color, Integer numero, Double monto, Ruleta ruleta);
    public Apuesta asociarRuleta(Long idRuleta, Long idApuesta);


    @Query(value = "select * from ruleta.apuestas where ruleta_id = 1;", nativeQuery=true)
    public Apuesta buscarPorIdRuleta(Long idRuleta);
}
