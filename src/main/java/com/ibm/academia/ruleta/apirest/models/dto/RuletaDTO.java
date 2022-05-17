package com.ibm.academia.ruleta.apirest.models.dto;

import com.ibm.academia.ruleta.apirest.enums.EstadoRuleta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class RuletaDTO implements Serializable {
    private static final long serialVersionUID = 3250647385802063424L;
    private Long ruletaId;
    private String nombre;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
