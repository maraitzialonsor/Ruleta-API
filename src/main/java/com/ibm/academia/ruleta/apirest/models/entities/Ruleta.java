package com.ibm.academia.ruleta.apirest.models.entities;

import com.ibm.academia.ruleta.apirest.enums.Color;
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

    @Column(name = "estado_ruleta")
   private Boolean estaAbierta = false;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "numero")
    private Integer numero;

    @Column(name="fecha_creacion", nullable=false)
    private Date fechaCreacion;

    @Column(name="fecha_modificacion")
    private Date fechaModificacion;

    //@OneToMany(mappedBy = "ruleta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "ruleta", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Apuesta>apuestas;

    public Ruleta(Long id, Boolean estaAbierta) {
        this.id = id;
        this.estaAbierta = estaAbierta;
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
