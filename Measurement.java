import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


public class Measurement {

	protected DateTimeStr dtStr; 
	protected String name; 
	protected String tool;
	HashMap<String, String> map = new HashMap<String, String>();
	
	public DateTimeStr getDtStr() {
		return dtStr;
	}
	public void setDtStr(DateTimeStr dtStr) {
		this.dtStr = dtStr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTool() {
		return tool;
	}
	public void setTool(String tool) {
		this.tool = tool;
	} 
	
	public void addMetric (String mName, String value) 
	{
		map.put (mName, value);
	}
	
	public String getValue (String mName) 
	{
		return map.get(mName); 
		
	}
	
	public void toStr () 
	{
		
		System.out.println("Module/Class Name " + this.getName() ); 
		System.out.println("Date " +  this.getDtStr()); 
		System.out.println("Tool " +  this.getTool());
		
		Iterator<String> keySetIterator = map.keySet().iterator();

		while(keySetIterator.hasNext()){
		  String key = keySetIterator.next();
		  System.out.println("Metric Name: " + key + " Value: " + map.get(key));
		}
		
		
	}
	
	
	/*public void SetDateTime (DateTimeStr dt)
	{
		dtStr = dt; 		
	}
	public DateTimeStr GetDateTime () 
	{
		return dtStr; 		
	}
	
	public void SetName (String n) 
    {
	     name = n;  	
	}
	public String GetName () 
	{
		return name; 
	}*/
	
	
}
