

package edu.hm.hafner.util;


/**
 * Several useful utility methods that work on {@link String} instances.
 *
 * @author Ulli Hafner
 * @author Marvin Schütz
 * @author habast
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
     * Testet ob ein String nur aus Blanks besteht
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
}
