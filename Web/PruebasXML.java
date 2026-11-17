package Web;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class PruebasXML {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory fabrica =
                DocumentBuilderFactory.newInstance();

            fabrica.setValidating(true);

            DocumentBuilder constructor =
                fabrica.newDocumentBuilder();

            constructor.setErrorHandler(new ErrorHandler() {
                public void warning(SAXParseException e)
                        throws SAXException {
                    throw e;
                }

                public void error(SAXParseException e)
                        throws SAXException {
                    throw e;
                }

                public void fatalError(SAXParseException e)
                        throws SAXException {
                    throw e;
                }
            });

            Document documento =
                constructor.parse(new File("Ranking/ranking.xml"));

            System.out.println("XML válido según el DTD.");

            XPath xpath = XPathFactory.newInstance().newXPath();

            String[] consultas = {
                "//jugador/nombre",
                "//jugador/puntuacion",
                "//jugador[puntuacion > 300]",
                "//jugador[nombre = 'Alex']/puntuacion"
            };

            for (int i = 0; i < consultas.length; i++) {
                NodeList resultados = (NodeList) xpath.evaluate(
                    consultas[i], documento, XPathConstants.NODESET);

                System.out.println("\nConsulta " + (i + 1) + ":");

                for (int j = 0; j < resultados.getLength(); j++) {
                    Node nodo = resultados.item(j);

                    if (i == 2) {
                        System.out.println(
                            ((Element) nodo).getElementsByTagName("nombre")
                            .item(0).getTextContent()
                        );
                    } else {
                        System.out.println(nodo.getTextContent());
                    }
                }
            }

            double total = (double) xpath.evaluate(
                "count(//jugador)", documento, XPathConstants.NUMBER);

            System.out.println("\nConsulta 5:");
            System.out.println("Total de jugadores: " + (int) total);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
