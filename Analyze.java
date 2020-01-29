import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Analyze {

    private OneMetricList oml ; 
    private  List <OneMetric> mList = new ArrayList<OneMetric>();
    
	
	public Analyze() {
		
		// TODO Auto-generated constructor stub
	}
	
	public Analyze(OneMetricList transfer) {
		
		// TODO Auto-generated constructor stub
		oml = transfer; 
	}	
	
	public boolean stringExistsInTheList (List<String> searchList, String str) 
	{
		
		 Iterator iterator = searchList.iterator();
		  int i = 0;  
		 String element; 
		  while(iterator.hasNext())
		  {
		    element = (String) iterator.next();
		    if (element.compareTo(str) == 0) 
		    {
		        return true; 
		    }
		    
		  }
	
	
		return false;
		
	}
	
	public List<String> listMethods () 
	{
		List<String> methodList = new ArrayList<String>(); 
		List<String> methodBasedMetricList = listMethodBasedMetrics(); 
		methodList.clear();  
		mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		    
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    
		    if (stringExistsInTheList(methodBasedMetricList, element.getMetricName()) && !stringExistsInTheList(methodList, element.getModuleName())) 
		    {
		    	methodList.add(element.getModuleName()); 
		    	//System.out.println("mm : " + element.toString());
		    }
		    
		  }
		return methodList;
	}
	
	public List<String> listClasses () 
	{
		List<String> classList = new ArrayList<String>(); 
		List<String> classBasedMetricList = listClassBasedMetrics(); 
		classList.clear();  
		mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		    
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    
		    if (stringExistsInTheList(classBasedMetricList, element.getMetricName()) && !stringExistsInTheList(classList, element.getModuleName())) 
		    {
		    	classList.add(element.getModuleName()); 
		    	//System.out.println("cm : " + element.toString());
		    }
		    
		  }
		return classList;
	}
	
	public List<String> listDateTime () 
	{
		List<String> dateTimeList = new ArrayList<String>(); 
		dateTimeList.clear();  
		mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		    
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (!stringExistsInTheList(dateTimeList, element.getDateTime())) 
		    {
		         dateTimeList.add(element.getDateTime()); 
		    }
		    
		  }
		return dateTimeList;
	}
	
	public List<String> listProjects () 
	{
		List<String> projectList = new ArrayList<String>(); 
		projectList.clear();  
		mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		  
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (!stringExistsInTheList(projectList, element.getProjectName())) 
		    {
		    	projectList.add(element.getProjectName()); 
		    }
		    
		  }
		return projectList;
	}
	
	public List<String> listTools () 
	{
		List<String> toolList = new ArrayList<String>(); 
		toolList.clear();  
		mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		  
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (!stringExistsInTheList(toolList, element.getMeasuremenTool())) 
		    {
		    	toolList.add(element.getMeasuremenTool()); 
		    }
		    
		  }
		return toolList;
	}
	
	public List<String> listMetrics () 
	{
		List<String> metricsList = new ArrayList<String>(); 
		metricsList.clear();  
		mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		    
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (!stringExistsInTheList(metricsList, element.getMetricName())) 
		    {
		    	metricsList.add(element.getMetricName()); 
		    }
		    
		  }
		return metricsList;
	}
	
	
	
	public List<String> listMethodBasedMetrics () 
	{
		List<String> metricsList = new ArrayList<String>(); 
		metricsList.clear();  
		mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		    
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    String mName = element.getMetricName();
		    if ( element.getMetricType() == 1 &&  !stringExistsInTheList(metricsList, element.getMetricName())&& element.getMetricName().compareTo("identifier")!= 0) 
		    {
		    	metricsList.add(element.getMetricName()); 
		    }
		    
		  }
		return metricsList;
	}
	
	public List<String> listClassBasedMetrics () 
	{
		List<String> metricsList = new ArrayList<String>(); 
		metricsList.clear();  
		mList = oml.Get(); 
		  Iterator iterator = mList.iterator();
		    
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    String mName = element.getMetricName();
		    if ( element.getMetricType() == 2 &&  !stringExistsInTheList(metricsList, element.getMetricName()) && element.getMetricName().compareTo("identifier")!= 0) 
		    {
		    	metricsList.add(element.getMetricName()); 
		    }
		    
		  }
		return metricsList;
	}
	
	
	
}
