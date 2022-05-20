package com.ibm.academia.ruleta.apirest.services;

import com.ibm.academia.ruleta.apirest.enums.TipoApuesta;
import com.ibm.academia.ruleta.apirest.exceptions.handler.BadRequestException;
import com.ibm.academia.ruleta.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.ruleta.apirest.models.entities.Apuesta;
import com.ibm.academia.ruleta.apirest.models.entities.Ruleta;
import com.ibm.academia.ruleta.apirest.repositories.ApuestaRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ApuestaDAOImpl extends GenericoDAOImpl<Apuesta, ApuestaRepository> implements ApuestaDAO
{

    private RuletaDAO ruletaDao;
    private ApuestaDAO apuestaDao;
    @Autowired
    public ApuestaDAOImpl(ApuestaRepository repository) {
        super(repository);
    }


    @Override
    public ResponseEntity<?> apostar(Apuesta apuesta, TipoApuesta tipoApuesta, String color, Integer numero, Double monto, Ruleta ruleta) {
        apuesta.setEsGanadora(false);
        if (!color.isBlank() && numero!=null){
            throw new BadRequestException(String.format("Solo puede elegir numero o color"));
        }

        if (tipoApuesta==TipoApuesta.ROJO_NEGRO){
            return apuestaRojoNegro(apuesta, ruleta);
        }
        if (tipoApuesta==TipoApuesta.PAR_IMPAR){
            return apuestaParImpar(apuesta,ruleta);
        }
        if (tipoApuesta==TipoApuesta.PASA_FALTA){
            return apuestaPasaFalta(apuesta,ruleta);
        }
        if (tipoApuesta==TipoApuesta.DOCENA){
            return apuestaDocena(apuesta,ruleta);
        }
        if (tipoApuesta==TipoApuesta.COLUMNA){
            return apuestaColumna(apuesta,ruleta);
        }
        if (tipoApuesta==TipoApuesta.DOS_DOCENAS){
            return apuestaDosDocenas(apuesta,ruleta);
        }
        if (tipoApuesta==TipoApuesta.DOS_COLUMNAS){
            return apuestaDosColumnas(apuesta,ruleta);
        }
        if (tipoApuesta==TipoApuesta.PLENO){
            return apuestaPleno(apuesta,ruleta);
        }
        throw new NotFoundException("No existe el tipo de apuesta");

    }

    private ResponseEntity<?> apuestaPleno(Apuesta apuesta, Ruleta ruleta) {
        apuesta.getRuleta().getNumero();
        Long ruletaId = ruleta.getId();
        if(apuesta.getNumero()==ruleta.getNumero()){
            apuesta.setPremio(apuesta.getMonto()*35);
            apuesta.setEsGanadora(true);
            return new ResponseEntity<>("Ganaste", HttpStatus.OK);
        }
        else{
            apuesta.setEsGanadora(false);
            return new ResponseEntity<>("Perdiste", HttpStatus.OK);
        }
    }

    private ResponseEntity<?> apuestaDosColumnas(Apuesta apuesta, Ruleta ruleta) {
        Long ruletaId = ruleta.getId();
        if(apuesta.getNumero()>0 && apuesta.getNumero()<=24){
            apuesta.setPremio(apuesta.getMonto()/2);
            apuesta.setEsGanadora(true);
            return new ResponseEntity<>("Ganaste", HttpStatus.OK);
        }
        else{
            apuesta.setEsGanadora(false);
            return new ResponseEntity<>("Perdiste", HttpStatus.OK);
        }
    }

    private ResponseEntity<?> apuestaDosDocenas(Apuesta apuesta, Ruleta ruleta) {
        Long ruletaId = ruleta.getId();
        if(apuesta.getNumero()>0 && apuesta.getNumero()<=24){
            apuesta.setPremio(apuesta.getMonto()/2);
            apuesta.setEsGanadora(true);
            return new ResponseEntity<>("Ganaste", HttpStatus.OK);
        }
        else{
            apuesta.setEsGanadora(false);
            return new ResponseEntity<>("Perdiste", HttpStatus.OK);
        }
    }

    private ResponseEntity<?> apuestaColumna(Apuesta apuesta, Ruleta ruleta) {
        Long ruletaId = ruleta.getId();
        if(apuesta.getNumero()>0 && apuesta.getNumero()<=12){
            apuesta.setPremio(apuesta.getMonto()*2);
            apuesta.setEsGanadora(true);
            return new ResponseEntity<>("Ganaste", HttpStatus.OK);
        }
        else{
            apuesta.setEsGanadora(false);
            return new ResponseEntity<>("Perdiste", HttpStatus.OK);
        }
    }

    private ResponseEntity<?> apuestaDocena(Apuesta apuesta, Ruleta ruleta) {
        Long ruletaId = ruleta.getId();
        if(apuesta.getNumero()>=1 && apuesta.getNumero()<=12){
            apuesta.setPremio(apuesta.getMonto()*2);
            apuesta.setEsGanadora(true);
            return new ResponseEntity<>("Ganaste", HttpStatus.OK);
        }
        else{
            apuesta.setEsGanadora(false);
            return new ResponseEntity<>("Perdiste", HttpStatus.OK);
        }
    }

    private ResponseEntity<?> apuestaPasaFalta(Apuesta apuesta, Ruleta ruleta) {
        Long ruletaId = ruleta.getId();
        if(apuesta.getNumero()>1 && apuesta.getNumero()<18){
            apuesta.setPremio(apuesta.getMonto());
            apuesta.setEsGanadora(true);
            return new ResponseEntity<>("Ganaste", HttpStatus.OK);
        }
        else{
            apuesta.setEsGanadora(false);
            return new ResponseEntity<>("Perdiste", HttpStatus.OK);
        }
    }

    private ResponseEntity<?> apuestaParImpar(Apuesta apuesta, Ruleta ruleta) {
        Long ruletaId = ruleta.getId();
        if(apuesta.getNumero()%2==0){
            apuesta.setPremio(apuesta.getMonto());
            apuesta.setEsGanadora(true);
            return new ResponseEntity<>("Ganaste", HttpStatus.OK);
        }
        else{
            apuesta.setEsGanadora(false);
            return new ResponseEntity<>("Perdiste", HttpStatus.OK);
        }
    }

    private ResponseEntity<?> apuestaRojoNegro(Apuesta apuesta, Ruleta ruleta) {
        Long ruletaId = ruleta.getId();
        if(apuesta.getColor()==ruleta.getColor().name()){
            apuesta.setPremio(apuesta.getMonto());
            apuesta.setEsGanadora(true);
            return new ResponseEntity<>("Ganaste", HttpStatus.OK);
        }
        else{
            apuesta.setEsGanadora(false);
            return new ResponseEntity<>("Perdiste", HttpStatus.OK);
        }
    }

    void guardar(Apuesta apuesta){
        apuestaDao.crear(apuesta);
    }

    @Override
    @Transactional
    public Apuesta asociarRuleta(Long idRuleta, Long idApuesta) {
        Optional<Apuesta> oApuesta = repository.findById(idApuesta);
        if(!oApuesta.isPresent())
            throw new NotFoundException(String.format("La apuesta con ID %d no existe", idApuesta));

        Optional<Ruleta> oRuleta = ruletaDao.buscarPorId(idRuleta);
        if(!oApuesta.isPresent())
            throw new NotFoundException(String.format("La ruleta con ID %d no existe", idRuleta));

        ((Apuesta)oApuesta.get()).setRuleta(oRuleta.get());
        return repository.save(oApuesta.get());
    }

    @Override
    public Apuesta buscarPorIdRuleta(Long idRuleta) {
        return null;
    }

}
