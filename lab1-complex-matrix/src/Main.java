import java.util.Scanner;
import java.util.Locale;
import java.util.InputMismatchException;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        in.useLocale(Locale.US);
        while (true) {
            System.out.println("Выберите операцию:");
            System.out.println("1 — сложить две матрицы");
            System.out.println("2 — умножить две матрицы");
            System.out.println("3 — разделить две матрицы");
            System.out.println("4 — транспонировать матрицу");
            System.out.println("5 — вычислить определитель");
            System.out.println("0 — выход");
            System.out.print("> ");
            try {
                int choice = in.nextInt();
                switch (choice) {
                    case 0: {
                        System.out.println("Работа завершена");
                        return;
                    }
                    case 1: {
                        System.out.println("Первая матрица: ");
                        ComplexMatrix a = readMatrix();
                        System.out.println("Вторая матрица: ");
                        ComplexMatrix b = readMatrix();
                        System.out.println("Сумма двух матриц: " + "\n" + a.add(b));
                        break;
                    }
                    case 2: {
                        System.out.println("Первая матрица: ");
                        ComplexMatrix a = readMatrix();
                        System.out.println("Вторая матрица: ");
                        ComplexMatrix b = readMatrix();
                        System.out.println("Произведение двух матриц:\n" + a.multiply(b));
                        break;
                    }
                    case 3: {
                        System.out.println("Первая матрица: ");
                        ComplexMatrix a = readMatrix();
                        System.out.println("Вторая матрица: ");
                        ComplexMatrix b = readMatrix();
                        System.out.println("Частное двух матриц:\n" + a.divide(b));
                        break;
                    }
                    case 4: {
                        ComplexMatrix a = readMatrix();
                        System.out.println("Транспонированная матрица:\n" + a.transpose());
                        break;
                    }
                    case 5: {
                        ComplexMatrix a = readMatrix();
                        System.out.println("Определитель: " + a.determinant());
                        break;
                    }
                    default: {
                        System.out.println("Нет такого пункта");
                    }
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: нужно вводить числа");
                in.nextLine();
            }
        }
    }

    private static Complex readComplex() {
        double re = in.nextDouble();
        double im = in.nextDouble();
        return new Complex(re, im);
    }

    private static ComplexMatrix readMatrix() {
        System.out.print("Введите число строк и столбцов: ");
        int rows = in.nextInt();
        int cols = in.nextInt();
        ComplexMatrix m = new ComplexMatrix(rows, cols);
        System.out.println("Введите элементы построчно, каждый как два числа (действительная и мнимая часть):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.set(i, j, readComplex());
            }
        }
        return m;
    }
}