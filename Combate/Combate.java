package Combate;

import java.util.Scanner;
import Poderes.Poderes;

public class Combate {

    public static int[] luchar(Scanner teclado, int vidaHeroe,
                               int vidaEnemigo, boolean[] pociones) {

        while (vidaHeroe > 0 && vidaEnemigo > 0) {

            System.out.println("\n======================================");
            System.out.println("                COMBATE");
            System.out.println("======================================");

            System.out.println("\n------------- ESTADO ----------------");

            System.out.println("HEROE:");
            System.out.println("Vida actual: " + vidaHeroe + " / 100");

            System.out.println("\nENEMIGO:");
            System.out.println("Vida actual: " + vidaEnemigo);

            System.out.println("\n------------- ACCIONES --------------");
            System.out.println("1. Atacar");
            System.out.println("2. Usar pocion");
            System.out.println("3. Ver inventario");
            System.out.println("4. Abandonar");
            System.out.println("--------------------------------------");

            System.out.print("Selecciona una opcion: ");

            String opcion = teclado.nextLine();

            switch (opcion) {

                case "1":

                    int ataque = Poderes.generarAtaqueHeroe();

                    vidaEnemigo = Poderes.aplicarDanio(
                        vidaEnemigo, ataque
                    );

                    System.out.println("\n---------- ATAQUE HEROE --------------");
                    System.out.println("Dano causado: " + ataque);
                    System.out.println("Vida del enemigo: " + vidaEnemigo);

                    break;

                case "2":

                    if (vidaHeroe == 100) {
                        System.out.println("\nYa tienes la vida al maximo.");
                        continue;
                    }

                    int indice = -1;

                    for (int i = 0; i < pociones.length; i++) {
                        if (pociones[i]) {
                            indice = i;
                            break;
                        }
                    }

                    if (indice == -1) {
                        System.out.println("\nNo te quedan pociones.");
                        continue;
                    }

                    pociones[indice] = false;

                    int vidaAnterior = vidaHeroe;
                    vidaHeroe = Poderes.usarPocion(vidaHeroe);

                    System.out.println("\n---------- POCION -------------------");
                    System.out.println("Vida anterior: " + vidaAnterior);
                    System.out.println("Vida recuperada: " + (vidaHeroe - vidaAnterior));
                    System.out.println("Vida actual: " + vidaHeroe);

                    break;

                case "3":

                    System.out.println("\n======================================");
                    System.out.println("              INVENTARIO");
                    System.out.println("======================================");

                    int disponibles = 0;

                    for (int i = 0; i < pociones.length; i++) {

                        if (pociones[i]) {
                            System.out.println("Pocion " + (i + 1) + ": DISPONIBLE");
                            disponibles++;
                        } else {
                            System.out.println("Pocion " + (i + 1) + ": UTILIZADA");
                        }
                    }

                    System.out.println("--------------------------------------");
                    System.out.println("Pociones restantes: " + disponibles);
                    System.out.println("======================================");

                    continue;

                case "4":

                    System.out.println("\nHas abandonado el combate.");
                    return new int[] {vidaHeroe, 2};

                default:

                    System.out.println("\nOpcion no valida.");
                    continue;
            }

            if (vidaEnemigo > 0) {

                int danio = Poderes.generarAtaqueEnemigo();

                vidaHeroe = Poderes.aplicarDanio(
                    vidaHeroe, danio
                );

                System.out.println("\n---------- ATAQUE ENEMIGO ------------");
                System.out.println("Dano recibido: " + danio);
                System.out.println("Tu vida restante: " + vidaHeroe);
            }

            System.out.println("\n======================================");
        }

        if (vidaHeroe <= 0) {
            return new int[] {vidaHeroe, 0};
        }

        return new int[] {vidaHeroe, 1};
    }
}
