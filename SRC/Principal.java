package SRC;

import java.util.Scanner;
import Combate.Combate;
import Personajes.Personajes;
import Poderes.Poderes;
import Ranking.Ranking;

public class Principal {

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        // Cargar el ranking guardado al iniciar el programa.
        Ranking.cargar();

        int opcion;

        do {
            System.out.println("\n======================================");
            System.out.println("        EL DESPERTAR DEL HEROE");
            System.out.println("======================================");

            System.out.println("\n-------------- MENU -----------------");
            System.out.println("1. Nueva partida");
            System.out.println("2. Consultar ranking");
            System.out.println("3. Ayuda");
            System.out.println("4. Salir");
            System.out.println("--------------------------------------");

            System.out.print("Selecciona una opcion: ");

            String entrada = teclado.nextLine();

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {

                case 1:
                    nuevaPartida();
                    break;

                case 2:
                    Ranking.mostrar();
                    break;

                case 3:
                    mostrarAyuda();
                    break;

                case 4:
                    System.out.println("\n======================================");
                    System.out.println("       GRACIAS POR JUGAR");
                    System.out.println("======================================");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 4);
    }

    public static void nuevaPartida() {

        System.out.println("\n======================================");
        System.out.println("             NUEVA PARTIDA");
        System.out.println("======================================");

        System.out.print("Introduce tu nombre: ");
        String nombre = teclado.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }

        int vida = Personajes.VIDA_INICIAL_HEROE;
        int puntuacion = 0;

        boolean[] pociones = new boolean[Poderes.NUM_POCIONES];

        for (int i = 0; i < pociones.length; i++) {
            pociones[i] = true;
        }

        int[] enemigos = Personajes.crearEnemigos();
        boolean victoria = true;

        for (int i = 0; i < enemigos.length; i++) {

            Personajes.mostrarEnemigo(i + 1, enemigos[i]);

            int[] resultado = Combate.luchar(
                teclado, vida, enemigos[i], pociones
            );

            vida = resultado[0];
            int estado = resultado[1];

            if (estado == 2) {
                System.out.println("\nHas abandonado la aventura.");
                victoria = false;
                break;
            }

            if (estado == 0) {
                System.out.println("\nHas sido derrotado.");
                victoria = false;
                break;
            }

            puntuacion += 100;

            System.out.println("\nEnemigo derrotado: +100 puntos.");
            System.out.println("Puntuacion actual: " + puntuacion);
        }

        if (victoria) {
            puntuacion += vida;

            System.out.println("\n======================================");
            System.out.println("          ¡VICTORIA FINAL!");
            System.out.println("======================================");
            System.out.println("Vida restante: " + vida);
        }

        System.out.println("\n======================================");
        System.out.println("          RESUMEN DE PARTIDA");
        System.out.println("======================================");
        System.out.println("Jugador: " + nombre);
        System.out.println("Puntuacion final: " + puntuacion);
        System.out.println("======================================");

        // Registrar la puntuacion y guardar el ranking en XML.
        Ranking.registrar(nombre, puntuacion);
        Ranking.guardar();
    }

    public static void mostrarAyuda() {

        System.out.println("\n======================================");
        System.out.println("                AYUDA");
        System.out.println("======================================");

        System.out.println("OBJETIVO:");
        System.out.println("Derrotar a los tres enemigos.");

        System.out.println("\nVIDA:");
        System.out.println("Empiezas con 100 puntos de vida.");

        System.out.println("\nPOCIONES:");
        System.out.println("Dispones de 3 pociones.");
        System.out.println("Cada pocion recupera 25 puntos de vida.");

        System.out.println("\nCOMBATE:");
        System.out.println("Tu ataque causa entre 10 y 20 de dano.");
        System.out.println("El enemigo causa entre 5 y 15 de dano.");

        System.out.println("\nPUNTUACION:");
        System.out.println("Cada enemigo derrotado suma 100 puntos.");
        System.out.println("Al ganar, sumas tambien la vida restante.");

        System.out.println("======================================");
    }
}
