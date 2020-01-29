import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MMFileWriter {
	
	
	protected  List <ArrayList<String>> lines = new ArrayList<ArrayList<String>>();

    public void WriteToFile (String target, ArrayList<String> content) 
    {
    	 BufferedWriter bufferedWriter = null;
		   
	        try {
	        	File f = new File(".");
	            //Construct the BufferedWriter object
	            bufferedWriter = new BufferedWriter(new FileWriter(target));
	            String wStr = ""; 
	            for (int i=0; i<content.size(); i++)
	            {
	            	
	            	 
	            	wStr = content.get(i) ;  
	            	if (wStr.compareTo("")!=0)
	            	{
	            	
	            		bufferedWriter.write(wStr); 
	            		 bufferedWriter.newLine();
	            	}
	            }
	            
	            bufferedWriter.close();
	            bufferedWriter = null ; 
	                 
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	        	
	        	bufferedWriter = StreamHelper.cleanClose (bufferedWriter); 
	            //Close the BufferedWriter
/*	            try {
	                if (bufferedWriter != null) {
	                    bufferedWriter.flush();
	                    bufferedWriter.close();
	                }
	                
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }*/
	        }
    	
    }
	
}
