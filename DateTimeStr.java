import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateTimeStr {

	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	DateFormat dateFormats = new SimpleDateFormat("yyyyMMdd_HHmm");
	public String dtString() 
	{
		
    	
    	Calendar cal = Calendar.getInstance();
    	String dateString = dateFormat.format(cal.getTime());
    	return dateString;
	}
	public String dtString(String format) 
	{
		
    	dateFormat = new SimpleDateFormat(format);
    	Calendar cal = Calendar.getInstance();
    	String dateString = dateFormat.format(cal.getTime());
    	return dateString;
	}
	
	 public int getMonth () 
	    {
	    	return Integer.parseInt(dtString().substring (4,6));     
	    }
	    
	    public int getYear () 
	    {
	    	return Integer.parseInt(dtString().substring (0,4));     
	    }
	    public int getDay () 
	    {
	    	return Integer.parseInt(dtString().substring (6,8));     
	    }
	    
	    public int getHour () 
	    {
	    	return Integer.parseInt(dtString().substring (9,11));     
	    }
	    
	    public int getMinute () 
	    {
	    	return Integer.parseInt(dtString().substring (11,13));     
	    }
	    public int getSecond () 
	    {
	    	return Integer.parseInt(dtString().substring (13,15));     
	    }
}
