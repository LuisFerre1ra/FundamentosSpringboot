package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

    @Autowired
    private MensajeService mensajeService;
    @Autowired
    private TareaService tareaService;

    @Override
    public void run(String... args) throws Exception {
        mensajeService.mostrarBienvenida();

        tareaService.imprimirConfiguracion();

        System.out.println("=== TAREAS INICIALES ===");
        tareaService.listarTodas().forEach(System.out::println);

        tareaService.agregarTarea("Tarea añadida", Prioridad.MEDIA);

        System.out.println("=== TAREAS PENDIENTES ===");
        tareaService.listarPendientes().forEach(System.out::println);

        tareaService.completarTarea(1L);

        tareaService.obtenerEstadisticas();

        System.out.println("=== TAREAS COMPLETADAS ===");
        tareaService.listarCompletadas().forEach(System.out::println);

        mensajeService.mostrarDespedida();
    }
}
