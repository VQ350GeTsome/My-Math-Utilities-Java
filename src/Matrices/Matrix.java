package Matrices;

/**
 * A matrix class with most commonly used functions.
 * 
 * @author Harrison Davis
 */
public class Matrix {

    // The matrix.
    private float[][] mat;
    // The row & column count.
    private int r, c;
    
    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    /**
     * Constructor for a square matrix.
     * 
     * @param size The size. This will become the row & column count. 
     */
    public Matrix(int size) { mat = new float[size][size]; r = size; c = size; }
    /**
     * Full explicit constructor. 
     * 
     * @param rows The amount of rows.
     * @param columns The amount of columns.
     */
    public Matrix(int rows, int columns) { mat = new float[rows][columns]; r = rows; c = columns; }
    /**
     * 2D array constructor. Transforms a 2D array into a matrix.
     * 
     * @param data The 2D to use for this new matrix.
     */
    public Matrix(float[][] data) {
        r = data.length; c = data[0].length;
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            this.mat[i][j] = data[i][j];
    }
    
    /**
     * Copy constructor.
     * 
     * @param copy The matrix to copy.
     */
    public Matrix(Matrix copy) {
        this(copy.r, copy.c);
        for (int i = 0; copy.r > i; i++) for (int j = 0; copy.c > j; j++) 
            mat[i][j] = copy.mat[i][j];
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Getters & Setters ">
    /**
     * Gets the amount of rows in this matrix.
     * 
     * @return The amount of rows.
     */
    public int getRows() { return r; }
    /**
     * Gets the amount of columns in this matrix.
     * 
     * @return The amount of columns. 
     */
    public int getColumns() { return c; }
    
    /**
     * Gets a value at some position in the matrix.
     * The row & column inputs must be within the matrix.
     * 
     * @param i The row position.
     * @param j The column position.
     * @return The value at that position.
     * 
     * @throws ArrayIndexOutOfBoundsException If you try to get a value that's outside the matrix size.
     */
    public float getValue(int i, int j) throws ArrayIndexOutOfBoundsException {
        //Check bounds
        if (i > r - 1 || 0 > i) throw new ArrayIndexOutOfBoundsException("Row index, " + i + ", out of bounds!");
        if (j > c - 1 || 0 > j) throw new ArrayIndexOutOfBoundsException("Column index, " + j + ", out of bounds!");
        //If it's valid return the data.
        return mat[i][j];
    }
    /**
     * Sets a value at some position in the matrix.
     * The row & column inputs must be within the matrix.
     * 
     * @param i The row position.
     * @param j The column position.
     * @param value The value to be placed at ( i , j )
     * 
     * @throws ArrayIndexOutOfBoundsException If you try to set a value that's outside the matrix size.
     */
    public void setValue(int i, int j, float value) throws ArrayIndexOutOfBoundsException {
        //Check bounds
        if (i > r - 1 || 0 > i) throw new ArrayIndexOutOfBoundsException("Row index, " + i + ", out of bounds!");
        if (j > c - 1 || 0 > j) throw new ArrayIndexOutOfBoundsException("Column index, " + j + ", out of bounds!");
        //If it's valid set the data
        mat[i][j] = value;
    } 
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Scalar Operations ">
    /**
     * Adds a scalar to each element in the matrix.
     * 
     * @param f The scalar to add.
     * @return A new Matrix where each element has had the value added.
     */
    public Matrix add(float f) {
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] += f;
        return result;
    }
    /**
     * Subtracts a scalar to each element in the matrix.
     * 
     * @param f The scalar to subtract.
     * @return A new Matrix where each element has had the value subtracted.
     */
    public Matrix subtract(float f) { return this.add(-f); }
    /**
     * Scales each element in the matrix by some scalar.
     * 
     * @param f The scalar to scale by.
     * @return A new Matrix where each element has been scaled.
     */
    public Matrix scale(float f) {
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] *= f;
        return result;
    }
    /**
     * Divides each element in the matrix by some scalar.
     * 
     * @param f The scalar to divide by.
     * @return A new Matrix where each element has been scaled.
     */
    public Matrix divide(float f) { return this.scale( 1/f ); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Matrix Operations ">
    /**
     * Adds two matrices together. They must be of the same dimensions.
     * 
     * @param o The other Matrix to add.
     * @return A new Matrix that's elements are the sum of this one and the other.
     * 
     * @throws ArithmeticException if the two matrices are not equal in dimensions.
     */
    public Matrix add(Matrix o) throws ArithmeticException {
        if (!this.sizeEquals(o)) throw new ArithmeticException("Matrices must be the same size to add ...");
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] += o.mat[i][j];
        return result;
    }
    /**
     * Subtracts two matrices. They must be of the same dimensions.
     * 
     * @param o The subtrahend Matrix.
     * @return A new matrix that's elements are the difference of this one and the other.
     * 
     * @throws ArithmeticException if the two matrices are not equal in dimensions.
     */
    public Matrix subtract(Matrix o) throws ArithmeticException { return this.add(o.negate()); }
    /**
     * Multiplies two matrices together.
     * 
     * @param o The other Matrix.
     * @return A new Matrix with the calculated results.
     * 
     * @throws ArithmeticException if the column count of this Matrix is not equal to the row count of the other Matrix.
     */
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
    /**
     * Multiplies two matrices together using the Hadamard product.
     * 
     * @param o The other Matrix.
     * @return A new Matrix that's elements are the product of this one and the other.
     * 
     * @throws ArithmeticException if the two matrices are not equal in dimensions.
     */
    public Matrix hadamardProduct(Matrix o) throws ArithmeticException {
        if (!this.sizeEquals(o)) throw new ArithmeticException("Matrices must be the same size to calculate the Hadamard Product ...");
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] *= o.mat[i][j];
        return result;
    }
    /**
     * Calculates a new Matrix that's the inverse of the other Matrix, and uses 
     * it to multiply with this Matrix.
     * 
     * @param o The other Matrix, which will be used to calculate it's inverse.
     * @return A new matrix that's been divided.
     * 
     * @throws ArithmeticException if the divisor Matrix is not a square Matrix.
     */
    public Matrix divide(Matrix o) throws ArithmeticException { 
        if (o.r != o.c) throw new ArithmeticException("Cannot divide by a non-square matrix ..."); 
        return this.multiply(o.inverse()); 
    }
    /**
     * Multiplies two matrices together using the Hadamard quotient.
     * 
     * @param o The other Matrix.
     * @return A new Matrix that's elements are the quotient of this one and the other.
     * 
     * @throws ArithmeticException if the two matrices are not equal in dimensions.
     */
    public Matrix hadamardquotient(Matrix o) throws ArithmeticException {
        if (!this.sizeEquals(o)) throw new ArithmeticException("Matrices must be the same size to calculate the Hadamard Product ...");
        Matrix result = new Matrix(this);
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            result.mat[i][j] /= o.mat[i][j];
        return result;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Matrix Operations ">
    /**
     * Performs Gaussian elimination. Assumes the right half of this matrix is the identity or another matrix.
     * 
     * @return A new matrix that's the left half of the reduced augmented matrix.
     * 
     * @throws ArithmeticException if the column count is odd.
     */
    public Matrix gaussianEliminate() throws ArithmeticException { 
        if (this.c % 2 == 1) throw new ArithmeticException("Cannot perform a guassian elimination without explicit column arguement if column count is odd ...");
        return this.getLeftHalf().guassianEliminate(this.getRightHalf()); 
    }
    /**
     * Performs Gaussian elimination with another augmented matrix, o.
     * 
     * @param o The other mMatrix.
     * @return A new Matrix that's been reduced.
     * 
     * @throws ArithmeticException if the matrix is singular.
     */
    public Matrix guassianEliminate(Matrix o) throws ArithmeticException {
        Matrix augmented = this.augment(o);

        for (int pivot = 0; this.c > pivot; pivot++) {
            // 1. Find a nonzero pivot (swap rows if needed)
            if (augmented.mat[pivot][pivot] == 0) {
                boolean swapped = false;
                for (int k = pivot + 1; k < this.r; k++) {
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

            // 2. Scale pivot row so pivot = 1
            float pivotVal = augmented.mat[pivot][pivot];
            for (int j = 0; augmented.c > j; j++) augmented.mat[pivot][j] /= pivotVal;

            // 3. Eliminate other rows in this column
            for (int i = 0; this.r > i; i++) {
                if (i != pivot) {
                    float factor = augmented.mat[i][pivot];
                    for (int j = 0; augmented.c > j; j++) 
                        augmented.mat[i][j] -= factor * augmented.mat[pivot][j];
                }
            }
        }

        // 4. Return the left half (the reduced original matrix)
        return augmented.getLeft(this.c);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Information Calculators ">
    /**
     * Calculates the determinant of this matrix. To do so this matrix
     * must be a square matrix.
     * 
     * @return The determinant.
     * 
     * @throws ArithmeticException if this matrix isn't a square matrix.
     */
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
    /**
     * Gets the identity matrix of this matrix using it's size. 
     * This matrix must be square in order to get it's identity.
     * 
     * @return A new Matrix that's an identity Matrix with equal size to this one.
     * 
     * @throws ArithmeticException if this Matrix isn't square.
     */
    public Matrix getIdentity() throws ArithmeticException {
        if (this.r != this.c) throw new ArithmeticException("Cannot get the identity of a non-square matrix ..."); 
        return Matrix.getIdentity(this.r);
    }
    /**
     * Forms an identity matrix using the input size.
     * 
     * @param n How large to make the Matrix.
     * @return A new Matrix that's the identity matrix of size n.
     */
    public static Matrix getIdentity(int n) {
        Matrix id = new Matrix(n);
        for (int i = 0; n > i; i++)
            id.mat[i][i] = 1.0f;
        return id;
    }
    
    /**
     * Calculates the square of this matrix's norm.
     * This can be sometimes used in place of .norm(),
     * as this is a cheaper calculation as it doesn't 
     * use Math.sqrt().
     * 
     * @return The square of this Matrix's norm.
     */
    public float squareNorm() { 
        float sumSqrs = 0.0f;
        for (int i = 0; this.r > i; i++) for (int j = 0; this.c > j; j++) 
            sumSqrs += this.mat[i][j] * this.mat[i][j];
        return sumSqrs;
    }
    /**
     * Calculates and returns the norm of this matrix.
     * 
     * @return The norm of this Matrix.
     */
    public float norm() { return (float) Math.sqrt(this.squareNorm()); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    /**
     * Negates every element of this matrix. This is
     * equivalent to scaling it by -1.
     * 
     * @return A new Matrix that's equal to this matrix but each element has been negated.
     */
    public Matrix negate() { return this.scale(-1.0f); }
    /**
     * Calculates the inverse of this matrix (M^-1) and returns it.
     * The inverse holds that (M) * (M^-1) = the identity matrix.
     * 
     * @return A new Matrix that's the inverse of this Matrix.
     */
    public Matrix inverse() {
        if (r != c) throw new ArithmeticException("Cannot inverse a non-square matrix ...");
        if (this.determinant() == 0) throw new ArithmeticException("Matrix is singular and cannot be inverted ...");
        Matrix augmented = augment(this.getIdentity());
        augmented = augmented.gaussianEliminate();
        return augmented.getRightHalf();
    }
    
    /**
     * Creates a new Matrix & copies this one to it, then
     * augments (or concatenates) another Matrix to it. 
     * 
     * @param o The other Matrix
     * @return A new Matrix that's equal to ( this | o ) .
     * 
     * @throws ArithmeticException if the two matrices have different row counts.
     */
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
    
    /**
     * Creates a new Matrix and fills it with the right half 
     * of this matrix, then returns it.
     * 
     * @return A new Matrix that's the right half of this one.
     * 
     * @throws ArithmeticException if this Matrix isn't able 
     *      to be halfed. (i.e., the columns mod 2 == 1)
     */
    public Matrix getRightHalf() throws ArithmeticException {
        if (this.c % 2 == 1) throw new ArithmeticException("Cannot half a matrix when the column count is odd ...");
        return this.getRight(this.c / 2);
    }
    /**
     * Creates a new Matrix and fills it with whatever is to 
     * the right of colStart.
     * 
     * @param colStart The column to start the split.
     * @return A new sub Matrix with whatever is to the right of colStart.
     */
    public Matrix getRight(int colStart) {
        Matrix right = new Matrix(this.r, colStart);
        for (int i = 0; this.r > i; i++) for (int j = colStart; this.c > j; j++) 
            right.mat[i][j - colStart] = this.mat[i][j];
        return right;
    }
    
    /**
     * Creates a new Matrix and fills it with the left half 
     * of this matrix, then returns it.
     * 
     * @return A new Matrix that's the left half of this one.
     * 
     * @throws ArithmeticException if this Matrix isn't able 
     *      to be halfed. (i.e., the columns mod 2 == 1)
     */
    public Matrix getLeftHalf() throws ArithmeticException {
        if (this.c % 2 == 1) throw new ArithmeticException("Cannot half a matrix when the column count is odd ...");
        return this.getLeft(this.c / 2);
    } 
    /**
     * Creates a new Matrix and fills it with whatever is to 
     * the left of colEnd.
     * 
     * @param colEnd The column to end the split.
     * @return A new sub Matrix with whatever is to the left of colEnd.
     */
    public Matrix getLeft(int colEnd) {
        Matrix left = new Matrix(this.r, colEnd);
        for (int i = 0; this.r > i; i++) for (int j = 0; colEnd > j; j++) 
            left.mat[i][j] = this.mat[i][j];
        return left;
    }
    
    /**
     * Calculates a new normalized Matrix using this one.
     * 
     * @return A new Matrix that's been normalized.
     */
    public Matrix normalize() {
        float n = this.norm();
        Matrix normalized = new Matrix(this.r, this.c);
        for (int i = 0; i < this.r; i++) for (int j = 0; j < this.c; j++) 
            normalized.setValue(i, j, this.mat[i][j] / n);
        return normalized;
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
    /**
     * Checks if this Matrix and another are equal to another.
     * 
     * @param o The other Matrix.
     * @return True if they're equal, false if not.
     */
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
