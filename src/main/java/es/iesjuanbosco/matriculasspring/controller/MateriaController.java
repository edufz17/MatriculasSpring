package es.iesjuanbosco.matriculasspring.controller;

import es.iesjuanbosco.matriculasspring.entity.Materia;
import es.iesjuanbosco.matriculasspring.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MateriaController {
    private final MateriaRepository materiaRepository;

    @GetMapping("/materias")
    public ResponseEntity<List<Materia>> findAll() {
        return ResponseEntity.ok(materiaRepository.findAll());
    }

    @PostMapping("/materias")
    public ResponseEntity<Materia> create(Materia materia) {
        materia.setId(null); // Para que se genere un nuevo ID al crear la materia.
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaRepository.save(materia));
    }

    @DeleteMapping("/materias/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id) {
        materiaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
