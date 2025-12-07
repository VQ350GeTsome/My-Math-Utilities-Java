package Vectors;

public class bvec3 implements Comparable<bvec3> {

    //The three booleans.
    boolean a, b, c;

    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    /**
     * Default constructor. All components are false.
     */
    public bvec3() { a = false; b = false; c = false; }
    /**
     * Constructor that fills all components with the input.
     * 
     * @param w The boolean value that the fields will be set to.
     */
    public bvec3(boolean w) { a = w; b = w; c = w; }
    /**
     * Full explicit constructor.
     * 
     * @param a The first boolean value a.
     * @param b The second boolean value b.
     * @param c The third boolean value c.
     */
    public bvec3(boolean a, boolean b, boolean c) { this.a = a; this.b = b; this.c = c; }
    /**
     * Copy constructor.
     * 
     * @param v The bvec3 to be copied.
     */
    public bvec3(bvec3 v) { a = v.a; b = v.b; c = v.c; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Operators ">
    /**
     * Component-wise and operator. Performs (P ∧ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a ∧ q.a , b ∧ q.b , c ∧ q.c ) .
     */
    public bvec3 and(bvec3 q) { return new bvec3(a && q.a, b && q.b, c && q.c); }
    /**
     * Component-wise nand operator. Performs ¬(P ∧ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ∧ q.a) , ¬(b ∧ q.b) , ¬(c ∧ q.c) ) .
     */
    public bvec3 nand(bvec3 q) { return this.and(q).not(); }
    
    /**
     * Component-wise or operator. Performs (P ∨ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a ∨ q.a , b ∨ q.b , c ∨ q.c ) .
     */
    public bvec3 or(bvec3 q) { return new bvec3(a || q.a, b || q.b, c || q.c); }
    /**
     * Component-wise nor operator. Performs ¬(P ∨ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ∨ q.a) , ¬(b ∨ q.b) , ¬(c ∨ q.c) ) .
     */
    public bvec3 nor(bvec3 q) { return this.or(q).not(); }
    
    /**
     * Component-wise xor operator. Performs (P ⊕ Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a ⊕ q.a , b ⊕ q.b , c ⊕ q.c ) .
     */
    public bvec3 xor(bvec3 q) { return new bvec3(a ^ q.a, b ^ q.b, c ^ q.c); }
    /**
     * Component-wise xnor operator. Performs ¬(P ⊕ Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ⊕ q.a) , ¬(b ⊕ q.b) , ¬(c ⊕ q.c) ) .
     */
    public bvec3 xnor(bvec3 q) { return this.xor(q).not(); }
    
    /**
     * Component-wise implies operator. Performs (P → Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a → q.a , b → q.b , c → q.c )
     */
    public bvec3 implies(bvec3 q) { return this.not().or(q); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Reductions ">
    /**
     * @return True if any component is true, false if all components are false.
     */
    public boolean any() { return a || b || c; }
    /**
     * @return True if all components are true, false if any component is false.
     */
    public boolean all() { return a && b && c; }
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
    public boolean equals(bvec3 q) { return a == q.a && b == q.b && c == q.c; }
    /**
     * @return The amount of true components. For example if (a = true) & (b = false) & (c = true) it returns 2.
     */
    public int totalTrue() { return ((a)?1:0) + ((b)?1:0) + ((c)?1:0); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    /**
     * Flips each component of this vector.
     * 
     * @return A new vector equal to ( ¬a , ¬b , ¬c ) .
     */
    public bvec3 not() { return new bvec3(!a, !b, !c); }
    /**
     * Flips the components of this vector.
     * 
     * @return A new vector equal to ( c, b , a ) .
     */
    public bvec3 flip() { return new bvec3(c, b, a); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Overrides ">
    @Override
    public String toString() { return "( " + ((a)?"1":"0") + " , " + ((b)?"1":"0") + " , " + ((c)?"1":"0") + ")"; }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof bvec3)) return false;
        if (this == obj) return true;
        bvec3 o = (bvec3) obj;
        return this.equals(o);
    }
    @Override 
    public int hashCode() { 
        int ha = a ? 1 : 0;
        int hb = b ? 1 : 0; 
        return ha | (hb << 1); 
    }
    @Override
    public int compareTo(bvec3 q) { return Integer.compare(this.totalTrue(), q.totalTrue()); }
    //</editor-fold>
    
}
