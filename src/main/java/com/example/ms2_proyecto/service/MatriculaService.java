package com.example.ms2_proyecto.service;

import com.example.ms2_proyecto.model.Estudiante;
import com.example.ms2_proyecto.model.Matricula;
import com.example.ms2_proyecto.repository.EstudianteRepository;
import com.example.ms2_proyecto.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Obtener todas las matrículas
    public List<Matricula> obtenerTodas() {
        return matriculaRepository.findAll();
    }

    // Obtener matrícula por ID
    public Optional<Matricula> obtenerPorId(Integer id) {
        return matriculaRepository.findById(id);
    }

    // Obtener matrículas por estudiante
    public List<Matricula> obtenerPorEstudiante(Integer idEstudiante) {
        return matriculaRepository.findByEstudianteIdEstudiante(idEstudiante);
    }

    // Obtener matrículas por curso
    public List<Matricula> obtenerPorCurso(Integer idCurso) {
        return matriculaRepository.findByIdCurso(idCurso);
    }

    // Obtener matrículas por estado
    public List<Matricula> obtenerPorEstado(String estado) {
        return matriculaRepository.findByEstado(estado);
    }

    // Obtener matrículas activas por curso
    public List<Matricula> obtenerMatriculasActivasPorCurso(Integer idCurso) {
        return matriculaRepository.findMatriculasActivasPorCurso(idCurso);
    }

    // Contar matrículas por curso
    public Long contarMatriculasPorCurso(Integer idCurso) {
        return matriculaRepository.contarMatriculasPorCurso(idCurso);
    }

    // Crear matrícula
    public Matricula crear(Integer idEstudiante, Matricula matricula) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + idEstudiante));

        matricula.setEstudiante(estudiante);
        return matriculaRepository.save(matricula);
    }

    // Actualizar matrícula
    public Matricula actualizar(Integer id, Matricula matriculaActualizada) {
        return matriculaRepository.findById(id)
                .map(matricula -> {
                    matricula.setIdCurso(matriculaActualizada.getIdCurso());
                    matricula.setFechaMatricula(matriculaActualizada.getFechaMatricula());
                    matricula.setFechaInicio(matriculaActualizada.getFechaInicio());
                    matricula.setFechaFin(matriculaActualizada.getFechaFin());
                    matricula.setEstado(matriculaActualizada.getEstado());
                    return matriculaRepository.save(matricula);
                })
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));
    }

    // Eliminar matrícula
    public void eliminar(Integer id) {
        if (!matriculaRepository.existsById(id)) {
            throw new RuntimeException("Matrícula no encontrada con ID: " + id);
        }
        matriculaRepository.deleteById(id);
    }
}