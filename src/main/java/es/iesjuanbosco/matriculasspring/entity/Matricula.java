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

    @ManyToOne
    @JoinColumn(name = "curso_id") // Indica la columna de la tabla Matricula que se usará como clave foránea para la relación con Curso
    private Alumno alumno;
}
