package com.syscolegio.patrones.comportamiento.observer;

import java.util.ArrayList;
import java.util.List;

// ====== INTERFACES ======
interface Observador {
    void actualizar(String evento, String datos);
}

interface Sujeto {
    void agregarObservador(Observador obs);

    void eliminarObservador(Observador obs);

    void notificarObservadores(String evento, String datos);
}

// ====== SUJETO CONCRETO ======
class GestorAsistencia implements Sujeto {
    private List<Observador> observadores = new ArrayList<>();

    @Override
    public void agregarObservador(Observador obs) {
        observadores.add(obs);
    }

    @Override
    public void eliminarObservador(Observador obs) {
        observadores.remove(obs);
    }

    @Override
    public void notificarObservadores(String evento, String datos) {
        for (Observador obs : observadores) {
            obs.actualizar(evento, datos);
        }
    }

    public void registrarAsistencia(String alumno, String estado) {
        System.out.println("\n[ASISTENCIA] " + alumno + " -> " + estado);
        notificarObservadores("ASISTENCIA_REGISTRADA",
                "Alumno: " + alumno + " | Estado: " + estado);
    }
}

// ====== OBSERVADORES CONCRETOS ======
class NotificadorPadre implements Observador {
    private String nombrePadre;

    public NotificadorPadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    @Override
    public void actualizar(String evento, String datos) {
        System.out.println("[PUSH -> " + nombrePadre + "] " + datos);
    }
}

class RegistroAuditoria implements Observador {
    @Override
    public void actualizar(String evento, String datos) {
        System.out.println("[AUDITORIA] Evento: " + evento + " | " + datos);
    }
}

class PanelDirector implements Observador {
    @Override
    public void actualizar(String evento, String datos) {
        System.out.println("[DIRECTOR DASHBOARD] Actualizado: " + datos);
    }
}

// ====== MAIN ======
public class Observer {
    public static void main(String[] args) {
        System.out.println("====== PATRÓN OBSERVER - SYS COLEGIO EMANUEL ======");

        GestorAsistencia gestor = new GestorAsistencia();

        gestor.agregarObservador(new NotificadorPadre("Sr. Juan Pérez"));
        gestor.agregarObservador(new RegistroAuditoria());
        gestor.agregarObservador(new PanelDirector());

        gestor.registrarAsistencia("Ana García", "TEMPRANO");
        gestor.registrarAsistencia("Luis Torres", "TARDE");
        gestor.registrarAsistencia("María López", "FALTA");
    }
}