import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class McCabeCSV extends BaseTool{


	//private String ; 
	
	public McCabeCSV() {
		
		// TODO Auto-generated constructor stub
		
		
	}
	


	public  void DeleteFile()
	{
		/* for (int i = 0; i < mTypes.Count; i++)
         {
             if (File.Exists(raporPFileName + mTypes[mTypes.Count - 1] + ".csv"))
             {
                 File.Delete(raporPFileName + mTypes[i] + ".csv");
             }
         }*/
		
	};
    public  void ExecuteFile()
    {
    	//String batchFileName = getPcfPath() + "\\" +"PCFandCSVCreation.bat";
    	//CommandExecution ce = new CommandExecution(batchFileName, 0);
    };
    
    
    
    public  void CreatePCF() 
    {
  
    	//DateTimeStr dtStr = new DateTimeStr(); 
    	    	
    	
    	String projectName = getProjectName(); 
    	
        // pcf oluþturma
    	System.out.println ("c:\\f2\\common_pcf.bat " + projectName + " "+ getProjectPath() + getLanguage() +" c:\\f2");
    	CommandExecution ce = new CommandExecution("c:\\f2\\common_pcf.bat " + projectName + " "+ getProjectPath() + getLanguage() +" c:\\f2", 1);
    //	System.out.println ("c:\\f2\\common_pcf.bat " + projectName + " "+ getProjectPath() + " JDK1.7  *.java+12  c:\\f2");
    //	CommandExecution ce = new CommandExecution("c:\\f2\\common_pcf.bat " + projectName + " "+ getProjectPath() + " JDK1.7  *.java+12  c:\\f2", 1);

    	WriteFile(); 
    	String batchFileName = getPcfPath() + "\\" +"PCFandCSVCreation.bat";
    	CommandExecution ce2 = new CommandExecution(batchFileName, 0);
    	/*try {
			Process proc = Runtime.getRuntime().exec("c:\\f2\\common_pcf.bat " + projectName + " "+ getProjectPath() + " JDK1.7  *.java+12*.jsp  c:\\f2");
			System.out.println("...creating PCF project...");
			System.out.println("...please wait until process completion ...");
			proc.waitFor();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	
    	
    }
    
    
    public  void WriteFile() 
    {
    	  
    	BufferedWriter bufferedWriter = null;
    	File f = new File(".");
    	
    	//DateTimeStr dtStr = new DateTimeStr();
    	
    	String batchFileName = getPcfPath() + "\\" +"PCFandCSVCreation.bat";
    	String projectName = getProjectName(); 
    	String pcfFileName = getPcfPath() + "\\" + projectName + "\\"+ projectName +".pcf";
    	//String resultFileName = getPcfPath() + "\\" + projectName +"\\CSV\\"+projectName+"_GENEL_"+dtStr.dtString("yyyyMMdd")+"_McCabe_GENELNESNE.csv"; 
    	
	        try {

	        	
	        	String content = "C:"; 
		        content = content + "\r\n" + "cd " ;		        
		        content = content + System.getenv("MCCABE");
		        
		        String echo = echoString (getMeasurementType(0));
		        String resultFileName  = getPcfPath() + "\\" + projectName +"\\CSV\\"+projectName+"_GENEL_"+getDateStr()+"_McCabe_GENELSATIR.csv"; 
		        content = content + "\r\n" ;
	        	content = content +  echo +"cli metrics -report " + "GENELSATIR" + " -pcf " + pcfFileName + " -ssheader -ss -ssdelim \",\" -output \"" +  resultFileName + "\"";
	        	
	        	echo = echoString (getMeasurementType(1));
	        	resultFileName = getPcfPath() + "\\" + projectName +"\\CSV\\"+projectName+"_GENEL_"+getDateStr()+"_McCabe_GENELKARMASIKLIK.csv"; 
	        	content = content + "\r\n" ;
	        	content = content +  echo + "cli metrics -report " + "GENELKARMASIKLIK" + " -pcf " + pcfFileName + " -ssheader -ss -ssdelim \",\" -output \"" + resultFileName + "\"";
	        	
	        	echo = echoString (getMeasurementType(2));
	        	resultFileName = getPcfPath() + "\\" + projectName +"\\CSV\\"+projectName+"_GENEL_"+getDateStr()+"_McCabe_GENELNESNE.csv";
	        	content = content + "\r\n" ;
	        	content = content +  echo + "cli metrics -report " + "GENELNESNE" + " -pcf " + pcfFileName + " -ssheader -ss -ssdelim \",\" -output \"" +  resultFileName + "\"";
		        
	        	echo = echoString (getMeasurementType(3));
	        	resultFileName = getPcfPath() + "\\" + projectName +"\\CSV\\"+projectName+"_GENEL_"+getDateStr()+"_McCabe_GENELBAKIM.csv"; 
	        	content = content + "\r\n" ;
	        	content = content +  echo + "cli metrics -report " + "GENELBAKIM" + " -pcf " + pcfFileName + " -ssheader -ss -ssdelim \",\" -output \"" +  resultFileName + "\"";

	        	content = content + "\r\n" ;
	        	content = content + "\r\n" ;
		        content = content +  "echo McCabe CSV Rapor oluþturma iþlemi tamamlandý. ";
		        content = content + "\r\n" ;
		        content = content + "\r\n" ;
		        
		        System.out.println ("Sonuç dosyasý adý " + resultFileName);
		        System.out.println ("PCF dosya adý : " + pcfFileName);
		   
		      
	        	//Construct the BufferedWriter object
	        	bufferedWriter = new BufferedWriter(new FileWriter(batchFileName));
	            bufferedWriter.write(content); 
	            bufferedWriter.newLine();
	        
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            //Close the BufferedWriter
	            try {
	                if (bufferedWriter != null) {
	                    bufferedWriter.flush();
	                    bufferedWriter.close();
	                }
	                
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }   
    	
    };
    

}
