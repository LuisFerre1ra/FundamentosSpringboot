package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {
    @Override
    public void mostrarBienvenida() {
        System.out.println("Sistema iniciado correctamente.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("Ejecución finalizada.");
    }
}
