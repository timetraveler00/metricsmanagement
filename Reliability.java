
public class Reliability {

	 
	private double a; 
	private double b;
	private double c; 
	
	private double x; 
	private double w;
	private double u;
	
	public double reliability () 
	{
		return  (getSx() * getSm() )  ;  
	}

	public double getSx() {
		return (1.5*a+b+0.8*c)/(a+b+c); 
	}

	public double getSm() {
		return (2*x+w+0.9*u)/(x+w+u);
	}
	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}
	
	
}
