package es.iesjuanbosco.matriculasspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 15, nullable = true)
    private String abreviatura;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private Nivel nivel;

    public enum Nivel {
        ESO, BACHILLERATO, CFGS, CFGM,
    }

    @JsonIgnore
    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
    private List<Matricula> matriculas;
}

