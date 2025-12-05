package ComplexNumbers;


public class Quaternion {
    
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
    public Quaternion(float s, Vectors.vec3 v) { this.s = s; i = v.x; j = v.y; k = v.z; }
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
    public float magnitude() { return (float) Math.sqrt(this.magnitude()); }
    public float magnitudeSquared() { return s*s + i*i + j*j + k*k; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    public Quaternion negate() { return this.scale(-1.0f); }
    public Quaternion normalize() {
        float l = this.magnitude();
        return new Quaternion(s / l, i / l, j / l, k / l);
    }
    public Quaternion inverse() {
        float normSq = this.magnitudeSquared();
        if (normSq == 0) throw new ArithmeticException("Cannot invert a zero quaternion");
        Quaternion conj = conjugate();
        return new Quaternion(conj.s / normSq, conj.i / normSq, conj.j / normSq, conj.k / normSq);
    }
    public Quaternion conjugate() { return new Quaternion(s, -i, -j, -k); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" String Methods & Constructor ">
    @Override
    public String toString() { return "{" + s + ":" + i + ":" + j + ":" + k + "}"; }
    public Quaternion(String s) {
        s = s.trim().substring(1, s.length() - 2);   //Trim off grouping character
        String[] comp = s.split("[,\\:]");           //Split by , or :
        
        if (comp.length != 4) {
            throw new IllegalArgumentException("Invalid Quaternion format: " + s);
        }
                
        // Parse and assign
        this.s = Float.parseFloat(comp[0].trim());
        this.i = Float.parseFloat(comp[1].trim());
        this.j = Float.parseFloat(comp[2].trim());
        this.k = Float.parseFloat(comp[3].trim());
    }
    public Quaternion(String[] s) {
        this.s = Float.parseFloat(s[0].trim());
        this.i = Float.parseFloat(s[1].trim());
        this.j = Float.parseFloat(s[2].trim());
        this.k = Float.parseFloat(s[3].trim());
    }
    public String toStringImag() { return "{" + i + ":" + j + ":" + k + "}"; }
    //</editor-fold>
}