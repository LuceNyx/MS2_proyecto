package com.example.ms2_proyecto.service;

import com.example.ms2_proyecto.model.Estudiante;
import com.example.ms2_proyecto.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Obtener todos los estudiantes
    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    // Obtener estudiante por ID
    public Optional<Estudiante> obtenerPorId(Integer id) {
        return estudianteRepository.findById(id);
    }

    // Obtener estudiante por código
    public Optional<Estudiante> obtenerPorCodigo(Integer codigo) {
        return estudianteRepository.findByCodigo(codigo);
    }

    // Buscar por nombre o apellido
    public List<Estudiante> buscarPorNombreOApellido(String termino) {
        return estudianteRepository.buscarPorNombreOApellido(termino);
    }

    // Crear estudiante
    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Actualizar estudiante
    public Estudiante actualizar(Integer id, Estudiante estudianteActualizado) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setCodigo(estudianteActualizado.getCodigo());
                    estudiante.setNombre(estudianteActualizado.getNombre());
                    estudiante.setApellido(estudianteActualizado.getApellido());
                    estudiante.setFechaNac(estudianteActualizado.getFechaNac());
                    estudiante.setEmail(estudianteActualizado.getEmail());
                    return estudianteRepository.save(estudiante);
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    // Eliminar estudiante
    public void eliminar(Integer id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    // Contar total de estudiantes
    public long contarEstudiantes() {
        return estudianteRepository.count();
    }
}