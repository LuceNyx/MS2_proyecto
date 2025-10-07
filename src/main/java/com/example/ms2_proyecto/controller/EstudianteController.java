package com.example.ms2_proyecto.controller;

import com.example.ms2_proyecto.model.Estudiante;
import com.example.ms2_proyecto.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/ms2/api/estudiantes")
@Tag(name = "Estudiantes", description = "API para gestión de estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    @Operation(summary = "Obtener todos los estudiantes")
    public ResponseEntity<List<Estudiante>> obtenerTodos() {
        return ResponseEntity.ok(estudianteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estudiante por ID")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Integer id) {
        return estudianteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    @Operation(summary = "Obtener estudiante por código")
    public ResponseEntity<Estudiante> obtenerPorCodigo(@PathVariable Integer codigo) {
        return estudianteService.obtenerPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar estudiante por nombre o apellido")
    public ResponseEntity<List<Estudiante>> buscar(@RequestParam String termino) {
        return ResponseEntity.ok(estudianteService.buscarPorNombreOApellido(termino));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo estudiante")
    public ResponseEntity<Estudiante> crear(@Valid @RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevoEstudiante = estudianteService.crear(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estudiante")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Integer id,
                                                 @Valid @RequestBody Estudiante estudiante) {
        try {
            Estudiante estudianteActualizado = estudianteService.actualizar(id, estudiante);
            return ResponseEntity.ok(estudianteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estudiante")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        try {
            estudianteService.eliminar(id);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Estudiante eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estadisticas")
    @Operation(summary = "Obtener estadísticas de estudiantes")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("total_estudiantes", estudianteService.contarEstudiantes());
        return ResponseEntity.ok(estadisticas);
    }
}
