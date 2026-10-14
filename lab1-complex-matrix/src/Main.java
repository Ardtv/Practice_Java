import java.util.Scanner;
import java.util.Locale;
import java.util.InputMismatchException;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static ComplexMatrix lastResult = null;

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
                        ComplexMatrix a = chooseMatrix("Первая матрица:");
                        ComplexMatrix b = chooseMatrix("Вторая матрица:");
                        lastResult = a.add(b);
                        System.out.println("Сумма двух матриц:\n" + lastResult);
                        break;
                    }
                    case 2: {
                        ComplexMatrix a = chooseMatrix("Первая матрица:");
                        ComplexMatrix b = chooseMatrix("Вторая матрица:");
                        lastResult = a.multiply(b);
                        System.out.println("Произведение двух матриц:\n" + lastResult);
                        break;
                    }
                    case 3: {
                        ComplexMatrix a = chooseMatrix("Первая матрица:");
                        ComplexMatrix b = chooseMatrix("Вторая матрица:");
                        lastResult = a.divide(b);
                        System.out.println("Частное двух матриц:\n" + lastResult);
                        break;
                    }
                    case 4: {
                        ComplexMatrix a = chooseMatrix("Матрица:");
                        lastResult = a.transpose();
                        System.out.println("Транспонированная матрица:\n" + lastResult);
                        break;
                    }
                    case 5: {
                        ComplexMatrix a = chooseMatrix("Матрица:");
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

    private static ComplexMatrix chooseMatrix(String title){
        System.out.println(title);
        if(lastResult == null) {
            return readMatrix();
        }else{
            System.out.println("1 - ввести новую матрицу");
            System.out.println("2 - использовать последний результат");
            System.out.print("> ");
            int choice = in.nextInt();
            if(choice == 2){
                System.out.println("Используется:\n" + lastResult);
                return lastResult;
            }
            return readMatrix();
        }
    }
}