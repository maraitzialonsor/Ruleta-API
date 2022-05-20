package com.ibm.academia.ruleta.apirest.controllers;

import com.ibm.academia.ruleta.apirest.exceptions.handler.BadRequestException;
import com.ibm.academia.ruleta.apirest.models.entities.Apuesta;
import com.ibm.academia.ruleta.apirest.services.ApuestaDAO;
import com.ibm.academia.ruleta.apirest.services.RuletaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restapi")
@Api(value = "Metodos relacionados con ruletas", tags = "Metodos sobre ruleta")
public class ApuestaController {
    Logger logger = LoggerFactory.getLogger(RuletaController.class);

    @Autowired
    private ApuestaDAO apuestaDao;

    @Autowired
    private RuletaDAO ruletaDao;

    @PostMapping("/apuesta/crear")
    public ResponseEntity<?>crearApuesta(@Valid @RequestBody Apuesta apuesta, BindingResult result){
        if (apuesta.getId()==null || apuesta.getRuleta()==null)
            throw new BadRequestException(String.format("No puede ser nulo el id o la ruleta"));

        ResponseEntity<?> apustaGuardada = apuestaDao.apostar(apuesta, apuesta.getTipoApuesta(), apuesta.getColor(), apuesta.getNumero(), apuesta.getMonto(), apuesta.getRuleta());
        return new ResponseEntity<>(apustaGuardada, HttpStatus.OK);
    }

    /**
     *
     * @return Lista de todas las ruletas existentes
     */
    @ApiOperation(value = "Consultar todas las apuestas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    @GetMapping("/apuesta/listaDeApuestas")
    public List<Apuesta> listarTodas()
    {
        List<Apuesta> apuestas = (List<Apuesta>) apuestaDao.buscarTodos();
        return apuestas;
    }
}
