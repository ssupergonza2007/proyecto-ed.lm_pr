<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Ranking - El despertar del héroe</title>
                <link rel="stylesheet" href="estilos.css"/>
            </head>

            <body>
                <h1>El despertar del héroe</h1>
                <h2>Ranking de jugadores</h2>

                <xsl:choose>
                    <xsl:when test="ranking/jugador">
                        <table>
                            <tr>
                                <th>Posición</th>
                                <th>Jugador</th>
                                <th>Puntuación</th>
                            </tr>

                            <xsl:for-each select="ranking/jugador">
                                <xsl:sort select="number(puntuacion)"
                                          data-type="number"
                                          order="descending"/>

                                <tr>
                                    <td><xsl:value-of select="position()"/></td>
                                    <td><xsl:value-of select="nombre"/></td>
                                    <td><xsl:value-of select="puntuacion"/></td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </xsl:when>

                    <xsl:otherwise>
                        <p>Todavía no hay jugadores en el ranking.</p>
                    </xsl:otherwise>
                </xsl:choose>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
