package edu.hm.hafner.util;

/**
 * Several useful utility methods that work on {@link String} instances.
 * @author Sebastian Hansbauer
 * @author Marvin Schütz
 */
public final class StringUtils {
    /**
     * Prüft, ob der übergebene String leer ist, d.h. kein Zeichen enthält.
     * @param value der zu prüfende String
     * @return <code>true</code> falls der String kein Zeichen enthält oder <code>null</code> ist, <code>false</code>
     *         andernfalls.
     */
    public static boolean isEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Creates a new instance of {@link StringUtils}.
     * @author Ulli Hafner
     */
    private StringUtils() {
        // prevents instantiation
    }

    /**
     * Testet ob ein String nur aus Blanks besteht
     * @author Marvin Schütz
     * @param toTest
     * @return true falls der String nur aus Blanks besteht sonst false
     */
    public static boolean isBlank(final String toTest) {
        if(toTest == null) {
            return true;
        }
        else {
            for(int i=0;i<toTest.length();i++){
                if(toTest.charAt(i) != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *Verbindet Strings zu einem neuen.
     *@author Marvin Schütz
     *@param elements
     *@return result neuer string
     */
    public static String join(final String... elements) {
        String result ="";
        if(elements.length==0) {
            throw new IllegalArgumentException();
        }
        else{
            for(int i = 0; i<elements.length; i++){
                if(elements[i]!=null) {
                    result+=elements[i];
                }
                else {
                    result+="(null)";
                }
                if(i<elements.length-1){
                    result+=",";
                }
            }
        }
        return result;
    }

    /**
     * Prüft eine 10 stellige ISBN auf Gültigkeit.
     * @author Marvin Schütz
     * @param isbnEingabe
     * @return false falls die Eingabe ungültig ist ansonsten true
     */
    public static boolean isValidIsbn10(final String isbnEingabe){
        int sum = 0;

        //abfrage ob null und ob die länge in Ordnung ist
        if(isbnEingabe == null || isbnEingabe.length()!=13) {
            return false;
        }
        //entfärnt die Formatierungszeichen
        String[] tmp = isbnEingabe.split("[ \\-]");
        String isbn="";
        for(int i=0;i<4;i++){
            isbn+=tmp[i];
        }
        //nochmal testen auf die richtige länge falls zuviele fotmatierungszeichen enthalten waren
        if(isbn.length()!=10) {
            return false;
        }
        for(int i=0 ; i<=8;i++){
           int digit = Character.getNumericValue(isbn.charAt(i));
           //prüft ob dass zeichen eine zahl ist
           if(!(-1<digit&&digit<10)) {
            return false;
           }
        else {
            sum+=(i+1)*digit;
        }
        }
        sum =sum %11;
        if(isbn.charAt(9) == 'x'||isbn.charAt(9) == 'X') {
            if(sum == 10) {
                return true;
            }
        }
        if(sum != Character.getNumericValue(isbn.charAt(9))) {
            return false;
        }
        return true;
    }

     /**
     * Entfernt die Zeichen von toBeRemoved aus dem String eingabe.
     * @author Sebastian Hansbauer
     * @param eingabe Der zu bearbeitende String
     * @param toBeRemoved Die zu entfernende Zeichen
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
     * @author Sebastian Hansbauer
     * @param passwordEingabe Das zu prüfende Passwort
     * @return True Passwort ist sicher, false Passwort ist unsicher.
     */
    public static boolean isSecure(final String passwordEingabe) {
        if (passwordEingabe == null) {
            return false;
        }
        if (passwordEingabe.length() < 20) {
            return false;
        }

        //Alle doppelten Zeichen herausfiltern
        StringBuilder diffChars = new StringBuilder();
        for (int i = 0; i < passwordEingabe.length(); i++) {
            String si = passwordEingabe.substring(i, i + 1);
            if (diffChars.indexOf(si) == -1) {
                diffChars.append(si);
            }
        }
        if (diffChars.length() < 10) {
            return false;
        }

        boolean small = false;
        boolean big = false;
        boolean number = false;
        boolean special = false;

        for (int i = 0; i < passwordEingabe.length(); i++) {
            //Zahl
            if (47 < passwordEingabe.charAt(i) && passwordEingabe.charAt(i) < 58) {
                number = true;
                continue;
            }
            //Kleinbuchstaben
            if (96 < passwordEingabe.charAt(i) && passwordEingabe.charAt(i) < 123) {
                small = true;
                continue;
            }
            //Großbuchstaben
            if (64 < passwordEingabe.charAt(i) && passwordEingabe.charAt(i) < 91) {
                big = true;
                continue;
            }
            //Alle restlichen Sonderzeichen
            special = true;
        }
        //Hier keine volle Couverage da der Fall: 4xfalse ausgeschlossen ist
        return small && big && number && special;
    }

    /**
     * Prüft die Gültigkeit der übergebenen ISBN Nummer.
     * @author Sebastian Hansbauer
     * @param isbnEingabe Die zu prüfende ISBN Nummer
     * @return True wenn gültig, false wenn ungültig.
     */
    public static boolean isValidIsbn13(final String isbnEingabe) {
        if (isbnEingabe == null) {
            return false;
        }
        // Rausfiltern von '-' und ' '
        String toCheck = isbnEingabe.replace("-", "").replace(" ", "");
        if (toCheck.length() == 13) {
            int checksum = 0;
            for (int i = 0; i < toCheck.length() - 1; i++) {
                checksum = checksum + (toCheck.charAt(i) - 48) * ((i % 2 * 2) + 1);
            }
            //Überprüfen
            if (((checksum + (toCheck.charAt(12) - 48)) % 10) == 0) {
                return true;
            }
        }
        //Hier fliegen alle ungültigen raus.
        return false;
    }
}
