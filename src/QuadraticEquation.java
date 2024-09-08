import java.util.Scanner;
import java.util.InputMismatchException;

public class QuadraticEquation {
    private double A, B, C;

    public QuadraticEquation(Double A, Double B, Double C) {
        if (A != null && B != null && C != null) {
            this.A = A;
            this.B = B;
            this.C = C;
        } else {
            getCoefficients();
        }
    }

    private void getCoefficientFromInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                if (prompt.contains("A") && value == 0) {
                    System.out.println("Коэффициент A не должен быть равен нулю. Попробуйте снова.");
                    continue;
                }
                if (prompt.contains("A")) {
                    this.A = value;
                } else if (prompt.contains("B")) {
                    this.B = value;
                } else {
                    this.C = value;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение. Попробуйте снова.");
                scanner.next(); // очистить буфер ввода
            }
        }
    }

    private void getCoefficients() {
        getCoefficientFromInput("Введите коэффициент A: ");
        getCoefficientFromInput("Введите коэффициент B: ");
        getCoefficientFromInput("Введите коэффициент C: ");
    }

    public double[] getRoots() {
        double determinant = B * B - 4 * A * C;
        if (determinant == 0) {
            return new double[]{-B / (2.0 * A)};
        } else if (determinant > 0) {
            double sqrtD = Math.sqrt(determinant);
            return new double[]{
                (-B + sqrtD) / (2.0 * A),
                (-B - sqrtD) / (2.0 * A)
            };
        } else {
            return new double[]{};
        }
    }

    public void solveAndPrint() {
        System.out.println("Коэффициенты: A=" + this.A + ", B=" + this.B + ", C=" + this.C);
        System.out.println("Уравнение: " + this.A + "*x^2 + " + this.B + "*x + " + this.C + " = 0");

        double[] roots = this.getRoots();
        if (roots.length == 1) {
            System.out.println("Корень: " + roots[0]);
        } else if (roots.length == 2) {
            System.out.println("Корни: " + roots[0] + ", " + roots[1]);
        } else {
            System.out.println("Нет корней");
        }
    }

    public static void main(String[] args) {
        QuadraticEquation equation;
        if (args.length == 3) {
            try {
                double A = Double.parseDouble(args[0]);
                double B = Double.parseDouble(args[1]);
                double C = Double.parseDouble(args[2]);
                equation = new QuadraticEquation(A, B, C);
            } catch (NumberFormatException e) {
                System.out.println("Некорректные коэффициенты в командной строке. Используйте ввод через консоль.");
                equation = new QuadraticEquation(null, null, null);
            }
        } else {
            equation = new QuadraticEquation(null, null, null);
        }
        equation.solveAndPrint();
    }
}