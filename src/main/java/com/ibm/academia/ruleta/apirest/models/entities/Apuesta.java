package com.ibm.academia.ruleta.apirest.models.entities;

import com.ibm.academia.ruleta.apirest.enums.EstadoApertura;
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
@Table(name = "apuestas", schema = "ruleta")
public class Apuesta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_ruleta", nullable = false, unique = true)
    private String nombre;

    @Column(name = "estado_Apertura", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoApertura estadoApertura;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name="fecha_creacion", nullable=false)
    private Date fechaCreacion;

    @Column(name="fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(optional = true, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ruleta_id", foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;

    public Apuesta(Long id, String nombre, EstadoApertura estadoApertura, Integer numero, String color, Double monto) {
        this.id = id;
        this.nombre = nombre;
        this.estadoApertura = estadoApertura;
        this.numero = numero;
        this.color = color;
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Apuesta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estadoApertura +
                ", numero=" + numero +
                ", color='" + color + '\'' +
                ", monto=" + monto +
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

    private static final long serialVersionUID = 4278224110700208078L;
}
