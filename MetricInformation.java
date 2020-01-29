
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class MetricInformation {

	 private  HashMap<String, String> commonMetricIdentifiers = new HashMap<String, String>();
	 private  HashMap<String, Integer> commonMetricTypes = new HashMap<String, Integer>();
	 private  HashMap<String, Double> thresholds = new HashMap<String, Double>();
	 private  HashMap<String, Double> thresholds_2 = new HashMap<String, Double>();

	public double getThreshold (String selectedMetrik) 
	 {
	 	//System.out.println ("123 "+thresholds.get(selectedMetrik) );  
	 	if (thresholds.get(selectedMetrik)!=null) 
	 		return thresholds.get(selectedMetrik); 
	 	return -99; 
	 }
	
	public double getThreshold2 (String selectedMetrik) 
	 {
	 	//System.out.println ("123 "+thresholds.get(selectedMetrik) );  
	 	if (thresholds_2.get(selectedMetrik)!=null) 
	 		return thresholds_2.get(selectedMetrik); 
	 	return -99; 
	 }
	 

	public boolean ThresholdExceeded (String selectedMetrik, double value)
	{
		double thr1 = getThreshold(selectedMetrik); 
		double thr2 = getThreshold2(selectedMetrik); 
		if (thr2 <= -99) 
		{	
			if (value>thr1)
			 return true;
			
		}	
		else 
		{
			
			if (value>thr1 || value < thr2) 
				return true; 
				
			
		}
		return false; 
		
			
	
	}    
	/*public boolean ThresholdExceeded2 (String selectedMetrik, double value)
		{
			if (thresholds.get(selectedMetrik)!=null && value>thresholds.get(selectedMetrik))
				return true;
			return false; 
			
				
		
		}
		*/
		public double ThresholdExceedance (String selectedMetrik, double value)
		{
			double thr1 = getThreshold(selectedMetrik); 
			double thr2 = getThreshold2(selectedMetrik); 
			if (thr2 <= -99) 
				if (value>thr1)
					return value-thr1;
			else
			 if (value > thr1 ) 
			 		return value-thr1; 
			 else if (value < thr2) 
					return thr2-value; 
			
			return 0; 
		}
		
		
		/*public boolean ThresholdWarning (String selectedMetrik, double value)
		{
			
			return ThresholdExceeded(selectedMetrik, value*1.2) ; 
		}*/
		
		public boolean ThresholdWarning (String selectedMetrik, double value)
		{
			
			double thr1 = getThreshold(selectedMetrik); 
			double thr2 = getThreshold2(selectedMetrik); 
			if (thr2 <= -99) 
			{
				if (value*1.2>thr1)
					return true;
			}
			else
			{
			 	 double mid = (thr1-thr2)/2 + thr2;
			 	  if (value >= mid && value*1.2>thr1) 
			 		return true; 
			     else if (value < mid && value*0.8<thr2) 
					return true; 
			 	  
			
			}
			return false;  
		}

		/*public double ThresholdExceedance2 (String selectedMetrik, double value)
		{
			if (thresholds.get(selectedMetrik)!=null && value>thresholds.get(selectedMetrik))
				return (value - thresholds.get(selectedMetrik));
			return 0; 
			
				
		
		}*/
		
		/*public boolean ThresholdWarning (String selectedMetrik, double value)
		{
			
			if (thresholds.get(selectedMetrik)!=null &&  value*1.2 > thresholds.get(selectedMetrik))
				return true;
			return false; 
			
				
		
		}*/
		
		
		public void readMetricNames (String csvFile) {
			  
			  
			BufferedReader br = null;
			String line ;
			String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

			try {
		     	br = new BufferedReader(new FileReader(csvFile));
				
				boolean contentFlag = false; 
				while ((line = br.readLine()) != null) {
		 			String[] cells = line.split(cvsSplitBy); 
				//	System.out.println(line);
					
					if (cells[0]!=null)
					{
						commonMetricIdentifiers.put(cells[0], cells[1]); 
					}
					
					
		 	    }  // while
		  	} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		  }
		
		
		 public  void setCommonMetricIdentifiers() 
		  {
				// Bu metod farklý araçlarla ölçülen metriklerin 
			    // isimlerinin karýþmamasý için ortak bir dil oluþturan
			    // hashmap'e giriþleri dosyadan okuyarak saðlar
			  
			  
			    // McCabe file : Module Name,code,comment,blank,code & comm,File Name
			    // Understand file : Kind,Name,CountLineCode,CountLineComment,CountLineBlank
				
				File f = new File("."); 
				String refFolder = f.getAbsolutePath() + "\\ref\\"; 
				readMetricNames(refFolder+ "metrics.txt"  );
				readMetricTypes(refFolder+ "metrictype.txt");
				readThresholds(refFolder+ "thresholds.txt");
				
		  }
	  
	  public void readMetricTypes (String csvFile) {
		  
		
			BufferedReader br = null;
			String line ;
			String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		
			// Regex'i nereden aldým : 
			// http://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes 
			try {
		     	br = new BufferedReader(new FileReader(csvFile));
				
		
				while ((line = br.readLine()) != null) {
		 			String[] cells = line.split(cvsSplitBy); 
				//	System.out.println(line);
					
					if (cells[0]!=null)
					{
						commonMetricTypes.put(cells[0], Integer.parseInt(cells[1]));
					}
					
					
					
		 	    }  // while
		  	} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
		  }
	  public void readThresholds (String csvFile) {
		  
		   
			BufferedReader br = null;
			String line ;
			String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
			 
			// Regex'i nereden aldým : 
			// http://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes 
		    
			try {
		     	br = new BufferedReader(new FileReader(csvFile));
				
			 
				while ((line = br.readLine()) != null) {
		 			String[] cells = line.split(cvsSplitBy); 
					//System.out.println(line);
					
					if (cells[0]!=null)
					{
						thresholds.put(cells[0], Double.parseDouble(cells[1]));
						thresholds_2.put(cells[0], Double.parseDouble(cells[2])); 
					}
					
					
					
		 	    }  // while
		  	} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		  }
	  
	  
	  public String getUniversalMetricName (String mName) 
	  {
		  return commonMetricIdentifiers.get(mName) ; 
	  }
	  
	  public int getMetricType (String metricName) 
	  {
		  return commonMetricTypes.get(metricName);
	  }



}

