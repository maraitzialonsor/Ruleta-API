package com.ibm.academia.ruleta.apirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuarios", schema = "ruleta")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Usuario extends Persona{
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Ruleta> ruletas;

    public Usuario(Long id, String nombre, String apellido, String dni, Set<Ruleta> ruletas) {
        super(id, nombre, apellido, dni);
        this.ruletas = ruletas;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("Alumno []");
        return builder.toString();
    }

    private static final long serialVersionUID = 3721247363095303612L;
}
