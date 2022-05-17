package com.ibm.academia.ruleta.apirest.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class PersonaDTO implements Serializable {
    private static final long serialVersionUID = 2417596787315510135L;
    private Long personaId;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
