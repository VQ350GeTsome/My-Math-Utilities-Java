package Vectors;

/**
 * A two component vector class, with many common operations & operators.
 * 
 * @author Harrison Davis
 */
public class vec2 implements Comparable<vec2> {
    
    //The two components.
    public float x, y;
    
    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    /**
     * Default constructor. Components initialized at 0.
     */
    public vec2() { x = 0; y = 0; }
    /**
     * Constructor that fills all components with the input.
     * 
     * @param w What each component will be ( w , w ) .
     */
    public vec2(float w) { x = w; y = w; }
    /**
     * Full explicit constructor.
     * 
     * @param x The x component.
     * @param y The y component.
     */
    public vec2(float x, float y) { this.x = x; this.y = y; }
    /**
     * Copy constructor.
     * 
     * @param v The vec2 to be copied.
     */
    public vec2(vec2 v) { x = v.x; y = v.y; }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Simple Scalar Operators ">
    /**
     * Adds a scalar to each component.
     * 
     * @param f The scalar to add.
     * @return A new vector equal to ( x + f , y + f ) .
     */
    public vec2 add(float f) { return new vec2 ( x + f , y + f ); }
    /**
     * Subtracts a scalar from each component.
     * 
     * @param f The subtrahend scalar.
     * @return A new vector equal to ( x - f , y - f ) .
     */
    public vec2 subtract(float f) { return this.add( -f ); }
    /**
     * Scales ( multiplies ) each component by a scalar.
     * 
     * @param f The scalar to scale
     * @return A new vector equal to ( x * f , y * f ) .
     */
    public vec2 scale(float f) { return new vec2( x * f , y * f  ); }
    /**
     * Divides each component by a scalar.
     * 
     * @param f The dividend scalar.
     * @return A new vector equal to ( x / f , y / f ) .
     */
    public vec2 divide(float f) { return this.scale( 1.0f / f ); }
    /**
     * Raises each component by a scalar.
     * 
     * @param f The scalar to use as the exponent.
     * @return A new vector equal to ( x^f , y^f ) .
     */
    public vec2 pow(float f) { return new vec2((float) Math.pow(x, f), (float) Math.pow(y, f)); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Vector Operators ">
    /**
     * Component wise addition.
     * 
     * @param o The other vector to add.
     * @return A new vector equal to ( x + o.x , y + o.y ) .
     */
    public vec2 add(vec2 o) { return new vec2( x + o.x , y + o.y ); }
    /**
     * Component wise subtraction.
     * 
     * @param o The subtrahend vector.
     * @return A new vector equal to ( x - o.x , y - o.y ) .
     */
    public vec2 subtract(vec2 o) { return this.add(o.negate()); }
    /**
     * Multiplies a vector with another. ( Per-component / Hadamard product )
     * 
     * @param o The multiplicator vector.
     * @return A new vector equal to ( x * o.x , y * o.y ) .
     */
    public vec2 multiply(vec2 o) { return new vec2( x * o.x , y * o.y ); }
    /**
     * Divideds a vector by another ( Per-component / Hadamard quotient )
     * 
     * @param o The dividend.
     * @return A new vector equal to ( x / o.x , y / o.y )
     */
    public vec2 divide(vec2 o) { return this.multiply(o.reciprocal()); }
    /**
     * Raises each component by the corresponding component of the input vector.
     * 
     * @param o The vector that'll be used as the exponent.
     * @return A new vector equal to ( x^o.x , y^o.y ) .
     */
    public vec2 pow(vec2 o) { return new vec2((float) Math.pow(x, o.x), (float) Math.pow(y, o.y)); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Scalar Operators ">
    /**
     * Component-wise max ( Java's % operator ) .
     * 
     * @param m The dividend.
     * @return A new vector that is equal to ( x % m , y % m ) .
     */
    public vec2 remainder(float m) { return new vec2(x % m, y % m); }
    /**
     * Component-wise mathematical modulus ( Always positive ) .
     * 
     * @param m The dividend.
     * @return A new vector equal to ( x mod m , y mod m ) .
     */
    public vec2 modulus(float m) { 
        return new vec2(
                ((x % m) + m) % m,
                ((y % m) + m) % m
        );
    }
    /**
     * Component-wise maximum operation.
     * 
     * @param f The scalar.
     * @return A new vector that's equal to ( max(x , f) , max(y , f) ) .
     */
    public vec2 max(float f) { return new vec2( Math.max(x, f) , Math.max(y, f)  ); }
    /**
     * Component-wise minimum operation.
     * 
     * @param f The scalar.
     * @return A new vector that's equal to ( min(x , f) , min(y , f) ) .
     */
    public vec2 min (float f) { return new vec2( Math.min(x, f) , Math.min(y, f) ); }
    /**
     * Component-wise clamping operation.
     * Throws an error is h is less than l.
     * 
     * @param l The lowest allowed value.
     * @param h The highest allowed value.
     * @return A new vector where each component is within [l, h].
     */
    public vec2 clamp(float l, float h) { 
        if (h < l) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value ... " + h + " < " + l);
        return this.max(l).min(h); 
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Vector Operators ">
    //Object methods
    /**
     * Per-component maximum against another vector.
     * 
     * @param o The other vector whose components will be used.
     * @return A new vector that's equal to ( max(x , o.x) , max(y , o.y) ) .
     */
    public vec2 max(vec2 o) { return new vec2(Math.max(x, o.x), Math.max(y, o.y)); }
    /**
     * Per-component minimum against another vector.
     * 
     * @param o The other vector whose components will be used.
     * @return A new vector that's equal to ( min(x , o.x) , min(y , o.y) ) .
     */
    public vec2 min(vec2 o) { return new vec2(Math.min(x, o.x), Math.min(y, o.y)); }
    /**
     * Per-component clamping operation.
     * Throws an error if any component of h is less 
     * than the corresponding component of l. For example
     * if you try to clamp some vector in between [ (1, 2) , (0 , 3) ]
     * this will throw an error because l.x > h.x .
     * 
     * @param l The other vector whose components will be used for the low.
     * @param h The other vector whose components will be used for the high.
     * @return A new vector where each component is within [l, h].
     */
    public vec2 clamp(vec2 l, vec2 h) {
        if (h.x < l.x) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.x + " < " + l.x);
        if (h.y < l.y) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.y + " < " + l.y);
        return this.max(l).min(h);
    }
    
    /**
     * Calculates the dot product of this vector and another.
     * 
     * @param o The other vector.
     * @return Returns a float that is equal to ( x * o.x + y * o.y ) .
     */
    public float dot(vec2 o) { return x * o.x + y * o.y; } 
    
    /**
     * Weighted average of two vectors.
     * 
     * @param o The other vector.
     * @param w The weight. [0 , 1]. 0 = this, 1 = other.
     * @return A new vector that's the weighted average of the two.
     */
    public vec2 blend(vec3 o, float w) {
        //Clamp the weight
        w = Math.min(w, 1.0f);
        w = Math.max(w, 0.0f);
        
        float nx = (1 - w) * x + w * o.x;
        float ny = (1 - w) * y + w * o.y;
        return new vec2(nx, ny); 
    }  
    /**
     * Weighted average of two vectors.
     * 
     * @param o The other vector.
     * @param w The weight. [0 , 1]. 0 = this, 1 = other.
     * @return A new vector that's the weighted average of the two.
     */
    public vec2 wAvg(vec3 o, float w) { return blend(o, w); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Information Calculators ">
    /**
     * Calculates the average of each component.
     * 
     * @return The average as a float.
     */
    public float average() { return (x + y) / 2.0f; }

    /**
     * Calculates the distance between this vector and another.
     * 
     * @param o The other vector.
     * @return The distance between the two vectors.
     */
    public float getDist(vec2 o) { return (float) Math.sqrt(getDistSqrd(o)); }
    /**
     * Calculates the distance between this vector and another, but does NOT
     * take it's square root leading to a cheaper calculation.
     * 
     * @param o The other vector
     * @return The distance between the two vectors squared.
     */
    public float getDistSqrd(vec2 o) { return (float) (Math.pow(o.x - x, 2) + Math.pow(o.y - y, 2)); }
    
    /**
     * @return The length of this vector. ( The square root of the sum of each component squared )
     */
    public float length() { return (float) Math.sqrt(lengthSquared()); }
    /**
     * @return The length of this vector without taking the square root. Which is effectively
     * the sum of each component squared. This is a cheaper calculation.
     */
    public float lengthSquared() { return x*x + y*y; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    /**
     * @return A new vector that's the absolute value of this vector.
     */
    public vec2 abs() { return new vec2(Math.abs(x), Math.abs(y)); }
    /**
     * @return A new vector that is equal to ( -x , -y ) .
     */
    public vec2 negate() { return this.scale(-1.0f); }
    /**
     * @return A new vector equal to ( 1/x , 1/y ) .
     */
    public vec2 reciprocal() { return new vec2(1/x, 1/y); }
    
    /**
     * Calculates the length of this vector and divides each 
     * component by the length. If the length is 0 it returns
     * ( 0 , 0 , 0 ) .
     * 
     * @return A new vector that's this vector but normalized.
     */
    public vec2 normalize(){
        float length = length();
        if (length == 0) { return new vec2(); }
        return divide(length);
    }
   
    /**
     * Default round method. Calls & returns round(0).
     * 
     * @return A new vector where each component is rounded to the nearest whole number.
     */
    public vec2 round() { return round(0); }
    /**
     * Rounds each component to some amount of places.
     * 
     * @param places The amount of places to round to.
     * @return A new vector with each component rounded.
     */
    public vec2 round(int places) {
        int q = 1;
        for (int i = 0; places > i; i++) q *= 10;
        
        float nx = x, ny = y;
        
        nx = Math.round(nx * q) / (float) q;
        ny = Math.round(ny * q) / (float) q;
        
        return new vec2(nx, ny);
    }
    
    /**
     * Rotates this vector by theta (in radians)
     * 
     * @param thetaRadians The amount of radians to rotate this vector by.
     * @return A new vector that's been rotated.
     */
    public vec2 rotate(float thetaRadians) {
        if (this.lengthSquared() == 0) return this;
        float cs = (float) Math.cos(thetaRadians), sn = (float) Math.sin(thetaRadians);
        return new vec2(x * cs - y * sn, x * sn + y * cs);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Equal Operators ">
    @Override
    public boolean equals(Object obj) { 
        if (!(obj instanceof vec2)) return false;
        if (this == obj) return true;
        vec2 o = (vec2) obj;
        return x == o.x && y == o.y; 
    }
    /**
     * Compares if two vectors are approximately equal.
     * 
     * @param o     The other vector.
     * @param eps   Epsilon, how far away each component can be.
     * @return      True if all components differ by at most eps.
     */
    public boolean epsilonEquals(vec2 o, float eps) {
        if (o == null) return false;
        return Math.abs(x - o.x) < eps &&
               Math.abs(y - o.y) < eps;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Overrides ">
    @Override
    public int hashCode() { return java.util.Objects.hash(x, y); }
    @Override
    public int compareTo(vec2 o) { return Float.compare(this.lengthSquared(), o.lengthSquared()); }
    //</editor-fold>
    
}
