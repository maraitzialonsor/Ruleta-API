package com.ibm.academia.ruleta.apirest.models.entities;

import com.ibm.academia.ruleta.apirest.enums.EstadoRuleta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ruletas", schema = "ruleta")
public class Ruleta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_ruleta", nullable = false, unique = true)
    private String nombre;

    @Column(name = "estado_ruleta")
    @Enumerated(EnumType.STRING)
    private EstadoRuleta estadoRuleta;

    @Column(name="fecha_creacion", nullable=false)
    private Date fechaCreacion;

    @Column(name="fecha_modificacion")
    private Date fechaModificacion;
    @OneToMany(mappedBy = "ruleta", fetch = FetchType.LAZY)
    private Set<Apuesta>apuestas;

    public Ruleta(Long id, String nombre, EstadoRuleta estadoRuleta) {
        this.id = id;
        this.nombre = nombre;
        this.estadoRuleta = estadoRuleta;
    }

    @Override
    public String toString() {
        return "Ruleta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estadoRuleta=" + estadoRuleta +
                '}';
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

    private static final long serialVersionUID = 7055388732061366647L;
}
