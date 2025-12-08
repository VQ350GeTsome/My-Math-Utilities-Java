package Vectors;

public class bvec4 implements Comparable<bvec4> {

    //The four booleans.
    boolean a, b, c, d;

    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    /**
     * Default constructor. All components are false.
     */
    public bvec4() { a = false; b = false; c = false; }
    /**
     * Constructor that fills all components with the input.
     * 
     * @param w The boolean value that the fields will be set to.
     */
    public bvec4(boolean w) { a = w; b = w; c = w; d = w; }
    /**
     * Full explicit constructor.
     * 
     * @param a The first boolean value, a.
     * @param b The second boolean value, b.
     * @param c The third boolean value, c.
     * @param d The fourth boolean value, d.
     */
    public bvec4(boolean a, boolean b, boolean c, boolean d) { this.a = a; this.b = b; this.c = c; this.d = d; }
    
    /**
     * Constructor using two bvec2s.
     * 
     * @param ab The bvec2 that'll be used for the a & b components.
     * @param cd The bvec2 that'll be used for the c & d components.
     */
    public bvec4(bvec2 ab, bvec2 cd) { a = ab.a; b = ab.b; c = cd.a; d = cd.b; }

    /**
     * Constructor using a bvec2.
     * 
     * @param ab The bvec2 that'll be used for the a & b component.
     * @param c The third boolean value, c.
     * @param d The fourth boolean value, d.
     */
    public bvec4(bvec2 ab, boolean c, boolean d) { a = ab.a; b = ab.b; this.c = c; this.d = d; }
    /**
     * Constructor using a bvec2.
     * 
     * @param a The first boolean value, a.
     * @param bc The bvec2 that'll be used for the b & c components.
     * @param d  The fourth boolean value, d.
     */
    public bvec4(boolean a, bvec2 bc, boolean d) { this.a = a; b = bc.a; c = bc.b; this.d = d; }
    /**
     * Constructor using a bvec2.
     * 
     * @param a The first boolean value, a.
     * @param b The second boolean value, b.
     * @param cd The bvec2 that'll be used for the c & d components.
     */
    public bvec4(boolean a, boolean b, bvec2 cd) { this.a = a; this.b = b; c = cd.a; d = cd.b; }
    
    /**
     * Constructor using a bvec3.
     * 
     * @param abc The bvec3 to be used for a, b, & c components.
     * @param d The fourth boolean value, d.
     */
    public bvec4(bvec3 abc, boolean d) { a = abc.a; b = abc.b; c = abc.c; this.d = d; }
    /**
     * Constructor using a bvec3. 
     * 
     * @param a The first boolean value, a.
     * @param bcd The bvec3 that'll be used for the b, c, & d components.
     */
    public bvec4(boolean a, bvec3 bcd) { this.a = a; b = bcd.a; c = bcd.b; d = bcd.c; }
    
    /**
     * Copy constructor.
     * 
     * @param v The bvec4 to be copied.
     */
    public bvec4(bvec4 v) { a = v.a; b = v.b; c = v.c; d = v.d; }
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
    public bvec4 and(bvec4 q) { return new bvec4(a && q.a, b && q.b, c && q.c, d && q.d); }
    /**
     * Component-wise nand operator. Performs ¬(P ∧ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ∧ q.a) , ¬(b ∧ q.b) , ¬(c ∧ q.c) ) .
     */
    public bvec4 nand(bvec4 q) { return this.and(q).not(); }
    
    /**
     * Component-wise or operator. Performs (P ∨ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a ∨ q.a , b ∨ q.b , c ∨ q.c ) .
     */
    public bvec4 or(bvec4 q) { return new bvec4(a || q.a, b || q.b, c || q.c, d || q.d); }
    /**
     * Component-wise nor operator. Performs ¬(P ∨ Q) on each component
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ∨ q.a) , ¬(b ∨ q.b) , ¬(c ∨ q.c) ) .
     */
    public bvec4 nor(bvec4 q) { return this.or(q).not(); }
    
    /**
     * Component-wise xor operator. Performs (P ⊕ Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a ⊕ q.a , b ⊕ q.b , c ⊕ q.c ) .
     */
    public bvec4 xor(bvec4 q) { return new bvec4(a ^ q.a, b ^ q.b, c ^ q.c, d ^ q.d); }
    /**
     * Component-wise xnor operator. Performs ¬(P ⊕ Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( ¬(a ⊕ q.a) , ¬(b ⊕ q.b) , ¬(c ⊕ q.c) ) .
     */
    public bvec4 xnor(bvec4 q) { return this.xor(q).not(); }
    
    /**
     * Component-wise implies operator. Performs (P → Q) on each component 
     * of this vector (P) and the other vector (Q), and creates a new 
     * vector with the result.
     * 
     * @param q The other vector (Q).
     * @return A new vector equal to ( a → q.a , b → q.b , c → q.c )
     */
    public bvec4 implies(bvec4 q) { return this.not().or(q); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Reductions ">
    /**
     * @return True if any component is true, false if all components are false.
     */
    public boolean any() { return a || b || c || d; }
    /**
     * @return True if all components are true, false if any component is false.
     */
    public boolean all() { return a && b && c && d; }
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
    public boolean equals(bvec4 q) { return a == q.a && b == q.b && c == q.c && d == q.d; }
    /**
     * @return The amount of true components. For example if (a = true) & (b = false) & (c = true) it returns 2.
     */
    public int totalTrue() { return ((a)?1:0) + ((b)?1:0) + ((c)?1:0) + ((c?1:0)); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Transformers ">
    /**
     * Flips each component of this vector.
     * 
     * @return A new vector equal to ( ¬a , ¬b , ¬c ) .
     */
    public bvec4 not() { return new bvec4(!a, !b, !c, !d); }
    /**
     * Flips the components of this vector.
     * 
     * @return A new vector equal to ( c, b , a ) .
     */
    public bvec4 flip() { return new bvec4(d, c, b, a); }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Overrides ">
    @Override
    public String toString() { return "( " + ((a)?"1":"0") + " , " + ((b)?"1":"0") + " , " + ((c)?"1":"0") + ")"; }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof bvec4)) return false;
        if (this == obj) return true;
        bvec4 o = (bvec4) obj;
        return this.equals(o);
    }
    @Override 
    public int hashCode() { 
        int ha = a ? 1 : 0;
        int hb = b ? 1 : 0; 
        int hc = c ? 1 : 0;
        int hd = d ? 1 : 0;
        return ha | (hb << 1) | (hc << 2) | (hd << 3); 
    }
    @Override
    public int compareTo(bvec4 q) { return Integer.compare(this.totalTrue(), q.totalTrue()); }
    //</editor-fold>
    
}