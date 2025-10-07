package com.example.ms2_proyecto.controller;

import com.example.ms2_proyecto.model.Matricula;
import com.example.ms2_proyecto.service.MatriculaService;
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
@RequestMapping("/ms2/api/matriculas")
@Tag(name = "Matrículas", description = "API para gestión de matrículas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    @Operation(summary = "Obtener todas las matrículas")
    public ResponseEntity<List<Matricula>> obtenerTodas() {
        return ResponseEntity.ok(matriculaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener matrícula por ID")
    public ResponseEntity<Matricula> obtenerPorId(@PathVariable Integer id) {
        return matriculaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estudiante/{idEstudiante}")
    @Operation(summary = "Obtener matrículas por estudiante")
    public ResponseEntity<List<Matricula>> obtenerPorEstudiante(@PathVariable Integer idEstudiante) {
        return ResponseEntity.ok(matriculaService.obtenerPorEstudiante(idEstudiante));
    }

    @GetMapping("/curso/{idCurso}")
    @Operation(summary = "Obtener matrículas por curso")
    public ResponseEntity<List<Matricula>> obtenerPorCurso(@PathVariable Integer idCurso) {
        return ResponseEntity.ok(matriculaService.obtenerPorCurso(idCurso));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Obtener matrículas por estado")
    public ResponseEntity<List<Matricula>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(matriculaService.obtenerPorEstado(estado));
    }

    @GetMapping("/curso/{idCurso}/activas")
    @Operation(summary = "Obtener matrículas activas por curso")
    public ResponseEntity<List<Matricula>> obtenerMatriculasActivasPorCurso(@PathVariable Integer idCurso) {
        return ResponseEntity.ok(matriculaService.obtenerMatriculasActivasPorCurso(idCurso));
    }

    @GetMapping("/curso/{idCurso}/contador")
    @Operation(summary = "Contar matrículas por curso")
    public ResponseEntity<Map<String, Long>> contarMatriculasPorCurso(@PathVariable Integer idCurso) {
        Map<String, Long> response = new HashMap<>();
        response.put("total_matriculas", matriculaService.contarMatriculasPorCurso(idCurso));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/estudiante/{idEstudiante}")
    @Operation(summary = "Crear nueva matrícula para un estudiante")
    public ResponseEntity<Matricula> crear(@PathVariable Integer idEstudiante,
                                           @Valid @RequestBody Matricula matricula) {
        try {
            Matricula nuevaMatricula = matriculaService.crear(idEstudiante, matricula);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMatricula);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar matrícula")
    public ResponseEntity<Matricula> actualizar(@PathVariable Integer id,
                                                @Valid @RequestBody Matricula matricula) {
        try {
            Matricula matriculaActualizada = matriculaService.actualizar(id, matricula);
            return ResponseEntity.ok(matriculaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar matrícula")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        try {
            matriculaService.eliminar(id);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Matrícula eliminada correctamente");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
