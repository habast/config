package edu.hm.hafner.util;

/**
 * Several useful utility methods that work on {@link String} instances.
 *
 * @author Sebastian Hansbauer P1
 */
public final class StringUtils {
    /**
     * Prüft, ob der übergebene String leer ist, d.h. kein Zeichen enthält.
     *
     * @param value
     *            der zu prüfende String
     * @return <code>true</code> falls der String kein Zeichen enthält oder <code>null</code> ist, <code>false</code>
     *         andernfalls.
     */
    public static boolean isEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Creates a new instance of {@link StringUtils}.
     *
     * @author Ulli Hafner
     */
    private StringUtils() {
        // prevents instantiation
    }

    /**
     * Prüft ob der übergebene String nur Leerzeichen enthält.
     *
     * @author Sebastian Hansbauer
     * @param input
     *            Zu prüfender String
     * @return True wenn nur Leerzeichen, false wenn nicht.
     */
    public static boolean isBlank(final String input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Konkateniert die übergebenen Strings.
     *
     * @author Sebastian Hansbauer
     * @param elements
     *            Übergebene Strings
     * @return Konkatenierter String
     */
    public static String join(final String... elements) {
        if (elements != null) {
            String toAdd;
            String konkateniert = "";
            for (int i = 0; i < elements.length; i++) {
                // null Elemente erkennen
                if (elements[i] == null) {
                    toAdd = "null";
                }
                else {
                    toAdd = elements[i];
                }
                konkateniert = konkateniert + toAdd;
                if (i < elements.length - 1) {
                    konkateniert = konkateniert + ',';
                }
            }
            // System.out.println(konkateniert);
            return konkateniert;
        }
        else {
            throw new IllegalArgumentException("Es muss mindestens 1 Element übergeben werden.");
        }
    }

    /**
     * Prüft die Gültigkeit der übergebenen ISBN Nummer.
     *
     * @author Sebastian Hansbauer
     * @param isbnEingabe
     *            Übergebene ISBN Nummer.
     * @return True wenn gültig, false wenn ungültig.
     */
    public static boolean isValidIsbn10(final String isbnEingabe) {
        if (isbnEingabe == null) {
            return false;
        }
        // Rausfiltern von '-' und ' '
        String toCheck = isbnEingabe.replace("-", "").replace(" ", "");
        // System.out.println(toCheck);
        if (toCheck.length() == 10) {
            int checksum = 0;
            for (int i = 0; i < toCheck.length() - 1; i++) {
                checksum = checksum + (toCheck.charAt(i) - 48) * (10 - i);
                // System.out.println((toCheck.charAt(i)-48) * (10-i));
            }
            // System.out.println(((checksum + (toCheck.charAt(9)-48))%11));
            if (((checksum + (toCheck.charAt(9) - 48)) % 11) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Entfernt die Zeichen von toBeRemoved aus dem String eingabe.
     *
     * @author Sebastian Hansbauer
     * @param eingabe
     *            Zu bearbeitender String
     * @param toBeRemoved
     *            Zu entfernende Zeichen
     * @return Bearbeiteter String.
     */
    public static String strip(final String eingabe, final String toBeRemoved) {
        if (eingabe == null || toBeRemoved == null) {
            throw new IllegalArgumentException("Null als Übergabeparameter ungültig.");
        }
        String result = eingabe;
        for (int i = 0; i < toBeRemoved.length(); i++) {
            result = result.replace(String.valueOf(toBeRemoved.charAt(i)), "");
        }
        return result;
    }

    /**
     * Prüft das übergebene Passwort auf ausreichende Sicherheit.
     *
     * @author Sebastian Hansbauer
     * @param passwordEingabe
     *            Zu prüfende Passwort
     * @return True Passwort ist sicher, false Passwort ist unsicher.
     */
    public static boolean isSecure(final String passwordEingabe) {
        if (passwordEingabe == null) {
            return false;
        }
        if (passwordEingabe.length() < 20) {
            return false;
        }
        boolean small = false, big = false, number = false, diff = false, special = false;
        StringBuilder diffChars = new StringBuilder();
        for (int i = 0; i < passwordEingabe.length(); i++) {
            String si = passwordEingabe.substring(i, i + 1);
            if (diffChars.indexOf(si) == -1) {
                diffChars.append(si);
            }
        }
        if (diffChars.length() > 10) {
            diff = true;
        }
        for (int i = 0; i < passwordEingabe.length(); i++) {
            if (47 < passwordEingabe.charAt(i) && passwordEingabe.charAt(i) < 58) {
                number = true;
                continue;
            }
            if (96 < passwordEingabe.charAt(i) && passwordEingabe.charAt(i) < 123) {
                small = true;
                continue;
            }
            if (64 < passwordEingabe.charAt(i) && passwordEingabe.charAt(i) < 91) {
                big = true;
                continue;
            }
            special = true;
        }
        return small && big && number && diff && special;
    }

    /**
     * Prüft die Gültigkeit der übergebenen ISBN Nummer.
     *
     * @author Sebastian Hansbauer
     * @param isbnEingabe
     *            Übergebene ISBN Nummer.
     * @return True wenn gültig, false wenn ungültig.
     */
    public static boolean isValidIsbn13(final String isbnEingabe) {
        if (isbnEingabe == null) {
            return false;
        }
        // Rausfiltern von '-' und ' '
        String toCheck = isbnEingabe.replace("-", "").replace(" ", "");
        // System.out.println(toCheck);
        if (toCheck.length() == 13) {
            int checksum = 0;
            for (int i = 0; i < toCheck.length() - 1; i++) {
                checksum = checksum + (toCheck.charAt(i) - 48) * ((i % 2 * 2) + 1);
                // System.out.println((toCheck.charAt(i)-48) * ((i%2 * 2) +1));
            }
            // System.out.println(((checksum + (toCheck.charAt(12)-48))%10));
            if (((checksum + (toCheck.charAt(12) - 48)) % 10) == 0) {
                return true;
            }
        }
        return false;
    }
}
