package com.ibm.academia.ruleta.apirest.models.mapper;

import com.ibm.academia.ruleta.apirest.models.dto.RuletaDTO;
import com.ibm.academia.ruleta.apirest.models.entities.Ruleta;

public class RuletaMapper {
    public static RuletaDTO mapRuleta(Ruleta ruleta){
        RuletaDTO ruletaDTO = new RuletaDTO();
        ruletaDTO.setRuletaId(ruleta.getId());
        ruletaDTO.setNombre(ruleta.getNombre());
        ruletaDTO.setFechaCreacion(ruleta.getFechaCreacion());
        ruletaDTO.setFechaModificacion(ruleta.getFechaModificacion());
        return ruletaDTO;
    }
}
