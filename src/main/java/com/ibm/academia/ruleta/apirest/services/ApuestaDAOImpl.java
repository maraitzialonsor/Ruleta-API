package com.ibm.academia.ruleta.apirest.services;

import com.ibm.academia.ruleta.apirest.models.entities.Apuesta;
import com.ibm.academia.ruleta.apirest.repositories.ApuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApuestaDAOImpl extends GenericoDAOImpl<Apuesta, ApuestaRepository> implements ApuestaDAO
{
    @Autowired
    public ApuestaDAOImpl(ApuestaRepository repository) {
        super(repository);
    }
}
