package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {
    @Override
    public void mostrarBienvenida() {
        System.out.println("====================================");
        System.out.println("    Sistema iniciado en modo DEV.   ");
        System.out.println("====================================");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("====================================");
        System.out.println(" Ejecución finalizada del modo DEV. ");
        System.out.println("====================================");
    }
}
