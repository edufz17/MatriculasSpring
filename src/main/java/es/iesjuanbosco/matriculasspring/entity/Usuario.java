package es.iesjuanbosco.matriculasspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellidos;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol;

    @CreatedDate
    private LocalDateTime fechaNacimiento; // Fecha de creación del usuario
    @LastModifiedDate
    private LocalDateTime fechaUltimaModificacion; // Fecha de la última modificación del usuario

    @JsonIgnore // Ignorar la propiedad "matriculas" al serializar a JSON
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE) // Relación uno a muchos con la entidad Matricula
    private List<Matricula> matriculas;

    @PrePersist
    protected void onCreate() {
        this.fechaNacimiento = LocalDateTime.now();
        this.fechaUltimaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaUltimaModificacion = LocalDateTime.now();
    }
}
