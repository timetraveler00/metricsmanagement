import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class SimpleTable extends JPanel {

	private boolean DEBUG = false; 
	String[] columnNames; 
	Object [][] data ; 
	public SimpleTable() {
		
		super (new GridLayout(1,0));
		
		
			
	}
	public SimpleTable(String[] columnNames, Object[][] data) {
		
		super (new GridLayout(1,0));
		final JTable table = new JTable (data, columnNames); 
		table.setPreferredScrollableViewportSize(new Dimension(500,700)); 
		table.setFillsViewportHeight(true);
		
		if (DEBUG) 
		{
			table.addMouseListener(new MouseAdapter() 
			                       {
				                       public void mouseClicked (MouseEvent e) { 
			                    	      printDebugData(table); 
			                    	    }
			                       } ); 
		}
		
		JScrollPane scrollPane = new JScrollPane(table) ; 
		
		add (scrollPane) ; 
			
	}
	
	public void SetColumns (String [] cols) 
	{
		columnNames = new String [cols.length]; 
		for (int i=0; i<cols.length; i++) 
			columnNames[i] = cols[i]; 
	}
	
	public void SetData (OneMetricList oml) 
	{
		data = new Object [oml.GetSize()] [2]; 
		for (int i=0; i<oml.GetSize(); i++) 
		{
			data[i][0] = oml.Get(i).getModuleName(); 
			data[i][1] = oml.Get(i).getValue();
		}
		
	
	}
	
	public void SetTableData (String table[][]) 
	{
		data = table; //new Object [table.length] [7]; 
		/*for (int i=0; i<table.length; i++) 
		{
			data[i][0] = table[]; 
			data[i][1] = oml.Get(i).getValue();
		}*/
		
	
	}
	

	
	private void printDebugData (JTable table) 
	{
		int numRows = table.getRowCount(); 
		int numCols = table.getColumnCount(); 
		javax.swing.table.TableModel model = table.getModel(); 
		
		System.out.println ("Value of data ");
		for (int i=0; i < numRows; i++ ) 
		{
			System.out.print ("    row " + i + ":");
			for (int j=0; j<numCols ; j++)
			{
				System.out.print ("   "+model.getValueAt(i , j)); 
			}
			System.out.println();
		}
			 
		System.out.println("-----------------------------");
		
	}
	
	public void createandShowGUI(String frameName) 
	{
		JFrame frame = new JFrame (frameName) ; 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SimpleTable newContentPane = new SimpleTable(columnNames, data); 
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		
		frame.pack();
		frame.setVisible(true);

	}
	
	
	
	
	
	
}
