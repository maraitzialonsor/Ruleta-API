package com.ibm.academia.ruleta.apirest.services;

import com.ibm.academia.ruleta.apirest.models.entities.Ruleta;

public interface RuletaDAO extends GenericoDAO<Ruleta>{
    public Ruleta abrirRuleta(Long ruletaId);
    public Ruleta cerrarRuleta(Long ruletaId);
}
