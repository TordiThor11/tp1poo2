package org.example.sistemacConcursos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Concurso {
    private String nombre;
    private Set<Participante> participantes = new HashSet<Participante>();
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaCierre;

    public Concurso(String nombre, LocalDateTime fechaInicio, LocalDateTime fechaCierre) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;

    }

    public void agregarParticipante(Participante participante) throws Exception {
        if (esFechaValida()) {
            this.participantes.add(participante);
            if (esPrimerDia()) {
                participante.sumarPuntosPorInscripcion();
            }
        } else {
            throw new Exception("La fecha no es valida");
        }

    }

    private boolean esFechaValida() {
        LocalDateTime fechaActual = LocalDateTime.now();
        return (fechaInicio.isBefore(fechaActual) && fechaCierre.isAfter(fechaActual));
    }

    private boolean esPrimerDia() {
        LocalDateTime fechaActual = LocalDateTime.now();
        return (fechaInicio.getDayOfYear() == fechaActual.getDayOfYear());
    }

    public boolean estaInscriptoElParticipante(Participante participante) {
        return participantes.contains(participante);
    }
}
