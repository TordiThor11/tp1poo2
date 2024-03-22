package org.example.sistemacConcursos;

import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.*;

public class ConcursoTest {

    @Test
    public void inscripcionDentroRango() {
        LocalDateTime fechaInicio = LocalDateTime.now().minusDays(2);
        LocalDateTime fechaCierre = LocalDateTime.now().plusDays(2);

        Concurso concurso1 = new Concurso("primerConcurso",fechaInicio, fechaCierre);
        Participante participante1 = new Participante("Juan", 44125665);
        concurso1.agregarParticipante(participante1);
        assertTrue(concurso1.estaInscriptoElParticipante(participante1));
    }
    @Test
    public void inscripcionEnPrimerDia() {
        /* Creo la fecha de inicio con el mismo dia pero a las 00:00 */
        LocalDateTime fechaInicio = LocalDateTime.of(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                0,
                0
        );
        LocalDateTime fechaCierre = LocalDateTime.now().plusDays(2);

        Concurso concurso1 = new Concurso("primerConcurso", fechaInicio, fechaCierre);
        Participante participante1 = new Participante("Juan", 44125665);
        int puntajeAntesDeAgregar = participante1.getPuntaje();
        concurso1.agregarParticipante(participante1);

        assertEquals(puntajeAntesDeAgregar + 10, participante1.getPuntaje());
    }
    @Test
    public void inscripcionFueraDeRango(){
        LocalDateTime fechaInicio = LocalDateTime.now().minusDays(5);
        LocalDateTime fechaCierre = LocalDateTime.now().minusDays(3);

        Concurso concurso1 = new Concurso("primerConcurso",fechaInicio, fechaCierre);
        Participante participante1 = new Participante("Juan", 44125665);
        concurso1.agregarParticipante(participante1);
        assertFalse(concurso1.estaInscriptoElParticipante(participante1));
    }
}