package ComplexNumbers;

public class ComplexNumber {

    //The real and imaginary components.
    public float r, i;
    
    //<editor-fold defaultstate="collapsed" desc=" Constructors ">
    /**
     * Default constructor. Both the real component & the imaginary are initialized at 0.
     */
    public ComplexNumber() { r = 0; i = 0; }
    /**
     * Full explicit constructor.
     * 
     * @param real The real component.
     * @param imaginary The imaginary component.
     */
    public ComplexNumber(float real, float imaginary) { r = real; i = imaginary; }
    public ComplexNumber(Vectors.vec2 ri) { r = ri.x; i = ri.y; }
    //</editor-fold>
    
}