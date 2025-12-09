package Vectors;

/**
 * Boolean vector class with most boolean operators.
 * Made of two components.
 * 
 * @author Harrison Davis
 */
public class bvec2 implements Comparable<bvec2> {

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
     * @param a The first boolean value, a.
     * @param b The second boolean value, b.
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
     * Component-wise and operator. Performs (P ∧ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a ∧ q.a , b ∧ q.b ) .
     */
    public bvec2 and(bvec2 q) { return new bvec2(a && q.a, b && q.b); }
    /**
     * Component-wise nand operator. Performs ¬(P ∧ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ∧ q.a) , ¬(b ∧ q.b) ) .
     */
    public bvec2 nand(bvec2 q) { return this.and(q).not(); }
    
    /**
     * Component-wise or operator. Performs (P ∨ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a ∨ q.a , b ∨ q.b ) .
     */
    public bvec2 or(bvec2 q) { return new bvec2(a || q.a, b || q.b); }
    /**
     * Component-wise nor operator. Performs ¬(P ∨ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ∨ q.a) , ¬(b ∨ q.b) ) .
     */
    public bvec2 nor(bvec2 q) { return this.or(q).not(); }
    
    /**
     * Component-wise xor operator. Performs (P ⊕ Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a ⊕ q.a , b ⊕ q.b ) .
     */
    public bvec2 xor(bvec2 q) { return new bvec2(a ^ q.a, b ^ q.b); }
    /**
     * Component-wise xnor operator. Performs ¬(P ⊕ Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ⊕ q.a) , ¬(b ⊕ q.b) ) .
     */
    public bvec2 xnor(bvec2 q) { return this.xor(q).not(); }
    
    /**
     * Component-wise implies operator. Performs (P → Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a → q.a , b → q.b )
     */
    public bvec2 implies(bvec2 q) { return this.not().or(q); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Reductions ">
    /**
     * @return True if any component is true, false if all components are false.
     */
    public boolean any() { return a || b; }
    /**
     * @return True if all components are true, false if any component is false.
     */
    public boolean all() { return a && b; }
    /**
     * @return True if all components are false, false if any component is true.
     */
    public boolean none() { return !any(); }
    /**
     * Checks if each component of this vector (P) is equal
     * to the correlated component of the other vector (Q)
     * 
     * @param q The other vector (Q).
     * @return True if they're all the same, false if at least one is different.
     */
    public boolean equals(bvec2 q) { return a == q.a && b == q.b; }
    /**
     * @return The amount of true components. For example if (a = true) & (b = false) it returns 1.
     */
    public int totalTrue() { return ((a)?1:0) + ((b)?1:0); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    /**
     * Flips each component of this vector.
     * 
     * @return A new vector equal to ( ¬a , ¬b ) .
     */
    public bvec2 not() { return new bvec2(!a, !b); }
    /**
     * Flips the components of this vector.
     * 
     * @return A new vector equal to ( b , a ) .
     */
    public bvec2 flip() { return new bvec2(b, a); }
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
    @Override
    public int compareTo(bvec2 q) { return Integer.compare(this.totalTrue(), q.totalTrue()); }
    //</editor-fold>
    
}