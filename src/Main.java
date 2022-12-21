import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Materialele de referinta pentru a rezolva restul exercitiilor poate fi gasit aici
 * Link - https://ro.wikipedia.org/wiki/Cod_numeric_personal_(Rom%C3%A2nia)
 */

public class Main {

    /**
     * Metoda verifica da un CNP este valid prin a verifica lungimea acestuia
     * Lungimea unui CNP este intodeauna de 13 caractere astfel, verificam daca lungimea NU este egala cu 13
     * In cazul in care lungimea este diferita de 13, CNP-ul este marcat ca fiind invalid
     * @param length - transmis ca si parametru folosind CNP.length() in main la apelare
     * @return True/False in functie de rezultatul conditiei
     */
    public static boolean isCNPValid(int length) {
        boolean flag = true;
        if (length != 13) {
            flag = false;
        }
        return flag;
    }

    /**
     * Verificam prefixul anului pe baza primei cifre din CNP. Distribuita anilor arata in felul urmator:
     * 1/2 - Persoana de sex masculin/feminin nascuta in intervalul 1900 - 1999
     * 3/4 - Persoana de sex masculin/feminin nascuta in intervalul 1800 - 1899
     * 5/6 - Persoana de sex masculin/feminin nascuta in intervalul 2000 - 2099
     * 7/8 - Persoana de sex masculin/feminin rezidenta (Nu vom lua cazult acesta in calcul)
     * @param firstNumber - primul caracater din CNP transmis din main folosind CNP.charAt(0)
     * @return - Returnam un string de tip "19" in cazul persoanelor cu prima cifra fiind 1 din CNP
     */
    public static String getYearPrefix(char firstNumber) {
        String prefix = "";
        switch (firstNumber){
            case '1':
                prefix = "19";
                break;
            case '2':
                prefix = "19";
                break;
            case '3':
                prefix = "18";
                break;
            case '4':
                prefix = "18";
                break;
            case '5':
                prefix = "20";
                break;
            case '6':
                prefix = "20";
                break;
            default:
                prefix ="INVALID";
                break;
        }
        return prefix;
    }

    /**
     * Metoda returneaza sexul persoanei reprezentat printr-un caracter (M/F) prin verificarea primei cifre din CNP
     * @param firstNumber - primul caracater din CNP transmis din main folosind CNP.charAt(0)
     * @return - Returnam un singur caracter care reprezinta sexul persoanei i.e primul caracter 2 atunci returnam 'F'
     */
    public static char returnSexOfPerson(char firstNumber) {
        char sex;
        if (firstNumber == '1' ||
                firstNumber == '3' ||
                firstNumber == '5' ||
                firstNumber == '7') {
            sex = 'M';
        } else {
            sex = 'F';
        }
        return sex;
    }

    /**
     * Metoda care proceseaza substringul din CNP care contine data si il formateaza intr-un mod prin care putem lucra mai usor cu el
     * Astfel daca avem un CNP de forma: 1961201XXXXXX atunci data o sa fie substringul pentru data o sa fie 961201
     * @param CNP - Transmitem intregul CNP catre metoda
     * @return - Returnam data intr-un format de tipul dd-MM-yyyy in format String
     */
    public static String getBirthDateFromCnpAsString(String CNP) {
        String dateOfBirth = CNP.substring(1,7);
        String prefix = getYearPrefix(CNP.charAt(0));

        dateOfBirth = prefix + dateOfBirth;
        return dateOfBirth.substring(6,8) + "-" + dateOfBirth.substring(4,6) + "-" + dateOfBirth.substring(0,4);
    }

    // TODO: Scrieti o metoda care, pe baza stringului de tip dd-MM-yyyy obtinut, sa returneze varsta persoanei ca un int
    public static int calculateAgeBasedOnBirthDate(String birthDate) {
        String[] formattedBirthDate = birthDate.split("-");
        int dayOfBirth = Integer.parseInt(formattedBirthDate[0]);
        int monthOfBirth = Integer.parseInt(formattedBirthDate[1]);
        int yearOfBirth = Integer.parseInt(formattedBirthDate[2]);

        String pattern = "dd-MM-yyyy";
        String currentDate = new SimpleDateFormat(pattern).format(new Date());

        String[] formattedCurrentDate = currentDate.split("-");
        int currentDay = Integer.parseInt(formattedCurrentDate[0]);
        int currentMonth = Integer.parseInt(formattedCurrentDate[1]);
        int currentYear = Integer.parseInt(formattedCurrentDate[2]);

        int age = currentYear - yearOfBirth;
        if (monthOfBirth > currentMonth) {
            // Echivalent cu age = age - 1
            age -= 1;
        } else if (monthOfBirth == currentMonth) {
            if (dayOfBirth > currentDay) {
                age -= 1;
            }
        }
        return age;
    }

    /** TODO: Scrieti o metoda care sa verifice judetul in care persoana s-a nascut folosind caracterele aflate pe pozitia 7-8
     *  EXEMPLU: In cazul unei persoane cu CNP-ul 295060708XXXX caracterele care va intereseaza sunt '08'
     * Daca verificati linkul de mai sus, o sa vedeti in sectiunea 'Structura' la 'JJ' numarul 08 este rezervat pentru Brasov
     * Retrunati un String cu numele judetului i.e "Brasov"
     */
    public static String getCountyBasedOnCnp(String CNP) {
        String county = "";
        int countyCode = Integer.parseInt(CNP.substring(7,9));
        switch (countyCode) {
            case 1:
                county = "Alba";
                break;
            case 2:
                county = "Arad";
                break;
            case 3:
                county = "Arges";
                break;
            case 4:
                county = "Bacau";
                break;
            case 5:
                county = "Bihor";
                break;
            default:
                county = "Invalid";
                break;
        }
        return county;
    }


    public static void main(String[] args) {
        String CNP = "2961031050098";
        System.out.println(getBirthDateFromCnpAsString(CNP));
        System.out.println(calculateAgeBasedOnBirthDate(getBirthDateFromCnpAsString(CNP)));
        System.out.println(getCountyBasedOnCnp(CNP));
    }
}
