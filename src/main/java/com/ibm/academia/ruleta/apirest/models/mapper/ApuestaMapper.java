package com.ibm.academia.ruleta.apirest.models.mapper;

import com.ibm.academia.ruleta.apirest.models.dto.ApuestaDTO;
import com.ibm.academia.ruleta.apirest.models.entities.Apuesta;

public class ApuestaMapper {
    public static ApuestaDTO mapApuesta(Apuesta apuesta){
        ApuestaDTO apuestaDTO = new ApuestaDTO();
        apuestaDTO.setApuestaId(apuesta.getId());
        apuestaDTO.setValorApuesta(apuesta.getValorApuesta());
        apuestaDTO.setTipoApuesta(apuesta.getTipoApuesta());
        apuestaDTO.setMonto(apuesta.getMonto());
        apuestaDTO.setFechaCreacion(apuesta.getFechaCreacion());
        apuestaDTO.setFechaModificacion(apuesta.getFechaModificacion());
        return apuestaDTO;
    }
}
