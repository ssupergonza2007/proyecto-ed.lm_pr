
import java.util.Scanner;

public class Principal {

    // Constantes del juego
    static final int VIDA_INICIAL = 100;
    static final int POCIONES_INICIALES = 3;
    static final int CURACION_POCION = 25;
    static final int ATAQUE_MINIMO = 10;
    static final int ATAQUE_MAXIMO = 20;
    static final int DANIO_ENEMIGO_MIN = 5;
    static final int DANIO_ENEMIGO_MAX = 15;

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==============================");
            System.out.println("   EL DESPERTAR DEL HEROE");
            System.out.println("==============================");
            System.out.println("1. Nueva partida");
            System.out.println("2. Consultar ranking");
            System.out.println("3. Ayuda");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");

            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        nuevaPartida(teclado);
                        break;
                    case 2:
                        System.out.println("El ranking estara disponible pronto.");
                        break;
                    case 3:
                        System.out.println("Derrota a tres enemigos para ganar.");
                        System.out.println("Puedes atacar o usar pociones.");
                        break;
                    case 4:
                        System.out.println("Saliendo del juego...");
                        break;
                    default:
                        System.out.println("Opcion no valida. Elige del 1 al 4.");
                }

            } else {
                System.out.println("Error: debes introducir un numero.");
                teclado.nextLine();
                opcion = 0;
            }

        } while (opcion != 4);

        teclado.close();
    }

    public static void nuevaPartida(Scanner teclado) {

        String nombre;

        do {
            System.out.print("Introduce el nombre de tu heroe: ");
            nombre = teclado.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacio.");
            }
        } while (nombre.isEmpty());

        int vida = VIDA_INICIAL;
        int pociones = POCIONES_INICIALES;
        int enemigosDerrotados = 0;

        System.out.println("\n¡Heroe creado correctamente!");
        System.out.println("Nombre: " + nombre);

        // Enfrentarse a los tres enemigos
        int[] vidasEnemigos = {40, 60, 80};

        for (int i = 0; i < vidasEnemigos.length; i++) {

            if (vida <= 0) {
                break;
            }

            System.out.println("\nTe enfrentas al enemigo " + (i + 1));
            System.out.println("Vida del enemigo: " + vidasEnemigos[i]);

            int[] resultado = combatir(
                    teclado, nombre, vida, pociones, vidasEnemigos[i]);

            vida = resultado[0];
            pociones = resultado[1];

            if (vida > 0 && resultado[2] == 1) {
                enemigosDerrotados++;
                System.out.println("¡Enemigo derrotado!");
            } else {
                break;
            }
        }

        System.out.println("\n===== FIN DE LA PARTIDA =====");
        System.out.println("Heroe: " + nombre);
        System.out.println("Enemigos derrotados: " + enemigosDerrotados);
        System.out.println("Vida restante: " + vida);
        System.out.println("Pociones restantes: " + pociones);

        if (enemigosDerrotados == 3 && vida > 0) {
            System.out.println("¡Has ganado la aventura!");
        } else if (vida <= 0) {
            System.out.println("Has perdido. Te has quedado sin vida.");
        }
    }

    public static int[] combatir(
            Scanner teclado, String nombre, int vida,
            int pociones, int vidaEnemigo) {

        while (vida > 0 && vidaEnemigo > 0) {

            System.out.println("\n------------------------------");
            System.out.println(nombre + ": " + vida + " puntos de vida");
            System.out.println("Enemigo: " + vidaEnemigo + " puntos de vida");
            System.out.println("Pociones: " + pociones);
            System.out.println("1. Atacar");
            System.out.println("2. Usar pocion");
            System.out.println("3. Abandonar partida");
            System.out.print("Elige una accion: ");

            if (!teclado.hasNextInt()) {
                System.out.println("Debes introducir un numero.");
                teclado.nextLine();
                continue;
            }

            int opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion == 1) {

                int danio = (int) (Math.random()
                        * (ATAQUE_MAXIMO - ATAQUE_MINIMO + 1))
                        + ATAQUE_MINIMO;

                vidaEnemigo -= danio;

                if (vidaEnemigo < 0) {
                    vidaEnemigo = 0;
                }

                System.out.println("¡Atacas y causas " + danio + " de daño!");

            } else if (opcion == 2) {

                if (pociones <= 0) {
                    System.out.println("No te quedan pociones.");
                    continue;
                }

                if (vida == VIDA_INICIAL) {
                    System.out.println("Ya tienes la vida completa.");
                    continue;
                }

                vida += CURACION_POCION;

                if (vida > VIDA_INICIAL) {
                    vida = VIDA_INICIAL;
                }

                pociones--;
                System.out.println("Te has curado. Vida actual: " + vida);

            } else if (opcion == 3) {

                System.out.println("Has abandonado la partida.");
                return new int[]{vida, pociones, 0};

            } else {

                System.out.println("Opcion no valida.");
                continue;
            }

            // El enemigo contraataca si sigue vivo
            if (vidaEnemigo > 0) {

                int danioEnemigo = (int) (Math.random()
                        * (DANIO_ENEMIGO_MAX - DANIO_ENEMIGO_MIN + 1))
                        + DANIO_ENEMIGO_MIN;

                vida -= danioEnemigo;

                if (vida < 0) {
                    vida = 0;
                }

                System.out.println("El enemigo te causa "
                        + danioEnemigo + " de daño.");
            }
        }

        return new int[]{
            vida,
            pociones,
            vidaEnemigo <= 0 ? 1 : 0
        };
    }
}
