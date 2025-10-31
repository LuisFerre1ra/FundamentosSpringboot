package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {
    private final AtomicLong nextId = new AtomicLong();
    private final List<Tarea> tareas = new ArrayList<>();

    public TareaRepository() {
        addTarea(
                Tarea.builder().prioridad(Prioridad.BAJA).descripcion("Tarea prioridad baja").build()
        );
        addTarea(
                Tarea.builder().prioridad(Prioridad.MEDIA).descripcion("Tarea prioridad media").build()
        );
        addTarea(
                Tarea.builder().prioridad(Prioridad.ALTA).descripcion("Tarea prioridad alta").build()
        );
    }

    public void addTarea(Tarea tarea) {
        tarea.setId(nextId.getAndIncrement());
        tareas.add(tarea);
    }

    public List<Tarea> getTareas() {
        return Collections.unmodifiableList(tareas);
    }

    public Optional<Tarea> getTarea(Long idTarea) {
        return tareas.stream()
                .filter(t -> t.getId().equals(idTarea))
                .findFirst();
    }

    public boolean deleteTarea(Long idTarea) {
        return tareas.removeIf(t -> t.getId().equals(idTarea));
    }
}
