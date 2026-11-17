# EL DESPERTAR DEL HEROE

## 1. Descripcion del proyecto

El despertar del héroe es un juego de aventuras por turnos desarrollado en Java.

El jugador controla a un héroe que debe enfrentarse a diferentes enemigos mediante un sistema de combate.

Durante la partida puede atacar, utilizar pociones para recuperar vida y consultar su inventario.

El juego incluye un sistema de puntuaciones que permite guardar los mejores resultados en un ranking.

## 2. Tecnologias utilizadas

- Java.
- Visual Studio Code.
- XML.
- DTD.
- XPath.
- XSLT.
- HTML.
- CSS.
- Git y GitHub.

## 3. Estructura del proyecto

- Combate: funcionalidades relacionadas con los combates.
- Documentacion: documentos y pruebas del proyecto.
- Personajes: elementos relacionados con los personajes.
- Poderes: elementos relacionados con las habilidades.
- Ranking: sistema de puntuaciones y archivos XML y DTD.
- SRC: clase principal del juego.
- Web: transformación XSLT, estilos CSS y página HTML.

## 4. Requisitos para ejecutar el proyecto

- Tener instalado Java JDK.
- Tener instalado Visual Studio Code.
- Disponer de los archivos del proyecto.

## 5. Como ejecutar el proyecto

Desde la carpeta principal del proyecto, compilar los archivos Java y ejecutar la clase principal.

```bash
javac SRC/Principal.java Ranking/Ranking.java
java SRC.Principal
```

## 6. Sistema de ranking

El ranking almacena las puntuaciones de los jugadores en un archivo XML.

Los resultados se ordenan de mayor a menor puntuación y se conservan como máximo diez jugadores.

Los datos se cargan al iniciar el programa y se guardan al finalizar cada partida.

## 7. Transformacion XML a HTML

El proyecto utiliza una hoja de estilos XSLT para transformar el archivo ranking.xml en una página HTML.

Para generar la página se utiliza la clase Transformar.java.

Comandos desde la carpeta principal:

```bash
javac Web/Transformar.java
java Web.Transformar
```

El resultado se genera en Web/ranking.html.

La página utiliza los estilos definidos en Web/estilos.css.

## 8. Pruebas realizadas

Se han realizado pruebas de:

- Funcionamiento del ranking.
- Lectura y escritura de datos XML.
- Validación del XML mediante DTD.
- Ejecución de cinco consultas XPath.
- Detección y corrección de errores XML.
- Transformación XSLT a HTML.
- Actualización de la página HTML al modificar el XML.

## 9. Autoría

Proyecto académico desarrollado como trabajo de los módulos de Programación, Entornos de Desarrollo y Lenguajes de Marcas.