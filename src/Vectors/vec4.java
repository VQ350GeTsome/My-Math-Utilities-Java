package Vectors;

/**
 * A four component vector class, with many common operations & operators.
 * 
 * @author Harrison Davis
 */
public class vec4 implements Comparable<vec4> {

    //The four components
    public float x, y, z, w;
    
    //<editor-fold defaultstate="collapsed" desc=" Regular Constructors ">
    /**
     * Default constructor. ( 0 , 0 , 0 , 0 ) .
     */
    public vec4() { x = 0.0f; y = 0.0f; z = 0.0f; w = 0.0f; }
    /**
     * Constructor that fills all components with the one input.
     * 
     * @param a What each component will be ( a , a , a , a ) .
     */
    public vec4(float a) { x = a; y = a; z = a; w = a; }
    /**
     * Full explicit constructor with x , y , & z components { x , y , z , w } .
     * 
     * @param x The x component.
     * @param y The y component.
     * @param z The z component.
     * @param w The w component.
     */
    public vec4(float x, float y, float z, float w)  { this.x = x; this.y = y; this.z = z; this.w = w; }
    
    /**
     * Constructor using two vec2s.
     * 
     * @param xy The vec2 that'll be used for the x & y components.
     * @param zw The vec2 that'll be used for the z & w components.
     */
    public vec4(vec2 xy, vec2 zw) { x = xy.x; y = xy.y; z = zw.x; w = zw.y; }
    
    /**
     * Constructor using a vec2.
     * 
     * @param xy The vec2 that'll be used for the x & y components.
     * @param z The z component.
     * @param w The w component.
     */
    public vec4(vec2 xy, float z, float w) { x = xy.x; y = xy.y; this.z = z; this.w = w; }
    /**
     * Constructor using a vec2.
     * 
     * @param x The x component.
     * @param yz The vec2 that'll be used for the y & z components.
     * @param w The w component.
     */
    public vec4(float x, vec2 yz, float w) { this.x = x; y = yz.x; z = yz.y; this.w = w; }
    /**
     * Constructor using a vec2.
     * 
     * @param x The x component.
     * @param y The y component.
     * @param zw The vec2 that'll be used for the z & w components.
     */
    public vec4(float x, float y, vec2 zw) { this.x = x; this.y = y; z = zw.x; w = zw.y; }
    
    /**
     * Constructor using a vec3.
     * 
     * @param xyz The vec3 that'll be used for the x, y, & z components.
     * @param w The w component.
     */
    public vec4(vec3 xyz, float w) { x = xyz.x; y = xyz.y; z = xyz.z; this.w = w; }
    /**
     * Constructor using a vec3.
     * 
     * @param x The x component.
     * @param yzw The vec3 that'll be used for the y, z, & w components.
     */
    public vec4(float x, vec3 yzw) { this.x = w; y = yzw.x; z = yzw.y; w = yzw.z; }
        
    /**
     * Copy constructor.
     * @param copy The vec3 to be copied
     */
    public vec4(vec4 copy) { x = copy.x; y = copy.y; z = copy.z; w = copy.w; }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Simple Scalar Operators ">
    /**
     * Adds a scalar to each component.
     * 
     * @param f The scalar to add.
     * @return A new vector equal to ( x + f , y + f , z + f ) .
     */
    public vec4 add(float f) { return new vec4(x+f, y+f, z+f, w+f); }
    /**
     * Subtracts a scalar from each component.
     * 
     * @param f The subtrahend scalar.
     * @return A new vector equal to ( x - f , y - f , z - f ) .
     */
    public vec4 subtract(float f) { return this.add( -f ); }
    /**
     * Scales ( multiplies ) each component by a scalar.
     * 
     * @param f The scalar to scale
     * @return A new vector equal to ( x * f , y * f , z * f ) .
     */
    public vec4 scale(float f) { return new vec4(x*f, y*f, z*f, w*f); }
    /**
     * Divides each component by a scalar.
     * 
     * @param f The dividend scalar.
     * @return A new vector equal to ( x / f , y / f , z / f ) .
     */
    public vec4 divide(float f) { return this.scale( 1.0f / f ); }
    /**
     * Raises each component by a scalar.
     * 
     * @param f The scalar to use as the exponent.
     * @return A new vector equal to ( x^f , y^f , z^f ) .
     */
    public vec4 pow(float f) { 
        return new vec4(
                (float) Math.pow(x, f), 
                (float) Math.pow(y, f), 
                (float) Math.pow(z, f), 
                (float) Math.pow(w, f)
        ); 
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Simple Vector Operators ">
    /**
     * Component wise addition.
     * 
     * @param o The other vector to add.
     * @return A new vector equal to ( x + o.x , y + o.y , z + o.z ) .
     */
    public vec4 add(vec4 o) { return new vec4(x+o.x, y+o.y, z+o.z, w+o.w); }
    /**
     * Component wise subtraction.
     * 
     * @param o The subtrahend vector.
     * @return A new vector equal to ( x - o.x , y - o.y , z - o.z ) .
     */
    public vec4 subtract(vec4 o) { return this.add(o.negate()); }
    /**
     * Multiplies a vector with another. ( Per-component / Hadamard product )
     * 
     * @param o The multiplicator vector.
     * @return A new vector equal to ( x * o.x , y * o.y , z * z.y ) .
     */
    public vec4 multiply(vec4 o) { return new vec4(x*o.x, y*o.y, z*o.z, w*o.w); }
    /**
     * Divideds a vector by another ( Per-component / Hadamard quotient )
     * 
     * @param o The dividend.
     * @return A new vector equal to ( x / o.x , y / o.y , z / o.z )
     */
    public vec4 divide(vec4 o) { return this.multiply(o.reciprocal()); }
    /**
     * Raises each component by the corresponding component of the input vector.
     * 
     * @param o The vector that'll be used as the exponent.
     * @return A new vector equal to ( x^o.x , y^o.y , z^o.z ) .
     */
    public vec4 pow(vec4 o) { 
        return new vec4(
                (float) Math.pow(x, o.x), 
                (float) Math.pow(y, o.y), 
                (float) Math.pow(z, o.z),
                (float) Math.pow(w, o.w)
        ); 
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Special Scalar Operators ">
    /**
     * Component-wise max ( Java's % operator ) .
     * 
     * @param m The dividend.
     * @return A new vector that is equal to ( x % m , y % m , z % m ) .
     */
    public vec4 remainder(float m) { return new vec4(x%m, y%m, z%m, w%m); }
    /**
     * Component-wise mathematical modulus ( Always positive ) .
     * 
     * @param m The dividend.
     * @return A new vector equal to ( x mod m , y mod m , z mod m ) .
     */
    public vec4 modulus(float m) { 
        return new vec4(
                ((x % m) + m) % m,
                ((y % m) + m) % m,
                ((z % m) + m) % m,
                ((w % m) + m) % m
        );
    }
    /**
     * Component-wise maximum operation.
     * 
     * @param f The scalar.
     * @return A new vector that's equal to ( max(x , f) , max(y , f) , max(z , f) ) .
     */
    public vec4 max(float f) { return new vec4(Math.max(x, f), Math.max(y, f), Math.max(z, f), Math.max(w, f)); }
    /**
     * Component-wise minimum operation.
     * 
     * @param f The scalar.
     * @return A new vector that's equal to ( min(x , f) , min(y , f) , min(z , f) ) .
     */
    public vec4 min (float f) { return new vec4( Math.min(x,f), Math.min(y,f), Math.min(z,f), Math.min(w,f)); }
    /**
     * Component-wise clamping operation.
     * Throws an error is h is less than l.
     * 
     * @param l The lowest allowed value.
     * @param h The highest allowed value.
     * @return A new vector where each component is within [l, h].
     */
    public vec4 clamp(float l, float h) { 
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
     * @return A new vector that's equal to ( max(x , o.x) , max(y , o.y) , max(z , o.z) ) .
     */
    public vec3 max(vec3 o) { return new vec3(Math.max(x, o.x), Math.max(y, o.y), Math.max(z, o.z)); }
    /**
     * Per-component minimum against another vector.
     * 
     * @param o The other vector whose components will be used.
     * @return A new vector that's equal to ( min(x , o.x) , min(y , o.y) , min(z , o.z) ) .
     */
    public vec3 min(vec3 o) { return new vec3(Math.min(x, o.x), Math.min(y, o.y), Math.min(z, o.z)); }
    /**
     * Per-component clamping operation.
     * Throws an error if any component of h is less 
     * than the corresponding component of l. For example
     * if you try to clamp some vector in between [ (1, 2, 3) , (0 , 3, 5) ]
     * this will throw an error because l.x > h.x .
     * 
     * @param l The other vector whose components will be used for the low.
     * @param h The other vector whose components will be used for the high.
     * @return A new vector where each component is within [l, h].
     */
    public vec3 clamp(vec3 l, vec3 h) {
        if (h.x < l.x) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.x + " < " + l.x);
        if (h.y < l.y) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.y + " < " + l.y);
        if (h.z < l.z) throw new ArithmeticException("Highest allowed value cannot be less than the lowest allowed value... " + h.z + " < " + l.z);
        return this.max(l).min(h);
    }
    
    /**
     * Calculates the cross product between this vector and another.
     * 
     * @param o The other vector
     * @return A new vector orthogonal to both this and the other vector (o)
     */
    public vec3 cross(vec3 o){
        return new vec3(
                y * o.z - z * o.y,
                z * o.x - x * o.z,
                x * o.y - y * o.x
        );
    }
    /**
     * Calculates the dot product of this vector and another.
     * 
     * @param o The other vector.
     * @return Returns a float that is equal to ( x * o.x + y * o.y + z * o.z ) .
     */
    public float dot(vec3 o) { return x * o.x + y * o.y + z * o.z; } 
    
    /**
     * Weighted average of two vectors.
     * 
     * @param o The other vector.
     * @param w The weight. [0 , 1]. 0 = this, 1 = other.
     * @return A new vector that's the weighted average of the two.
     */
    public vec3 blend(vec3 o, float w) {
        //Clamp the weight
        w = Math.min(w, 1.0f);
        w = Math.max(w, 0.0f);
        
        float nx = (1 - w) * x + w * o.x;
        float ny = (1 - w) * y + w * o.y;
        float nz = (1 - w) * z + w * o.z;
        return new vec3(nx, ny, nz); 
    }  
    /**
     * Weighted average of two vectors.
     * 
     * @param o The other vector.
     * @param w The weight. [0 , 1]. 0 = this, 1 = other.
     * @return A new vector that's the weighted average of the two.
     */
    public vec3 wAvg(vec3 o, float w) { return blend(o, w); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Information Calculators ">
    /**
     * Calculates the average of each component.
     * 
     * @return The average as a float.
     */
    public float average() { return (x + y + z) / 3.0f; }

    /**
     * Calculates the distance between this vector and another.
     * 
     * @param o The other vector.
     * @return The distance between the two vectors.
     */
    public float getDist(vec3 o) { return (float) Math.sqrt(getDistSqrd(o)); }
    /**
     * Calculates the distance between this vector and another, but does NOT
     * take it's square root leading to a cheaper calculation.
     * 
     * @param o The other vector
     * @return The distance between the two vectors squared.
     */
    public float getDistSqrd(vec3 o) { return (float) (Math.pow(o.x - x, 2) + Math.pow(o.y - y, 2) + Math.pow(o.z - z, 2)); }
    
    /**
     * @return The length of this vector. ( The square root of the sum of each component squared )
     */
    public float length() { return (float) Math.sqrt(lengthSqrd()); }
    /**
     * @return The length of this vector without taking the square root. Which is effectively
     * the sum of each component squared. This is a cheaper calculation.
     */
    public float lengthSqrd() { return x*x + y*y + z*z; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    /**
     * @return A new vector that's the absolute value of this vector.
     */
    public vec4 abs() { return new vec4(Math.abs(x), Math.abs(y), Math.abs(z), Math.abs(w)); }
    /**
     * @return A new vector that is equal to ( -x , -y , -z ) .
     */
    public vec4 negate() { return this.scale(-1.0f); }
    /**
     * @return A new vector equal to ( 1/x , 1/y , 1/z ) .
     */
    public vec4 reciprocal() { return new vec4(1/x, 1/y, 1/z, 1/w); }
    
    /**
     * Calculates the length of this vector and divides each 
     * component by the length. If the length is 0 it returns
     * ( 0 , 0 , 0 ) .
     * 
     * @return A new vector that's this vector but normalized.
     */
    public vec4 normalize(){
        float length = length();
        if (length == 0) { return new vec4(); }
        return divide(length);
    }
   
    /**
     * Default round method. Calls & returns round(0).
     * 
     * @return A new vector where each component is rounded to the nearest whole number.
     */
    public vec4 round() { return round(0); }
    /**
     * Rounds each component to some amount of places.
     * 
     * @param places The amount of places to round to.
     * @return A new vector with each component rounded.
     */
    public vec4 round(int places) {
        int q = 1;
        for (int i = 0; places > i; i++) q *= 10;
        
        float nx = x, ny = y, nz = z, nw = w;
        
        nx = Math.round(nx * q) / (float) q;
        ny = Math.round(ny * q) / (float) q;
        nz = Math.round(nz * q) / (float) q;
        nw = Math.round(nw * q) / (float) q;
        
        return new vec4(nx, ny, nz, nw);
    }
    
    /**
     * Flips the components. 
     * This is equivalent to .wzyx().
     * 
     * @return A new vector equal to ( w , z , y , x ) .
     */
    public vec4 flip() { return this.wzyx(); }
    /**
     * Shifts all components to the right. 
     * This is equivalent to .wxyz().
     * 
     * @return A new vector equal to ( w , x , y , z ) .
     */
    public vec4 shiftRight() { return this.wxyz(); }
    /**
     * Shifts all components to the left. 
     * This is equivalent to .yzwx().
     * 
     * @return A new vector equal to ( y , z , w , x ) .
     */
    public vec4 shiftLeft() { return this.yzwx(); }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" java.awt.Color Methods & Constructors ">
    /**
     * From java.awt.Color constructor. { a , r , g , b }
     * 
     * @param c a java.awt.Color that'll be used to create a new vec4
     */
    public vec4(java.awt.Color c) { x = c.getAlpha(); y = c.getRed(); z = c.getGreen(); w = c.getBlue(); }
    /**
     * Calculates a vector that's this clamped between [0, 255] 
     * and then creates a java.awt.Color object using 
     * x as alpha, y as red, z as green, & w as blue.
     * 
     * @return The java.awt.Color object.
     */
    public java.awt.Color toAwtColor() { 
        vec4 c = clamp(0, 255);
        return new java.awt.Color((int) c.x, (int) c.y, (int) c.z, (int) c.w);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" String Methods & Constructors ">
    @Override
    public String toString() { return "(" + x + " : " + y + " : " + z + " : " + w + " )"; }
    public String[] toStringArray() { return new String[] { ""+x, ""+y, ""+z, ""+w }; }
    /**
     * Turns the .toString() method back into a vec4 object
     * @param vector A String in the form {x:y:z:w} or (x, y, z, w) .
     */
    public vec4(String vector) { this(vector.trim().replaceAll("[(){}]", "").split("[,\\:]")); }
    /**
     * Turns the .toStringArray method back into a vec3 object
     * @param comp A length 3 array of Strings that are floats
     */
    public vec4(String[] comp) {
        try {
            x = Float.parseFloat(comp[0].trim());
            y = Float.parseFloat(comp[1].trim());
            z = Float.parseFloat(comp[2].trim());
            w = Float.parseFloat(comp[3].trim());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing string for vec4 ...");
            System.err.println(e.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Swizzle Methods ">
    /**
     * Swizzle method to extract the x & y components.
     * @return The x & y components as a vec2.
     */
    public vec2 xy() { return new vec2(x, y); }
    /**
     * Swizzle method to extract the x & z components.
     * 
     * @return The x & z components as a vec2.
     */
    public vec2 xz() { return new vec2(x, z); }
    /**
     * Swizzle method to extract the x & w components.
     * 
     * @return The x & w components as a vec2.
     */
    public vec2 xw() { return new vec2(x, w); }
    
    /**
     * Swizzle method to extract the y & x components.
     * 
     * @return The y & x components as a vec2.
     */
    public vec2 yx() { return new vec2(y, x); }
    /**
     * Swizzle method to extract the y & z components.
     * 
     * @return The z & z components as a vec2.
     */
    public vec2 yz() { return new vec2(y, z); }
    /**
     * Swizzle method to extract the y & w components.
     * 
     * @return The y & w components as a vec2.
     */
    public vec2 yw() { return new vec2(y, w); }
    
    /**
     * Swizzle method to extract the z & x components.
     * 
     * @return The z & x components as a vec2.
     */
    public vec2 zx() { return new vec2(z, x); }
    /**
     * Swizzle method to extract the z & y components.
     * 
     * @return The z & y components as a vec2.
     */
    public vec2 zy() { return new vec2(z, y); }
    /**
     * Swizzle method to extract the z & w components.
     * 
     * @return The z & w components as a vec2.
     */
    public vec2 zw() { return new vec2(z, w); }
    
    /**
     * Swizzle method to extract the w & x component.
     * 
     * @return The w & x components as a vec2.
     */
    public vec2 wx() { return new vec2(w, x); }
    /**
     * Swizzle method to extract the w & y component.
     * 
     * @return The w & y component as a vec2.
     */
    public vec2 wy() { return new vec2(w, y); }
    /**
     * Swizzle method to extract the w & z component.
     * 
     * @return The w & z component as a vec2.
     */
    public vec2 wz() { return new vec2(w, z); }
    
    public vec3 xyz() { return new vec3(x, y, z); }
    public vec3 xyw() { return new vec3(x, y, w); }
    public vec3 xzy() { return new vec3(x, z, y); }
    public vec3 xzw() { return new vec3(x, z, w); }
    public vec3 xwz() { return new vec3(x, w, z); }
    public vec3 xwy() { return new vec3(x, w, y); }
    
    public vec3 yxz() { return new vec3(y, x, z); }
    public vec3 yxw() { return new vec3(y, x, w); }
    public vec3 yzx() { return new vec3(y, z, x); }
    public vec3 yzw() { return new vec3(y, z, w); }
    public vec3 ywx() { return new vec3(y, w, x); }
    public vec3 ywz() { return new vec3(y, w, z); }
    
    public vec3 zxy() { return new vec3(z, x, y); }
    public vec3 zxw() { return new vec3(z, x, w); }
    public vec3 zyx() { return new vec3(z, y, x); }
    public vec3 zyw() { return new vec3(z, y, w); }
    public vec3 zwx() { return new vec3(z, w, x); }
    public vec3 zwy() { return new vec3(z, w, y); }
    
    public vec3 wxy() { return new vec3(w, x, y); }
    public vec3 wxz() { return new vec3(w, x, z); }
    public vec3 wyx() { return new vec3(w, y, x); }
    public vec3 wyz() { return new vec3(w, y, z); }
    public vec3 wzx() { return new vec3(w, z, x); }
    public vec3 wzy() { return new vec3(w, z, y); }
    
    public vec4 wxyz() { return new vec4(w, x, y, z); }
    public vec4 wzyx() { return new vec4(w, z, y, x); }
    public vec4 yzwx() { return new vec4(y, z, w, x); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Miscellaneous ">
    /**
     * Generates a random vector where each component is random and between [-1, 1]
     * 
     * @return The new randomized vector.
     */
    public static vec4 getRandom() {
        return new vec4((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random())
                   .subtract(0.50f).scale(2.0f); 
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Equal Operators ">
    @Override
    public boolean equals(Object obj) { 
        if (!(obj instanceof vec4)) return false;
        if (this == obj) return true;
        vec4 o = (vec4) obj;
        return x == o.x && y == o.y && z == o.z && w == o.w; 
    }
    /**
     * Compares if two vectors are approximately equal.
     * 
     * @param o     The other vector.
     * @param eps   Epsilon, how far away each component can be.
     * @return      True if all components differ by at most eps.
     */
    public boolean epsilonEquals(vec4 o, float eps) {
        if (o == null) return false;
        return Math.abs(x - o.x) < eps &&
               Math.abs(y - o.y) < eps &&
               Math.abs(z - o.z) < eps &&
               Math.abs(w - o.w) < eps;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Overrides ">
    @Override
    public int hashCode() { return java.util.Objects.hash(x, y, z, w); }
    @Override
    public int compareTo(vec4 o) { return Float.compare(lengthSqrd(), o.lengthSqrd()); }
    //</editor-fold>
    
}