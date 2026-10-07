package es.iesjuanbosco.matriculasspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean pagoSeguro;

    @Column
    private String cursoLectivo;

    // Indica la columna de la tabla Matricula que se usará como clave foránea para la relación con Alumno
    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    // Indica la columna de la tabla Curso que se usará como clave foránea para la relación con Matricula
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

}
