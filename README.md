# GameZone Unicesar

Sistema de informacion para la tienda de videojuegos GameZone Unicesar,
desarrollado como parte del Taller 2 de la asignatura Programacion III
(Universidad Popular del Cesar).

> **Nota:** este README es un punto de partida. El Lider Tecnico debe
> completarlo con la descripcion final, instrucciones de compilacion
> y ejecucion antes de la entrega (ver Actividad 13 del rol).

## Requisitos

- Java 17+
- Maven 3.8+

## Como compilar

```bash
mvn clean package
```

## Como ejecutar

```bash
mvn exec:java -Dexec.mainClass="com.gamezone.Main"
```

o bien, tras el empaquetado:

```bash
java -jar target/gamezone-unicesar.jar
```

## Estructura del proyecto

```
com.gamezone
 |-- model         # clases del dominio del negocio
 |-- persistence   # acceso y almacenamiento de datos en archivos
 |-- service       # reglas de negocio
 |-- ui            # menu de consola
 |-- Main.java     # punto de entrada de la aplicacion
```

## Equipo

Ver [TEAM.md](./TEAM.md) para roles, modulos y distribucion de trabajo.
