package es.iesjuanbosco.matriculasspring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean pagoSeguro;

    private String cursoLectivo;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Curso curso;

    @ManyToOne
    private Usuario usuario;
}

