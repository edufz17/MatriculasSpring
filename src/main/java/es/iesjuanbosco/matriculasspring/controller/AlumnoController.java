package es.iesjuanbosco.matriculasspring.controller;

import es.iesjuanbosco.matriculasspring.entity.Alumno;
import es.iesjuanbosco.matriculasspring.repository.AlumnoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class AlumnoController {

    // Inyección de dependencias.
    @Autowired // Spring se encarga de crear el objeto y destruirlo cuando sea necesario (ciclo de vida del objeto).
    private AlumnoRepository alumnoRepository;

    @GetMapping("/alumnos") // https://localhost:8080/alumnos
    public List<Alumno> findAll() {
        return (List<Alumno>) alumnoRepository.findAll();
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> findByID(@PathVariable Long id) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
        /*
        if(alumnoOptional.isPresent()){
            // Devolvemos el alumno junto al código 200 OK
            return ResponseEntity.ok(alumnoOptional.get());
        }else{
            // Devolvemos un error 404
            return ResponseEntity.notFound().build();
        }*/
        return alumnoOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id) {
        alumnoRepository.deleteById(id);
        // Devolvemos un 204: No content (el estándar para la creación)
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Alumno alumno) {
       /* return alumnoRepository.findById(id) // Devuelve un Optional<Alumno>
                .map(a -> { // Existe el alumno en la BD.
                    a.setId(id);
                    return ResponseEntity.ok(alumnoRepository.save(a));
                }) // No existe el alumno en la BD.
                .orElseGet(() -> ResponseEntity.notFound().build());*/

        if (!alumnoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        alumno.setId(id);
        return ResponseEntity.ok(alumnoRepository.save(alumno));
    }

    @PostMapping("/alumnos")
    public ResponseEntity<Alumno> create(@RequestBody Alumno alumno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoRepository.save(alumno));
    }

    // PATCH http://localhost:8080/alumnos/5/importe-beca
    @PatchMapping("/alumnos/{id}/importe-beca")
    public ResponseEntity<Alumno> modifyImporteBeca(@PathVariable Long id, @RequestBody BigDecimal nuevoImporte) {
//        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
//        if (alumnoOptional.isPresent()) {
//            Alumno alumno = alumnoOptional.get();
//            alumno.setImporteBeca(nuevoImporte);
//            alumnoRepository.save(alumno);
//        } else {
//            return null;
//        }
//        alumnoOptional.ifPresent(alumno -> {
//            alumno.setImporteBeca(nuevoImporte);
//            alumnoRepository.save(alumno);
//        });
//        return alumno;
//    }
        return alumnoRepository.findById(id)
                .map(alumno -> { // Si el alumno existe een la BD ejecuta .map
                    alumno.setImporteBeca(nuevoImporte);
                    return ResponseEntity.ok(alumnoRepository.save(alumno));
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si el alumno no existe en la BD ejecutra .orElseGet
    }
}