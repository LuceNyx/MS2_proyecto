package com.example.ms2_proyecto.repository;

import com.example.ms2_proyecto.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByCodigo(Integer codigo);
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
    List<Estudiante> findByApellidoContainingIgnoreCase(String apellido);

    @Query("SELECT e FROM Estudiante e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(e.apellido) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Estudiante> buscarPorNombreOApellido(String termino);
}