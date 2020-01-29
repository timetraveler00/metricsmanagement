import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class UnderstandCSV extends BaseTool {

    public UnderstandCSV() {
		
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
    	System.out.println ("UnderstandCSV::ExecuteFile()");
    DateTimeStr dtStr = new DateTimeStr(); 
    int sec = Integer.parseInt(dtStr.dtString("ss"));
    	
    		String batchFileName = getPcfPath() + "\\" +"UnderstandCSVCreation_"+Integer.toString(1)+".bat";
    		CommandExecution ce = new CommandExecution(batchFileName, 0);
    		    	
    };
    

    
    public void WriteFile(int metricType) 
    {
    	System.out.println ("UnderstandCSV::WriteFile(int)");
    	BufferedWriter bufferedWriter = null;
    	File f = new File(".");
    	String batchFileName = getPcfPath() + "\\" +"UnderstandCSVCreation_"+Integer.toString(metricType)+".bat";
    	//DateTimeStr dtStr = new DateTimeStr(); 
    	
    	String projectName = getProjectName(); 
    	String measurementType ;
    	String resultFileName = getPcfPath() + "\\" + projectName +"\\CSV\\"+projectName+"_GENEL_"+getDateStr()+"_Understand_";
	        try {
	        	bufferedWriter = new BufferedWriter(new FileWriter(batchFileName));
	        	String content = "\r\n"; 
	        	content = content + "\r\n"; 
	        	content = content + "C:"; 
		        content = content + "\r\n" + "cd " ;		        
		        content = content + System.getenv("UNDERSTAND");
		        content = content + "\r\n" ;
		        //content = content + "und create -db "+ getProjectPath() +"\\" + getProjectName() +".udb -languages c++ c# java python web";
		        content = content + "und create -db "+ getProjectPath() +"\\" + getProjectName() +".udb -languages web";
		        content = content + "\r\n" ;
		        content = content + "und -db "+ getProjectPath() +"\\" + getProjectName() +".udb add "+ getProjectPath();
		        content = content + "\r\n" ;
		        content = content + "\r\n";
		        String postfix = ""; 
	            String echo;
		       
		        for (int i=0; i<3; i++) 
		        {
				         echo = echoString (getMeasurementType(i));
		        		content = content + "\r\n" ;
			        	content = content + echo + "und -db "+ getProjectPath() +"\\" + getProjectName() +".udb analyze -all";
		        		content = content + "\r\n";
		        		
		        		 switch (i)
		          	    {
		          	    case 0: 
		          	    	content = content + echo + "und settings -metrics CountLineCode CountLineComment CountLineBlank "+ getProjectPath() +"\\" + getProjectName() +".udb";
		          	    	postfix = "GENELSATIR.CSV";
		          	    break; 
		          	    case 1: 
		          	    	content = content + echo + "und settings -metrics Essential Cyclomatic CyclomaticModified CyclomaticStrict "+ getProjectPath() +"\\" + getProjectName() +".udb";
		          	    	postfix =  "GENELKARMASIKLIK.CSV";
		          	    break; 
		          	    case 2: 
		          	    	content = content +  echo + "und settings -metrics SumCyclomatic AvgCyclomatic MaxCyclomatic CountClassDerived MaxInheritanceTree CountDeclMethodAll CountDeclMethod CountClassCoupled PercentLackOfCohesion CountClassBase MaxEssential "+ getProjectPath() +"\\" + getProjectName() +".udb";
		          	    	postfix=  "GENELNESNE.CSV";
		        	    	break; 
		          	    }
		        		content = content + "\r\n";
				        content = content + "\r\n" ;
				        content = content + echo + "und settings -MetricOutputFile "+ resultFileName+postfix + " "+ getProjectPath() +"\\" + getProjectName() +".udb";
				        content = content + "\r\n" ;
				        content = content + echo + "und settings -MetricFileNameDisplayMode fullpath "+ getProjectPath() +"\\" + getProjectName() +".udb";
				        content = content + "\r\n" ;
				        content = content + echo + "und metrics "+ getProjectPath() +"\\" + getProjectName() +".udb";
                   
		        }
				   
     	        content = content + "\r\n" ;
		        //content = content + "del "+ getProjectPath() +"\\" + getProjectName() +".udb" ;
		        content = content + "\r\n" ;
		        content = content + "c:";
		        content = content + "\r\n" ;
		        content = content + "cd \\" ;
		        content = content + "\r\n" ;
		        content = content + "cd f2" ;
		        content = content + "\r\n" ;
		        content = content + "echo tamamlandi";
		        content = content + "\r\n" ;
	            bufferedWriter.write(content); 
	            bufferedWriter.newLine(); 
	            
		        System.out.println ("Batch dosyasý adý " + batchFileName);
		        System.out.println ("Sonuç dosyasý adý " + resultFileName);
	   
		        //Construct the BufferedWriter object
	        
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {

	        	SafeClose(bufferedWriter); 
	        }   
	        
	 	
    }
    
    
    public void SafeClose (BufferedWriter bufferedWriter)
    {
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
   
    public  void WriteFile() 
    {
    	//for (int i=1;i<4;i++) 
    	System.out.println ("WriteFile()");	
    	WriteFile(1);
    	
    }
}
