package com.syscolegio.patrones.creacionales;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== PATRÓN SINGLETON - SYS COLEGIO EMANUEL ======");

        // Obtenemos dos referencias al Singleton
        Singleton sesion1 = Singleton.getInstance();
        Singleton sesion2 = Singleton.getInstance();

        // Iniciamos sesión con el primero
        sesion1.iniciarSesion("maria.docente", "DOCENTE", "jwt-token-abc123");

        // Verificamos que ambas variables apuntan a la MISMA instancia
        if (sesion1 == sesion2) {
            System.out.println("¡Ambas variables apuntan a la misma y única instancia!");
            System.out.println("sesion1: " + sesion1);
            System.out.println("sesion2: " + sesion2);
        }

        // El segundo también ve la sesión iniciada
        System.out.println("Usuario desde sesion2: " + sesion2.getUsuarioActual());
        System.out.println("Rol desde sesion2: " + sesion2.getRol());
        System.out.println("Autenticado: " + sesion2.estaAutenticado());

        // Cerramos sesión
        sesion1.cerrarSesion();
        System.out.println("Autenticado tras cerrar: " + sesion2.estaAutenticado());
    }
}