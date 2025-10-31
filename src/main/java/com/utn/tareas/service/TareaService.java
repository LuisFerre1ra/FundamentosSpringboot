package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaService {
    private final TareaRepository repository;

    @Autowired
    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public void agregarTarea(String descripcion, Prioridad prioridad) {
        Tarea tarea = Tarea.builder()
                .descripcion(descripcion)
                .prioridad(prioridad)
                .completada(false)
                .build();
        repository.addTarea(tarea);
    }

    public List<Tarea> listarTodas() {
        return repository.getTareas();
    }

    public List<Tarea> listarPendientes() {
        return repository.getTareas().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarCompletadas() {
        return repository.getTareas().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public boolean completarTarea(Long idTarea) {
        Optional<Tarea> tareaOpt = repository.getTarea(idTarea);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setCompletada(true);
            return true;
        }
        return false;
    }

    public String obtenerEstadisticas() {
        List<Tarea> tareas = repository.getTareas();
        long total = tareas.size();
        long completadas = tareas.stream().filter(Tarea::isCompletada).count();
        long pendientes = total - completadas;

        return String.format("Total: %d | Completadas: %d | Pendientes: %d", total, completadas, pendientes);
    }
}
