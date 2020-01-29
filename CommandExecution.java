import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CommandExecution {

	public CommandExecution(String command, int type) {
		
		// TODO Auto-generated constructor stub
		// Ýki farklý process iþletimi alternatifi sunuyoruz. 
		// Hangisi çalýþýrsa o kullanýlabilir. 
		try {
				
				Process p = null; 
				if (type == 0) 
				{
					ProcessBuilder pb = new ProcessBuilder(command);
					p = pb.start();
				}
				else if (type == 1)
				{
					p = Runtime.getRuntime().exec(command);
				}
				
				if (p != null) 
				{
				
					InputStream is = p.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr); 
				
					String line; 
				
					while ((line = br.readLine()) != null )
					{
						System.out.println(line);
					};
				
				}
				else 
				{
					System.out.print("");
					System.out.print(command + " prosesi baþlatýlamadý !....");
					System.out.print("");
					
					
				}
				
				//p.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
}
