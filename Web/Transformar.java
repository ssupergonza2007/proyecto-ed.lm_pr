package Web;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Transformar {

    public static void main(String[] args) {

        try {
            TransformerFactory fabrica = TransformerFactory.newInstance();

            Transformer transformador = fabrica.newTransformer(
                new StreamSource(new File("Web/ranking.xsl"))
            );

            transformador.transform(
                new StreamSource(new File("Ranking/ranking.xml")),
                new StreamResult(new File("Web/ranking.html"))
            );

            System.out.println("HTML generado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al transformar: " + e.getMessage());
        }
    }
}
