package com.syscolegio.patrones.creacionales;

/**
 * Patrón Singleton - SYS COLEGIO EMANUEL
 * Gestiona la sesión única del usuario autenticado en el sistema.
 * Solo puede existir una instancia activa de sesión por dispositivo.
 */
public class Singleton {

    // Instancia única (volatile para seguridad en hilos)
    private static volatile Singleton instancia;

    private String usuarioActual;
    private String rol;
    private String tokenJWT;

    // Constructor privado - nadie puede crear instancias desde fuera
    private Singleton() {
        System.out.println("====== GESTOR DE SESIÓN CREADO ======");
    }

    // Método de acceso global (Thread-Safe con Double-Check Locking)
    public static Singleton getInstance() {
        if (instancia == null) {
            synchronized (Singleton.class) {
                if (instancia == null) {
                    instancia = new Singleton();
                }
            }
        }
        return instancia;
    }

    public void iniciarSesion(String usuario, String rol, String token) {
        this.usuarioActual = usuario;
        this.rol = rol;
        this.tokenJWT = token;
        System.out.println("Sesión iniciada: " + usuario + " | Rol: " + rol);
    }

    public void cerrarSesion() {
        System.out.println("Sesión cerrada para: " + this.usuarioActual);
        this.usuarioActual = null;
        this.rol = null;
        this.tokenJWT = null;
    }

    public boolean estaAutenticado() {
        return this.tokenJWT != null;
    }

    public String getUsuarioActual() { return usuarioActual; }
    public String getRol() { return rol; }
    public String getTokenJWT() { return tokenJWT; }
}