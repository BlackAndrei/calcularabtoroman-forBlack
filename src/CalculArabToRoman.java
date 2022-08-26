import java.util.InputMismatchException;
import java.util.Scanner;
/* Комментарии от ментора по твоему решению ТЗ:
 Все пять классов с методом main, непонятно какой запускать, выгрузи проект с одной точкой входа */
public class CalculArabToRoman {
    static Scanner scan = new Scanner(System.in);
    public static int n1, n2;
    public static char operation;
    public static int result;
    public static String znak1;
    public static void main(String[] args) throws Exception {
        System.out.println("Введите числовое выражение в формате 2 + 2, или I + II");
        String sc = scan.nextLine().strip();
        String[] stroka = sc.split(" ");//разбиваем введённую строку на массивы stroka[1-2]
        for (int i = 0; i < stroka.length; i++) {
            try {        //закидываем в трайкэтч, чтобы при введении чего-то другого кроме [+-/*] у нас выдало ошибку и код закрылся
                znak1 = stroka[1].trim();
            } catch (ArrayIndexOutOfBoundsException e){
                throw new Exception ("throws Exception //т.к. строка не является математической операцией");
            }
            char znak = znak1.charAt(0); //конвертируем из стринга в чар
            if (znak == ('+')) {
                operation = '+';
            }
            else if (znak == ('-')) {
                operation = '-';
            }
            else if (znak == ('/')) {
                operation = '/';
            }
            else if (znak == ('*')) {
                operation = '*';
            }
            else {throw new Exception("Неверный знак операции");} //в случае если знак не соответсвует выше перечисленному в if'е, то код выдаёт ошибку.
        }
        String Roman1 = stroka[0];
        String Roman2 = stroka[2];
        try {//тут мы заключаем в исключение последующие данные массива, чтобы обойти этот элемент при неудаче)
            String Roman3 = stroka[3];
            String Roman4 = stroka[4];
            if (Roman3 != null || Roman4 != null){throw new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");}
        }catch (ArrayIndexOutOfBoundsException e) {

        }
//тк код мне давал посчитать римские, но всё равно выдавал ошибку, парсы я поместил в исключение, а в кетч закинул римские
        // один трай в другом, т.к. без него при n1 = I + n2 = 1 || I + 1 = 2;
        try{n1 = Integer.parseInt(stroka[0]);
            try{n2 = Integer.parseInt(stroka[2]);
                if (n1< 1 || n2 < 1){throw new Exception("Одно из чисел меньше одного");} // тут мы выбрасиваем ошибку, если одно из чисел меньше чем 1 :)
                if (n1 > 10 || n2 > 10){throw new Exception("Одно из чисел больше десяти.");} // а тут если больше 10 :3
                System.out.println("Arabic");
                result = calcul(n1, n2, operation);
                System.out.println(n1 + " " + operation + " " + n2 + " " + "=" + " " + result); //выводим ответ в случаи удачи решения арабскими
            }catch (NumberFormatException e)
            {throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");}
        } catch (NumberFormatException e) {
            if (n1 < 0 || n2 < 0) {throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");}
            else if (n1 > 10 || n2 > 10) { throw new Exception("тк фыв");

            } else { //переходим сюда, если в парсах вылетает исключение
                n1 = NumRoman(stroka[0]);
                n2 = NumRoman(stroka[2]);
                if (n1 > 10 || n2 > 10){throw new Exception("Одно из чисел больше X.");}//закрываем код если римские больше X
                if (n1 == -1 || n2 == -1){throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");}
                //переходим в метод нум роман, если n2 != NumRoman[2] получаем -1, далее при значении -1 выбрасываем ошибку
                result = calcul(n1, n2, operation);
                System.out.println("Rome");
                String Rom1 = ConvRoman(result);

                System.out.println(Roman1 + " " + operation + " " + Roman2 + " "+ "=" + " " + Rom1);} // ответ римскими
        }

    }
    // метод конвертации в ответ на римских
    private static String ConvRoman(int nArab) {
        //enum не особо разобрал, потому сделал через массив...
        String[] roman = {" ", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII",
                "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI",
                "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        try {final String l = roman[nArab];
            return l; }catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("римские цифры не могут иметь отрицательное значение ");
        } // если вводим V - X то получаем такое исключение и закрываемся
    }
    private static int NumRoman(String roman) throws Exception {
        // тут я ничего умнее не придумал, как просто в ифах прогнать ввод римскими до 100, где после 10 будет выброшено исключение
        try{
            if (roman.equals("I")){return 1;} if (roman.equals("II")){return 2;} if(roman.equals("III")){return 3;}
            if (roman.equals("IV")){return 4;} if (roman.equals("V")){return 5;} if(roman.equals("VI")){return 6;}
            if (roman.equals("VII")){return 7;} if (roman.equals("VIII")){return 8;} if (roman.equals("IX")){return 9;} if (roman.equals("X")){return 10;}
            if(roman.equals("XI")){return 11;} if (roman.equals("XII")){return 12;}if (roman.equals("XIII")){return 13;}if (roman.equals("XIV")){return 14 ;}if (roman.equals("XV")){return 15;}if (roman.equals("XVI")){return 16;}
            if (roman.equals("XVII")){return 17;}if (roman.equals("XVIII")){return 18;}if (roman.equals("XIX")){return 19;}if (roman.equals("XX")){return 20 ;}if (roman.equals("XXI")){return 21 ;}if (roman.equals(" XXII")){return 22;}
            if (roman.equals("XXIII")){return 23;}if (roman.equals("XXIV")){return 24 ;}if (roman.equals("XXV")){return 25;}if (roman.equals("XXVI")){return 26 ;}if (roman.equals("XXVII")){return 27;}if (roman.equals("XXVIII")){return 27 ;}
            if (roman.equals("XXVIII")){return 28 ;}if (roman.equals("XXIX")){return 29;}if (roman.equals("XXX")){return 30;}if (roman.equals("XXXI")){return 31;}if (roman.equals("XXXII")){return 32;}if (roman.equals("XXXIII")){return 33;}
            if (roman.equals("XXXIV")){return 34 ;}if (roman.equals("XXXV")){return 35;}if (roman.equals("XXXVI")){return 36;}if (roman.equals("XXXVII")){return 37 ;}if (roman.equals("XXXVIII")){return 38;}if (roman.equals("XXXIX")){return 39;}
            if (roman.equals("XL")){return 40;}if (roman.equals("XLI")){return 41;}if (roman.equals("XLII")){return 42;}if (roman.equals("XLIII")){return 43;}if (roman.equals("XLIV")){return 44;}if (roman.equals("XLV")){return 45;}
            if (roman.equals("XLVI")){return 46;}if (roman.equals("XLVII")){return 47;}if (roman.equals("XLVIII")){return 48;}if (roman.equals("XLIX")){return 49;}if (roman.equals("L")){return 50 ;}if (roman.equals("LI")){return 51;}
            if (roman.equals("LII")){return 52 ;}if (roman.equals("LIII")){return 53;}if (roman.equals("LIV")){return 54;}if (roman.equals("LV")){return 55;}if (roman.equals("LVI")){return 56;}if (roman.equals("LVII")){return 57;}
            if (roman.equals("LVIII")){return 58;}if (roman.equals("LVIX")){return 59;}if (roman.equals("LX")){return 60;}if (roman.equals("LXI")){return 61;}if (roman.equals("LXII")){return 62;}if (roman.equals("LXIII")){return 63;}if (roman.equals("LXIV")){return 64;}if (roman.equals("LXV")){return 65;}
            if (roman.equals("LXVI")){return 66;}if (roman.equals("LXVII")){return 67;}if (roman.equals("LXVIII")){return 68;}if (roman.equals("LXIX")){return 69;}if (roman.equals("LXX")){return 70;}if (roman.equals("LXXI")){return 71;}if (roman.equals("LXXII")){return 72;}if (roman.equals("LXXIII")){return 73;}
            if (roman.equals("LXXIV")){return 74;}if (roman.equals("LXXV")){return 75;}if (roman.equals("LXXVI")){return 76;}if (roman.equals("LXVII")){return 77;}if (roman.equals("LXXVIII")){return 78;}if (roman.equals("LXXIX")){return 79 ;}if (roman.equals("LXXX")){return 80;}if (roman.equals("LXXXI")){return 81;}
            if (roman.equals("LXXXII")){return 82;}if (roman.equals("LXXXIII")){return 83;}if (roman.equals("LXXXIV")){return 84;}if (roman.equals("LXXXV")){return 85;}if (roman.equals("LXXXVI")){return 86;}if (roman.equals("LXXXVII")){return 87;}if (roman.equals("LXXXVIII")){return 88;}if (roman.equals("LXXXIX")){return 89;}
            if (roman.equals("XC")){return 90;}if (roman.equals("XCI")){return 91;}if (roman.equals("XCII")){return 92;}if (roman.equals("XCIII")){return 93;}if (roman.equals("XCIV")){return 94;}if (roman.equals("XCV")){return 95;}if (roman.equals("XCVI")){return 96 ;}if (roman.equals("XCVII")){return 97;}
            if (roman.equals("XCVIII")){return 98;}if (roman.equals("XCIX")){return 99;}if (roman.equals("C")){return 100;}
        }catch (InputMismatchException e){throw new RuntimeException("throws Exception //т.к. используются одновременно разные системы счисления");}
        return -1; }

    //собственно и сам свитч кейс, где при успешном нахождении знака мы выполняем соответствующее действие.
    private static int calcul(int n1, int n2, char operation) {
        switch (operation) {
            case '+':
                result = (n1 + n2);
                break;
            case '-':
                result = (n1 - n2);
                break;
            case '/':
                result = (n1 / n2);
                break;
            case '*':
                result = (n1 * n2);
                break;}
        return result;
    }
}
// на калькулятор ушло 2-3 дня и чуть больше на изучение исключений и их добавление в код....
