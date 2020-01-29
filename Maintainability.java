import java.util.HashMap;


public class Maintainability {

	private double veryhigh ; 
	private double high ;
	private double moderate ;
	private double low ;
	private int vh, h, m; 
	
	
	
	public Maintainability()  
	{
	
	}
	
	public boolean checkRange (double value, double lower, double upper) 
	{
		if (value > lower  && value<= upper) 
		{
			return true; 
		}
		return false; 
		
	}
	
	public double maintainability () 
	{
		double maintainability = 0; 
		  double total = vh + h + m; 
		  if (total >10000 ) 
		  {
			  maintainability = 1; 
		  }
		  else if (total > 1000) 
		  {
			  maintainability = 2; 
		  } 
		  else if (total > 100) 
		  {
			  maintainability = 3; 
		  } 
		  else if (total > 10) 
		  {
			  maintainability = 4; 
		  } 
		  else 
		  {
			  maintainability = 5; 
		  } 
		  
		  return maintainability; 
		
	}
	
/*	public double maintainabilitya () 
	{
		double maintainability = 0; 
		  
		  if (moderate <= 25.0 && high <= 0.0 && veryhigh <=0.0) 
		  {
			  maintainability = 5; 
		  }
		  else if (moderate <= 30.0 && high <= 5.0 && veryhigh <=0.0) 
		  {
			  maintainability = 4; 
		  } 
		  else if (moderate <= 40.0 && high <= 10.0 && veryhigh <=0.0) 
		  {
			  maintainability = 3; 
		  } 
		  else if (moderate <= 50.0 && high <= 15.0 && veryhigh <=5.0) 
		  {
			  maintainability = 2; 
		  } 
		  else 
		  {
			  maintainability = 1; 
		  } 
		  
		  return maintainability; 
		
	}
*/
	public double getVeryhigh() {
		return veryhigh;
	}

	public void setVeryhigh(double veryhigh) {
		this.veryhigh = veryhigh;
		if  (this.veryhigh == 0) 
		{
			vh = 1; 
		}
		else if (checkRange(this.veryhigh, 0, 5))
		{
			vh = 1000; 
		}
		else if (this.veryhigh > 5 )
		{
			vh = 10000; 
		}
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high; 
		if  (this.high == 0) 
		{
			h = 1; 
		}
		else if (checkRange(this.high, 0, 5) )
		{
			h = 10; 
		}
		else if (checkRange(this.high, 5, 10))
		{
			h = 100; 
		}
		else if (checkRange(this.high, 10, 15))
		{
			h = 1000; 
		}
		else if (this.high > 15 )
		{
			h = 10000; 
		}

	}

	public double getModerate() {
		return moderate;
	}

	public void setModerate(double moderate) {
		this.moderate = moderate;
		if  (this.moderate < 25) 
		{
			m = 1; 
		}
		else if (checkRange(this.moderate, 25, 30) )
		{
			m = 10; 
		}
		else if (checkRange(this.moderate, 30, 40))
		{
			m = 100; 
		}
		else if (checkRange(this.moderate, 40, 50))
		{
			m = 1000; 
		}
		else if (this.moderate > 50 )
		{
			m = 10000; 
		}
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}
	
	
	
}
