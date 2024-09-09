import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BiquadSolver {
    private Double A;
    private Double B;
    private Double C;

    private double getCoefficientFromInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректное значение. Попробуйте снова.");
            }
        }
    }

    private double getCoefficient(String arg, String prompt) {
        try {
            return Double.parseDouble(arg);
        } catch (NumberFormatException e) {
            System.out.println("Некорректное значение для " + prompt + " в командной строке.");
            return getCoefficientFromInput(prompt);
        }
    }

    private void getCoefficients(String[] args) {
        if (args.length == 3) {
            this.A = getCoefficient(args[0], "коэффициент A");
            this.B = getCoefficient(args[1], "коэффициент B");
            this.C = getCoefficient(args[2], "коэффициент C");
        } else {
            while (true) {
                this.A = getCoefficientFromInput("Введите коэффициент A: ");
                if (this.A != 0) {
                    break;
                }
                System.out.println("Коэффициент A не должен быть равен нулю. Попробуйте снова.");
            }
            this.B = getCoefficientFromInput("Введите коэффициент B: ");
            this.C = getCoefficientFromInput("Введите коэффициент C: ");
        }
    }

    private List<Double> solveQuadratic(double A, double B, double C) {
        List<Double> result = new ArrayList<>();
        double D = B * B - 4 * A * C;
        if (D == 0.0) {
            double root = -B / (2.0 * A);
            result.add(root);
        } else if (D > 0.0) {
            double sqD = Math.sqrt(D);
            double root1 = (-B + sqD) / (2.0 * A);
            double root2 = (-B - sqD) / (2.0 * A);
            result.add(root1);
            result.add(root2);
        }
        return result;
    }

    private List<Double> solveBiquadratic() {
        List<Double> quadraticRoots = solveQuadratic(this.A, this.B, this.C);
        Set<Double> biquadraticRoots = new HashSet<>();
        for (Double root : quadraticRoots) {
            if (root > 0) {
                biquadraticRoots.add(Math.sqrt(root));
                biquadraticRoots.add(-Math.sqrt(root));
            } else if (root == 0) {
                biquadraticRoots.add(Math.sqrt(root));
            }
        }
        return new ArrayList<>(biquadraticRoots);
    }

    public void run(String[] args) {
        getCoefficients(args);

        System.out.printf("Коэффициенты: A=%.2f, B=%.2f, C=%.2f%n", this.A, this.B, this.C);
        System.out.printf("Уравнение: %.2f*x^4 + %.2f*x^2 + %.2f = 0%n", this.A, this.B, this.C);

        List<Double> result = solveBiquadratic();
        if (result.isEmpty()) {
            System.out.println("Нет корней");
        } else {
            System.out.println("Решение: " + result.toString().replace("[", "").replace("]", ""));
        }
    }

    public static void main(String[] args) {
        BiquadSolver solver = new BiquadSolver();
        solver.run(args);
    }
}