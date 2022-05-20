package com.ibm.academia.ruleta.apirest.services;

import com.ibm.academia.ruleta.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.ruleta.apirest.models.entities.Apuesta;
import com.ibm.academia.ruleta.apirest.models.entities.Ruleta;
import com.ibm.academia.ruleta.apirest.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RuletaDAOImpl extends GenericoDAOImpl<Ruleta, RuletaRepository> implements RuletaDAO
{
    @Autowired
    public RuletaDAOImpl(RuletaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Ruleta abrirRuleta(Long ruletaId) {
        Optional<Ruleta> oRuleta = repository.findById(ruletaId);
        if (!oRuleta.isPresent())
            throw new NotFoundException(String.format("La ruleta con id %d no existe", ruletaId));

        Ruleta ruletaAbierta = null;
        oRuleta.get().setEstaAbierta(true);
        ruletaAbierta = repository.save(oRuleta.get());
        return ruletaAbierta;
    }

    @Override
    public Ruleta cerrarRuleta(Long ruletaId) {
        Optional<Ruleta> oRuleta = repository.findById(ruletaId);
        if (!oRuleta.isPresent())
            throw new NotFoundException(String.format("La ruleta con id %d no existe", ruletaId));

        Ruleta ruletaCerrada = null;
        oRuleta.get().setEstaAbierta(false);
        ruletaCerrada = repository.save(oRuleta.get());
        return ruletaCerrada;
    }
}
