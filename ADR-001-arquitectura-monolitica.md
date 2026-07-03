\# ADR 001: Uso de Arquitectura Monolítica en Capas para el Sistema SYS COLEGIO EMANUEL



\## Estado

Aceptado



\## Fecha

2026-07-01



\## Contexto

El sistema SYS COLEGIO EMANUEL es un sistema de gestión educativa que centraliza 

los procesos académicos y administrativos del Colegio Emanuel. El sistema debe 

gestionar módulos de asistencia, notas, mensualidades, comunicados, avance 

académico, matrícula y consultas académicas para seis tipos de usuarios: 

Director, Secretaria, Docente, Auxiliar, Padre de familia y Estudiante.



Se evaluaron dos enfoques arquitectónicos para el desarrollo del sistema:



1\. \*\*Arquitectura de Microservicios:\*\* Alta escalabilidad y autonomía de 

despliegue, pero con mayor complejidad de implementación, mayor curva de 

aprendizaje para el equipo y overhead de comunicación entre servicios.



2\. \*\*Arquitectura Monolítica en Capas (Layered Architecture):\*\* Desarrollo 

unificado con separación clara de responsabilidades en capas (Presentación, 

Negocio, Persistencia), más adecuado para el tamaño y alcance del sistema 

educativo del Colegio Emanuel.



\## Decisión

Hemos decidido adoptar la \*\*Arquitectura Monolítica en Capas\*\* para el desarrollo 

del sistema SYS COLEGIO EMANUEL.



La estructura interna del sistema se dividirá en:



\* \*\*Capa de Presentación (Controller):\*\* Expone los endpoints REST que reciben 

las solicitudes de la aplicación web y móvil. Gestiona la validación de entrada 

y la respuesta al cliente.



\* \*\*Capa de Negocio (Service):\*\* Contiene toda la lógica de negocio del sistema 

educativo (cálculo de promedios, clasificación de asistencia, generación de 

comprobantes de pago, etc.). Es independiente de la tecnología de persistencia.



\* \*\*Capa de Persistencia (Repository):\*\* Gestiona el acceso a la base de datos 

única del sistema mediante el uso de JPA/Hibernate, garantizando la integridad 

referencial entre entidades como Estudiante, Curso, Nota, Pago, etc.



\* \*\*Capa de Dominio (Model/Entity):\*\* Define las entidades del sistema 

(Usuario, Estudiante, Matricula, Nota, Asistencia, Pago, Comunicado, etc.) 

y sus relaciones.



\## Consecuencias



\### Positivas (Beneficios)

\* \*\*Simplicidad de Desarrollo:\*\* Un único proyecto, una única base de datos y 

un único despliegue. Reduce la complejidad operativa para un equipo pequeño.

\* \*\*Transacciones Consistentes:\*\* Al compartir una sola base de datos, las 

operaciones que involucran múltiples módulos (como registrar un pago y 

actualizar el estado financiero del estudiante) se ejecutan en una sola 

transacción ACID.

\* \*\*Curva de Aprendizaje Reducida:\*\* El equipo trabaja con un único framework 

(Spring Boot) y un único modelo de datos, sin necesidad de gestionar 

comunicación entre servicios distribuidos.

\* \*\*Facilidad de Pruebas:\*\* Las pruebas de integración son más directas al 

no requerir mocks de servicios externos.



\### Negativas (Compensaciones / Trade-offs)

\* \*\*Escalabilidad Limitada:\*\* Si un módulo específico (como asistencia QR) 

requiere más recursos, no puede escalarse de forma independiente; debe 

escalar todo el sistema.

\* \*\*Riesgo de Acoplamiento:\*\* Si no se mantiene la separación de capas de 

forma disciplinada, los módulos pueden volverse dependientes entre sí, 

dificultando el mantenimiento futuro.

\* \*\*Despliegue Conjunto:\*\* Cualquier cambio en cualquier módulo requiere 

redesplegar todo el sistema, lo que puede generar interrupciones en todos 

los módulos simultáneamente.

