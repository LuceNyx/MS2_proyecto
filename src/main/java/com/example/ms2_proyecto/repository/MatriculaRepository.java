package com.example.ms2_proyecto.repository;

import com.example.ms2_proyecto.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    List<Matricula> findByEstudianteIdEstudiante(Integer idEstudiante);
    List<Matricula> findByIdCurso(Integer idCurso);
    List<Matricula> findByEstado(String estado);

    @Query("SELECT m FROM Matricula m WHERE m.estudiante.idEstudiante = ?1 AND m.estado = ?2")
    List<Matricula> findByEstudianteAndEstado(Integer idEstudiante, String estado);

    @Query("SELECT COUNT(m) FROM Matricula m WHERE m.idCurso = ?1")
    Long contarMatriculasPorCurso(Integer idCurso);

    @Query("SELECT m FROM Matricula m WHERE m.idCurso = ?1 AND m.estado = 'ACTIVO'")
    List<Matricula> findMatriculasActivasPorCurso(Integer idCurso);
}