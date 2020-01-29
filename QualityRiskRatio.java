import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Locale;


public class QualityRiskRatio {

	private OneMetricList oml;
	MetricInformation mi; 
	public String getCallerMethod (int order)  
	{
		
		  final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		  String methodName = ste[order].getMethodName();
		  return methodName ; 
	}
	public void enterMethod () 
	{
		//System.out.println (" **************************************** "); 
		System.out.println (" ");
		
		System.out.println ("OneMetricList::" + getCallerMethod(3) +"()  called by "+getCallerMethod(2));
		System.out.println (" ");
		//System.out.println (" **************************************** ");
	}
	public QualityRiskRatio(OneMetricList omlist) {
		// TODO Auto-generated constructor stub
		oml = omlist;
		mi = new MetricInformation(); 
		mi.setCommonMetricIdentifiers();
	}

	public double howFarFromThreshold (String metric, double value) 
	{
		double threshold = mi.getThreshold(metric);
		if (value>threshold)
			return value-threshold; 
		return 0.0; 
			
	}
	
	public boolean isExceeded (String metric, double value) 
	{
		double threshold = mi.getThreshold(metric);
		if (value>threshold)
			return true; 
		return false; 
			
	} 
	
	public double totalOfExceedance (String metric) 
	{
		  // enterMethod();   
		   List<OneMetric> mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		  OneMetric element ; 
		  double cumulative = 0.0; 
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricName().compareTo(metric) == 0 && element.getValue().compareTo("")!=0 ) 
		      {
		    	 cumulative += howFarFromThreshold(element.getMetricName(), Double.parseDouble(element.getValue()));      	    	
		      } 	
		    	
		  }
		  return cumulative;
	}
	
	public int numberOfExceeded (String metric) 
	{
		  List<OneMetric> mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		  OneMetric element ; 
		  int exceeded = 0; 
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricName().compareTo(metric) == 0 && element.getValue().compareTo("")!=0 && isExceeded(metric, Double.parseDouble(element.getValue()))) 
		      {
		    	 exceeded++;       	    	
		      } 	
		    	
		  }
		  return exceeded;
	}
	
	public double percentageOfContribution (String moduleName, String metric,double toe) 
	{
		
		
		  List<OneMetric> mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		  OneMetric element ; 
		  //double toe = totalOfExceedance(metric); 
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getModuleName().compareTo(moduleName) ==0 && element.getMetricName().compareTo(metric) == 0) 
		      {
		    	 double hfft = howFarFromThreshold(element.getMetricName(), Double.parseDouble(element.getValue())) ;
		    	 //System.out.println ("hfft : " + hfft);
		    	 return (hfft/toe) * 100; 
		      } 	
		    	
		  }
		  return 0;
		
	}
	
	public OneMetricList ListQRR (OneMetricList omlist, String metric)
	{
		 
		
		OneMetricList oml2 = new OneMetricList(omlist); 
		List <OneMetric> mList = oml.Get(); 
	     
		  Iterator iterator = mList.iterator();
		  OneMetric element ; 
		  double toe = totalOfExceedance(metric); 
		  OneMetric onem  = new OneMetric (oml.Get(0))	; 
		  onem.setModuleName(onem.getProjectName());
		  onem.setMetricName(metric + "_toe");
		  onem.setValue(Double.toString(toe/mi.getThreshold(metric)));
		  oml2.Add(onem);
		  
		  int noe = numberOfExceeded(metric); 
		  OneMetric onem2  = new OneMetric (oml.Get(0))	; 
		  onem2.setModuleName(onem.getProjectName());
		  onem2.setMetricName(metric + "_noe");
		  onem2.setValue(Double.toString(noe));
		  oml2.Add(onem2);
		  
		  int mType = onem.getMetricType();
		  
		  
		  DecimalFormat df = new DecimalFormat("##.##", DecimalFormatSymbols.getInstance(Locale.US));
		  
		  if (noe>0) 
		  {		  
			  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricName().compareTo(metric) == 0 && element.getValue().compareTo("")!=0) 
		      {
		    	  double hfft = howFarFromThreshold(element.getMetricName(), Double.parseDouble(element.getValue())) ;
		    	  if (hfft > 0.0) 
		    	  {
		    		  OneMetric om2 = new OneMetric(element);
		    		  om2.setMetricName("QQQ"+ element.getMetricName());
		    		 // om2.setValue(df.format(percentageOfContribution(om2.getModuleName(), metric,toe))); 
		    		  om2.setValue(df.format((hfft/toe)*100));
		    		  oml2.Add(om2);
		    		  oml2.addQRRIdentifier(om2, "1", mType);
		    		  
		    		 // System.out.println ("QRR contribution of module "+ om2.getModuleName() + " at metric " + om2.getMetricName() + "  is "+element.getValue()+" - "+om2.getValue()+" hfft "+ hfft);
		    	  }
		      } 	
		    	
		  }
		  }
		  
		return oml2; 
	}
	 
	

	
}
