package com.ibm.academia.ruleta.apirest.models.dto;

import com.ibm.academia.ruleta.apirest.enums.EstadoApertura;
import com.ibm.academia.ruleta.apirest.enums.TipoApuesta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApuestaDTO implements Serializable {
    private static final long serialVersionUID = -8691241505553376497L;
    private Long apuestaId;
    private String valorApuesta;
    private Long idRuleta;
    private TipoApuesta tipoApuesta;
    private Double monto;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
