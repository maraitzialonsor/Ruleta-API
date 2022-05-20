package com.ibm.academia.ruleta.apirest.controllers;

import com.ibm.academia.ruleta.apirest.enums.Color;
import com.ibm.academia.ruleta.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.ruleta.apirest.models.entities.Apuesta;
import com.ibm.academia.ruleta.apirest.models.entities.Ruleta;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/restapi")
@Api(value = "Metodos relacionados con ruletas", tags = "Metodos sobre ruleta")
public class RuletaController {
    Logger logger = LoggerFactory.getLogger(RuletaController.class);

    @Autowired
    private RuletaDAO ruletaDAO;

    @Autowired
    private ApuestaDAO apuestaDAO;
    /**
     * Enpoint para crear ruleta
     * @return Id de ruleta creada
     */
    @PostMapping("/ruleta/crearRuleta")
    public ResponseEntity<?> crearRuleta(){
        Random random = new Random();
        int numeroGanador = random.nextInt(36 + 0) + 0;
        int color = random.nextInt(2 + 1) + 1;
        Ruleta ruleta = new Ruleta();
        ruleta.setNumero(numeroGanador);
        if (color==1)
            ruleta.setColor(Color.ROJO);
        else
            ruleta.setColor(Color.NEGRO);


        ruletaDAO.crear(ruleta);
        return new ResponseEntity<>("Se creó la ruleta con id " + ruleta.getId(), HttpStatus.CREATED);
    }

    /**
     * Enpoint para abirir una Ruleta existente
     * @param ruletaId
     * @return Ruleta con estado abierta
     */
    @PutMapping("ruleta/apertura-ruleta/{ruletaId}")
    public ResponseEntity<?> aperturaRuleta(@PathVariable Long ruletaId){
        Optional<Ruleta> oRuleta = ruletaDAO.buscarPorId(ruletaId);
        if(!oRuleta.isPresent())
            throw new NotFoundException(String.format("Operacion denegada. \nLa ruleta con id %d no existe", ruletaId));

        if (oRuleta.get().getEstaAbierta()==true)
            throw new NotFoundException(String.format("Operacion denegada. \nLa ruleta con id %d ya esta abierta", ruletaId));

        Ruleta ruletaAbierta = null;
        try{
            ruletaAbierta = ruletaDAO.abrirRuleta(ruletaId);
        }catch (Exception e){
            logger.info(e.getMessage());
            throw e;
        }
        return new ResponseEntity<>("Operacion exitosa", HttpStatus.OK);
    }

    /**
     * Enpoint para cerrar ruleta existente
     * @param ruletaId
     * @return Ruleta con estado cerrada con resultado de las apuestas
     */
    @PutMapping("ruleta/cerrar-ruleta/{ruletaId}")
    public ResponseEntity<?> cerrarRuleta(@PathVariable Long ruletaId){
        Optional<Ruleta> oRuleta = ruletaDAO.buscarPorId(ruletaId);
        if(!oRuleta.isPresent())
            throw new NotFoundException(String.format("Operacion denegada. \nLa ruleta con id %d no existe", ruletaId));

        if (oRuleta.get().getEstaAbierta()==false)
            throw new NotFoundException(String.format("Operacion denegada. \nLa ruleta con id %d ya esta cerrada", ruletaId));

        Ruleta ruletaCerrada = null;
        try{
            ruletaCerrada = ruletaDAO.cerrarRuleta(ruletaId);
        }catch (Exception e){
            logger.info(e.getMessage());
            throw e;
        }

        List<Apuesta> apuestas = (List<Apuesta>) apuestaDAO.buscarPorIdRuleta(ruletaId);

        return new ResponseEntity<>(apuestas, HttpStatus.OK);
    }

    /**
     *
     * @return Lista de todas las ruletas existentes
     */
    @ApiOperation(value = "Consultar todas las ruletas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    @GetMapping("/ruleta/listaDeRuletas")
    public List<Ruleta> listarTodas()
    {
        List<Ruleta> ruletas = (List<Ruleta>) ruletaDAO.buscarTodos();
        return ruletas;
    }
}
