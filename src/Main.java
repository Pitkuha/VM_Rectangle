import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String otdelitel = ANSI_RED + "===================================================================" + ANSI_RESET;

    public static final String[] functions = {"y = x²", "y = x", "y = 2x+1", "y = √x", "y = 1/x"};


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        double lower;
        double higher;
        double accuracy;
        System.out.println(ANSI_CYAN + "Program has started" + ANSI_RESET);
        System.out.println(otdelitel);
        printFunctions();
        System.out.print(ANSI_CYAN + "Выберите желаемую функцию:" + ANSI_RESET);
        number = scanner.nextInt();
        System.out.print(ANSI_CYAN + "Введите нижнюю границу:" + ANSI_RESET);
        lower = scanner.nextDouble();
        System.out.print(ANSI_CYAN + "Введите верхнюю границу:" + ANSI_RESET);
        higher = scanner.nextDouble();
        System.out.print(ANSI_CYAN + "Введите точность:" + ANSI_RESET);
        accuracy = scanner.nextDouble();
        System.out.println(otdelitel);

        n_mid(number, lower, higher, 4, accuracy);
        n_left(number, lower, higher, 4, accuracy);
        n_right(number, lower, higher, 4, accuracy);
    }

    //метод правых прямоугольников
    static double right_rect(int number, double lower, double higher, int n){
        double h = (higher - lower) / n;
        double s = 0;
        for (int i = 1; i < n + 1; i++) {
            s += functions(number, lower + h * i);
        }
        return h * s;
    }

    //метод левых прямоугольников
    static double left_rect(int number, double lower, double higher, int n){
        double h = (higher - lower) / n;
        double s = 0;
        for (int i = 0; i < n; i++) {
            s += functions(number, lower + h * i);
        }
        return h * s;
    }

    //метод средних прямоугольников
    static double middle_rect(int number, double lower, double higher, int n){
        double h = (higher - lower) / n;
        double s = 0;
        for (int i = 0; i < n; i++) {
            s += functions(number, lower + h / 2 + h * i);
        }
        return h * s;
    }

    static void n_right(int number, double lower, double higher, int n, double accuracy){
        int s_n = n * 2;
        double i0 = right_rect(number, lower, higher, n);
        double i1 = right_rect(number, lower, higher, n * 2);
        while (Math.abs(i1 - i0) > accuracy) {
            s_n *= 2;
            i0 = i1;
            i1 = right_rect(number, lower, higher, s_n);
        }
        System.out.println(ANSI_CYAN + "Метод правых прямоугольников: " + ANSI_GREEN + Math.abs(i1) + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Кол-во шагов: " + ANSI_GREEN + s_n + ANSI_RESET);
    }

    static void n_left(int number, double lower, double higher, int n, double accuracy){
        int s_n = n * 2;
        double i0 = left_rect(number, lower, higher, n);
        double i1 = left_rect(number, lower, higher, n * 2);
        while (Math.abs(i1 - i0) > accuracy) {
            s_n *= 2;
            i0 = i1;
            i1 = left_rect(number, lower, higher, s_n);
        }
        System.out.println(ANSI_CYAN + "Метод левых прямоугольников: " + ANSI_GREEN + Math.abs(i1) + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Кол-во шагов: " + ANSI_GREEN + s_n + ANSI_RESET);
    }

    static void n_mid(int number, double lower, double higher, int n, double accuracy){
        int s_n = n * 2;
        double i0 = middle_rect(number, lower, higher, n);
        double i1 = middle_rect(number, lower, higher, n * 2);
        while (Math.abs(i1 - i0) > accuracy) {
            s_n *= 2;
            i0 = i1;
            i1 = middle_rect(number, lower, higher, s_n);
        }
        System.out.println(ANSI_CYAN + "Метод средних прямоугольников: " + ANSI_GREEN + Math.abs(i1) + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Кол-во шагов: " + ANSI_GREEN + s_n + ANSI_RESET);
    }

    static double functions(int number, double x){
        if (number == 0){
            return x * x;
        } else if (number == 1){
            return x;
        } else if (number == 2){
            return 2 * x + 1;
        } else if (number == 3){
            return Math.sqrt(x);
        } else if (number == 4){
            return (1 / x);
        } else return 1231;
    }

    static void printFunctions(){
        int i = 0;
        for (String e : functions) {
            System.out.println("(" + i + ")" + " " + e);
            i++;
        }
    }
}
