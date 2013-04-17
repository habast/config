package edu.hm.hafner.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testet die Klasse {@link StringUtils}.
 *
 * @author Ulli Hafner
 */
public class StringUtilsTest {
    /**
     * Prüft, ob die beiden gültigen String Eingaben korrekt verarbeitet werden.
     */
    @Test
    public void testeGueltigeLeereStrings() {
        assertTrue("Der String sollte leer sein", StringUtils.isEmpty(null));
        assertTrue("Der String sollte leer sein", StringUtils.isEmpty(""));
    }

    /**
     * Prüft, ob ungültigen String Eingaben korrekt verarbeitet werden.
     */
    @Test
    public void testeLeereStringsMitZeichen() {
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty(" "));
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty("\t"));
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty("Hallo"));
    }
    /**
     * testet die methode isBlank auf true Ausgabe.
     * @author Marvin Schütz
     */
    @Test
    public void testeIsBlankTrue() {
        assertTrue("Null muss als String mit Blanks anerkannt werden", StringUtils.isBlank(null));
        assertTrue("Der String Besteht nur aus Blanks", StringUtils.isBlank(" "));
        assertTrue("Der String Besteht nur aus Blanks", StringUtils.isBlank("          "));
    }
    /**
     * tetstet die Methode auf false Ausgabe.
     * @author Marvin Schütz
     */
    @Test
    public void testeIsBlankFalse() {
        assertFalse("String enhält zeichen es sollte false zurückgegeben werden", StringUtils.isBlank("hsdghdfk   sdfg"));
        assertFalse("String enhält zeichen es sollte false zurückgegeben werden", StringUtils.isBlank("   hsdghdfkfg"));
        assertFalse("String enhält zeichen es sollte false zurückgegeben werden", StringUtils.isBlank("&%!?"));
    }
    /**
     * Testet die Methode join auf exception.
     * @author Marvin Schütz
     */
    @Test(expected = IllegalArgumentException.class)
    public void testeJoinIllegalArgumentexception() {
        StringUtils.join();
    }
    /**
     * Teste die Methode join auf richtige Ausgabe.
     * @author Marvin Schütz
     */
    @Test
    public void testeJoinAusgabe(){
        assertEquals("falscher String","hallo",StringUtils.join("hallo"));
        assertEquals("falscher String","hallo,du",StringUtils.join("hallo","du"));
        assertEquals("falscher String","hallo,(null)",StringUtils.join("hallo",null));
        assertEquals("falscher String","hallo,(null),du",StringUtils.join("hallo",null,"du"));
    }
    /**
     * Testet die Methode isValidIsbn auf false Rückgabe.
     * @author Marvin Schütz
     */
    @Test
    public void testeIsValidIsbnFalse(){
        assertFalse("EIngabe null muss false ergeben",StringUtils.isValidIsbn10(null));
        assertFalse("leerer string muss false ergeben",StringUtils.isValidIsbn10(""));
        assertFalse("zu langer string muss false ergeben",StringUtils.isValidIsbn10("1235468476465489456134823"));
        assertFalse("zu viele formatierungszeichen muss false ergeben",StringUtils.isValidIsbn10("3--1258--9825"));
        assertFalse("zu viele formatierungszeichen muss false ergeben",StringUtils.isValidIsbn10("3  1258  9825"));
        assertFalse("Buchstaben sind nicht erlaubt",StringUtils.isValidIsbn10("3-8fg21-453-9"));
        assertFalse("Ungültige Prüfziffer",StringUtils.isValidIsbn10("3-89721-453-5"));
        assertFalse("Sonderzeichen sind nicht erlaubt",StringUtils.isValidIsbn10("3-8!?21-453-9"));
    }
    /**
     * Testet die Methode isValidIsbn auf true Rückgabe-
     * @author Marvin Schütz
     */
    @Test
    public void testeIsValidIsbnTrue(){
        assertTrue("Die ISBN ist gültig",StringUtils.isValidIsbn10("3-89721-453-9"));
    }
}

