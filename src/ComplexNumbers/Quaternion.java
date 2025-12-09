package ComplexNumbers;

/**
 * Quaternion class with most common functionality.
 * 
 * @author Harrison Davis
 */
public class Quaternion implements Comparable<Quaternion> {
    
    //Scalar + the three imaginary components
    public float s, i, j, k;

    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    /**
     * Default constructor. Initialized this quaternion as
     * a unit quaternion equal to ( 1 , 0 , 0 , 0 ) also known
     * as the identity quaternion.
     */
    public Quaternion() { s = 1; i = 0; j = 0; k = 0; }
    /**
     * Explicit constructor. 
     * 
     * @param s The scalar component.
     * @param i The i component.
     * @param j The j component.
     * @param k The k component.
     */
    public Quaternion(float s, float i, float j, float k) { this.s = s; this.i = i; this.j = j; this.k = k; }
    
    /**
     * Constructor that uses a float plus a vec3 object to instantiate this. 
     * The x, y, & z will correspond to i, j, & k. The float will be the scalar.
     * 
     * @param s What will be the scalar of this quaternion.
     * @param v The vector to be used for the imaginary components.
     */
    public Quaternion(float s, Vectors.vec3 v) { 
        if (v == null) { this.s = s; i = 0; j = 0; k = 0; } 
        this.s = s; i = v.x; j = v.y; k = v.z; 
    }
    /**
     * Constructor using a vec4 object. x, y, & z corresponds to i, j, & k.
     * w will be the scalar value.
     * 
     * @param v The vec4 to use. If null this will be equal to the identity quaternion.
     */
    public Quaternion(Vectors.vec4 v) { 
        if (v == null) { s = 1; i = 0; j = 0; k = 0; } 
        else { s = v.w; i = v.x; j = v.y; k = v.z; }
    }
    
    /**
     * Constructor using a complex number.
     * 
     * @param si The complex number that'll be used for the scalar and i.
     * @param j The j component.
     * @param k The k component.
     */
    public Quaternion(ComplexNumber si, float j, float k) { s = si.r; i = si.i; this.j = j; this.k = k; }
    
    /**
     * Copy constructor.
     * 
     * @param q The quaternion to copy.
     */
    public Quaternion(Quaternion q) { s = q.s; i = q.i; j = q.j; k = q.k; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Scalar Operators ">
    /**
     * Adds a scalar to each component.
     * 
     * @param f The scalar to add.
     * @return A new Quaternion equal to ( s + f , i + f , j + f , k + f ) .
     */
    public Quaternion add(float f) { return new Quaternion(s + f, i + f, j + f, k + f); }
    /**
     * Subtracts a scalar from each component.
     * 
     * @param f The subtrahend scalar.
     * @return A new Quaternion equal to  s - f , i - f , j - f , k - f ) .
     */
    public Quaternion subtract(float f) { return this.add( -f ); }
    /**
     * Scales each component.
     * 
     * @param f The scalar value to scale by.
     * @return A new quaternion equal to ( s * f , i * f , j * f , k * f , ) .
     */
    public Quaternion scale(float f) { return new Quaternion(s*f, i*f, j*f, k*f); }
    /**
     * Scales each imaginary component.
     * 
     * @param f The scalar value to scale the imaginary components by.
     * @return A new quaternion equal to ( s , i * f , j * f , k * f , ) .
     */
    public Quaternion scaleImag(float f) { return new Quaternion(s, i*f, j*f, k*f); }
    /**
     * Divides each component by a scalar.
     * 
     * @param f The dividend scalar.
     * @return A new quaternion equal to ( s / f , i / f , j / f , k / f ) .
     */
    public Quaternion divide(float f) { return this.scale( 1/f ); }
    /**
     * Divides each imaginary component by a scalar.
     * 
     * @param f The dividend scalar.
     * @return A new quaternion equal to ( s , i / f , j / f , k / f ) .
     */
    public Quaternion divideImag(float f) { return this.scaleImag( 1/f ); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Quaternion Operators ">
    /**
     * Component-wise addition.
     * 
     * @param o The other quaternion to add.
     * @return A new quaternion equal to ( s + o.s , i + o.i , j + o.j , k + o.k ) .
     */
    public Quaternion add(Quaternion o) { return new Quaternion(s + o.s, i + o.i, j + o.j, k + o.k); }
    /**
     * Component-wise subtraction.
     * 
     * @param o The subtrahend quaternion.
     * @return A new quaternion equal to ( s - o.s , i - o.i , j - o.j , k - o.k ) .
     */
    public Quaternion subtract(Quaternion o) { return this.add(o.negate()); }
    /**
     * Multiplies the quaternions.
     * 
     * @param o The multiplicator quaternion.
     * @return A new quaternion that's been multiplied.
     */
    public Quaternion multiply(Quaternion o) {
        return new Quaternion(
            s*o.s - i*o.i - j*o.j - k*o.k,
            s*o.i + i*o.s + j*o.k - k*o.j,
            s*o.j - i*o.k + j*o.s + k*o.i,
            s*o.k + i*o.j - j*o.i + k*o.s
        );
    }
    /**
     * Divides the quaternions.
     * 
     * @param o The dividend quaternion.
     * @return A new quaternion that's been dividend.
     */
    public Quaternion divide(Quaternion o) { return this.multiply(o.inverse()); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Scalar Operators ">
    /**
     * Component-wise max ( Java's % operator ) .
     * 
     * @param m The dividend.
     * @return A new quaternion  equal to ( s % m , i % m , j % m , k % m ) .
     */
    public Quaternion remainder(float m) { return new Quaternion(s % m, i % m, j % m, k % m); }
    /**
     * Component-wise mathematical modulus ( Always positive ) .
     * 
     * @param m The dividend.
     * @return A new quaternion equal to ( s mod m , i mod m , j mod m , k mod m ) .
     */
    public Quaternion modulus(float m) { 
        return new Quaternion(
                ((s % m) + m) % m,
                ((i % m) + m) % m,
                ((j % m) + m) % m,
                ((k % m) + m) % m
        );
    }
    /**
     * Component-wise maximum operation.
     * 
     * @param f The scalar.
     * @return A new quaternion that's equal to ( max(s , f) , max(i , f) , max(j , f) , max(k , f) ) .
     */
    public Quaternion max(float f) { return new Quaternion( Math.max(s, f) , Math.max(i, f) , Math.max(j, f) , Math.max(k, f) ); }
    /**
     * Component-wise minimum operation.
     * 
     * @param f The scalar.
     * @return A new quaternion that's equal to ( min(s , f) , min(i , f) , min(j , f) , min(k , f) ) .
     */
    public Quaternion min (float f) { return new Quaternion( Math.min(s, f) , Math.min(i, f) , Math.min(j, f) , Math.min(k, f) ); }
    /**
     * Component-wise clamping operation.
     * Throws an error is h is less than l.
     * 
     * @param l The lowest allowed value.
     * @param h The highest allowed value.
     * @return A new quaternion where each component is within [l, h].
     */
    public Quaternion clamp(float l, float h) { 
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
     * @return A new Quaternion that has been exponentiated.
     */
    public Quaternion pow(float n) {
        //If n is close enough to a whole number
        if (Math.abs(n % 1) < 1e-9 && n > 0) {
            n = (int) n;
            Quaternion result = new Quaternion();
            for (; n > 0; n--) result = result.multiply(this);
            return result;
        }
        
        float[] components = this.getPolarForm();
        if (components[0] == 0) return new Quaternion();
        
        float newMag = (float) Math.pow(components[0], n);
        float newTheta = (float) Math.pow(components[1], n);
        
        float s = (float) (newMag * Math.cos(newTheta));
        float sinTheta = (float) Math.sin(newTheta);
        
        float i = newMag * components[2] * sinTheta,
              j = newMag * components[3] * sinTheta,
              k = newMag * components[4] * sinTheta;
        
        return new Quaternion (s, i, j, k);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Quaternion Operators ">
    /**
     * Per-component maximum against another vector.
     * 
     * @param o The other quaternion whose components will be used.
     * @return A new quaternion that's equal to ( max(s , o.s) , max(i , o.i) , max(j , o.j) , min(k , o.k) ) .
     */
    public Quaternion max(Quaternion o) { return new Quaternion(Math.max(s, o.s), Math.max(i, o.i), Math.max(j, o.j), Math.max(k, o.k)); }
    /**
     * Per-component minimum against another vector.
     * 
     * @param o The other quaternion whose components will be used.
     * @return A new quaternion that's equal to ( min(s , o.s) , min(i , o.i) , min(j , o.j) , min(k, o.k) ) .
     */
    public Quaternion min(Quaternion o) { return new Quaternion(Math.min(s, o.s), Math.min(i, o.i), Math.min(j, o.j), Math.min(k, o.k)); }
    /**
     * Per-component clamping operation.
     * Throws an error if any component of h is less 
     * than the corresponding component of l. For example
     * if you try to clamp some quaternion in between [ (1, 2, 3, 3) , (0 , 3, 5, 7) ]
     * this will throw an error because l.s > h.s .
     * 
     * @param l The other quaternion whose components will be used for the low.
     * @param h The other quaternion whose components will be used for the high.
     * @return A new quaternion where each component is within [l, h].
     */
    public Quaternion clamp(Quaternion l, Quaternion h) {
        if (h.s < l.s) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.s + " < " + l.s);
        if (h.i < l.i) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.i + " < " + l.i);
        if (h.j < l.j) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.j + " < " + l.j);
        if (h.k < l.k) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.k + " < " + l.k);
        return this.max(l).min(h);
    }
    
    /**
     * Calculates the dot product.
     * 
     * @param o The other quaternion.
     * @return The dot product.
     */
    public float dot(Quaternion o) { return s*o.s + i*o.i + j*o.j + k*o.k; }
    
    /**
     * Takes in a vec3 object and rotates it using this quaternion.
     * 
     * @param v The vector to be rotated.
     * @return A new vec3 that's been rotated in accordance to this quaternion.
     */
    public Vectors.vec3 rotate(Vectors.vec3 v) {
        if (v == null) return v;
        if (s == 1 && magnitude() == 1) return v;  //If no rotation just return v.
        Quaternion qv = new Quaternion(0, v.x, v.y, v.z);
        Quaternion res = this.multiply(qv).multiply(this.conjugate());
        return new Vectors.vec3(res.i, res.j, res.k);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Information Calculators ">
    /**
     * Calculates and returns the magnitude of this quaternion.
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
    public float magnitudeSquared() { return s*s + i*i + j*j + k*k; }
    
    /**
     * Calculates the polar form of this quaternion and returns it as
     * a float array in the form of { magnitude, theta, unit i, unit j, unit k }
     * 
     * @return The size 5 float array with the components.
     */
    public float[] getPolarForm() {
        float mag = this.magnitude();
        if (mag == 0) return new float[] { 0, 0, 0, 0, 0 };
        float theta = (float) Math.acos(s / mag); 
        
        float vMag = (float) Math.sqrt(i*i + j*j + k*k);
        
        float ui = i / vMag, uj = j / vMag, uk = k / vMag;
        
        return new float[] { mag, theta, ui, uj, uk };
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    /**
     * Negates every component.
     * 
     * @return A new quaternion equal to ( -s , -i , -j , -k ) .
     */
    public Quaternion negate() { return this.scale(-1.0f); }
    /**
     * Calculates the magnitude of this quaternion and creates a new one
     * with each component being divided by the magnitude.
     * 
     * @return A new quaternion that has been normalized.
     */
    public Quaternion normalize() {
        float l = this.magnitude();
        if (l == 0) return new Quaternion(this);
        return new Quaternion(s / l, i / l, j / l, k / l);
    }
    /**
     * Calculates the inverse of this quaternion and creates a new quaternion that's the inverse.
     * 
     * @return A new quaternion that's the inverse of this one.
     */
    public Quaternion inverse() {
        float lsqrd = this.magnitudeSquared();
        if (lsqrd == 0) throw new ArithmeticException("Cannot invert a zero quaternion");
        Quaternion conj = conjugate();
        return new Quaternion(conj.s / lsqrd, conj.i / lsqrd, conj.j / lsqrd, conj.k / lsqrd);
    }
    /**
     * Negates only the imaginary components.
     * 
     * @return A new quaternion equal to ( s , -i , -j , -k ) .
     */
    public Quaternion conjugate() { return this.scaleImag(-1.0f); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" String Methods & Constructor ">
    @Override
    public String toString() { return "{" + s + ":" + i + ":" + j + ":" + k + "}"; }
    public Quaternion(String s) { this(s.trim().substring(1, s.length() - 2).split("[,\\:]")); }
    public Quaternion(String[] s) throws NumberFormatException {
        try {
            this.s = Float.parseFloat(s[0].trim());
            this.i = Float.parseFloat(s[1].trim());
            this.j = Float.parseFloat(s[2].trim());
            this.k = Float.parseFloat(s[3].trim());
        } catch (NumberFormatException e) { 
            throw new NumberFormatException(
                    "Error parsing Quaternion from String ...\n" +
                     e.getMessage()
            );
        } 
    }
    public String toStringImag() { return "{" + i + ":" + j + ":" + k + "}"; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Equal Operators ">
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quaternion)) return false;
        if (this == obj) return true;
        Quaternion q = (Quaternion) obj;
        return s == q.s && i == q.i && j == q.j && k == q.k; 
    }
    /**
     * Compares if two quaternion are approximately equal.
     * 
     * @param o     The other quaternion.
     * @param eps   Epsilon, how far away each component can be.
     * @return      True if all components differ by at most eps.
     */
    public boolean epsilonEquals(Quaternion o, float eps) {
        if (o == null) return false;
        return Math.abs(s - o.s) < eps &&
               Math.abs(i - o.i) < eps &&
               Math.abs(j - o.j) < eps &&
               Math.abs(k - o.k) < eps;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Overrides ">
    @Override
    public int hashCode() { return java.util.Objects.hash(s, i, j, k); }
    @Override
    public int compareTo(Quaternion o) { return Float.compare(this.magnitudeSquared(), o.magnitudeSquared()); }
    //</editor-fold>
}