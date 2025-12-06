package Vectors;

public class bvec2 {

    //The two booleans.
    boolean a, b;
    
    public bvec2() { a = false; b = false; }
    public bvec2(boolean w) { a = w; b = w; }
    public bvec2(boolean a, boolean b) { this.a = a; this.b = b; }
    public bvec2(bvec2 v) { a = v.a; b = v.b; }
    
    public bvec2 and(bvec2 o) { return new bvec2(a && o.a, b && o.b); }
    public bvec2 or(bvec2 o) { return new bvec2(a || o.a, b || o.b); }
    public bvec2 xor(bvec2 o) { return new bvec2(a ^ o.a, b ^ o.b); }
    
    public boolean any() { return a || b; }
    public boolean all() { return a && b; }
    public boolean equals(bvec2 o) { return a == o.a && b == o.b; }
    
    public bvec2 not() { return new bvec2(!a, !b); }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof bvec2)) return false;
        if (this == obj) return true;
        bvec2 o = (bvec2) obj;
        return this.equals(o);
    }
    @Override 
    public int hashCode() { 
        int ha = a ? 1 : 0;   // bit 0
        int hb = b ? 1 : 0;   // bit 1
        return ha | (hb << 1); 
    }
    
}
