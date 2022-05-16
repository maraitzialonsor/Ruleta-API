package com.ibm.academia.ruleta.apirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "personas", schema = "ruleta")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona implements Serializable {
    private static final long serialVersionUID = -3190509830788699081L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 60)
    private String apellido;

    @Column(name = "dni", nullable = false, unique = true, length = 10)
    private String dni;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name="fecha_modificacion")
    private Date fechaModificacion;

    public Persona(Long id, String nombre, String apellido, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fecha creacion='" + fechaCreacion + '\'' +
                ", fechaModificacion='" + fechaModificacion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id.equals(persona.id) && Objects.equals(apellido, persona.apellido) && dni.equals(persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni);
    }

    @PrePersist
    public void antesPersistir()
    {
        this.fechaCreacion= new Date();

    }
    @PreUpdate
    public void antesActualizar()
    {
        this.fechaModificacion= new Date();
    }

}
