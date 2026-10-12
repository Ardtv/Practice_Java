public class ComplexMatrix {
    private final int rows;
    private final int cols;
    private final Complex[][] data;

    public ComplexMatrix(int rows, int cols){
        if(rows < 1 || cols < 1) throw new IllegalArgumentException("Размеры матрицы должны быть положительными");
        this.cols = cols;
        this.rows = rows;
        this.data = new Complex[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                data[i][j] = Complex.ZERO;
            }
        }
    }

    public Complex get(int i, int j){return data[i][j];}

    public void set(int i, int j, Complex value){data[i][j] = value;}

    public int getRows() {return rows;}
    public int getCols() {return cols;}

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < rows; i++){
            for(int j = 0 ; j < cols; j++){
                if(j < cols - 1){
                result += data[i][j] + "\t";} else{
                    result += data[i][j];
                }
            }
            result += "\n";
        }
        return result;
    }

    public ComplexMatrix add(ComplexMatrix other){
        if(rows != other.rows  || cols != other.cols){throw new IllegalArgumentException("Для сложения матрицы должны быть одного размера");}
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0 ; j < cols; j++){
                result.set(i, j, data[i][j].add(other.data[i][j]));
            }
        }
        return result;
    }

    public ComplexMatrix transpose(){
        ComplexMatrix result = new ComplexMatrix(cols, rows);
        for(int i = 0; i < rows; i++){
            for(int j = 0 ; j < cols; j++){
                result.set(j, i, data[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other){
        if(cols != other.rows) throw new IllegalArgumentException("Число столбцов первой матрицы должно совпадать с числом строк второй");
        ComplexMatrix result = new ComplexMatrix(rows, other.cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < other.cols; j++){
                Complex sum = Complex.ZERO;
                for(int k = 0; k < cols; k++){
                    sum = sum.add(data[i][k].multiply(other.data[k][j]));
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public Complex determinant(){
        if(rows != cols) throw new IllegalArgumentException("Определитель есть только у квадратной матрицы");
        int n = rows;
        Complex[][] a = new Complex[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                a[i][j] = data[i][j];
            }
        }
        Complex det = Complex.ONE;
        for (int col = 0; col < n; col++) {
            int pivot = col;
            for (int r = col + 1; r < n; r++) {
                if (a[r][col].abs() > a[pivot][col].abs()) {
                    pivot = r;
                }
            }
            if (a[pivot][col].isZero()) {
                return Complex.ZERO;
            }
            if (pivot != col) {
                Complex[] temp = a[col];
                a[col] = a[pivot];
                a[pivot] = temp;
                det = det.multiply(new Complex(-1, 0));
            }
            for(int r = col + 1; r < n; r++){
                Complex factor = a[r][col].divide(a[col][col]);
                for(int c = col; c < n; c++) {
                    a[r][c] = a[r][c].subtract(factor.multiply(a[col][c]));
                }
            }
            det = det.multiply(a[col][col]);
        }
        return det;
    }

    public ComplexMatrix inverse() {
        if (rows != cols) throw new IllegalArgumentException("Обратная матрица есть только у квадратной матрицы");
        int n = rows;
        Complex[][] a = new Complex[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = data[i][j];
            }
        }
        Complex[][] inv = new Complex[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    inv[i][j] = Complex.ONE;
                } else {
                    inv[i][j] = Complex.ZERO;
                }
            }
        }
        for (int col = 0; col < n; col++) {
            int pivot = col;
            for (int r = col + 1; r < n; r++) {
                if (a[r][col].abs() > a[pivot][col].abs()) {
                    pivot = r;
                }
            }
            if (a[pivot][col].isZero()) {
                throw new ArithmeticException("Матрица вырождена, обратной не существует");
            }
            if (pivot != col) {
                Complex[] temp = a[col];
                a[col] = a[pivot];
                a[pivot] = temp;
                Complex[] temp2 = inv[col];
                inv[col] = inv[pivot];
                inv[pivot] = temp2;
            }
            Complex pivotValue = a[col][col];
            for(int c = 0; c < n; c++){
                a[col][c] = a[col][c].divide(pivotValue);
                inv[col][c] = inv[col][c].divide(pivotValue);
            }
            for(int r = 0; r < n; r++) {
                if (r == col) continue;
                Complex factor = a[r][col];
                for (int c = 0; c < n; c++) {
                    a[r][c] = a[r][c].subtract(factor.multiply(a[col][c]));
                    inv[r][c] = inv[r][c].subtract(factor.multiply(inv[col][c]));
                }
            }
        }
        ComplexMatrix result = new ComplexMatrix(n, n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result.set(i, j, inv[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix divide(ComplexMatrix other){return this.multiply(other.inverse());}
}
