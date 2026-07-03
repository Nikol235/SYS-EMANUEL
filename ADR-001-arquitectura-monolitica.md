ADR 001: Uso de Arquitectura Monolítica en Capas - SYS COLEGIO EMANUEL
Estado: AceptadoFecha: 2026-07-01Autor: Equipo de Desarrollo

Contexto
El sistema SYS COLEGIO EMANUEL centraliza los procesos académicos y administrativos del Colegio Emanuel. Gestiona los siguientes módulos:

Asistencia: Registro y control de asistencia
Notas: Gestión de calificaciones
Mensualidades: Control de pagos
Comunicados: Envío de notificaciones
Avance Académico: Seguimiento del progreso
Matrícula: Proceso de inscripción
Consultas Académicas: Búsqueda de información
Usuarios del sistema: Director, Secretaria, Docente, Auxiliar, Padre de familia, Estudiante.

Enfoques evaluados
1. Microservicios

Ventaja: Alta escalabilidad, autonomía de despliegue
Desventaja: Alta complejidad, curva de aprendizaje elevada, overhead de comunicación
2. Monolítica en Capas

Ventaja: Simplicidad, desarrollo unificado
Desventaja: Escalabilidad limitada, despliegue conjunto
Decisión
Adoptar la Arquitectura Monolítica en Capas.

Estructura del proyecto
src/main/java/com/colegio/emanuel/
├── controller/
├── service/
├── repository/
├── model/
│ ├── entity/
│ └── dto/
├── config/
├── exception/
└── util/

### Capas del sistema

**Presentación (Controller):** Expone endpoints REST, validación de entrada, respuesta al cliente.

**Negocio (Service):** Lógica de negocio: cálculo de promedios, clasificación de asistencia, generación de comprobantes.

**Persistencia (Repository):** Acceso a base de datos única con JPA/Hibernate, integridad referencial.

**Dominio (Model/Entity):** Entidades del sistema: Usuario, Estudiante, Matrícula, Nota, Asistencia, Pago, Comunicado.

---

## Tecnologías

- Java 21 - Lenguaje de programación principal
- Spring Boot 3.x - Framework principal
- Spring Data JPA - Acceso a datos
- Hibernate - ORM para mapeo objeto-relacional
- Spring Security - Autenticación y autorización
- MySQL 8.x - Base de datos relacional
- Flyway - Migraciones de base de datos
- Lombok - Reducción de código boilerplate
- MapStruct - Mapeo entre DTOs y Entities
- JUnit + Mockito - Pruebas unitarias y de integración
- Swagger/OpenAPI - Documentación de API

---

## Consecuencias

### Positivas

- Desarrollo Rápido: Un único proyecto, una única base de datos y un único despliegue
- Transacciones Consistentes: Operaciones ACID en una sola base de datos
- Curva de Aprendizaje Reducida: Solo Spring Boot, sin complejidad distribuida
- Facilidad de Pruebas: Pruebas de integración directas sin mocks externos
- Menor Costo Operativo: Un solo servidor para despliegue
- Mantenimiento Simplificado: Un solo codebase para debuggear

### Negativas

- Escalabilidad Limitada: No se puede escalar un módulo independientemente
- Riesgo de Acoplamiento: Los módulos pueden volverse dependientes si no se mantiene la separación
- Despliegue Conjunto: Cualquier cambio requiere redesplegar todo el sistema
- Monolito Grande: Requiere modularización por paquetes y principios SOLID