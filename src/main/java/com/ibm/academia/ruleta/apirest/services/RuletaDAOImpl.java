package com.ibm.academia.ruleta.apirest.services;

import com.ibm.academia.ruleta.apirest.models.entities.Ruleta;
import com.ibm.academia.ruleta.apirest.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuletaDAOImpl extends GenericoDAOImpl<Ruleta, RuletaRepository> implements RuletaDAO
{
    @Autowired
    public RuletaDAOImpl(RuletaRepository repository) {
        super(repository);
    }
}
