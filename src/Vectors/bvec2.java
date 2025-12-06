package Vectors;

public class bvec2 {

    //The two booleans.
    boolean a, b;

    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    /**
     * Default constructor. All components are false.
     */
    public bvec2() { a = false; b = false; }
    /**
     * Constructor that fills all components with the input.
     * 
     * @param w The boolean value that the fields will be set to.
     */
    public bvec2(boolean w) { a = w; b = w; }
    /**
     * Full explicit constructor.
     * 
     * @param a The first boolean value a.
     * @param b The second boolean value b.
     */
    public bvec2(boolean a, boolean b) { this.a = a; this.b = b; }
    /**
     * Copy constructor.
     * 
     * @param v The bvec2 to be copied.
     */
    public bvec2(bvec2 v) { a = v.a; b = v.b; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Operators ">
    /**
     * Component-wise and operator. 
     * 
     * @param q The other vector.
     * @return A new vector equal to ( a and q.a , b and q.b ) .
     */
    public bvec2 and(bvec2 q) { return new bvec2(a && q.a, b && q.b); }
    /**
     * Component-wise nand operator.
     * 
     * @param q The other vector.
     * @return A new vector equal to ( a nand q.a , b nand q.b ) .
     */
    public bvec2 nand(bvec2 q) { return this.and(q).not(); }
    
    /**
     * Component-wise or operator.
     * 
     * @param q The other vector.
     * @return A new vector equal to ( a or q.a , b or q.b ) .
     */
    public bvec2 or(bvec2 q) { return new bvec2(a || q.a, b || q.b); }
    /**
     * Component-wise nor operator.
     * 
     * @param q The other vector.
     * @return A new vector equal to ( a nor q.a , b nor q.b ) .
     */
    public bvec2 nor(bvec2 q) { return this.or(q).not(); }
    
    /**
     * Component wise xor operator.
     * 
     * @param q The other vector.
     * @return A new vector equal to ( a xor q.a , b xor q.b ) .
     */
    public bvec2 xor(bvec2 q) { return new bvec2(a ^ q.a, b ^ q.b); }
    /**
     * Component wise xnor operator.
     * 
     * @param q The other vector.
     * @return A new vector equal to ( a xnor q.a , b xnor q.b ) .
     */
    public bvec2 xnor(bvec2 q) { return this.xor(q).not(); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Reductions ">
    public boolean any() { return a || b; }
    public boolean all() { return a && b; }
    public boolean none() { return !any(); }
    public boolean equals(bvec2 o) { return a == o.a && b == o.b; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    public bvec2 not() { return new bvec2(!a, !b); }
    public bvec2 shift() { return new bvec2(b, a); }
    public bvec2 flip() { return shift(); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Overrides ">
    @Override
    public String toString() { return "( " + ((a)?"1":"0") + " , " + ((b)?"1":"0") + " )"; }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof bvec2)) return false;
        if (this == obj) return true;
        bvec2 o = (bvec2) obj;
        return this.equals(o);
    }
    @Override 
    public int hashCode() { 
        int ha = a ? 1 : 0;
        int hb = b ? 1 : 0; 
        return ha | (hb << 1); 
    }
    //</editor-fold>
    
}
