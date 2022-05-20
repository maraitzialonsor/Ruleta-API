package com.ibm.academia.ruleta.apirest.models.entities;

import com.ibm.academia.ruleta.apirest.enums.EstadoApertura;
import com.ibm.academia.ruleta.apirest.enums.TipoApuesta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "apuestas", schema = "ruleta")
public class Apuesta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_apuesta")
    private String valorApuesta;

    @Column(name = "tipo_apuesta")
    @Enumerated(EnumType.STRING)
    private TipoApuesta tipoApuesta;

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

    @Column(name = "esGanadora")
    private Boolean esGanadora = false;

    @Column(name = "premio")
    private Double premio = 0.0;

    @ManyToOne(optional = true, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ruleta_id", foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;

    public Apuesta(String valorApuesta, TipoApuesta tipoApuesta, Double monto, Ruleta ruleta) {
        this.valorApuesta = valorApuesta;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
        this.ruleta = ruleta;
    }

    public Apuesta(Long id, String valorApuesta, TipoApuesta tipoApuesta, Double monto) {
        this.id = id;
        this.valorApuesta = valorApuesta;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
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
