package Poderes;

import java.util.Random;

public class Poderes {

    private static final Random ALEATORIO = new Random();

    public static final int CURACION_POCION = 25;
    public static final int NUM_POCIONES = 3;

    public static int generarAtaqueHeroe() {

        return ALEATORIO.nextInt(11) + 10;
    }

    public static int generarAtaqueEnemigo() {

        return ALEATORIO.nextInt(11) + 5;
    }

    public static int usarPocion(int vidaActual) {

        return Math.min(100, vidaActual + CURACION_POCION);
    }

    public static int aplicarDanio(int vidaActual, int danio) {

        return Math.max(0, vidaActual - danio);
    }
}
