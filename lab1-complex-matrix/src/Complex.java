public class Complex {

    private final double real;
    private final double imag;
    private static final double EPS = 1e-10;
    public static final Complex ZERO = new Complex(0,0);
    public static final Complex ONE = new Complex(1, 0);




    public Complex(double real, double imag){
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {return real;}

    public double getImag() {return imag;}

    private static String format(double x){
        double rounded = Math.round(x * 10000) / 10000.0;
        if( Math.abs(rounded - Math.round(rounded)) < EPS ){
            return Math.round(rounded) + "";}
        return "" + rounded;
    }

    private static String imagPart(double y){
        if(Math.abs(Math.abs(y) - 1) < EPS){
            if(y < 0) return "-i";
            return "i";
        }
        return format(y) + "i";
    }

    @Override
    public String toString() {
        if(Math.abs(imag) < EPS) return format(real);
        else if(Math.abs(real) < EPS) return imagPart(imag);
        else{
            String sign = "";
            if(imag < 0){ sign = " - ";}
            else{sign = " + ";}
            return format(real) + sign + imagPart(Math.abs(imag));
        }

    }

    public Complex add(Complex other){return new Complex(real + other.real, imag + other.imag);}

    public Complex subtract(Complex other){return new Complex(real - other.real, imag - other.imag);}

    public Complex multiply(Complex other){return new Complex(real * other.real - imag * other.imag, real * other.imag + imag * other.real);}

    public double abs(){return Math.hypot(real, imag);}

    public Complex conjugate(){return new Complex(real, -1 * imag);}

    public boolean isZero(){return (Math.abs(real) < EPS && Math.abs(imag) < EPS);}

    public Complex divide(Complex other){
        if(other.isZero())
        {
            throw new ArithmeticException("Деление на ноль");
        }
        double c = Math.pow(other.real, 2) + Math.pow(other.imag, 2);
        return new Complex((real * other.real + imag * other.imag)/ c, (imag * other.real - real * other.imag) / c);
    }

}
