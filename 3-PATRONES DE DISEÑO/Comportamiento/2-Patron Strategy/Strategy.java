package com.syscolegio.patrones.comportamiento.strategy;

// ====== INTERFAZ ESTRATEGIA ======
interface EstrategiaNotificacion {
    void enviar(String destinatario, String mensaje);
}

// ====== ESTRATEGIAS CONCRETAS ======
class NotificacionPush implements EstrategiaNotificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[PUSH] -> " + destinatario + ": " + mensaje);
    }
}

class NotificacionSMS implements EstrategiaNotificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[SMS] -> " + destinatario + ": " + mensaje);
    }
}

class NotificacionEmail implements EstrategiaNotificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[EMAIL] -> " + destinatario + ": " + mensaje);
    }
}

// ====== CONTEXTO ======
class GestorComunicados {
    private EstrategiaNotificacion estrategia;

    public void setEstrategia(EstrategiaNotificacion estrategia) {
        this.estrategia = estrategia;
    }

    public void enviarComunicado(String destinatario, String mensaje) {
        if (estrategia == null) {
            System.out.println("No hay estrategia definida.");
            return;
        }
        estrategia.enviar(destinatario, mensaje);
    }
}

// ====== MAIN ======
public class Strategy {
    public static void main(String[] args) {
        System.out.println("====== PATRÓN STRATEGY - SYS COLEGIO EMANUEL ======");

        GestorComunicados gestor = new GestorComunicados();

        System.out.println("\n--- Enviando por PUSH ---");
        gestor.setEstrategia(new NotificacionPush());
        gestor.enviarComunicado("Padres 3ro B", "Reunión mañana a las 9am");

        System.out.println("\n--- Enviando por SMS ---");
        gestor.setEstrategia(new NotificacionSMS());
        gestor.enviarComunicado("Sr. Juan Pérez", "Su hijo llegó tarde hoy");

        System.out.println("\n--- Enviando por EMAIL ---");
        gestor.setEstrategia(new NotificacionEmail());
        gestor.enviarComunicado("docente@colegio.pe", "Notas del periodo T1 pendientes");
    }
}