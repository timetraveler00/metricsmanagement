
import net.sourceforge.jFuzzyLogic.FIS;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;




public class OneMetricList {

	public String getCallerMethod (int order)  
	{
		
		  final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		  String methodName = ste[order].getMethodName();
		  return methodName ; 
	}

	public void enterMethod () 
	{
		
		  DateTimeStr dts = new DateTimeStr(); 
		  String edatetime = dts.dtString("yyyyMMdd_HHmm");
		  
		//		System.out.println (" **************************************** "); 
		System.out.println (" ");
		
		System.out.println ("OneMetricList::" + getCallerMethod(3) +"()   "+edatetime);
		System.out.println (" ");
		//System.out.println (" **************************************** ");
	}
	public class intervalSet {
		
		double lower; 
		double upper; 
		
		
		
		public double getLower() {
			return lower;
		}

		public void setLower(double lower) {
			this.lower = lower;
		}

		public double getUpper() {
			return upper;
		}

		public void setUpper(double upper) {
			this.upper = upper;
		}

		public boolean IsInInterval (double value)
		{
			if (value >lower && value <= upper) 
			{
				return true; 
			}
			return false; 
		}

		public intervalSet(double lower, double upper) {
			super();
			this.lower = lower;
			this.upper = upper;
		}
		
	}
	
	
	public String logString = ""; 
	
	private  List <OneMetric> mList = new ArrayList<OneMetric>();
	private Reliability reliability, reliability_vol ; 
      Map<String, intervalSet> intervals = new HashMap <String, intervalSet> ();	
	  double lnormal;  
	  double unormal ;
	  double lmoderate; 
	  double umoderate ;
	  double lhigh ;
	  double uhigh ;
	  double lveryhigh ;
	  double uveryhigh ;
	  double la ;
	  double ua ;
	  double laa ;
	  double uaa ;
	  double lb; 
	  double ub ;		  
	  double lc ;
	  double uc ;
	  double lx ;
	  double ux ;
	  double lw ;
	  double uw ;		  
	  double lu ;
	  double uu;
	  double lz ;
	  double uz;

	  
	  Maintainability mntvgl, mntvgf, mntcl, mntcf;
	  FIS fis;
	  
	  OMLSummary omls = new OMLSummary();
	  OMLSummary omlf = new OMLSummary(); 
	  
	  boolean fuzzyFlag = false; 
	
	  public void PrepareFuzzy () throws Exception {
	        // Load from 'FCL' file
		  File f = new File(".");   
		  String fileName = f.getAbsolutePath() + "\\ref\\mntmodel2.fcl";
	        fis = FIS.load(fileName,true);
	        	        // Error while loading?
	        if( fis == null ) { 
	            System.err.println("Can't load file: '" 
	                                   + fileName + "'");
	            return;
	        }

	  }
	public void HashMapInitialize ()  throws Exception  
	{

	/*	  	  intervals.put("veryhigh", new intervalSet (50.0,10000.0));
	  intervals.put("high", new intervalSet (20.0,50.0));
	  intervals.put("moderate", new intervalSet (10.0,20.0));
	  intervals.put("normal", new intervalSet (0.0,10.0));
	  intervals.put("a", new intervalSet (20.0,10000.0));
	  intervals.put("b", new intervalSet (10.0,20.0));
	  intervals.put("c", new intervalSet (0.0,10.0));
	  intervals.put("x", new intervalSet (200.0,100000.0));
	  intervals.put("w", new intervalSet (100.0,200.0));
	  intervals.put("u", new intervalSet (0.0,100.0));
		  
		  
		   lnormal = intervals.get("normal").lower; 
		   unormal = intervals.get("normal").upper;
	     	 lmoderate = intervals.get("moderate").lower;
		  umoderate = intervals.get("moderate").upper;
		    lhigh = intervals.get("high").lower;
		    uhigh = intervals.get("high").upper;
		    lveryhigh = intervals.get("veryhigh").lower;
		    uveryhigh = intervals.get("veryhigh").upper;
		    la = intervals.get("a").lower;
		    ua = intervals.get("a").upper;
		    lb = intervals.get("b").lower;
		    ub = intervals.get("b").upper;		  
		    lc = intervals.get("c").lower;
		    uc = intervals.get("c").upper;
		    lx = intervals.get("x").lower;
		    ux = intervals.get("x").upper;
		    lw = intervals.get("w").lower;
		    uw = intervals.get("w").upper;		  
		    lu = intervals.get("u").lower;
		    uu = intervals.get("u").upper;*/
		  lnormal = 0; 
		   unormal = 10;
	     	 lmoderate = 10;
		  umoderate = 20;
		    lhigh = 20;
		    uhigh = 50;
		    lveryhigh = 50;
		    uveryhigh = 10000;
		    la = 20;
		    ua = 50;
		    lb = 10;
		    ub = 20;		  
		    lc = 0;
		    uc =10;
		    lx = 200;
		    ux = 500; 
		    lw = 100;
		    uw = 200;		  
		    lu = 0;
		    uu = 100;
		    lz = 500; 
		    uz= 100000;
		    laa = 50; 
		    uaa = 10000; 
	
	}


	public void Remove (int index) 
	{
		mList.remove(index); 		
	}
   
	
	public String getCallerMethod ()  
	{
		
		  final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		  String methodName = ste[3].getMethodName();
		  return methodName ; 
	}
	public OneMetricList(List<OneMetric> list) {
		super();
		// TODO Auto-generated constructor stub
		mList = list;
		try {
			HashMapInitialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addIdentifier (OneMetric element, String value, int mType)
	  {
		  if (!ExistsInTheList(element.getModuleName(), "identifier")) 
		  {
			  OneMetric om3 = new OneMetric(element);
			  om3.setMetricName("identifier");
			  om3.setValue(value);
			  om3.setMetricType(mType);
			  Add(om3);
		  } 
	  }
	
	public void addQRRIdentifier (OneMetric element, String value, int mType)
	  {
		  if (!ExistsInTheList(element.getModuleName(), "QQQidentifier")) 
		  {
			  OneMetric om3 = new OneMetric(element);
			  om3.setMetricName("QQQidentifier");
			  om3.setValue(value);
			  om3.setMetricType(mType);
			  Add(om3);
		  } 
	  }
	
	public void setIdentifier (OneMetric element, String value)
	  {
		  
		OneMetric om3 = new OneMetric(element);	  
		  om3.setMetricName("identifier");
		  om3.setValue(value);
		  
		if (!ExistsInTheList(element.getModuleName(), "identifier")) 
		  {
			  
			  Add(om3);
		  } 
		  else 
		  {
			  int index = GetIndex (element.getModuleName(), "identifier");	  
			  mList.set(index, om3) ; 
			  
		  }
	  }
	
	public OneMetricList() {
		super();
		// TODO Auto-generated constructor stub
		mList.clear();
		try {
			HashMapInitialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		

		
	}
	

	
	public boolean ExistsInTheList (String modName, String metName)
	{
		  Iterator iterator = mList.iterator();
		  int i = 0;  
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMetricName().compareTo(metName) ==0  && element.getModuleName().compareTo(modName) ==0) 
		    {
		         return true; 
		    }
		  }
	     return false;  	
	}
	
	public void merge (OneMetricList oml ) 
	{
		for (int i=0; i<oml.GetSize(); i++ )
			this.Add(oml.Get(i));
	}
	
	public int GetIndex (String modName, String metName)
	{
		  Iterator iterator = mList.iterator();
		  int i = 0;  
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMetricName().compareTo(metName) ==0  && element.getModuleName().compareTo(modName) ==0) 
		    {
		         return i;  
		    }
		    i++; 
		    
		  }
	
	     return -1;  	
	
		
	}
	
	public OneMetricList (OneMetricList  list) {
		super();
		// TODO Auto-generated constructor stub
		mList = list.Get();
		try {
			HashMapInitialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clear () 
	{
		mList.clear();
	}
	
	public void SetList (List<OneMetric> list) 
	{
		mList = list; 
	}
	public void SetList (OneMetricList oml) 
	{
		mList = oml.Get(); 
	}
	public List<OneMetric> Get ()
	{
	     return mList; 
	}
	
	public void Add(OneMetric elm)
	{
		mList.add(elm); 
	}
	
	public int GetSize () 
	{
		return mList.size(); 
	}
	
	public OneMetric Get (int index)
	{
		return mList.get(index) ; 
	
	} 
	public double GetValue (int index)
	{
		return Double.parseDouble(mList.get(index).getValue()) ; 
	
	} 
	
	
	public void Filter(String metricName)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMetricName().compareTo(metricName) ==0 ) 
		    {
		        filteredOML.Add(element);
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter2(String metric1, String metric2)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMetricName().compareTo(metric1) ==0 || element.getMetricName().compareTo(metric2) ==0) 
		    {
		        filteredOML.Add(element);
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter(int metricType)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMetricType() == metricType ) 
		    {
		        filteredOML.Add(element);
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	
	public void QRRFilter()
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMetricName().contains("QQQ")) 
		    {
		        filteredOML.Add(element);
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	public void AntiQRRFilter()
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (!element.getMetricName().contains("QQQ")) 
		    {
		        filteredOML.Add(element);
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	
	
	public void FilterbyExceedance(MetricInformation mi)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		  int i = 0;  
		  OneMetric element = null; 
		  
		 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (mi.ThresholdExceeded(element.getMetricName(), Double.parseDouble(element.getValue())))  
		    {
		        filteredOML.Add(element);
		        filteredOML.addIdentifier(element, "1",element.getMetricType());
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	
	public double maxx (double p1, double p2) 
	{
		if (p1>p2) 
			return p1; 
		else 
			return p2; 
	}
	
	
	public void FilterbyWarning(MetricInformation mi, String sortBy)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		  int i = 0;  
		  OneMetric element = null; 
		  double identvg = 0.0, identevg=0.0 , identloc=0.0 ; 
		 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    
		    String val = getValue(element.getModuleName(), sortBy); 
		    if (val == null )
		    	System.out.println("Discrepancy warning : " + element.getModuleName() + " module does not have a " + sortBy + " value.");
		    if ( mi.ThresholdWarning(element.getMetricName(), Double.parseDouble(element.getValue())))  
		    {
		        
		        val = getValue(element.getModuleName(), "vg"); 
		        identvg = val==null ? 0:maxx(mi.ThresholdExceedance("vg", Double.parseDouble(val)), 0)*1000000; 
		        if (element.getValue().compareTo("")!=0) 
		        {
		        val = getValue(element.getModuleName(), "evg"); 
		        identevg = val==null ? 0:maxx(mi.ThresholdExceedance("evg",Double.parseDouble(val)),0)*1000;
		        }
		        val = getValue(element.getModuleName(), "code"); 
		        identloc = val==null ? 0:maxx(mi.ThresholdExceedance("code", Double.parseDouble(val)),0)*1; 
		        filteredOML.setIdentifier(element, Double.toString(identvg+identevg+identloc+1));
		       // filteredOML.setIdentifier(element, val);
		        filteredOML.Add(element);
		     
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	
	public void FilterbyWarningOO(MetricInformation mi, String sortBy)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		  int i = 0;  
		  OneMetric element = null; 
		  double identvg = 0.0, identevg, identloc, identRFC, identNOC, identDepth; 
		 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    
		    String val = getValue(element.getModuleName(), sortBy); 
		    if (val == null )
		    	System.out.println("Discrepancy warning : " + element.getModuleName() + " " + sortBy);
		    if (mi.ThresholdWarning(element.getMetricName(), Double.parseDouble(element.getValue())))  
		    {
		        
		        val = getValue(element.getModuleName(), "LOCM"); 
		        identvg = val==null ? 0:maxx(mi.ThresholdExceedance("LOCM", Double.parseDouble(val)), 0)*1000000; 
		        val = getValue(element.getModuleName(), "CBO"); 
		        identevg = val==null ? 0:maxx(mi.ThresholdExceedance("CBO",Double.parseDouble(val)),0)*100000000;
		        val = getValue(element.getModuleName(), "WMC"); 
		        identloc = val==null ? 0:maxx(mi.ThresholdExceedance("WMC", Double.parseDouble(val)),0)*10000;
		        val = getValue(element.getModuleName(), "RFC"); 
		        identRFC = val==null ? 0:maxx(mi.ThresholdExceedance("RFC", Double.parseDouble(val)),0)*100;
		        val = getValue(element.getModuleName(), "NOC"); 
		        identNOC = val==null ? 0:maxx(mi.ThresholdExceedance("NOC", Double.parseDouble(val)),0)*1;
		        val = getValue(element.getModuleName(), "DIT"); 
		        identDepth = val==null ? 0:maxx(mi.ThresholdExceedance("DIT", Double.parseDouble(val)),0)*0;
		        
		        double total = identvg+identevg+identloc+identRFC+identNOC+identDepth+1; 
	
		        filteredOML.setIdentifier(element, Double.toString(total));
		       // filteredOML.setIdentifier(element, val);
		        filteredOML.Add(element);
		     //System.out.println ("?????? " +  element.getModuleName() +  " - " + identvg +" - " + identloc + " - " + total);
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter(String metricType, String tool)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		  int i = 0;  
		  OneMetric element = null; 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMetricName().compareTo(metricType) ==0  && element.getMeasuremenTool().compareTo(tool) ==0) 
		    {
		        filteredOML.Add(element);
		    }
		    
		  }
	
	     this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter(String metricType, String tool, String dateTime)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		  int i = 0;  
		  OneMetric element = null; 
		  
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMetricName().compareTo(metricType) ==0  && element.getMeasuremenTool().compareTo(tool) ==0 && element.getDateTime().compareTo(dateTime) ==0) 
		    {
		        filteredOML.Add(element);
		    }
		    
		  }
	
	      this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter_p(String projectName,  String dateTime, String tool, String metricName)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		 
		  OneMetric element = null; 
		//  System.out.println (mList.size()); 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getProjectName().compareTo(projectName) == 0  && element.getMeasuremenTool().compareTo(tool) ==0 && element.getDateTime().compareTo(dateTime) ==0 && element.getMetricName().compareTo(metricName) ==0) 
		    {
		        filteredOML.Add(element);
		     //   System.out.println (" * " + element.toString()); 
		    }
		    
		  }
	
	      this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter_p(String projectName,  String dateTime, String tool)
	{
	    enterMethod(); 
		OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		 
		  OneMetric element = null; 
		//  System.out.println (mList.size()); 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getProjectName().compareTo(projectName) == 0  && element.getMeasuremenTool().compareTo(tool) ==0 && element.getDateTime().compareTo(dateTime) ==0 ) 
		    {
		        filteredOML.Add(element);
		     //   System.out.println (" * " + element.toString()); 
		    }
		    
		  }
	
	      this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter_pt(String projectName,  String tool)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		 
		  OneMetric element = null; 
		//  System.out.println (mList.size()); 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getProjectName().compareTo(projectName) == 0  && element.getMeasuremenTool().compareTo(tool) ==0  ) 
		    {
		        filteredOML.Add(element);
		     //   System.out.println (" * " + element.toString()); 
		    }
		    
		  }
	
	      this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter_t(String tool)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		 
		  OneMetric element = null; 
		//  System.out.println (mList.size()); 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMeasuremenTool().compareTo(tool) ==0  ) 
		    {
		        filteredOML.Add(element);
 
		    }
		    
		  }
	
	      this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Filter_p(String projectName,  String dateTime)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		 
		  OneMetric element = null; 
		//  System.out.println (mList.size()); 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getProjectName().compareTo(projectName) == 0  && element.getDateTime().compareTo(dateTime) ==0) 
		    {
		        filteredOML.Add(element);
		     //   System.out.println (" * " + element.toString()); 
		    }
		    
		  }
	
	      this.mList = filteredOML.Get(); 	
		  
	}
	public void Filter_p(String projectName)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		 
		  OneMetric element = null; 
		//  System.out.println (mList.size()); 
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getProjectName().compareTo(projectName) == 0 ) 
		    {
		        filteredOML.Add(element);
		     //   System.out.println (" * " + element.toString()); 
		    }
		    
		  }
	
	      this.mList = filteredOML.Get(); 	
		  
	}
	
	
	public void FilterByToolandDate(String tool, String dateTime)
	{
		  OneMetricList filteredOML = new OneMetricList();   
		  Iterator iterator = mList.iterator();
		  int i = 0;  
		  OneMetric element = null; 
		  
		  while(iterator.hasNext())
		  {
		    element = (OneMetric) iterator.next();
		    if (element.getMeasuremenTool().compareTo(tool) ==0 && element.getDateTime().compareTo(dateTime) ==0) 
		    {
		        filteredOML.Add(element);
		    }
		    
		  }
	
	      this.mList = filteredOML.Get(); 	
		  
	}
	
	public void Swap (int a, int b) 
	{
		OneMetric tempa = mList.get(a);
		OneMetric tempb = mList.get(b); 
		mList.set(a, tempb);
		mList.set(b, tempa);
		 
	}
	

	
	public void revertList () 
	{
		OneMetricList tempList = new OneMetricList(); 
		int size = this.GetSize(); 
		for (int i=size-1; i>-1 ; i--)
		{
			tempList.Add(this.Get(i));
		}
		 
		this.SetList(tempList);
	}
	
	public String getProgramName () 
	{
		  Iterator iterator = mList.iterator();
		  OneMetric element ; 
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricType() == 3) 
		      {
		    	 return element.getModuleName();    	    	
		      } 	
		    	
		  }
		  return null;
		
	}
	
	  public String getValue (String moduleName, String metricName ) 
	  {
		  Iterator iterator = mList.iterator();
		  OneMetric element ; 
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      //if (getCallerMethod().compareTo("FilterByWarning") == 0) 
		      //if (metricName.compareTo("identifier") == 0)
		      //	      System.out.println("getValue : " + element.getModuleName() + "- " + element.getMetricName() );
		      if (element.getModuleName().compareTo(moduleName) ==0 && element.getMetricName().compareTo(metricName) ==0) 
		      {
		    	  // System.out.println("getValue : " + element.getModuleName() + "- " + element.getMetricName()  + " - " + element.getValue());
		    	  return element.getValue();    	    	
		      } 	
		    	
		  }
		  return null;
		  
		  
	  }
	  
	  public int getMethodCount () 
	  {
		OneMetricList oml = new OneMetricList(mList); 
		  Analyze anl = new Analyze(oml);
		List<String> methodList = anl.listMethods();
		return methodList.size(); 
		  
	  }
	  
	  public int getClassCount () 
	  {
		  OneMetricList oml = new OneMetricList(mList); 
		  Analyze anl = new Analyze(oml);
		  List<String> classList = anl.listClasses();
	      return classList.size(); 
	  }
	  
	  public double Average (String metricName) 
	  {
		  Iterator iterator = mList.iterator();
		  OneMetric element ;
		  double cumulative = 0.0;
		  int counted =0; 
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricName().compareTo(metricName) ==0 )  
		      {
		    	 double value = Double.parseDouble(element.getValue());
		    	 cumulative += value; 
		    	 counted++; 
		      } 	
		    	
		  }
		  
		  if (counted>0) 
			  return cumulative/counted; 
		  
		  return 0;
		   
		  
	  } 
	  
	  public double Sum (String metricName) 
	  {
		  Iterator iterator = mList.iterator();
		  OneMetric element ;
		  double cumulative = 0.0;
		   
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricName().compareTo(metricName) ==0 )  
		      {
		    	 double value = Double.parseDouble(element.getValue());
		    	 cumulative += value; 
		    	 
		      } 	
		    	
		  }
		  
		   
		  
		  return cumulative;
		   
		  
	  } 
	  
	  public double Maximum (String metricName) 
	  {
		  Iterator iterator = mList.iterator();
		  OneMetric element ;
		  double max = 0.0;
		   
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricName().compareTo(metricName) ==0 )  
		      {
		    	 double value = Double.parseDouble(element.getValue());
		    	 //System.out.println ("value : " + value); 
		    	 if (value>max) 
		    		 
		    	     max = value;  
		      } 	
		    	
		  }
		  
		   
		  
		  return max;
		   
		  
	  } 
	  
	  public double Minimum (String metricName) 
	  {
		  Iterator iterator = mList.iterator();
		  OneMetric element ;
		  double min = 100000.0;
		   
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricName().compareTo(metricName) ==0 )  
		      {
		    	 double value = Double.parseDouble(element.getValue());
		    	 if (value< min)
		    	     min = value;  
		      } 	
		    	
		  }
		  
		   
		  
		  return min;
		   
		  
	  } 
	  
	  public int ThresholdExceedanceCount (String metricName, MetricInformation mi) 
	  {
		  Iterator iterator = mList.iterator();
		  OneMetric element ;
		  		  
		  int counted = 0; 
		   
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getMetricName().compareTo(metricName) ==0 && element.getValue().compareTo("")!=0)  
		      {
		    	 double value = Double.parseDouble(element.getValue());

		    	 if (mi.ThresholdExceeded(element.getMetricName(), value)) 
		    	     counted++;   
		      } 	
		    	
		  }
		  
		  return counted;
		  
	  }
	  
	  public String getValue (String moduleName, String metricName, String toolName) 
	  {
		  Iterator iterator = mList.iterator();
		  OneMetric element ; 
		  while(iterator.hasNext())
		  {
		      element = (OneMetric) iterator.next();
		      if (element.getModuleName().compareTo(moduleName) ==0 && element.getMetricName().compareTo(metricName) ==0 && (element.getMeasuremenTool().compareTo(toolName) ==0)) 
		      {
		    	 return element.getValue();    	    	
		      } 	
		    	
		  }
		  return null;
		   
	  }
	  
	  public double lineCountofSelectedMetric (String mType, double lvg, double mvg) 
	  {
		  
		 
		  double cumu = 0; 
		  for (int i=0; i<GetSize(); i++) 
		  {
			  
			  OneMetric element = Get(i); 
			  if (element.getMetricName().compareTo(mType) ==0 && element.getValue().compareTo("")!=0 && Double.parseDouble(element.getValue()) > lvg &&  Double.parseDouble(element.getValue()) < mvg) 
			  {
				  
				    cumu += getValueD (element.getModuleName(), "code")  ; 
				    cumu += getValueD (element.getModuleName(), "comment")  ;
				    cumu += getValueD (element.getModuleName(), "blank")  ;
				    cumu += getValueD (element.getModuleName(), "mixed")  ;
				  
			  }
		  }
		  
		  return cumu; 
		  
		
	  }
	  
	  public double lineCountof(double lvg, double mvg) 
	  {
		  
		 
		  double cumu = 0; 
		  for (int i=0; i<GetSize(); i++) 
		  {
			  
			  OneMetric element = Get(i); 
			  if (element.getValue().compareTo("")!=0 && Double.parseDouble(element.getValue()) > lvg &&  Double.parseDouble(element.getValue()) < mvg) 
			  {
				  
				    cumu += getValueD (element.getModuleName(), "code")  ; 
				    cumu += getValueD (element.getModuleName(), "comment")  ;
				    cumu += getValueD (element.getModuleName(), "blank")  ;
				    cumu += getValueD (element.getModuleName(), "mixed")  ;
				  
			  }
		  }
		  
		  return cumu; 
		  
		
	  } 
	  
	  public double calculateComplexity_vol() 
	  {
		  
	
		  double a =  omls.cvhl + omls.chl; //alines; // vgList.lineCountof(20, 10000); 
		  double b =  omls.cml; //blines; //lineCountofSelectedMetric("vg",10, 21) ; 
		  double c =  omls.cnl; //clines; //lineCountofSelectedMetric("vg", 0, 11) ; 
		  
		  omls.rell.setA(a);
		  omls.rell.setB(b);
		  omls.rell.setC(c);
		  double sx = omls.rell.getSx();
		  
		  
		  System.out.println (" SX =  (1.5*a+b+0.8*c)/(a+b+c) ");  
		  System.out.println (" COMPLEXITY  VOL :  " + " A : "+ a + " " + " B :" + b + " C :" + c +" Complexity Factor (SX) = " + sx); 
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + a;
		  logString = logString + "\r\n" + b;
		  logString = logString + "\r\n" + c;
		  logString = logString + "\r\n" + sx;  

		  System.out.println (" --------------------  ");
		  return sx; 
	  }
	  
	  public double calculateEssentialComplexity_vol() 
	  {
			 
		 
		  double a =  lineCountofSelectedMetric("evg",10, 10000); 
		  double b = lineCountofSelectedMetric("evg",4, 11) ; 
		  double c = lineCountofSelectedMetric("evg",0, 5) ; 
		  
		 /* double a = ltofComplexity (14, 10000) ; 
		  double b = FunctionCountofComplexity(6, 16) ; 
		  double c = FunctionCountofComplexity(0, 7)  ; */
		  double sx = (1.5*a+b+0.8*c)/(a+b+c); 
		  
		  System.out.println (" SX =  (1.5*a+b+0.8*c)/(a+b+c) ");  
		  System.out.println (" ESSENTIAL COMPLEXITY  VOL :  " + " A : "+ a + " " + " B :" + b + " C :" + c +" Complexity Factor (SX) = " + sx); 
		 // double veryhigh = LineCountofComplexity(50, 10000); 
		  
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + a;
		  logString = logString + "\r\n" + b;
		  logString = logString + "\r\n" + c;
		  logString = logString + "\r\n" + sx;  

		  System.out.println (" --------------------  ");
		  return sx; 
	  }
	  
	  
	  public double calculateModularity() 
	  {
		  
		  
		  double z = omls.lvhf; //zfunctions; //functionCountofModularity (200, 10000) ; 
		  double x = omls.lhf; // xfunctions; //functionCountofModularity (200, 10000) ; 
		  double w = omls.lmf; //wfunctions; // functionCountofModularity(100, 201) ; 
		  double u = omls.lnf; //ufunctions; // functionCountofModularity(0, 101)  ; 
		   
		  
		  omls.relf.setX(x+z);
		  omls.relf.setU(u);
		  omls.relf.setW(w);
		  double sm = omls.relf.getSm();
		  System.out.println (" SM = (2*x+w+0.9*u)/(x+w+u) "); 
		  System.out.println (" MODULARITY   :  " + " x : "+ (x+z) + " w :" + w +" u :" + u + " Modularity Factor (SM) = " + sm); 

		  
		  
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + (x+z);
		  logString = logString + "\r\n" + w;
		  logString = logString + "\r\n" + u;
		  logString = logString + "\r\n" + sm;  

		  System.out.println (" --------------------  ");
		  return sm; 
	  }
	  
	  public double calculateModularity_vol() 
	  {
		  double z = omls.lvhl; //zlines; //lineCountofModularity (200, 10000) ; 
		  double x = omls.lhl; //xlines; //lineCountofModularity (200, 10000) ; 
		  double w = omls.lml; //wlines; //lineCountofModularity(100, 201) ; 
		  double u = omls.lnl; //ulines; // lineCountofModularity(0, 101)  ; 
		   
		  omls.rell.setX(x+z);
		  omls.rell.setU(u);
		  omls.rell.setW(w);
		  double sm = omls.rell.getSm(); 
		  System.out.println (" SM = (2*x+w+0.9*u)/(x+w+u) ");
		  
		  logString = logString + "\r\n" + "";  
		  logString = logString + "\r\n" + (x+z);
		  logString = logString + "\r\n" + w;
		  logString = logString + "\r\n" + u;
		  logString = logString + "\r\n" + sm;  
		  System.out.println (" MODULARITY VOL   :  " + " x : "+ (x+z) + " w :" + w +" u :" + u + " Modularity Factor (SM) = " + sm);
		  System.out.println (" --------------------  ");
		  return sm; 
	  }
	  


	  public Maintainability getMaintainability () 
	  {
		  return omls.mntvgl;
	  }
	  public Maintainability calculateMaintainability () 
	  {
		  
		  		  
		 /* double code = metricCumulative("code"); 
		  double comment = metricCumulative("comment");
		  double blank = metricCumulative("blank");
		  double mixed = metricCumulative("mixed");
		  //double totalLOC = totalLOC();
		  double totalLOC = code + comment + blank + mixed;
		  */ 
		  omls.totalLOC =  omls.cvhl + omls.chl + omls.cml+ omls.cnl ; 
		  omls.methodCount = omls.cvhf + omls.chf + omls.cmf+ omls.cnf ;
		  
		   omlf.totalLOC = omlf.cvhl + omlf.chl + omlf.cml+ omlf.cnl ; 
		   omlf.methodCount = omlf.cvhf + omlf.chf + omlf.cmf+ omlf.cnf ;
		  
		  
		  
		  System.out.println (" Total LOC (code, comment, blank, mixed) : " + omls.totalLOC);
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + omls.totalLOC; 
		  
		  //logString = logString + "\r\n" + "";
		  /*logString = logString + "\r\n" + Double.toString(code);
		  logString = logString + "\r\n" + Double.toString(comment); 
		  logString = logString + "\r\n" + Double.toString(blank);
		  logString = logString + "\r\n" + Double.toString(mixed); 
		  */
		 /* System.out.println (" Total code : " + code);
		  System.out.println (" Total comment : " + comment);
		  System.out.println (" Total blank : " + blank);
		  System.out.println (" Total mixed : " + mixed);
		  
		   */
		  
		  omls.CalculateAllRatios();
		  System.out.println ("-------------------------------"); 
		  System.out.println (" COMPLEXITY - CODE ");
		  omls.mntvgl = GenericMaintainability(omls.cvhl, omls.chl, omls.cml, omls.cnl, omls.totalLOC);
		  System.out.println ("-------------------------------");
		  System.out.println ("");
		  
		  System.out.println ("-------------------------------"); 
		  System.out.println (" LOC - CODE ");
		  omls.mntcl = GenericMaintainability(omls.lvhl, omls.lhl, omls.lml, omls.lnl, omls.totalLOC);
		  System.out.println ("-------------------------------");
		  System.out.println ("");
		  
		  System.out.println ("-------------------------------"); 
		  System.out.println (" COMPLEXITY - COUNT ");
		  omls.mntvgf = GenericMaintainability(omls.cvhf, omls.chf, omls.cmf, omls.cnf, omls.methodCount);
		  System.out.println ("-------------------------------");
		  System.out.println ("");
		  
		  System.out.println ("-------------------------------"); 
		  System.out.println (" LOC - COUNT ");
		  omls.mntcf = GenericMaintainability(omls.lvhf, omls.lhf, omls.lmf, omls.lnf, omls.methodCount);
		  System.out.println ("-------------------------------");
		  System.out.println ("");
		  
		 
		 
		  if (fuzzyFlag) 
		  {
			  omlf.CalculateAllRatios(); 
		  System.out.println ("-------------------------------"); 
		  System.out.println (" FUZZY - QUALITY ");
		  omlf.mntvgl = GenericMaintainability(omlf.cvhl, omlf.chl, omlf.cml, omlf.cnl, omlf.totalLOC);
		  System.out.println ("-------------------------------");
		  System.out.println ("");
		  }
		  
		  
		  System.out.println ("   ");
		  System.out.println (" --------------------  ");
		  return omls.mntvgl;
		  
	  }
	  
	  public Maintainability GenericMaintainability(double vh, double h, double m, double n, double t) 
	  {
		 		  
		  double veryhigh = vh *100 / t; 
		  double high = h *100 / t; 
		  double moderate = m *100 / t;
		  double normal = n *100 / t; 
		  
		  Maintainability mnttemp = new Maintainability();
		  mnttemp.setVeryhigh(veryhigh);
		  mnttemp.setHigh(high);
		  mnttemp.setModerate(moderate);
		  mnttemp.setLow(normal);
		  
		  
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + Double.toString(veryhigh);
		  logString = logString + "\r\n" + Double.toString(high); 
		  logString = logString + "\r\n" + Double.toString(moderate);
		  logString = logString + "\r\n" + Double.toString(normal);
		  logString = logString + "\r\n" + ""; 
		  
		  System.out.println (" AMOUNT  :  total " + t + " veryhigh : "+ vh + " " + " h :" + h + " moderate :" + m + " normal : "+n);  
		  System.out.println (" RATED  :   veryhigh : "+ veryhigh + " " + " high :" + high + " moderate :" + moderate + " normal : "+normal); 


		  System.out.println (" --------------------  ");
		  
		  System.out.println ("  ");
		   
		  double maintainability = mnttemp.maintainability();
		  
		  System.out.println (" MAINTAINABILITY  RATING :     " + maintainability + " star(s)"); 
		  logString = logString + "\r\n" + maintainability ;
		  
		  return mnttemp; 
	  }
	  
	
	
	  public Reliability predictedFaultDensity (boolean fFlag) 
	  {
		  fuzzyFlag = fFlag; 
		  omls.relf = new Reliability(); 
		  
		  
		  OneMetricList vgList = new OneMetricList(this.mList); 
		  vgList.Filter("vg");
		  ComplexityCalculations (vgList);
		  
		  OneMetricList locList = new OneMetricList(this.mList); 
		  locList.Filter("totalLOC");
		  LOCCalculations (locList); 
		  		  
		  double sx = calculateComplexity(); 
		  double sm = calculateModularity();
		  double pfd = omls.relf.reliability(); //10*sx*sm; 
		  
		  System.out.println (" RELIABILITY   :  " + " Initial Fault Density : 10 "+ " SX :" + sx +" SM :" + sm + "    Current Fault Density = " + pfd); 
		  System.out.println (" --------------------  ");
		  
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + sx;
		  
		  logString = logString + "\r\n" + sm; 
		  logString = logString + "\r\n" + pfd;
		  
		  predictedFaultDensity_Vol();
		  calculateMaintainability();
		  return omls.relf; 
		    
	  }
	  
	  public Reliability predictedFaultDensity_Vol () 
	  {
		
		  omls.rell = new Reliability();

		  
		   
		  double sx = calculateComplexity_vol(); 
		  double sm = calculateModularity_vol(); 
		  double sxe = 1; //CalculateEssentialComplexity_vol();
		  
		  
		  
		  double pfd = omls.rell.reliability(); // 10*sx*sm*sxe; 
		  System.out.println (" RELIABILITY_VOL   :  " + " Initial Fault Density : 10 "+ " SX :" + sx +" SXE :" + sxe +" SM :" + sm + "    Current Fault Density = " + pfd); 
		  System.out.println (" --------------------  ");
		  
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + sx;
		  //logString = logString + "\r\n" + sxe; 
		  logString = logString + "\r\n" + sm; 
		  logString = logString + "\r\n" + pfd;
		  return reliability; 
	  }
	  public double calculateComplexity() 
	  {
			 
		  omls.relf =  new Reliability();
		
		  double a = omls.cvhf + omls.chf; //afunctions; //functionCountofComplexity (20, 10000) ; 
		  double b = omls.cmf ; //bfunctions; //functionCountofComplexity(10, 21) ; 
		  double c = omls.cnf ; //cfunctions; // functionCountofComplexity(0, 11)  ; 
		  
		  omls.relf .setA(a);
		  omls.relf .setB(b);
		  omls.relf .setC(c);
		  double sx =  omls.relf.getSx();
		  
		  System.out.println (" SX =  (1.5*a+b+0.8*c)/(a+b+c) ");  
		  System.out.println (" COMPLEXITY   :  " + " A : "+ a + " " + " B :" + b + " C :" + c +" Complexity Factor (SX) = " + sx); 
		  
		  
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + a;
		  logString = logString + "\r\n" + b;
		  logString = logString + "\r\n" + c;
		  logString = logString + "\r\n" + sx;  
		  
		 // double veryhigh = LineCountofComplexity(50, 10000); 
        
		  
		  
		  
		  System.out.println (" --------------------  ");
		  return sx; 
	  }	  
	  
	  
	  
	  
	  public double getValueD (String moduleName, String metric) 
	  {
		String value = getValue (moduleName, metric); 
		double val = 0.0 ; 
		if (value != null) 
		{
			val = Double.parseDouble(value); 	    	
		}
		return val; 
	  } 
	  public double MaintainabilityComplexity (double val, double locval, double evgval) 
	  {
		  double mq = -1; 
		 // if (fuzzyFlag)
		 // {
		  fis.setVariable("VG", val);
		  fis.setVariable("EVG", evgval);
		  fis.setVariable("LOC", locval);
		  //fis.chart();
		  
		  fis.evaluate();
		  //fis.getVariable("MQ").chartDefuzzifier(true); 
		  mq = fis.getVariable("MQ").getValue(); 
		  

		  if (mq>=300) 
		  {
	    		 omlf.cvhf++;
	    		 omlf.cvhl += locval; 
	    		 
		  }
		  else if (mq>=200) 
		  {
	    		 omlf.chf++;
	    		 omlf.chl += locval; 		  	 
		  }
		  else if (mq>=100)
		  {
	    		 omlf.cmf++;
	    		 omlf.cml += locval; 
		  }
		  else 
		  {
	    		 omlf.cnf++;
	    		 omlf.cnl += locval; 
 
		  }
		  
		  //System.out.println("cvhf: " + omlf.cvhf + " cvhl: "+omlf.cvhl);
		  //System.out.println("cnf: " + omlf.cnf + " cnl: "+omlf.cnl);
		 // }
		  
		  // fuzzyflag == false
		/*  else 
		  {
		  
			  MaintainabilityComplexity (val, locval); 
		  
		  }*/
		  
		 return mq;
	  }
	  public void MaintainabilityComplexity (double val, double locval) 
	  {
		  		  
		  if (val >lnormal  &&  val <= unormal) 
	    	{
	    		 omls.cnf++;
	    		 omls.cnl += locval; 
	    	}
	    	else if (val >lmoderate  &&  val <= umoderate) 
	    	{
	    		 omls.cmf++;
	    		 omls.cml += locval; 
	    	}    
	    	else if (val >lhigh  &&  val <= uhigh) 
	    	{
	    		 omls.chf++;
	    		 omls.chl += locval; 		
	    	}
	    	else if (val >lveryhigh  &&  val <= uveryhigh) 
	    	{
	    		 omls.cvhf++;
	    		 omls.cvhl += locval; 
	    	}
	  }
	  
	  public double ComplexityCalculations (OneMetricList vglist) 
	  {
		  enterMethod();
		  
		 /* System.out.println (" --------------------  ");
		  System.out.println (" --------------------  ");
		  System.out.println (" --------------------  ");
		  System.out.println (" --------------------  ");
		  System.out.println (" ");
		  System.out.println (" ");
		  System.out.println (" ");*/
		  System.out.println (" COMPLEXITY CALCULATIONS ");
		 /* System.out.println (" ");
		  System.out.println (" ");
		  System.out.println (" ");
		  System.out.println (" --------------------  ");
		  System.out.println (" --------------------  ");
		  System.out.println (" -----------   ---------  ");
		  System.out.println (" --------------------  ");*/
		  
		  List<OneMetric> mmList = vglist.Get(); //Get(); 
		 // System.out.println (mmList.size());
		  Iterator iterator = mmList.iterator();
		  Iterator iterator1 = mmList.iterator();
		  double cumu = 0; 
		 
		  if (fuzzyFlag)
		  {
			     try {
						PrepareFuzzy();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			     System.out.println ("FUZZY-COMPLEXITY CALCULATIONS ");
				  while(iterator.hasNext())
				  {
				    OneMetric element = (OneMetric) iterator.next();
				    
				   // if (element.getMetricName().compareTo("vg") ==0 ) 
			       // {
				    	double val =  Double.parseDouble(element.getValue());
				    	double locval = getValueD(element.getModuleName(), "totalLOC");
				    	double evgval = getValueD(element.getModuleName(), "evg");
				    	
				    	 //System.out.println ("val : " + val + " locval: " +locval + " unormal " + unormal + "ub "+ub);
				    	//System.out.println ("val : " + val + " locval: " +locval + " unormal " + unormal + "ub "+ub);

				        double mq=MaintainabilityComplexity(val, locval, evgval);
				       // System.out.println (element.getModuleName() + " vg :" + val + " evg: "+evgval + " loc:"+locval + " final mq:" +mq);
				        
				    	//ReliabilityComplexity(val,locval,evgval);  
				    	
				    	
			        //}
				  }
			  
			  
		  }
		  
		  enterMethod();
		  
		//  else {
		  
		  System.out.println ("SIG MAINTAINABILITY-COMPLEXITY CALCULATIONS ");
			  while(iterator1.hasNext())
			  {
			    OneMetric element = (OneMetric) iterator1.next();
			    
			   // if (element.getMetricName().compareTo("vg") ==0 ) 
		       // {
			    	double val =  Double.parseDouble(element.getValue());
			    	double locval = getValueD(element.getModuleName(), "totalLOC");
			    	
			    	 //System.out.println ("val : " + val + " locval: " +locval + " unormal " + unormal + "ub "+ub);

			        MaintainabilityComplexity(val, locval);
			    	
			    	
			    	
		        //}
			  } 
			  
		  //}
		  
		 
		  /*System.out.println (" ");
		  System.out.println (" ");
		  System.out.println (" ");
		  System.out.println (" --------------------  ");
		  System.out.println (" --------------------  ");
		  System.out.println (" --------------------  ");
		  System.out.println (" --------------------  ");*/
		  enterMethod();
		  return cumu; 
		  
	  } 
	  
	  public double LOCCalculations (OneMetricList loclist) 
	  {
		  

		  List<OneMetric> mmList = loclist.Get(); //Get(); 
		  Iterator iterator = mmList.iterator();
		  double cumu = 0; 
		  while(iterator.hasNext())
		  {
		    OneMetric element = (OneMetric) iterator.next();
		    
		   // if (element.getMetricName().compareTo("vg") ==0 ) 
	       // {
		    	double val =  Double.parseDouble(element.getValue());
		    	//double locval = getValueD(element.getModuleName(), "totalLOC");

		    	if (val >lz  &&  val <= uz) 
		    	{
	    	          omls.lvhf++; 
	    	          omls.lvhl+=val; 
	    	          
		    	}
		    	if (val >lx  &&  val <= ux) 
		    	{
	    	          omls.lhf++; 
	    	          omls.lhl+=val; 
		    	}
		    	else if (val >lw  &&  val <= uw) 
		    	{
	    	          omls.lmf++; 
	    	          omls.lml+=val; 
		    	}    
		    	else if (val >lu  &&  val <= uu) 
		    	{
	    	          omls.lnf++; 
	    	          omls.lnl+=val; 
		    	}
		    	
		    	
	        //}
		  }
		  
		  return cumu; 
		  
	  } 
	  
		public double functionCountofModularity (double lloc, double mloc) 
	  {
		  
			 List<OneMetric> mList = Get(); 
			Iterator iterator = mList.iterator();
		  double cumu = 0; 
		  while(iterator.hasNext())
		  {
		    OneMetric element = (OneMetric) iterator.next();
		    
		    //if (element.getMetricName().compareTo("code") ==0 ) 
	       // {
		        double loc =  totalLOCofaModule (element.getModuleName()) ; 
		    	if (loc> lloc &&  loc < mloc)
		    	 { 
		    		cumu ++; 	    	
		    	 }
	    	
		    //}
		  }
		  
		  return cumu; 
		  
	  } 
	  
	  public double lineCountofModularity (double lloc, double mloc) 
	  {
		  List<OneMetric> mList = Get(); 
		  Iterator iterator = mList.iterator();
		  double cumu = 0; 
		  while(iterator.hasNext())
		  {
		    OneMetric element = (OneMetric) iterator.next();
		    
		    // To calculate total loc of a module only once
		  //  if (element.getMetricName().compareTo("code") ==0 ) 
	       // {
		        double loc =  totalLOCofaModule (element.getModuleName()) ; 
		    	if (loc> lloc &&  loc < mloc)
		    	 { 
		    		cumu +=loc; 	    	
		    	 }
	    	
		   // }
		  }
		  
		  return cumu; 
		  
	  } 
	  
	  // Note: There is a RISK here like doubling calculations if there are more than one records for each module
	  public double totalLOCofaModule (String module) 
	  {
		  List<OneMetric> mList = Get(); 
		  Iterator iterator = mList.iterator();
		  double cumu = getValueD(module,"code") + getValueD(module,"comment") + getValueD(module,"blank") + getValueD(module,"mixed"); 
		
		  //System.out.println("Total LOC of the module "+module +" is  "+cumu);
		  return cumu; 
		  
	  } 
	  
	  public double totalLOC () 
	  {
		  
		  double cumulative =0.0; 
		  
		  cumulative += metricCumulative("code");
		  cumulative += metricCumulative("comment");
	  	  cumulative += metricCumulative("blank");
		  cumulative += metricCumulative("mixed");
		  return cumulative; 
		  
	  }
	
	  public double metricCumulative (String mType) 
	  {
		  double cumulative =0.0; 
		  
		  for (int i=0; i<GetSize(); i++) 
		  {
			  OneMetric element = Get(i);
			  if (element != null && element.getMetricName().compareTo(mType) == 0 ) 
		      {
		    	 cumulative += Double.parseDouble(element.getValue());   	    	
		      } 	
			  
		  }
		  return cumulative;
		
		  
	  }
	  public double metricCumulative (String mType, String tool) 
	  {
		  double cumulative =0.0; 
		  
		  for (int i=0; i<GetSize(); i++) 
		  {
			  OneMetric element = Get(i);
			  if (element != null && element.getMetricName().compareTo(mType) == 0  && element.getMeasuremenTool().compareTo(tool) == 0) 
		      {
		    	 cumulative += Double.parseDouble(element.getValue());   	    	
		      } 	
			  
		  }
		  return cumulative;
		
		  
	  }
	  
	  public void insertLog (String newLog)
	  {
		  
			  logString = logString + "\r\n" + newLog;
			
	  }
	  public void logHeaderInfo (OneMetric temp) 
	  {
			 
		    logString = logString + "\r\n" + temp.getProjectName();
			logString = logString + "\r\n" + temp.getModuleName();
			logString = logString + "\r\n" + temp.getDateTime();
			logString = logString + "\r\n" + temp.getMeasuremenTool();
	  }
	  
	  public void logDefectCount (MetricInformation mi) 
	  {
		 enterMethod();
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + Integer.toString(ThresholdExceedanceCount("defects", mi));
		  logString = logString + "\r\n" + Integer.toString(ThresholdExceedanceCount("vg", mi));
     	  logString = logString + "\r\n" + Integer.toString(ThresholdExceedanceCount("evg", mi));
		  logString = logString + "\r\n" + Integer.toString(ThresholdExceedanceCount("ivg", mi));
		  logString = logString + "\r\n" + "";
		  logString = logString + "\r\n" + lineCountofSelectedMetric("evg", 4, 10000)/totalLOC();
		  
		  
	  }
	  
	  public void logMI () 
	  {
		 
		  
		  enterMethod();
		  String programName = getProgramName(); 
		  if (programName != null ) 
		  {
		  
			  logString = logString + "\r\n" + "";
		  
			  logString = logString + "\r\n" + getValue(programName, "3MI");
			  logString = logString + "\r\n" + "";
		  
			  logString = logString + "\r\n" + getValue(programName, "4MI");
		  
		  }
		  
		 
	  }

	
	
	
}
