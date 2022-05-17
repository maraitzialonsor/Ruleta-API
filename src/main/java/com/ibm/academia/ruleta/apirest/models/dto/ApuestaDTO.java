package com.ibm.academia.ruleta.apirest.models.dto;

import com.ibm.academia.ruleta.apirest.enums.EstadoApertura;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class ApuestaDTO implements Serializable {
    private static final long serialVersionUID = -8691241505553376497L;
    private Long apuestaId;
    private String nombre;
    private Integer numero;
    private String color;
    private Double monto;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
