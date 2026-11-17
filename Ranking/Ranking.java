package Ranking;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ranking {

    private static final int MAX_JUGADORES = 10;
    private static final String RUTA_XML = "Ranking/ranking.xml";

    private static String[] nombres = new String[MAX_JUGADORES];
    private static int[] puntuaciones = new int[MAX_JUGADORES];
    private static int cantidad = 0;

    // Indica si el XML existente se ha podido leer correctamente.
    private static boolean archivoValido = true;

    public static void registrar(String nombre, int puntuacion) {

        int posicion = 0;

        // Los empates mantienen primero al jugador anterior.
        while (posicion < cantidad
                && puntuaciones[posicion] >= puntuacion) {
            posicion++;
        }

        // Si está lleno y la puntuación no entra en el top 10.
        if (posicion >= MAX_JUGADORES) {
            return;
        }

        int limite = Math.min(cantidad, MAX_JUGADORES - 1);

        for (int i = limite; i > posicion; i--) {
            nombres[i] = nombres[i - 1];
            puntuaciones[i] = puntuaciones[i - 1];
        }

        nombres[posicion] = nombre;
        puntuaciones[posicion] = puntuacion;

        if (cantidad < MAX_JUGADORES) {
            cantidad++;
        }
    }

    public static void mostrar() {

        System.out.println("\n===== RANKING =====");

        if (cantidad == 0) {
            System.out.println("Todavía no hay puntuaciones.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.println(
                (i + 1) + ". " + nombres[i]
                + " - " + puntuaciones[i] + " puntos"
            );
        }
    }

    public static void cargar() {

        File archivo = new File(RUTA_XML);

        if (!archivo.exists()) {
            cantidad = 0;
            archivoValido = true;
            return;
        }

        try {
            DocumentBuilderFactory fabrica =
                DocumentBuilderFactory.newInstance();

            // No necesitamos validar el DTD desde Java.
            fabrica.setFeature(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                false
            );

            DocumentBuilder constructor =
                fabrica.newDocumentBuilder();

            Document documento = constructor.parse(archivo);
            documento.getDocumentElement().normalize();

            NodeList jugadores =
                documento.getElementsByTagName("jugador");

            if (jugadores.getLength() > MAX_JUGADORES) {
                throw new Exception("El XML contiene más de 10 jugadores.");
            }

            String[] nuevosNombres = new String[MAX_JUGADORES];
            int[] nuevasPuntuaciones = new int[MAX_JUGADORES];

            for (int i = 0; i < jugadores.getLength(); i++) {

                Element jugador = (Element) jugadores.item(i);

                String nombre = jugador
                    .getElementsByTagName("nombre")
                    .item(0).getTextContent().trim();

                String textoPuntuacion = jugador
                    .getElementsByTagName("puntuacion")
                    .item(0).getTextContent().trim();

                int puntuacion = Integer.parseInt(textoPuntuacion);

                if (nombre.isEmpty() || puntuacion < 0) {
                    throw new Exception("Hay datos no válidos en el XML.");
                }

                nuevosNombres[i] = nombre;
                nuevasPuntuaciones[i] = puntuacion;
            }

            nombres = nuevosNombres;
            puntuaciones = nuevasPuntuaciones;
            cantidad = jugadores.getLength();
            archivoValido = true;

            System.out.println("Ranking cargado correctamente.");

        } catch (Exception e) {
            archivoValido = false;
            System.out.println(
                "ERROR: No se ha podido leer ranking.xml. "
                + "No se guardarán cambios para evitar sobrescribirlo."
            );
        }
    }

    public static void guardar() {

        if (!archivoValido) {
            System.out.println(
                "No se guarda el ranking porque el XML tiene errores."
            );
            return;
        }

        try {
            DocumentBuilderFactory fabrica =
                DocumentBuilderFactory.newInstance();

            DocumentBuilder constructor =
                fabrica.newDocumentBuilder();

            Document documento = constructor.newDocument();

            Element raiz = documento.createElement("ranking");
            documento.appendChild(raiz);

            for (int i = 0; i < cantidad; i++) {

                Element jugador = documento.createElement("jugador");

                Element nombre = documento.createElement("nombre");
                nombre.appendChild(
                    documento.createTextNode(nombres[i])
                );

                Element puntuacion =
                    documento.createElement("puntuacion");

                puntuacion.appendChild(
                    documento.createTextNode(
                        String.valueOf(puntuaciones[i])
                    )
                );

                jugador.appendChild(nombre);
                jugador.appendChild(puntuacion);
                raiz.appendChild(jugador);
            }

            File archivo = new File(RUTA_XML);
            archivo.getParentFile().mkdirs();

            Transformer transformador =
                TransformerFactory.newInstance().newTransformer();

            transformador.setOutputProperty(
                OutputKeys.INDENT, "yes"
            );
            transformador.setOutputProperty(
                OutputKeys.ENCODING, "UTF-8"
            );
            transformador.setOutputProperty(
                OutputKeys.DOCTYPE_SYSTEM, "ranking.dtd"
            );

            transformador.transform(
                new DOMSource(documento),
                new StreamResult(archivo)
            );

            System.out.println("Ranking guardado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al guardar el ranking: " + e.getMessage());
        }
    }
}