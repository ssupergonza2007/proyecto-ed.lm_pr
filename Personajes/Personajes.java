package Personajes;

public class Personajes {

    public static final int VIDA_INICIAL_HEROE = 100;

    public static final int VIDA_ENEMIGO_1 = 40;
    public static final int VIDA_ENEMIGO_2 = 60;
    public static final int VIDA_ENEMIGO_3 = 80;

    public static int[] crearEnemigos() {

        return new int[] {
            VIDA_ENEMIGO_1,
            VIDA_ENEMIGO_2,
            VIDA_ENEMIGO_3
        };
    }

    public static void mostrarEnemigo(int numero, int vida) {

        System.out.println("\n======================================");
        System.out.println("             ENEMIGO " + numero);
        System.out.println("======================================");

        System.out.println("Vida del enemigo: " + vida);
        System.out.println("======================================");
    }

    public static void mostrarHeroe(int vida) {

        System.out.println("\n------------- HEROE -----------------");
        System.out.println("Vida actual: " + vida + " / 100");
        System.out.println("--------------------------------------");
    }
}
