package com.ibm.academia.ruleta.apirest.models.dto;

import com.ibm.academia.ruleta.apirest.enums.EstadoRuleta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RuletaDTO implements Serializable {
    private Long ruletaId;
    private Boolean estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private static final long serialVersionUID = 3250647385802063424L;
}
