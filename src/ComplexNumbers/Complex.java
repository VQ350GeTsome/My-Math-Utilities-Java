package ComplexNumbers;

public class Complex {

    //The real and imaginary components.
    public float r, i;
    
    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    /**
     * Default constructor. Both the real component & the imaginary are initialized at 0.
     */
    public Complex() { r = 0; i = 0; }
    /**
     * Full explicit constructor.
     * 
     * @param real The real component.
     * @param imaginary The imaginary component.
     */
    public Complex(float real, float imaginary) { r = real; i = imaginary; }
    
    /**
     * Constructor using a vec2.
     * 
     * @param ri The vec2 that'll be used for the real and imaginary components.
     */
    public Complex(Vectors.vec2 ri) { r = ri.x; i = ri.y; }
    
    /**
     * Copy constructor.
     * 
     * @param copy The complex number to copy.
     */
    public Complex(Complex copy) { r = copy.r; i = copy.i; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Scalar Operators ">
    /**
     * Adds a scalar to each component.
     * 
     * @param f The scalar to add.
     * @return A new Complex equal to ( s + f , i + f ) .
     */
    public Complex add(float f) { return new Complex(r + f, i + f); }
    /**
     * Subtracts a scalar from each component.
     * 
     * @param f The subtrahend scalar.
     * @return A new Complex equal to  s - f , i - f , j - f , k - f ) .
     */
    public Complex subtract(float f) { return this.add( -f ); }
    /**
     * Scales each component.
     * 
     * @param f The scalar value to scale by.
     * @return A new Complex equal to ( s * f , i * f , j * f , k * f , ) .
     */
    public Complex scale(float f) { return new Complex(r*f, i*f); }
    /**
     * Divides each component by a scalar.
     * 
     * @param f The dividend scalar.
     * @return A new Complex equal to ( s / f , i / f , j / f , k / f ) .
     */
    public Complex divide(float f) { return this.scale( 1/f ); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Complex Operators ">
    /**
     * Component-wise addition.
     * 
     * @param o The other Complex to add.
     * @return A new Complex equal to ( r + o.r , i + o.i ) .
     */
    public Complex add(Complex o) { return new Complex(r + o.r, i + o.i); }
    /**
     * Component-wise subtraction.
     * 
     * @param o The subtrahend Complex.
     * @return A new Complex equal to ( r - o.r , i - o.i ) .
     */
    public Complex subtract(Complex o) { return this.add(o.negate()); }
    /**
     * Multiplies the two complex numbers.
     * 
     * @param o The multiplicator Complex.
     * @return A new Complex that's been multiplied.
     */
    public Complex multiply(Complex o) { return new Complex(r * o.r - i * o.i, r * o.i + o.r * i);      
    }
    /**
     * Divides the two complex numbers.
     * 
     * @param o The dividend Complex.
     * @return A new Complex that's been dividend.
     */
    public Complex divide(Complex o) { return this.multiply(this.inverse()); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Scalar Operators ">
    /**
     * Component-wise max ( Java's % operator ) .
     * 
     * @param m The dividend.
     * @return A new Complex equal to ( r % m , i % m ) .
     */
    public Complex remainder(float m) { return new Complex(r % m, i % m); }
    /**
     * Component-wise mathematical modulus ( Always positive ) .
     * 
     * @param m The dividend.
     * @return A new Complex equal to ( r mod m , i mod m ) .
     */
    public Complex modulus(float m) { 
        return new Complex(
                ((r % m) + m) % m,
                ((i % m) + m) % m
        );
    }
    /**
     * Component-wise maximum operation.
     * 
     * @param f The scalar.
     * @return A new Complex that's equal to ( max(r , f) , max(i , f) ) .
     */
    public Complex max(float f) { return new Complex( Math.max(r, f) , Math.max(i, f) ); }
    /**
     * Component-wise minimum operation.
     * 
     * @param f The scalar.
     * @return A new Complex that's equal to ( min(r , f) , min(i , f) ) .
     */
    public Complex min (float f) { return new Complex( Math.min(r, f) , Math.min(i, f) ); }
    /**
     * Component-wise clamping operation.
     * Throws an error is h is less than l.
     * 
     * @param l The lowest allowed value.
     * @param h The highest allowed value.
     * @return A new Complex where each component is within [l, h].
     */
    public Complex clamp(float l, float h) { 
        if (h < l) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value ... " + h + " < " + l);
        return this.max(l).min(h); 
    }
    
    /**
     * If n is a positive whole number, it'll repeatedly multiply a result 
     * Quaternion until the result is calculated and return a new Quaternion that's 
     * the result. 
     * 
     * If n is some non-whole number, it'll calculate the polar form, 
     * exponentiate  it, and calculate the s, i, j, & k components and return 
     * those as a new Quaternion. 
     * 
     * @param n The exponent.
     * @return A new Complex that has been exponentiated.
     */
    public Complex pow(float n) {
        return null;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Quaternion Operators ">
    /**
     * Per-component maximum against another vector.
     * 
     * @param o The other Quaternion whose components will be used.
     * @return A new Quaternion that's equal to ( max(r , o.r) , max(i , o.i) ) .
     */
    public Complex max(Complex o) { return new Complex(Math.max(r, o.r), Math.max(i, o.i)); }
    /**
     * Per-component minimum against another vector.
     * 
     * @param o The other Quaternion whose components will be used.
     * @return A new Quaternion that's equal to ( min(r , o.r) , min(i , o.i) ) .
     */
    public Complex min(Complex o) { return new Complex(Math.min(r, o.r), Math.min(i, o.i)); }
    /**
     * Per-component clamping operation.
     * Throws an error if any component of h is less 
     * than the corresponding component of l. For example
     * if you try to clamp some quaternion in between [ (1, 2, 3, 3) , (0 , 3, 5, 7) ]
     * this will throw an error because l.s > h.s .
     * 
     * @param l The other Quaternion whose components will be used for the low.
     * @param h The other Quaternion whose components will be used for the high.
     * @return A new Quaternion where each component is within [l, h].
     */
    public Complex clamp(Complex l, Complex h) {
        if (h.r < l.r) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.r + " < " + l.r);
        if (h.i < l.i) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.i + " < " + l.i);
        return this.max(l).min(h);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Information Calculators ">
    /**
     * Calculates and returns the magnitude of this complex number.
     * 
     * @return The square root of sum of each component squared.
     */
    public float magnitude() { return (float) Math.sqrt(this.magnitudeSquared()); }
    /**
     * Calculates the sum of each component squared. 
     * This is equivalent to the magnitude squared.
     * 
     * @return The sum of each component squared.
     */
    public float magnitudeSquared() { return r*r + i*i; }
    
    public Vectors.vec2 getPolarForm() {
        return null;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    /**
     * Negates every component.
     * 
     * @return A new Complex equal to ( -r , -i ) .
     */
    public Complex negate() { return this.scale(-1.0f); }
    /**
     * Calculates the magnitude of this complex number and creates a new one
     * with each component being divided by the magnitude.
     * 
     * @return A new Complex that has been normalized.
     */
    public Complex normalize() {
        float l = this.magnitude();
        if (l == 0) return new Complex(this);
        return new Complex(r / l, i / l);
    }
    /**
     * Calculates the inverse of this complex number and 
     * creates a new complex number that's the inverse.
     * 
     * @return A new Complex that's the inverse of this one.
     */
    public Complex inverse() {
        float lsqrd = this.magnitudeSquared();
        if (lsqrd == 0) throw new ArithmeticException("Cannot invert a zero complex number");
        Complex conj = conjugate();
        return new Complex(conj.r / lsqrd, conj.i / lsqrd);
    }
    /**
     * Negates only the imaginary component.
     * 
     * @return A new Complex equal to ( r , -i ) .
     */
    public Complex conjugate() { return new Complex(r, -i); }
    //</editor-fold>
}