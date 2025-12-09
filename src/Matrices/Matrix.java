package Matrices;

public class Matrix {

    // The matrix.
    private float[][] mat;
    // The row & column count.
    private int r, c;
    
    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    public Matrix(int size) { mat = new float[size][size]; r = size; c = size; }
    public Matrix(int rows, int columns) { mat = new float[rows][columns]; r = rows; c = columns; }
    public Matrix(float[][] data) {
        r = data.length; c = data[0].length;
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            this.mat[i][j] = data[i][j];
    }
    public Matrix(Matrix copy) {
        this(copy.r, copy.c);
        for (int i = 0; copy.r > i; i++) for (int j = 0; copy.c > j; j++) 
            mat[i][j] = copy.mat[i][j];
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Getters & Setters ">
    public int getRows() { return r; }
    public int getColumns() { return c; }
    public float getValue(int i, int j) throws ArrayIndexOutOfBoundsException {
        //Check bounds
        if (i > r - 1 || 0 > i) throw new ArrayIndexOutOfBoundsException("Row index, " + i + ", out of bounds!");
        if (j > c - 1 || 0 > j) throw new ArrayIndexOutOfBoundsException("Column index, " + j + ", out of bounds!");
        //If it's valid return the data.
        return mat[i][j];
    }
    public void setValue(int i, int j, float value) throws ArrayIndexOutOfBoundsException {
        //Check bounds
        if (i > r - 1 || 0 > i) throw new ArrayIndexOutOfBoundsException("Row index, " + i + ", out of bounds!");
        if (j > c - 1 || 0 > j) throw new ArrayIndexOutOfBoundsException("Column index, " + j + ", out of bounds!");
        //If it's valid set the data
        mat[i][j] = value;
    } 
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Scalar Operations ">
    public Matrix add(float f) {
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] += f;
        return result;
    }
    public Matrix subtract(float f) { return this.add(-f); }
    public Matrix scale(float f) {
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] *= f;
        return result;
    }
    public Matrix divide(float f) { return this.scale( 1/f ); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Matrix Operations ">
    public Matrix add(Matrix o) throws ArithmeticException {
        if (!this.sizeEquals(o)) throw new ArithmeticException("Matrices must be the same size to add ...");
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] += o.mat[i][j];
        return result;
    }
    public Matrix subtract(Matrix o) throws ArithmeticException { return this.add(o.negate()); }
    public Matrix multiply(Matrix o) throws ArithmeticException { 
        // Can we actually multiply them?
        // The columns of this matrix must 
        // equal the rows of the other.
        if (this.c != o.r) 
            throw new ArithmeticException
                (
                    "Cannot multiply these matrices ... \n" +
                    "Column count of this matrix: " + this.c + "\n" +
                    "Row count of the other matrix: " + o.r + "\n"
                );
        Matrix result = new Matrix(this.r, o.c);
        
        for (int i = 0; this.r > i; i++) for (int j = 0; o.r > j; j++) 
            for (int k = 0; this.c > k; k++) {
                result.mat[i][j] += this.mat[i][k] * o.mat[k][j];
            }
        return result;
    }
    public Matrix hadamardProduct(Matrix o) throws ArithmeticException {
        if (!this.sizeEquals(o)) throw new ArithmeticException("Matrices must be the same size to calculate the Hadamard Product ...");
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] *= o.mat[i][j];
        return result;
    }
    public Matrix divide(Matrix o) throws ArithmeticException { 
        if (r != c) throw new ArithmeticException("Cannot divide a non-square matrix ..."); 
        return this.multiply(o.inverse()); 
    }
    public Matrix hadamardquotient(Matrix o) throws ArithmeticException {
        if (!this.sizeEquals(o)) throw new ArithmeticException("Matrices must be the same size to calculate the Hadamard Product ...");
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] /= o.mat[i][j];
        return result;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Matrix Operations ">
    public Matrix gaussianEliminate() throws ArithmeticException { 
        if (this.c % 2 == 1) throw new ArithmeticException("Cannot perform a guassian elimination without explicit column arguement if column count is odd ...");
        return this.guassianEliminate(this.getRightHalf(), this.c / 2); 
    }
    public Matrix guassianEliminate(Matrix o, int column) {
        Matrix augmented = this.augment(o);

        for (int pivot = 0; column > pivot; pivot++) {
            // Find a nonzero pivot (swap if needed)
            if (augmented.mat[pivot][pivot] == 0) {
                boolean swapped = false;
                for (int k = pivot + 1; this.r > k; k++) {
                    if (augmented.mat[k][pivot] != 0) {
                        float[] temp = augmented.mat[pivot];
                        augmented.mat[pivot] = augmented.mat[k];
                        augmented.mat[k] = temp;
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) throw new ArithmeticException("Matrix is singular, cannot eliminate ...");
            }

            // Scale pivot row so pivot = 1
            double pivotVal = augmented.mat[pivot][pivot];
            for (int j = 0; augmented.c > j; j++) augmented.mat[pivot][j] /= pivotVal;

            // Eliminate other rows in this column
            for (int i = 0; this.r > i; i++) {
                if (i != pivot) {
                    double factor = augmented.mat[i][pivot];
                    for (int j = 0; augmented.c > j; j++) augmented.mat[i][j] -= factor * augmented.mat[pivot][j];
                }
            }
        }

        // 4. Return the left half 
        return augmented.getLeft(augmented.c - column);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Information Calculators ">
    public float determinant() throws ArithmeticException {
        if (r != c) throw new ArithmeticException("Cannot calculate the determinant of a non-square matrix ...");
        
        float[][] tempMat = new float[r][c];
        for (int i = 0; r > i; i++) 
            System.arraycopy(this.mat[i], 0, tempMat[i], 0, c);
        
        int n = r, swaps = 0;
        float det = 1.0f;

        for (int p = 0; n > p; p++) {
            int maxRow = p;
            for (int i = p + 1; n > i; i++) 
                if (Math.abs(tempMat[i][p]) > Math.abs(tempMat[maxRow][p])) 
                    maxRow = i;

            if (maxRow != p) {
                float[] temp = tempMat[p];
                tempMat[p] = tempMat[maxRow];
                tempMat[maxRow] = temp;
                swaps++; // Track the number of swaps
            }

            // Determinant is 0 if [p][p] is close enough to 0
            if (Math.abs(tempMat[p][p]) <= 1e-6f)  return 0.0f; 
            
            // Elimination loop to create an upper triangular matrix (Gaussian Elimination)
            for (int i = p + 1; n > i; i++) {
                float factor = tempMat[i][p] / tempMat[p][p];
                for (int j = p; n > j; j++) 
                    tempMat[i][j] -= factor * tempMat[p][j];
            }
        }

        // The determinant of the triangular matrix is the product of the diagonal elements
        for (int i = 0; n > i; i++) det *= tempMat[i][i];

        // Adjust the sign based on the number of row swaps
        if (swaps % 2 != 0) det = -det;

        return det;
    }
    public Matrix getIdentity() throws ArithmeticException {
        if (r != c) throw new ArithmeticException("Cannot get the identity of a non-square matrix ..."); 
        Matrix id = new Matrix(this.r);
        for (int i = 0; this.r > i; i++)
            id.mat[i][i] = 1.0f;
        return id;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    public Matrix negate() { return this.scale(-1.0f); }
    public Matrix inverse() {
        if (r != c) throw new ArithmeticException("Cannot inverse a non-square matrix ...");
        if (this.determinant() == 0) throw new ArithmeticException("Matrix is singular and cannot be inverted ...");
        Matrix augmented = augment(this.getIdentity());
        augmented = augmented.gaussianEliminate();
        return augmented.getRightHalf();
    }
    
    public Matrix augment(Matrix o) throws ArithmeticException {
        if (this.r != o.r) throw new ArithmeticException("Cannot augment two matrices if they have different row counts ...");
        int nc = this.c + o.c;
        Matrix augmented = new Matrix(this.r, nc);
        for (int i = 0; this.r > i; i++) for (int j = 0; nc > j; j++) {
            // Adding this matrix to the augmented matrix.
            if (this.c > j) {
                augmented.mat[i][j] = this.mat[i][j];
            }
            // Adding the other matrix to the augmented matrix.
            else {
                augmented.mat[i][j] = o.mat[i][j - this.c];
            }
        }
        return augmented;
    }
    
    public Matrix getRightHalf() throws ArithmeticException {
        if (this.c % 2 == 1) throw new ArithmeticException("Cannot half a matrix when the column count is odd ...");
        return this.getRight(this.c / 2);
    }
    public Matrix getRight(int column) {
        Matrix right = new Matrix(this.r, column);
        for (int i = 0; this.r > i; i++) for (int j = column; this.c > j; j++) 
            right.mat[i][j - column] = this.mat[i][j];
        return right;
    }
    public Matrix getLeft(int column) {
        Matrix left = new Matrix(this.r, column);
        for (int i = 0; this.r > i; i++) for (int j = 0; column > j; j++) 
            left.mat[i][j] = this.mat[i][j];
        return left;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" String Methods ">
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.r; i++) {
            for (int j = 0; j < this.c; j++) sb.append(String.format("%8.3f", this.mat[i][j]));
            sb.append("\n");
        }
        return sb.toString();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Equal Operators ">
    public boolean sizeEquals(Matrix o) { return this.r == o.r && this.c == o.c; }
    @Override 
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix)) return false;
        if (this == obj) return true;
        Matrix o = (Matrix) obj;
        return this.equals(o);
    }
    public boolean equals(Matrix o) {
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            if (this.mat[i][j] != o.mat[i][j]) return false;
        return true;
    }
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc=" Overrides ">
    @Override
    public int hashCode() {
        int hash = java.util.Arrays.deepHashCode(mat);
        return hash ^ (31 * r + c);
    }
    //</editor-fold>  
    
}
