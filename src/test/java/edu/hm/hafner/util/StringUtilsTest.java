package edu.hm.hafner.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testet die Klasse {@link StringUtils}.
 *
 * @author Sebastian Hansbauer P1
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

    // isBlank() Tests
    /**
     * Prüft die Methode isBlank() auf null Übergabe.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsBlankNull() {
        assertFalse("Null -> false", StringUtils.isBlank(null));
    }

    /**
     * Prüft die Methode isBlank() auf richtige Erkennung eines Strings mit nur blanks.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsBlankCorrect() {
        assertTrue("Blanks -> true", StringUtils.isBlank("   "));
    }

    /**
     * Prüft die Methode isBlank() auf Erkennung eines Strings mit teilweisen blanks.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsBlankUncorrect() {
        assertFalse("Not only blanks -> false", StringUtils.isBlank(" f "));
    }

    // join() Tests
    /**
     * Prüft die Methode join() auf null Übergabe.
     *
     * @author Sebastian Hansbauer
     */
    @Test(expected = IllegalArgumentException.class)
    public void testeJoinNull() {
        StringUtils.join(null);
    }

    /**
     * Prüft die Methode join() auf richtige Konkatenation, Kommasetzung, null Erkennung und Reihenfolge.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeJoinKonka() {
        assertEquals("Konkatenation nicht korrekt", StringUtils.join("1", "bla", null, " "), "1,bla,null, ");
    }

    // isValidIsbn10() Test
    /**
     * Prüft die Method isValidIsbn10() auf null Übergabe.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn10Null() {
        assertFalse("Null Übergabe sollte false sein", StringUtils.isValidIsbn10(null));
    }

    /**
     * Prüft die Method isValidIsbn10() auf zu kurze Übergabe.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn10Short() {
        assertFalse("Zu kurze Übergabe sollte false sein.", StringUtils.isValidIsbn10("123456789"));
    }

    /**
     * Prüft die Method isValidIsbn10() auf zu lange Übergabe.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn10Long() {
        assertFalse("Zu lange Übergabe sollte false sein.", StringUtils.isValidIsbn10("12345678910"));
    }

    /**
     * Prüft die Method isValidIsbn10() auf gültige Zeichen.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn10ValidChars() {
        assertFalse("Ungültige Zeichen sollten false ergeben.", StringUtils.isValidIsbn10("$§()∆ƒ‚∂∑⁄"));
    }

    /**
     * Prüft die Method isValidIsbn10() auf korrekte Erkennung einer falschen ISBN.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn10Uncorrect() {
        assertFalse("Ungültige ISBN sollte false liefern.", StringUtils.isValidIsbn10("3-86680-192-7"));
    }

    /**
     * Prüft die Method isValidIsbn10() auf korrekte Erkennung einer gültigen ISBN.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn10Correct() {
        assertTrue("Korrekte ISBN sollte true liefern.", StringUtils.isValidIsbn10("87 - 11 - 07559 - 7"));
    }

    // strip() Test
    /**
     * Prüft die Methode strip() auf null im ersten Parameter.
     *
     * @author Sebastian Hansbauer
     */
    @Test(expected = IllegalArgumentException.class)
    public void testeStripNullFirst() {
        StringUtils.strip(null, "abc");
    }

    /**
     * Prüft die Methode strip() auf null im zweiten Parameter.
     *
     * @author Sebastian Hansbauer
     */
    @Test(expected = IllegalArgumentException.class)
    public void testeStripNullSecond() {
        StringUtils.strip("abc", null);
    }

    /**
     * Prüft die Methode strip() ob der ganze String korrekt entfernt wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeStripRemoveAll() {
        assertEquals("Dieser String sollte leer sein.", StringUtils.strip("123abc /", " c/ba123"), "");
    }

    /**
     * Prüft die Methode strip() ob die Methode mit Duplikaten im 1.Parameter zurechtkommt.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeStripDuplicateFirst() {
        assertEquals("Dieser String sollte leer sein.", StringUtils.strip("1223aabbccc /1", " c/ba123"), "");
    }

    /**
     * Prüft die Methode strip() ob die Methode mit Duplikaten im 2.Parameter zurechtkommt.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeStripDuplicateSecond() {
        assertEquals("Dieser String sollte leer sein.", StringUtils.strip("123abc /", " cc/baa1233"), "");
    }

    /**
     * Prüft die Methode strip() ob die Methode mit Zeichen zurecht kommt welche im String nicht vorkommen.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeStripNotContainingChars() {
        assertEquals("Dieser String sollte getrimmt sein", StringUtils.strip("123", "1%5"), "23");
    }

    /**
     * Prüft die Methode strip() ob die Methode auch mit einem leerem ersten String zurecht kommt.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeStripEmptyFirst() {
        assertEquals("Dieser String sollte leer sein", StringUtils.strip("", "abc"), "");
    }

    /**
     * Prüft die Methode strip() ob die Methode auch mit einem leerem zweiten String zurecht kommt.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeStripEmptySecond() {
        assertEquals("Dieser String sollte gleich sein", StringUtils.strip("abc", ""), "abc");
    }

    /**
     * Prüft die Methode strip() ob der String korrekt getrimmt wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeStripCorrect() {
        assertEquals("Dieser String sollte richtig getrimmt sein.", StringUtils.strip("Ga123abc$ /", "a2b c3G /"), "1$");
        assertEquals("Dieser String sollte richtig getrimmt sein.", StringUtils.strip("OhneAenhO", "A"), "OhneenhO");
        assertEquals("Dieser String sollte richtig getrimmt sein.", StringUtils.strip(" A1,.", ", A1 "), ".");
    }

    // isSecure() Test
    /**
     * Prüft die Methode isSecure() auf null als Übergabeparameter.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureNull() {
        assertFalse("Null Übergabe sollte false sein.", StringUtils.isSecure(null));
    }

    /**
     * Prüft die Methode isSecure() auf "" als Übergabeparameter.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureEmpty() {
        assertFalse(" Leerer String sollte false sein.", StringUtils.isSecure(""));
    }


    /**
     * Prüft die Methode isSecure() auf zu kurzes sonst gültiges Passwort.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureShort() {
        assertFalse("Zu kurzes PW sollte false sein.", StringUtils.isSecure("1%Aabcdefghijklmnop"));
    }

    /**
     * Prüft die Methode isSecure() ob ein PW ohne Großbuchstaben als false erkannt wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureNoBig() {
        assertFalse("PW ohne Großbuchstaben sollte false sein.", StringUtils.isSecure("1%aabcdefghijklmnopqrstuvwxyz"));
    }

    /**
     * Prüft die Methode isSecure() ob ein PW ohne Kleinbuchstaben als false erkannt wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureNoSmall() {
        assertFalse("PW ohne Kleinbuchstaben sollte false sein.", StringUtils.isSecure("1%AABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }

    /**
     * Prüft die Methode isSecure() ob ein PW ohne Sonderzeichen als false erkannt wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureNoSpecial() {
        assertFalse("PW ohne Sonderzeichen sollte false sein.", StringUtils.isSecure("1aabcdefghijklmnopqrstuvwxyz"));
    }

    /**
     * Prüft die Methode isSecure() ob ein bestimmeter Branch durch das Sonderzeichen "|" durchlaufen wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureSpecialOtherBranch() {
        assertFalse("PW ohne Sonderzeichen sollte false sein.", StringUtils.isSecure("1|abcdefghijklmnopqrstuvwxyz"));
    }

    /**
     * Prüft die Methode isSecure() ob ein bestimmeter Branch durch das Sonderzeichen "|" durchlaufen wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureBranchTest() {
        assertFalse("PW ohne Sonderzeichen sollte false sein.", StringUtils.isSecure("                                    "));
    }


    /**
     * Prüft die Methode isSecure() ob ein PW ohne Zahl als false erkannt wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureNoNumber() {
        assertFalse("PW ohne Zahl sollte false sein.", StringUtils.isSecure("%Aabcdefghijklmnopqrstuvwxyz"));
    }

    /**
     * Prüft die Methode isSecure() ob ein PW ohne 10 verschiedenen Zeichen als false erkannt wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureNoDiff() {
        assertFalse("PW ohne 10 verschiedenen Zahlen sollte false sein.",
                StringUtils.isSecure("1%Aabbbbbbbbbbbbbbbbbbbbbbb"));
    }

    /**
     * Prüft die Methode isSecure() ob ein gutes PW richtig erkannt wird.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsSecureCorrect() {
        assertTrue("PW sollte gültig sein.", StringUtils.isSecure("1%Aabcdefghijklmnopq"));
        assertTrue("PW sollte gültig sein.", StringUtils.isSecure("ichTesteinGANZtollesPW mit Zahlen 1234&"));
        assertTrue("PW sollte gültig sein.", StringUtils.isSecure("BlaaahhB∂ƒª∑⁄€ƒº234234%ETWER"));
    }

    // isValidIsbn13() Test
    /**
     * Prüft die Method isValidIsbn13() auf null Übergabe.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn13Null() {
        assertFalse("Null Übergabe sollte false sein", StringUtils.isValidIsbn13(null));
    }

    /**
     * Prüft die Method isValidIsbn13() auf zu kurze Übergabe.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn13Short() {
        assertFalse("Zu kurze Übergabe sollte false sein.", StringUtils.isValidIsbn13("123456789"));
    }

    /**
     * Prüft die Method isValidIsbn13() auf zu lange Übergabe.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn13Long() {
        assertFalse("Zu lange Übergabe sollte false sein.", StringUtils.isValidIsbn13("123456789101234567"));
    }

    /**
     * Prüft die Method isValidIsbn13() auf gültige Zeichen.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn13ValidChars() {
        assertFalse("Ungültige Zeichen sollten false ergeben.", StringUtils.isValidIsbn13("97©381184086 5"));
        assertFalse("Ungültige Zeichen sollten false ergeben.", StringUtils.isValidIsbn13("973811210865 &"));
        assertFalse("Ungültige Zeichen sollten false ergeben.", StringUtils.isValidIsbn13("/97338133886 5"));
    }

    /**
     * Prüft die Method isValidIsbn13() auf korrekte Erkennung einer falschen ISBN.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn13Uncorrect() {
        assertFalse("Ungültige ISBN sollte false liefern.", StringUtils.isValidIsbn13("978-3-7657-2781-0"));
        assertFalse("Ungültige ISBN sollte false liefern.", StringUtils.isValidIsbn13("978-3-442-26628-1"));
        assertFalse("Ungültige ISBN sollte false liefern.", StringUtils.isValidIsbn13("978-3-7657-2781-6"));
    }

    /**
     * Prüft die Method isValidIsbn13() auf korrekte Erkennung einer gültigen ISBN.
     *
     * @author Sebastian Hansbauer
     */
    @Test
    public void testeIsValdiIsbn13Correct() {
        assertTrue("Korrekte ISBN sollte true liefern.", StringUtils.isValidIsbn13(" 978381582086 5"));
        assertTrue("Korrekte ISBN sollte true liefern.", StringUtils.isValidIsbn13(" 978-3-442-26628-9 "));
        assertTrue("Korrekte ISBN sollte true liefern.", StringUtils.isValidIsbn13(" 978-3-7657-2781-8"));
    }
}
