public class Main {

    public static boolean isCNPValid(int length) {
        boolean flag = true;
        if (length != 13) {
            flag = false;
        }
        return flag;
    }

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

    public static void main(String[] args) {
        String CNP = "3020607050098";

        String dateOfBirth = CNP.substring(1,7);
        String prefix = getYearPrefix(CNP.charAt(0));
        dateOfBirth = prefix + dateOfBirth;
        System.out.println(dateOfBirth);

        int year = Integer.parseInt(dateOfBirth.substring(0,4));
        int month = Integer.parseInt(dateOfBirth.substring(5,6));
        int day = Integer.parseInt(dateOfBirth.substring(7,8));

        System.out.println(year + "-" + month + "-" + day);




    }
}
