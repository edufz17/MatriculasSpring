package es.iesjuanbosco.matriculasspring.controller;

import es.iesjuanbosco.matriculasspring.entity.Alumno;
import es.iesjuanbosco.matriculasspring.entity.Curso;
import es.iesjuanbosco.matriculasspring.repository.AlumnoRepository;
import es.iesjuanbosco.matriculasspring.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {

    // Inyección de dependencias.
    // Spring se encarga de crear el objeto y destruirlo cuando sea necesario (ciclo de vida del objeto).
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/cursos") // https://localhost:8080/alumnos
    public List<Curso> findAll() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<?> findByID(@PathVariable Long id) {
        /*if (!cursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursoRepository.findById(id).get());*/

        return cursoRepository.findById(id)
                .map(ResponseEntity::ok)// Lo mismo que .map(curso -> ResponseEntity.ok(curso))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id) {
        if (!cursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Curso curso) {
        if (!cursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Asignar ID al curso entrante, en caso de no haber sido indicado en el cuerpo de la petición
        curso.setId(id);
        return ResponseEntity.ok(cursoRepository.save(curso));
    }

    @PostMapping("/cursos")
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoRepository.save(curso));
    }

    @DeleteMapping("/cursos")
    public void deleteAll() {
        cursoRepository.deleteAll();
    }

    @PatchMapping("/cursos/{id}/abreviatura")
    public ResponseEntity<?> modificarAbreviatura(@PathVariable Long id, @RequestBody String nuevaAbreviatura) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setAbreviatura(nuevaAbreviatura);
                    return ResponseEntity.ok(cursoRepository.save(curso));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
