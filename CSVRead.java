import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfviewer.PageWrapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;



public class CSVRead {


	
	 //public List mList = new ArrayList();
	 OneMetricList mainList = new OneMetricList();
	 
	 private int previous = 0; 
	 private String[] elements = new String[100]; 
	 	 
	 private JFrame frame ;
	 
	 JTabbedPane tabbedPane; 
	 JPanel panel1 = new JPanel(); 
	 JPanel panel2 = new JPanel(); 
     JPanel panel3 = new JPanel(); 
	 JPanel panel4 = new JPanel(); 
	 JPanel panel5 = new JPanel(); 
	 JPanel panel6 = new JPanel(); 
	 JPanel panel7 = new JPanel(); 
	 JPanel panel8 = new JPanel(); 
	 JButton fileDlgBtn = new JButton("Select CSV Files");
	 JButton fileDlgNASA = new JButton("PROMISE Metric Set");
	 JButton fileDlgBtn2 = new JButton("Browse Source");
	 JButton fileDlgBtn3 = new JButton("Browse Source"); 
	 JButton bStartMeasure = new JButton("Start Measuring");
	 JButton fileDlgBtn_Und = new JButton("Understand Measure");
	 JButton fileDlgBtn_Und2 = new JButton("Understand Execute");
	 JButton listMetrics = new JButton ("List");
	 JButton showTrend = new JButton ("Show Trend");
	 JButton showTrend2 = new JButton ("Show Trend");
	 JButton qrrTrend1 = new JButton ("QRR Trend");
	 JButton qrrTrend2 = new JButton ("QRR Trend");
	 
	 
	 //String logString = "";
	 String fileName="";  
	 JTextArea textArea1  = new JTextArea ();
	 JTextArea textAreaVG  = new JTextArea ();
	 JTextArea textAreaEVG  = new JTextArea ();
	 JTextArea textAreaLOC  = new JTextArea ();
	 JTextField fileProp = new JTextField("Selected file or folder will be shown here...");
	 JTextArea taProjName= new JTextArea(1,10);
	 JTextArea taMeasDate= new JTextArea(1,10);
	 
	 String projectFolderName ;
	 static String pcfFolderName = "c:\\f2";
	 
	 //CheckboxGroup cbgr = new CheckboxGroup(); 
	 JCheckBox cb1 = new JCheckBox("McCabe",  true);
	 JCheckBox cb2 = new JCheckBox("Understand",  true);
	 
	 
	 //CheckboxGroup mtgr = new CheckboxGroup(); 
	 JCheckBox mt1 = new JCheckBox("GENELSATIR", true);
	 JCheckBox mt2 = new JCheckBox("GENELKARMASIKLIK", true);
	 JCheckBox mt3 = new JCheckBox("GENELNESNE", true);
	 JCheckBox mt4 = new JCheckBox("GENELBAKIM", true);
	 
	 
	 Font labelFont = new Font ("Serif", Font.BOLD, 14);
	 Font standartFont = new Font ("Serif", Font.PLAIN, 12);
	 
	 
	 boolean MCCABE = true; 
	 boolean UNDERSTAND = true;
	 boolean GENELKARMASIKLIK = true;
	 boolean GENELSATIR = true;
	 boolean GENELNESNE = true;
	 boolean GENELBAKIM = true;
	 
	 String [] understandNaming = {"Method", "Function", "Class"};   
	 
	 JComboBox cbProjects = new JComboBox();
	 JComboBox cbMetrics = new JComboBox();
	 JComboBox cbTools = new JComboBox();
	 JComboBox cbDates = new JComboBox();
	 
	 JComboBox cbProjects2 = new JComboBox();
	 JComboBox cbProjects3 = new JComboBox();
	 JComboBox cbProjects4 = new JComboBox();
	 JComboBox cbProjects5 = new JComboBox();
	 
	 JComboBox cbMetrics2a = new JComboBox();
	 JComboBox cbMetrics2b = new JComboBox();
	 JComboBox cbMetrics3a = new JComboBox();
	 JComboBox cbMetrics3b = new JComboBox();
	 
	 JComboBox cbTools2 = new JComboBox();
	 JComboBox cbTools4 = new JComboBox();
	 JComboBox cbTools5 = new JComboBox();
	 DefaultListModel model2 = new DefaultListModel(); 
	 DefaultListModel model3 = new DefaultListModel();
	 DefaultListModel model4 = new DefaultListModel();
	 DefaultListModel model5 = new DefaultListModel();
	 JList lbDates2 = new JList(model2);
	 JList lbDates3 = new JList(model3); 
	 JList lbDates4 = new JList(model4);
	 JList lbDates5 = new JList(model4);
	 
	 DefaultListModel methodModel4 = new DefaultListModel();
	 DefaultListModel classModel5 = new DefaultListModel();
	 
	 JList lbMethods4 = new JList(methodModel4);
	 JList lbClasses5 = new JList(classModel5);
	 
	 
	  JRadioButton sumButton1 = new JRadioButton("Sum"); 
	  JRadioButton sumButton2 = new JRadioButton("Sum");
	  JRadioButton maxButton1 = new JRadioButton("Max"); 
	  JRadioButton maxButton2 = new JRadioButton("Max");
	  JRadioButton avgButton1 = new JRadioButton("Avg"); 
	  JRadioButton avgButton2 = new JRadioButton("Avg");
	  JRadioButton excButton1 = new JRadioButton("Exc",true); 
	  JRadioButton excButton2 = new JRadioButton("Exc",true);
	  JRadioButton sumButton1a = new JRadioButton("Sum"); 
	  JRadioButton sumButton2a = new JRadioButton("Sum");
	  JRadioButton maxButton1a = new JRadioButton("Max"); 
	  JRadioButton maxButton2a = new JRadioButton("Max");
	  JRadioButton avgButton1a = new JRadioButton("Avg"); 
	  JRadioButton avgButton2a = new JRadioButton("Avg");
	  JRadioButton excButton1a = new JRadioButton("Exc",true); 
	  JRadioButton excButton2a = new JRadioButton("Exc",true);
	 private MetricInformation mi ; 
	 
	 JRadioButton lanJava = new JRadioButton("Java",true); 
	  JRadioButton lanCSharp = new JRadioButton("C#");
	  JRadioButton lanCPlus = new JRadioButton("C++");
	  JRadioButton lanJavaScript = new JRadioButton("JavaScript"); 
	  
	  
	  JCheckBox jcbFuzzy = new JCheckBox("Fuzzy");
	  
	  private String languageString = "Java"; 
	  
      ArrayList<String> logString = new ArrayList<String>(); 
	  
	  

	 Graphics gg = null;
	 
	 private DrawCanvas canvas; 
	 
	 String primaryOp = "Excedance";
	 String secondaryOp = "Excedance";
	 
		String previousModule = "";  
		int sameModuleCounter = 0; 
		String[] keyList = new String [30]; 		
		String cdatetime;
	  
     //g = bf.getDrawGraphics(); 
	 
	 
	 
	 OneMetric tempMetric; 
	 public boolean fuzzyFlag = false; 
	 
	 
	/* @Override public void start (Stage stage)
	 {
		 stage.setTitle ("Line Chart");  
		 final NumberAxis xAxis = new NumberAxis(); 
		  final NumberAxis yAxis = new NumberAxis();
		  final LineChart <Number, Number> lineChart  = new LineChart <Number, Number> (xAxis, yAxis) ; 
		  XYChart.Series series = new XYChart.Series (); 
		  Scene scene = new Scene(lineChart, 800, 600);
		  stage.setScene (scene);
		  stage.show(); 
	 }*/
	 
	 
	 
	 
	 
	 private class DrawCanvas extends JPanel 
	 {
		
		 String [][] data = new String [3][20]; 
		 boolean dataFlag = false; 
		 @Override
		public void paintComponent (Graphics g) 
		{
			
			if (dataFlag ) 
			{
			 
			super.paintComponent(g); 
			setBackground(Color.BLACK); 
			
			g.setColor(Color.YELLOW);
			double[] doubleData ; 
			
			int horInterval = 300/data[0].length; 
	        		

				
				double max1 = Max (data[1]);      
				double scaleFactor1 = 180 / max1 ; 
				double max2 = Max (data[2]);      
				double scaleFactor2 = 180 / max2 ; 
				
				g.drawString(Double.toString(max1), (int) (0) , (int) (10));
				g.drawString(Double.toString(max2), (int) (290) , (int) (10));
				for (int i=0; i<data[0].length; i++) 
				{
					g.drawString(data[0][i], 20 + horInterval * i , 200 ); 
					double location1 = 200-(Double.parseDouble(data[1][i])*scaleFactor1);
					double location2 = 200-(Double.parseDouble(data[2][i])*scaleFactor2);
					//System.out.println ("max : "+max1);
					//System.out.println ("scaleFactor :" + scaleFactor1);
					 //System.out.println ("location : " + location1);
					g.drawString("X", (int) (20 + horInterval * i) , (int) (location1));
					g.drawString("O", (int) (20 + horInterval * i) , (int) (location2));
				}
				 
				g.drawLine(30, 40, 100, 200);
				g.drawString("Testing ...", 10, 20);
			
			}
		}
		 
		 public double Max (String [] row) 
		 {
			 double max = 0; 
			 for (int i=0; i<row.length; i++) 
			 {
				 //System.out.println (row[i]);
				 double value = Double.parseDouble(row[i]); 
				 
				 
				 if (value > max )
				     max = value; 
					 
			 }
			 return max; 
			 
		 }
		 
		public void setData (String [][] dat)
		{
			
			
			data = new String [3][]; 
			for (int i=0; i<3; i++) 
				data[i] = new String [dat[0].length]; 
			
			for (int i=0; i<3; i++) 
			{
				for (int j=0; j<dat[0].length; j++) 
					data[i][j] = dat[i][j];
				
							 
			}
			
			dataFlag = true; 
		}
		 
		 
		 
	 }	 
	 
	 
	 /*public class LineChart extends Application {
		 
		 @Override public void start (Stage stage)
		 {
			 stage.setTitle ("Line Chart");  
			 final NumberAxis xAxis = new NumberAxis(); 
			  final NumberAxis yAxis = new NumberAxis();
			  final LineChart <Number, Number> lineChart  = new LineChart <Number, Number> (xAxis, yAxis) ; 
			  Scene scene = new Scene(lineChart, 800, 600);
			  stage.setScene (scene);
			  stage.show(); 
		 }
		 
	 }*/
		
  public static void main(String[] args) {
  
	  
		  
	  	  
	  
	
	  CSVRead obj = new CSVRead();
	 
		
		
		
	 //  obj.run_("c:\\f1\\ws2a.txt");
	  obj.gui();
	  

	
	  

	
	  
  }
  
  
  public void pdfText (PDPageContentStream contentStream, Color c, int fontSize, float xx, float yy, String text) throws IOException 
  {
	  contentStream.beginText(); 
	  contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
	    contentStream.setNonStrokingColor(c);
	  
		contentStream.moveTextPositionByAmount(xx,yy);
		contentStream.drawString(text);
		contentStream.endText();
	  
  }
  
  
  public void drawTable(PDPage page, PDPageContentStream contentStream,
          float y, float margin,
          String[][] content, String headerString) throws IOException {
final int rows = content.length+1;
final int cols = content[0].length;
final float rowHeight = 20f;
final float pageWidth = page.findMediaBox().getWidth();
final float tableWidth = pageWidth -(2*margin);
final float tableHeight = rowHeight * rows;
final float colWidth = tableWidth*4/(float)((cols-1)*10);
final float cellMargin=3f;

//draw the rows
float nexty = y ;
for (int i = 0; i <= rows; i++) {
	contentStream.drawLine(margin,nexty,pageWidth-margin,nexty);
	nexty-= rowHeight;
}

//draw the columns

contentStream.drawLine(margin ,y,margin, y-tableHeight);
contentStream.drawLine(pageWidth-margin ,y,pageWidth-margin, y-tableHeight);
float nextx = margin + tableWidth * 6/10;

for (int i = 1; i <= cols; i++) {
	contentStream.drawLine(nextx,y-rowHeight, nextx,y-tableHeight);
	nextx += colWidth;
}

//now add the text

pdfText (contentStream, Color.BLACK, 8, margin + cellMargin + 10, y-15, headerString);  

contentStream.setFont(PDType1Font.HELVETICA_BOLD,8);
contentStream.setNonStrokingColor(Color.BLACK);


float textx = margin+cellMargin;
float texty = y-15-rowHeight;
for(int i = 0; i < content.length; i++){
	
	textx = margin+cellMargin;
	String text = content[i][0];
	
	int rowcount = text.length()/100;
	int rowremainder = text.length()%100;
	String []textrows = new String[50];
	int [] textyc = new int [50];
	int k=0; 
	for (k=0;k<rowcount;k++) 
	{
		textrows [k] = text.substring(k*100,k*100+100);
		 
	}
	//System.out.println(text + " rowcount " + rowcount + " rowremainder "+rowremainder + "k " + k);
		
	textrows[k] = text.substring(k*100,k*100+rowremainder);
	
    k++; 
	
	for (int l=0; l<k;l++) 
	{
	    pdfText (contentStream, Color.BLACK, 5, margin+cellMargin,texty +7 - (colWidth/(k+1))*l, textrows [l]);
	 }
	textx += tableWidth * 6 / 10; 	
	TableContent(textx, texty, tableHeight,  colWidth,  content[i], contentStream); 
	texty-=rowHeight; 
	textx = margin+cellMargin;
	
	/*contentStream.beginText();
	contentStream.moveTextPositionByAmount(margin+cellMargin,texty);
	contentStream.drawString(text);
	contentStream.endText();
	*/ 
	/*int fontSize = 8; 
	if (text.length()>80) 
		fontSize = 5; 
	else if (text.length()>70)
		fontSize = 6; 
	else if (text.length()>80)
		fontSize = 7;
	else 
		fontSize = 8; 
	*/	
		
	//pdfText (contentStream, Color.BLACK, 6, margin+cellMargin,texty, text);  
	
	
	
}	


}

public void TableContent ( float textx, float texty, float tableWidth,  float colWidth,  String[] contenti,  PDPageContentStream contentStream ) 
throws IOException 
{
	String text; 
	
	for(int j = 1 ; j < contenti.length; j++){
		text = contenti[j];
        

		
		
		//pdfText (contentStream, Color.BLACK, 8, textx,texty, text);
		//System.out.print(text+"  ");
		if (text!= null && text.contains("#") )
		{
			pdfText (contentStream, Color.RED, 6, textx,texty, text.substring(1, text.length()));  
			
		}
		else if  (text!= null && text.contains("$") )
		{
			pdfText (contentStream, Color.ORANGE, 6, textx,texty, text.substring(1, text.length()));
		}
		else 
		     pdfText (contentStream, Color.BLACK, 6, textx,texty, text);
		
		textx += colWidth;
	}

	//System.out.println("  ");
  
}
  
  
private static DefaultCategoryDataset createDataset(String [][] data) {
	// TODO Auto-generated method stub
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	for (int i=1; i< data.length; i++)
	{
		for (int j=1; j<data[0].length; j++) 
		{
			dataset.addValue (Double.parseDouble(data[i][j]), data[i][0],data[0][j]); 
		}
	}  
		
	return dataset;
}

public boolean IsUnderstandNaming (String mName, String fName) 
  {
      //  System.out.println(fName); 
	  if ( (mName.contains("Method")  || mName.contains("Function") ) && (fName.compareTo("GENELSATIR")==0 || fName.compareTo("GENELKARMASIKLIK")==0 ) )
		  return true;
	  
	  else if ( (mName.contains("Class")) && (fName.compareTo("GENELNESNE")==0) )
		  return true;
	  
	  return false; 

	  /*
	  for (int i=0; i<understandNaming.length; i++) 
	  {
		  if (mName.contains (understandNaming[i])) 
		  {
			  return true;    
		  }
		  
	  }
	  return false;*/ 
  }
  
	public void tabbedPane () 
	{
		tabbedPane = new JTabbedPane(); 
		

		tabbedPane.addTab("Measurement",null,panel6); 
		tabbedPane.addTab("Analyze",null,panel1);
	
		tabbedPane.addTab("Trend",null,panel3);
		tabbedPane.addTab("Tool Comparison",null,panel4);
		tabbedPane.addTab("methodQRR Trend",null,panel5);
		tabbedPane.addTab("classQRR Trend",null,panel7);
		tabbedPane.setSelectedIndex(0);
		 tabbedPane.setEnabledAt(1, false);
		   tabbedPane.setEnabledAt(2, false);
		   tabbedPane.setEnabledAt(3, false);
		   tabbedPane.setEnabledAt(4, false);
		   tabbedPane.setEnabledAt(5, false);
		
		//tabbedPane.addTab("System Testing",null,panel7);
		//tabbedPane.addTab("Maintenance",null,panel8);
	}
	


	
	
	class ShowTrend2 implements ActionListener {
		
	
		
		public double primaryOperation (OneMetricList oml, String selectedMetrik1) 
		{
			
			
			double returnValue = 0.0; 
			 
			if (sumButton1a.isSelected()) 
			{
				returnValue = oml.Sum(selectedMetrik1);
				primaryOp ="Sum"; 
			
			}	
			else if (avgButton1a.isSelected()) 
			{
				returnValue =  oml.Average(selectedMetrik1);
				primaryOp ="Avg"; 
			}
			else if (maxButton1a.isSelected())
			{
				returnValue =  oml.Maximum(selectedMetrik1);
				primaryOp ="Max"; 
			}
			else if (excButton1a.isSelected())
			{
				returnValue =  oml.ThresholdExceedanceCount(selectedMetrik1, mi);
				//System.out.println ("===  "+returnValue);
				primaryOp ="Esik asimi"; 
			}
			
			
			return returnValue; 
		}
		
		public double secondaryOperation (OneMetricList oml, String selectedMetrik2) 
		{
			double returnValue = 0.0; 
			 
			if (sumButton2a.isSelected()) 
			{
				returnValue = oml.Sum(selectedMetrik2);
				secondaryOp ="Sum"; 
			
			}	
			else if (avgButton2a.isSelected()) 
			{
				returnValue =  oml.Average(selectedMetrik2);
				secondaryOp ="Avg"; 
			}
			else if (maxButton2a.isSelected())
			{
				returnValue =  oml.Maximum(selectedMetrik2);
				secondaryOp ="Max"; 
			}
			else if (excButton2a.isSelected())
			{
				returnValue =  oml.ThresholdExceedanceCount(selectedMetrik2, mi);
				secondaryOp ="Esik asimi"; 
			}
			
			
			return returnValue; 
		}
		public void actionPerformed(ActionEvent e) 
			{
				String selectedProject = cbProjects3.getSelectedItem().toString();
				String selectedMetrik1 = cbMetrics3a.getSelectedItem().toString();
				String selectedMetrik2 = cbMetrics3b.getSelectedItem().toString();
				
				
				int[] indices = lbDates3.getSelectedIndices();
				OneMetricList [] omlList = new OneMetricList[indices.length]; 
				
				String[][] datam = new String [5][];
				for (int i=0; i<5 ; i++) 
				{
					datam[i] = new String[indices.length+1]; 
				}
				
				
				
				for (int i=0; i<indices.length; i++) 
				{
						
					    omlList[i] = new OneMetricList(mainList); 
						String selectedDate = lbDates3.getModel().getElementAt(i).toString(); 
						omlList[i].Filter_p(selectedProject, selectedDate, "McCabe");
					
						datam [0][i+1] = selectedDate; 
						datam [1][i+1] = Double.toString(primaryOperation (omlList[i], selectedMetrik1));
						datam [2][i+1] = Double.toString(secondaryOperation (omlList[i], selectedMetrik2));
						
						omlList[i] = new OneMetricList(mainList); 
						omlList[i].Filter_p(selectedProject, selectedDate, "Understand");
						datam [3][i+1] = Double.toString(primaryOperation (omlList[i], selectedMetrik1));
						datam [4][i+1] = Double.toString(secondaryOperation (omlList[i], selectedMetrik2));
					 	
				}
			
				datam [0][0] = "."; 
				datam [1][0] = selectedMetrik1 + " m  "+ primaryOp; 
				datam [2][0] = selectedMetrik2 + " m " + secondaryOp; 
				datam [3][0] = selectedMetrik1 + " u " + primaryOp; 
				datam [4][0] = selectedMetrik2 + " u " + secondaryOp;
				/*for (int i=0; i<3; i++) 
				{
					
					for (int j=0; j<indices.length+1; j++) 
					{
						System.out.print (datam[i][j] + " "); 
						
					}
					//System.out.println ( " ");
					
				}*/

				
				
		        final DualAxis demo = new DualAxis("Tool comparison by trend" + selectedProject + " "+selectedMetrik1 + " (" + primaryOp + ") - " + selectedMetrik2 + " (" + secondaryOp+ ") " , datam);
		        demo.pack();
		        RefineryUtilities.centerFrameOnScreen(demo);
		        demo.setVisible(true); 
		
		        frame.revalidate();
				frame.repaint();
		
			}
	}

class ShowTrend implements ActionListener {
		
		
	/*
	 @Override public void start (Stage stage)
	 {
		 stage.setTitle ("Line Chart");  
		 final NumberAxis xAxis = new NumberAxis(); 
		  final NumberAxis yAxis = new NumberAxis();
		  final LineChart <Number, Number> lineChart  = new LineChart <Number, Number> (xAxis, yAxis) ; 
		  XYChart.Series series = new XYChart.Series (); 
		  Scene scene = new Scene(lineChart, 800, 600);
		  stage.setScene (scene);
		  stage.show(); 
		  
	 }*/
	 
	public double primaryOperation (OneMetricList oml, String selectedMetrik1) 
	{
		
		
		double returnValue = 0.0; 
		 
		if (sumButton1.isSelected()) 
		{
			returnValue = oml.Sum(selectedMetrik1);
			primaryOp ="Sum"; 
		
		}	
		else if (avgButton1.isSelected()) 
		{
			returnValue =  oml.Average(selectedMetrik1);
			primaryOp ="Avg"; 
		}
		else if (maxButton1.isSelected())
		{
			returnValue =  oml.Maximum(selectedMetrik1);
			primaryOp ="Max"; 
		}
		else if (excButton1.isSelected())
		{
			returnValue =  oml.ThresholdExceedanceCount(selectedMetrik1,  mi);
			//System.out.println ("===  "+returnValue);
			primaryOp ="Esik asimi"; 
		}
		
		
		return returnValue; 
	}
	
	public double secondaryOperation (OneMetricList oml, String selectedMetrik2) 
	{
		double returnValue = 0.0; 
		 
		if (sumButton2.isSelected()) 
		{
			returnValue = oml.Sum(selectedMetrik2);
			secondaryOp ="Sum"; 
		
		}	
		else if (avgButton2.isSelected()) 
		{
			returnValue =  oml.Average(selectedMetrik2);
			secondaryOp ="Avg"; 
		}
		else if (maxButton2.isSelected())
		{
			returnValue =  oml.Maximum(selectedMetrik2);
			secondaryOp ="Max"; 
		}
		else if (excButton2.isSelected())
		{
			returnValue =  oml.ThresholdExceedanceCount(selectedMetrik2,  mi);
			secondaryOp ="Esik asimi"; 
		}
		
		
		return returnValue; 
	}
	
	public void actionPerformed(ActionEvent e) 
		{
			String selectedProject = cbProjects2.getSelectedItem().toString();
			String selectedTool = cbTools2.getSelectedItem().toString();
			String selectedMetrik1 = cbMetrics2a.getSelectedItem().toString();
			String selectedMetrik2 = cbMetrics2b.getSelectedItem().toString();
	
			int[] indices = lbDates2.getSelectedIndices();
			OneMetricList [] omlList = new OneMetricList[indices.length]; 
			
			String[][] datam = new String [3][];
			for (int i=0; i<3 ; i++) 
			{
				datam[i] = new String[indices.length+1]; 
			}
		for (int i=0; i<indices.length; i++) 
			{
				omlList[i] = new OneMetricList(mainList); 
				String selectedDate = lbDates2.getModel().getElementAt(indices[i]).toString(); 
				omlList[i].Filter_p(selectedProject, selectedDate, selectedTool);
				datam [0][i+1] = selectedDate; 
				datam [1][i+1] = Double.toString(primaryOperation (omlList[i], selectedMetrik1));
				datam [2][i+1] = Double.toString(secondaryOperation (omlList[i], selectedMetrik2));
			}
			datam [0][0] = "."; 
			datam [1][0] = selectedMetrik1 + " "+ primaryOp; 
			datam[2][0] = selectedMetrik2 + " " + secondaryOp; 
			/*/for (int i=0; i<3; i++) 
			{
				for (int j=0; j<indices.length+1; j++) 
				{
					System.out.print (datam[i][j] + " "); 
					
				}
 			//System.out.println ( " ");

			}*/
			 
	        final DualAxis demo = new DualAxis("Trend Graph" + selectedProject + " "+ selectedTool + " " +  selectedMetrik1 + " (" + primaryOp + ") - " + selectedMetrik2 + " (" + secondaryOp+ ") ", datam);
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true); 
			
			frame.revalidate();
			frame.repaint();
	
		}
}
	

class ShowTrendQRR implements ActionListener {
	
	    public void actionPerformed(ActionEvent e) 
		{
			String selectedProject = cbProjects4.getSelectedItem().toString();
			String selectedTool = cbTools4.getSelectedItem().toString();
			 
			
			
			int[] indices = lbDates4.getSelectedIndices();
			int[] methodIndices = lbMethods4.getSelectedIndices();
			OneMetricList [] omlList = new OneMetricList[indices.length]; 
			
			String[][] datam = new String [methodIndices.length+1][];
			for (int i=0; i<methodIndices.length+1 ; i++) 
			{
				datam[i] = new String[indices.length+1]; 
			}
			
			
			
			for (int i=0; i<indices.length; i++) 
			{
				omlList[i] = new OneMetricList(mainList); 
				String selectedDate = lbDates4.getModel().getElementAt(indices[i]).toString(); 
				omlList[i].Filter_p(selectedProject, selectedDate, selectedTool);
				
				
				datam [0][i+1] = selectedDate; 
				
				for (int j=0; j<methodIndices.length; j++) 
				{
				
					String selectedMethod = lbMethods4.getModel().getElementAt(methodIndices[j]).toString();  
					datam[j+1][0] = selectedMethod; 
					DecimalFormat df = new DecimalFormat("##.##", DecimalFormatSymbols.getInstance(Locale.US));
					//String formatted = df.format(omlList[i].getValueD(selectedMethod, "methodQRR")); 
					datam [j+1][i+1] = df.format(omlList[i].getValueD(selectedMethod, "methodQRR")); 
				}
				
				// System.out.print (datam[1][i+1] + " --- " + datam[2][i+1]);
				//OneMetric
				
				
			}
			datam [0][0] = "."; 
			
			/*for (int i=0; i<methodIndices.length+1; i++) 
			{
				
				for (int j=0; j<indices.length+1; j++) 
				{
					System.out.print (datam[i][j] + " "); 
					
				}
				System.out.println ( " ");
				
			}*/

			
			
	        final SimpleChart demo = new SimpleChart("QRR Trend Graph", datam);
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true); 
	        
		
			
			
			//canvas.setPreferredSize(new Dimension (640, 480));
		/*	canvas.setData (datam) ;
			
			  LineChartS lineChart = new LineChartS(); 
			  
			  lineChart.launch("");
			  lineChart.setData (datam); 
			  
			  
			//canvas.paintComponent(gg);
			canvas.repaint();
			
			*/
			
			
			frame.revalidate();
			frame.repaint();
	
		}
}

class ShowTrendQRR_OO implements ActionListener {
	
    public void actionPerformed(ActionEvent e) 
	{
		String selectedProject = cbProjects5.getSelectedItem().toString();
		String selectedTool = cbTools5.getSelectedItem().toString();
		
		 
		
		
		int[] indices = lbDates5.getSelectedIndices();
		int[] classIndices = lbClasses5.getSelectedIndices();
		OneMetricList [] omlList = new OneMetricList[indices.length]; 
		
		String[][] datam = new String [classIndices.length+1][];
		for (int i=0; i<classIndices.length+1 ; i++) 
		{
			datam[i] = new String[indices.length+1]; 
		}
		
		
		
		for (int i=0; i<indices.length; i++) 
		{
			omlList[i] = new OneMetricList(mainList); 
			String selectedDate = lbDates5.getModel().getElementAt(indices[i]).toString(); 
			
			omlList[i].Filter_p(selectedProject, selectedDate, selectedTool);
						
			datam [0][i+1] = selectedDate; 
			
			for (int j=0; j<classIndices.length; j++) 
			{
			
				String selectedClass = lbClasses5.getModel().getElementAt(classIndices[j]).toString();  
				datam[j+1][0] = selectedClass; 
				DecimalFormat df = new DecimalFormat("##.##", DecimalFormatSymbols.getInstance(Locale.US));
				//String formatted = df.format(omlList[i].getValueD(selectedMethod, "methodQRR")); 
				datam [j+1][i+1] = df.format(omlList[i].getValueD(selectedClass, "classQRR")); 
			}
			
			// System.out.print (datam[1][i+1] + " --- " + datam[2][i+1]);
			//OneMetric
			
			
		}
		datam [0][0] = "."; 
		
		/*for (int i=0; i<classIndices.length+1; i++) 
		{
			
			for (int j=0; j<indices.length+1; j++) 
			{
				System.out.print (datam[i][j] + " "); 
				
			}
			System.out.println ( " ");
			
		}*/

		
		
        final SimpleChart demo = new SimpleChart("QRR Trend Graph", datam);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true); 

        frame.revalidate();
		frame.repaint();

	}
}

class UpdateDates implements ActionListener {
	
	private JList list; 
	private DefaultListModel modl; 
	 
	public UpdateDates (JList jlst, DefaultListModel mdl) {
		list = jlst; 
		modl = mdl; 
		
		 
	}
	
	
	public void actionPerformed(ActionEvent e) 
    {
	  
		JComboBox combo = (JComboBox) e.getSource(); 
		String selectedProject = (String) combo.getSelectedItem(); 
		OneMetricList oml = new OneMetricList(mainList); 
		oml.Filter_p(selectedProject);
		Analyze anl = new Analyze (oml); 
	  
	  	  
	  List<String> dateList= anl.listDateTime();
	  if (dateList != null)
		  setListBoxContent(list, modl, dateList);
	  
	  frame.revalidate();
	  frame.repaint();
	}
	
}


class UpdateMethods implements ActionListener {
	
	private JList list; 
	private DefaultListModel modl; 

	public UpdateMethods ( JList jlst, DefaultListModel mdl) {
		list = jlst; 
		modl = mdl; 
		 
	}
	
	
   public void actionPerformed(ActionEvent e) 
{
		  SortOperations sort = new SortOperations(mainList, "methodQRR");
		  OneMetricList sortedList = sort.bubbleSort();
		  sortedList.revertList();
		
		
		String selectedProject = (String) cbProjects4.getSelectedItem();
		String selectedTool = (String) cbTools4.getSelectedItem();
		sortedList.Filter_pt(selectedProject, selectedTool);
		Analyze anl = new Analyze (sortedList); 

	  List<String> methodList= anl.listMethods();
	  if (methodList != null)
		  setListBoxContent(list, modl, methodList);
	  
	  frame.revalidate();
	  frame.repaint();
	  }
}

class UpdateClasses implements ActionListener {
	
	private JList list; 
	private DefaultListModel modl; 

	public UpdateClasses ( JList jlst, DefaultListModel mdl) {
		list = jlst; 
		modl = mdl; 
		 
	}
	
	
   public void actionPerformed(ActionEvent e) 
{
		
		  SortOperations sort = new SortOperations(mainList, "classQRR");
		  //OneMetricList sortedList = sort.quickSort();
		  OneMetricList sortedList = sort.bubbleSort();
		  sortedList.revertList();
		
		String selectedProject = (String) cbProjects5.getSelectedItem();
		String selectedTool = (String) cbTools5.getSelectedItem();
		
		sortedList.Filter_pt(selectedProject, selectedTool);
		Analyze anl = new Analyze (sortedList); 

	  List<String> classList= anl.listClasses();
	  if (classList != null)
		  setListBoxContent(list, modl, classList);
	  
	  frame.revalidate();
	  frame.repaint();
	  }
}

public void FileCopy (String from, String to ) 
{
	
	File savedFile = new File (from); 
	File newFile = new File (to);
	
  try {	
	FileInputStream instream = new FileInputStream(savedFile); 
	FileOutputStream outstream = new FileOutputStream(newFile); 
	
	byte [] buffer = new byte[1024]; 
	int length; 
	while ((length = instream.read(buffer))> 0) {
		outstream.write(buffer, 0 ,length);
	}
	
	instream.close(); 
	outstream.close(); 
	
	System.out.println("Console output is saved to :" +to);			
	
  } 
  catch (IOException ioe) 
  {
	  System.out.println("FAILURE : Console output can not be saved to :" + to);
	  ioe.printStackTrace();
  }
	
}

public void ConsoleOutput (String projectName) 
{
	enterMethod();
	try {
		int mmcounter = ReadStep(); 
		//System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("C:\\F8\\consoleoutput_"+mmcounter+".txt")),true));
		
		// konsol çýktýsýný "run configurations" belli bir dosyaya atýyor, biz de onu kopyalýyoruz. 
		FileCopy("C:\\F8\\mmLog.txt", "C:\\F8\\"+projectName +"_" +cdatetime+".txt");
		File logFile = new File ("c:\\f8\\mmlog.txt"); 
	     logFile.delete();
  
		WriteStep(++mmcounter);
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
}

public int ReadStep () 
{
	
	int experimentCounter = 0; 
	try{
		  // Open the file that is the first 
		  // command line parameter
		File f = new File(".");
		  //FileInputStream fstream = new FileInputStream(f.getAbsolutePath() + "\\..\\..\\Experiments\\ExperimentCounter.txt");
		FileInputStream fstream = new FileInputStream("c:\\f2\\MMCounter.txt");
		  //bufferedWriter = new BufferedWriter(new FileWriter(f.getAbsolutePath() + "\\..\\..\\Experiments\\"+cs+"_Results_All.txt"));
		  // Get the object of DataInputStream
		  DataInputStream in = new DataInputStream(fstream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		 
		  while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  //System.out.println (strLine);
			  
			 String content = strLine;  
		     String delimiter = "_"; 
			 String [] temp; 
			 temp = content.split(delimiter);
			 //System.out.println (temp[0]);
			  
			experimentCounter = Integer.parseInt(temp[0]); 
	
		  }
		  //Close the input stream
		  br.close(); 
		  in.close();
		  fstream.close(); 
		//  f.deleteOnExit() ;
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Read Experiment Counter Error: " + e.getMessage());
		  }
     return experimentCounter; 
	
}

public void WriteStep (int stepCount) 
{
   BufferedWriter bufferedWriter = null;
   
        try {
        	File f = new File(".");
                

            //bufferedWriter = new BufferedWriter(new FileWriter(f.getAbsolutePath() + "\\..\\..\\Experiments\\ExperimentCounter.txt"));
        	bufferedWriter = new BufferedWriter(new FileWriter("c:\\f2\\MMCounter.txt"));
            String wStr = Integer.toString(stepCount); 
           
            		bufferedWriter.write(wStr); 
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
	
}


class ListMetricValues implements ActionListener {
		
		public void actionPerformed(ActionEvent e) 
		{
			String selectedProject = cbProjects.getSelectedItem().toString();
			String selectedDate = cbDates.getSelectedItem().toString();
			String selectedTool = cbTools.getSelectedItem().toString();
			
			OneMetricList oml = new OneMetricList(mainList); 
			oml.Filter_p(selectedProject, selectedDate,selectedTool);
			if (oml.GetSize()>0) 
			{
				
				System.out.println("Caller : "+getCallerMethod(3)); 
				BigTable(oml,"identifier");
				
				BigTable_OO(oml);
				//BigTable_Program(oml);
				BigTable_Reliability(oml);
				BigTable_Maintainability(oml);
				BigTable_ProjectSummary(oml);
				
				BigTableQRRMethod(oml, "identifier");
				BigTableQRRClass(oml, "identifier");
				HeatMap hm = new HeatMap(1, oml); 

				
				
				
			}
			
			
		
		//System.out.println(selectedProject);
			
			
		}
}

class StartMeasure implements ActionListener {
	
	public void actionPerformed(ActionEvent e) 
	{
	    
		MeasurementSettings();
		
		if (MCCABE) 
	    {
	    
	    	McCabeCSV mccabe = new McCabeCSV(); 
	    	mccabe.setMeasurementType(GENELSATIR, GENELKARMASIKLIK, GENELNESNE, GENELBAKIM);
	    	mccabe.setLanguage(languageString); 
	    	mccabe.setPcfPath(pcfFolderName);
	    	mccabe.setProjectPath(projectFolderName);
	    	mccabe.setProjectName(taProjName.getText());
	    	mccabe.setDateStr(taMeasDate.getText());
	    	mccabe.CreatePCF();

	    } 
	    if (UNDERSTAND) 
	    {
	    	  UnderstandCSV understand = new UnderstandCSV(); 
	    	  understand.setMeasurementType(GENELSATIR, GENELKARMASIKLIK, GENELNESNE, GENELBAKIM);
	    	  understand.setPcfPath(pcfFolderName);
	    	  understand.setProjectPath(projectFolderName);
	    	  understand.setProjectName(taProjName.getText());
	    	  understand.setDateStr(taMeasDate.getText());
	    	  understand.CreateCSV();
	    }
	    
	    

		
	}
		
} 

class SetMeasurementPreferences implements ActionListener {
		
		public void actionPerformed(ActionEvent e) 
		{
			
			
			mainList.clear();
			JFileChooser chooser = new JFileChooser("D:\\EWS");
			chooser.setBounds(100, 100, 1200, 800);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			//chooser.setCurrentDirectory("c:\\f1\\");
			chooser.setMultiSelectionEnabled(false);
			chooser.showOpenDialog(frame);
			
			 
			projectFolderName = chooser.getSelectedFile().getPath();
			String projectName =  taProjName.getText(); // chooser.getSelectedFile().getName();
			
		    System.out.println ("Project source absolute path :"  + projectFolderName);
		    System.out.println ("Project name :"  + projectName);
		    UpdateFileName(projectFolderName);
		    
		     
			   frame.revalidate();
			   frame.repaint();
			
		}
			
}  



class FileSelection implements ActionListener {
		
		public void actionPerformed(ActionEvent e) 
		{
			
			//fuzzyFlag = jcbFuzzy.isSelected();
			fuzzyFlag = true; 
			//mainList.clear();
			JFileChooser chooser = new JFileChooser("c:\\f2");
			chooser.setBounds(100, 100, 1200, 800);
			//chooser.setCurrentDirectory("c:\\f1\\");
			chooser.setMultiSelectionEnabled(true);
			chooser.showOpenDialog(frame);
			
			File[] files = chooser.getSelectedFiles();
 
			for (int i=0; i<files.length; i++) 
			{
				//run_code("c:\\f1\\"+files[i].getName()); 
				fileName = files[i].getName();
				run_code(files[i].getAbsolutePath()); 
						
			    System.out.println(files[i].getName());
			} 
				
		    String str = AnalyzeReliability();
		    PanelUpdates();
		    

		    
		    			
			try {
				writeLogs(str);
				
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//sortedList(); 
			
			   tabbedPane.setEnabledAt(1, true);
			   tabbedPane.setEnabledAt(2, true);
			   tabbedPane.setEnabledAt(3, true);
			   tabbedPane.setEnabledAt(4, true);
			   tabbedPane.setEnabledAt(5, true);
			
			   frame.revalidate();
			   frame.repaint();
		    
			
		}
			
	}

class FileSelectionNASA implements ActionListener {
	
	public void actionPerformed(ActionEvent e) 
	{
		fuzzyFlag = jcbFuzzy.isSelected();
		//mainList.clear();
		JFileChooser chooser = new JFileChooser("c:\\f2");
		chooser.setBounds(100, 100, 1200, 800);
		//chooser.setCurrentDirectory("c:\\f1\\");
		chooser.setMultiSelectionEnabled(true);
		chooser.showOpenDialog(frame);
		
		File[] files = chooser.getSelectedFiles();

		for (int i=0; i<files.length; i++) 
		{
			//run_code("c:\\f1\\"+files[i].getName()); 
			fileName = files[i].getName();
			//run_code(files[i].getAbsolutePath()); 
			NASArun_code (files[i].getAbsolutePath(),i); 
			
					
		    System.out.println(files[i].getName());
		} 
			
	    String str = AnalyzeReliability();
	    PanelUpdates();
	    

	    
	    			
		try {
			writeLogs(str);
			
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//sortedList(); 
		
		   tabbedPane.setEnabledAt(1, true);
		   tabbedPane.setEnabledAt(2, true);
		   tabbedPane.setEnabledAt(3, true);
		   tabbedPane.setEnabledAt(4, true);
		   tabbedPane.setEnabledAt(5, true);
		
		   frame.revalidate();
		   frame.repaint();
	    
		
	}
		
}
  
  public void sortedList ()
  {
	  SortOperations sort = new SortOperations(mainList, "vg");
	  //OneMetricList sortedList = sort.quickSort();
	  OneMetricList sortedList = sort.bubbleSort();
	  sortedList.revertList();
	  
	  SortOperations sort2 = new SortOperations(mainList, "evg");
	  //OneMetricList sortedList = sort.quickSort();
	  OneMetricList sortedList2 = sort2.bubbleSort();
	  sortedList2.revertList();
	  
	  SortOperations sort3 = new SortOperations(mainList, "totalLOC");
	  //OneMetricList sortedList = sort.quickSort();
	  OneMetricList sortedList3 = sort3.bubbleSort();
	  sortedList3.revertList();
	  
	  SortOperations sort4 = new SortOperations(mainList, "LOCM");
	  //OneMetricList sortedList = sort.quickSort();
	  OneMetricList sortedList4 = sort4.bubbleSort();
	  sortedList4.revertList();
	  
	  String vgString = "";
	  String evgString = "";
	 /* for (int i=0; i<20; i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  System.out.println(om.getModuleName() + " - " + om.getValue());
		  vgString = vgString + om.getModuleName() + " - " + om.getValue() + "\r\n";
		  
		  OneMetric om2 = sortedList2.Get(i); 
		  System.out.println(om2.getModuleName() + " - " + om2.getValue());
		  evgString = evgString + om2.getModuleName() + " - " + om2.getValue() + "\r\n";
	  }*/
	  
	  textAreaVG.setText(vgString);
	  textAreaEVG.setText(evgString);
	  
	  SimpleTable st = new SimpleTable();
	  String [] cols = {"Module Name", "vg"};
	  st.SetColumns(cols);
	  st.SetData(sortedList);
	  st.createandShowGUI("Cyclomatic Complexity");
	  
	  SimpleTable st2 = new SimpleTable();
	  String [] cols2 = {"Module Name", "evg"};
	  st2.SetColumns(cols2);
	  st2.SetData(sortedList2);
	  st2.createandShowGUI("Essential Complexity"); 
	  
	  SimpleTable st3 = new SimpleTable();
	  String [] cols3 = {"Module Name", "totalLOC"};
	  st3.SetColumns(cols3);
	  st3.SetData(sortedList3);
	  st3.createandShowGUI("Total LOC");
	  
	  SimpleTable st4 = new SimpleTable();
	  String [] cols4 = {"Class Name", "LOCM"};
	  st4.SetColumns(cols4);
	  st4.SetData(sortedList4);
	  st4.createandShowGUI("LOCM"); 
	  
	  
	  //BigTable();
	  //BigTable_Understand();
	  //BigTable_OO();
	  
	 
  }
  
  
  public void BigTable () 
  {
	  
	  BigTable(mainList,"code");
	  
  } 
  
  public void BigTable2 (OneMetricList filtered) 
  {
	  
	  SortOperations sort = new SortOperations(filtered, "vg");
	  //OneMetricList sortedList = sort.quickSort();
	  OneMetricList sortedList = sort.bubbleSort();
	  sortedList.revertList();
	  
	   
	  String [][] bigTable= new String [sortedList.GetSize()][15]; 
	  
	  String [] cols = {"Module Name", "McCabe vg","Understand vg","McCabe evg","Understand evg","McCabe LOC","Understand LOC","McCabe code","Understand code","McCabe comment","Understand comment", "McCabe blank","Understand blank","McCabe mixed","Understand mixed"};
	    
	  
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[i][0] = om.getModuleName();
		  bigTable[i][1] = filtered.getValue(mName, "vg", "McCabe"); 
		  bigTable[i][2] = filtered.getValue(mName, "vg", "Understand");
		  bigTable[i][3] = filtered.getValue(mName, "evg", "McCabe"); 
		  bigTable[i][4] = filtered.getValue(mName, "evg", "Understand");
		  bigTable[i][5] = filtered.getValue(mName, "totalLOC", "McCabe");
		  bigTable[i][6] = filtered.getValue(mName, "totalLOC", "Understand");
		  bigTable[i][7] = filtered.getValue(mName, "code", "McCabe");
		  bigTable[i][8] = filtered.getValue(mName, "code", "Understand");
		  bigTable[i][9] = filtered.getValue(mName, "comment", "McCabe");
		  bigTable[i][10] = filtered.getValue(mName, "comment", "Understand");
		  bigTable[i][11] = filtered.getValue(mName, "blank", "McCabe");
		  bigTable[i][12] = filtered.getValue(mName, "blank", "Understand");
		  bigTable[i][13] = filtered.getValue(mName, "mixed", "McCabe");
		  bigTable[i][14] = filtered.getValue(mName, "mixed", "Understand");

	  }
	  
	  SimpleTable st5 = new SimpleTable();
	  
	  st5.SetColumns(cols);
	  st5.SetTableData(bigTable);
	  st5.createandShowGUI("Method Based Metrics");  
	  
	  
  } 
  
  public double[] SetMethodQRRCoefficients (OneMetricList oml) 
  {
	  String projectName = oml.Get(0).getProjectName();
	  double [] coefficients= new double [8]; 
	  
	  double toe_total = Double.parseDouble(oml.getValue(projectName, "code_toe")) + 
			             Double.parseDouble(oml.getValue(projectName, "comment_toe")) + 
			             Double.parseDouble(oml.getValue(projectName, "blank_toe")) +  
			             Double.parseDouble(oml.getValue(projectName, "mixed_toe")) + 
			             Double.parseDouble(oml.getValue(projectName, "vg_toe")) +
			             Double.parseDouble(oml.getValue(projectName, "evg_toe")) +
			             Double.parseDouble(oml.getValue(projectName, "totalLOC_toe"));
	  
	  double noe_total = Double.parseDouble(oml.getValue(projectName, "code_noe")) + 
	             Double.parseDouble(oml.getValue(projectName, "comment_noe")) + 
	             Double.parseDouble(oml.getValue(projectName, "blank_noe")) +  
	             Double.parseDouble(oml.getValue(projectName, "mixed_noe")) + 
	             Double.parseDouble(oml.getValue(projectName, "vg_noe")) +
	             Double.parseDouble(oml.getValue(projectName, "evg_noe")) +
	             Double.parseDouble(oml.getValue(projectName, "totalLOC_noe")); 
  
	  coefficients [1] = ( Double.parseDouble(oml.getValue(projectName, "vg_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "vg_noe"))/noe_total ) / 2; 
	  coefficients [2] = ( Double.parseDouble(oml.getValue(projectName, "evg_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "evg_noe"))/noe_total ) / 2;
	  coefficients [3] = ( Double.parseDouble(oml.getValue(projectName, "totalLOC_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "totalLOC_noe"))/noe_total ) / 2;
	  coefficients [4] = ( Double.parseDouble(oml.getValue(projectName, "code_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "code_noe"))/noe_total ) / 2;
	  coefficients [5] = ( Double.parseDouble(oml.getValue(projectName, "comment_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "comment_noe"))/noe_total ) / 2;
	  coefficients [6] = ( Double.parseDouble(oml.getValue(projectName, "blank_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "blank_noe"))/noe_total ) / 2;
	  coefficients [7] = ( Double.parseDouble(oml.getValue(projectName, "mixed_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "mixed_noe"))/noe_total ) / 2;
	  
	//  System.out.println("toe_total "  + toe_total);
	//  System.out.println("noe_total "  + noe_total); 
	  
	 /* 
	  for (int i=0; i<8; i++) 
		  System.out.println("coeff " + i + "     " + coefficients[i] ); 
	  */
	  return coefficients; 
	  
  }
  
  public double[] SetClassQRRCoefficients (OneMetricList oml) 
  {
	  enterMethod();
	  String projectName = oml.Get(0).getProjectName();
	  double [] coefficients= new double [7]; 
	  
	  double toe_total = Double.parseDouble(oml.getValue(projectName, "LOCM_toe")) + 
			             Double.parseDouble(oml.getValue(projectName, "CBO_toe")) + 
			             Double.parseDouble(oml.getValue(projectName, "DIT_toe")) +  
			             Double.parseDouble(oml.getValue(projectName, "WMC_toe")) + 
			             Double.parseDouble(oml.getValue(projectName, "RFC_toe")) +
			             Double.parseDouble(oml.getValue(projectName, "NOC_toe"));
	  
	  double noe_total = Double.parseDouble(oml.getValue(projectName, "LOCM_noe")) + 
	             Double.parseDouble(oml.getValue(projectName, "CBO_noe")) + 
	             Double.parseDouble(oml.getValue(projectName, "DIT_noe")) +  
	             Double.parseDouble(oml.getValue(projectName, "WMC_noe")) + 
	             Double.parseDouble(oml.getValue(projectName, "RFC_noe")) +
	             Double.parseDouble(oml.getValue(projectName, "NOC_noe")); 
  
	  coefficients [1] = ( Double.parseDouble(oml.getValue(projectName, "LOCM_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "LOCM_noe"))/noe_total ) / 2; 
	  coefficients [2] = ( Double.parseDouble(oml.getValue(projectName, "CBO_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "CBO_noe"))/noe_total ) / 2;
	  coefficients [3] = ( Double.parseDouble(oml.getValue(projectName, "DIT_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "DIT_noe"))/noe_total ) / 2;
	  coefficients [4] = ( Double.parseDouble(oml.getValue(projectName, "WMC_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "WMC_noe"))/noe_total ) / 2;
	  coefficients [5] = ( Double.parseDouble(oml.getValue(projectName, "RFC_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "RFC_noe"))/noe_total ) / 2;
	  coefficients [6] = ( Double.parseDouble(oml.getValue(projectName, "NOC_toe"))/toe_total + Double.parseDouble(oml.getValue(projectName, "NOC_noe"))/noe_total ) / 2;
	  
	  
	/*  System.out.println("toe_total "  + toe_total);
	  System.out.println("noe_total "  + noe_total); 
	  
	  
	  for (int i=0; i<7; i++) 
		  System.out.println("Class coeff " + i + "     " + coefficients[i] ); 
	  */
	  return coefficients; 
	  
  
  
  }
  
	public OneMetricList PrepareQRRMethodTable (OneMetricList filtered,String metricName) 
  {
		 enterMethod();
	/*	SortOperations sort = new SortOperations(filtered, metricName);
	  OneMetricList sortedList = sort.bubbleSort();
	  sortedList.revertList();
	  */
		 OneMetricList sortedList = new OneMetricList(filtered);
		 sortedList.Filter(metricName);
		
		 
	   
	  String [][] bigTable= new String [sortedList.GetSize()][9];
   
	  //SetMethodQRRCoefficients(filtered); 
	  //double [] coeff = SetMethodQRRCoefficients(filtered);  // {0, 0.2, 0.2, 0.2, 0.2,0.1,0.1,0}; 
	  double [] coeff = {0, 0.2, 0.2, 0.2, 0.2,0.1,0.1,0};   
	  DecimalFormat df = new DecimalFormat("##.##", DecimalFormatSymbols.getInstance(Locale.US));
	  System.out.println ("PrepareQRRMethodTable : "+sortedList.GetSize());
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[i][0] = om.getModuleName();
		  bigTable[i][1] = filtered.getValue(mName, "QQQ"+"vg"); 
		  bigTable[i][2] = filtered.getValue(mName, "QQQ"+"evg"); 
		  bigTable[i][3] = filtered.getValue(mName, "QQQ"+"totalLOC");
		  bigTable[i][4] = filtered.getValue(mName, "QQQ"+"code");
		  bigTable[i][5] = filtered.getValue(mName, "QQQ"+"comment");
		  bigTable[i][6] = filtered.getValue(mName, "QQQ"+"blank");
		  bigTable[i][7] = filtered.getValue(mName, "QQQ"+"mixed");
		  
		  double total = 0.0; 
		  for (int j=1; j<8; j++) 
			  
		  {
			 //System.out.println ("  M                i: "+ 	i+ " j :" + j+ " bigtable " + bigTable[i][j] + " & " + coeff[j]);
			  if (bigTable[i][j] != null) 
			     total += Double.parseDouble(bigTable[i][j])*coeff[j];
		  }
		  
		 
		  bigTable[i][8] = df.format(total);  
		  
		  OneMetric om2 = new OneMetric(om);
		  om2.setMetricName("QQQ"+"methodQRR");
		  om2.setValue( bigTable[i][8] );
		  
		  OneMetric om3 = new OneMetric(om);
		  om3.setMetricName("methodQRR");
		  om3.setValue( bigTable[i][8] );
		  
		  mainList.Add(om2);
		  filtered.Add(om2);
		  mainList.Add(om3);
		  filtered.Add(om3);
		
	  }
	  
	  return sortedList; 
	  
  }
	
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
		
		System.out.println ("CSVRead::" + getCallerMethod(3) +"()   "+edatetime);
		System.out.println (" ");
		//System.out.println (" **************************************** ");
	}
  
  public void BigTableQRRMethod (OneMetricList filtered,String metricName) 
  {
	  
	  enterMethod();
	  //System.out.println ("mn : " + getMethodName());
	  // OneMetricList sortedList = PrepareQRRMethodTable (filtered, metricName);
	  OneMetricList qrrFilter = new OneMetricList(filtered);
	  qrrFilter.QRRFilter(); 
	  
	  //SortOperations sort2 = new SortOperations(qrrFilter, "QQQmethodQRR");
	
	 /* MaxList ml = new MaxList(qrrFilter, "QQQmethodQRR", 20);
	  OneMetricList sortedList2 = ml.start();*/ 
	  
		 
	  OneMetricList sortedList2 = new MaxList(qrrFilter, "QQQmethodQRR", 20).start();
	  
	  	  //OneMetricList sortedList2 = sort2.bubbleSort_top(10);
	 
	  
	 
	  
	  String [][] bigTable2= new String [sortedList2.GetSize()][9];
	  double [][] excTable= new double  [sortedList2.GetSize()][9]; 
	  String [] cols = {"Module Name", "vg","evg","LOC","code","cmmnt", "blank","mixed","QRR"};
	  
	  for (int i=0; i<sortedList2.GetSize(); i++) 
	  {
		  OneMetric om = sortedList2.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable2[i][0] = om.getModuleName();
		  bigTable2[i][1] = qrrFilter.getValue(mName, "QQQ"+"vg"); 
		  bigTable2[i][2] = qrrFilter.getValue(mName, "QQQ"+"evg"); 
		  bigTable2[i][3] = qrrFilter.getValue(mName, "QQQ"+"totalLOC");
		  bigTable2[i][4] = qrrFilter.getValue(mName, "QQQ"+"code");
		  bigTable2[i][5] = qrrFilter.getValue(mName, "QQQ"+"comment");
		  bigTable2[i][6] = qrrFilter.getValue(mName, "QQQ"+"blank");
		  bigTable2[i][7] = qrrFilter.getValue(mName, "QQQ"+"mixed");
		  bigTable2[i][8] = qrrFilter.getValue(mName, "QQQ"+"methodQRR");
		
	  }
	  
	
	  pagingToPDF(bigTable2, excTable, cols, filtered.Get(0), " QRRMethod ");

	 /*SimpleTable st5 = new SimpleTable();
	  st5.SetColumns(cols);
	  st5.SetTableData(bigTable2);
	  OneMetric om = filtered.Get(0);
	   
	  st5.createandShowGUI("Method Based QRR - " + om.getProjectName() + " " +om.getMeasuremenTool() + " "+om.getDateTime());  
	  */
	  
  } 
  
  public void PrepareQRRClassTable (OneMetricList filtered,String metricName) 
  {
	  
	  enterMethod();
	/*/  SortOperations sort = new SortOperations(filtered, metricName);
	  OneMetricList sortedList = sort.bubbleSort_top(10);
	  sortedList.revertList();
	  */
	    
	  
	  OneMetricList sortedList  = new OneMetricList(filtered); 
	  sortedList.Filter(metricName);
	  
	  String [][] bigTable= new String [sortedList.GetSize()][8];
	
	  
	
	  //SetClassQRRCoefficients(filtered); 
	 // double [] coeff = SetClassQRRCoefficients(filtered);  // {0, 0.2, 0.2, 0.2, 0.2,0.1,0.1,0}; 
	 double [] coeff = {0, 0.3, 0.3, 0.2, 0.1, 0.05, 0.05};   
	  DecimalFormat df = new DecimalFormat("##.##", DecimalFormatSymbols.getInstance(Locale.US));
	   System.out.println ("PrepareQRRClassTable : "+sortedList.GetSize());
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[i][0] = om.getModuleName();
		  bigTable[i][1] = filtered.getValue(mName, "QQQ"+"LOCM"); 
		  bigTable[i][2] = filtered.getValue(mName, "QQQ"+"CBO"); 
		  bigTable[i][3] = filtered.getValue(mName, "QQQ"+"DIT");
		  bigTable[i][4] = filtered.getValue(mName, "QQQ"+"WMC");
		  bigTable[i][5] = filtered.getValue(mName, "QQQ"+"RFC");
		  bigTable[i][6] = filtered.getValue(mName, "QQQ"+"NOC");
		  
		  
		  double total = 0.0; 
		  for (int j=1; j<7; j++) 
		  {
			 //System.out.println ("  C                i: "+ 	i+ " j :" + j+ " bigtable " + bigTable[i][j] + " & " + coeff[j]);
			  if (bigTable[i][j] != null) 
			     total += Double.parseDouble(bigTable[i][j])*coeff[j];
			  
		  }
		//  System.out.println ("  C                i: "+ 	i+ " total " + total);
		  bigTable[i][7] = df.format(total);  
		  
		  OneMetric om2 = new OneMetric(om); 
		  om2.setMetricName("QQQ"+"classQRR");
		  om2.setValue( bigTable[i][7] );
		  
		  OneMetric om3 = new OneMetric(om); 
		  om3.setMetricName("classQRR");
		  om3.setValue( bigTable[i][7] );
		  
		  filtered.Add(om2);
		  mainList.Add(om2);
		  filtered.Add(om3);
		  mainList.Add(om3);
		
	  }
	  

  }
  
  
  public void BigTableQRRClass (OneMetricList filtered,String metricName) 
  {
	  enterMethod();
	  OneMetricList qrrFilter = new OneMetricList(filtered);
	  qrrFilter.QRRFilter(); 
	  
	  OneMetricList sortedList2 = new MaxList(qrrFilter, "QQQclassQRR", 20).start();
	  
	  String [][] bigTable2= new String [sortedList2.GetSize()][8]; 
	  double [][] excTable= new double  [sortedList2.GetSize()][8];
	  String [] cols = {"Class Name", "LOCM","CBO","DIT","RFC","WMC", "NOC","QRR"};
	  
	  
	  for (int i=0; i<sortedList2.GetSize(); i++) 
	  {
		  OneMetric om = sortedList2.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable2[i][0] = om.getModuleName();
		  bigTable2[i][1] = qrrFilter.getValue(mName, "QQQ"+"LOCM"); 
		  bigTable2[i][2] = qrrFilter.getValue(mName, "QQQ"+"CBO"); 
		  bigTable2[i][3] = qrrFilter.getValue(mName, "QQQ"+"DIT");
		  bigTable2[i][4] = qrrFilter.getValue(mName, "QQQ"+"RFC");
		  bigTable2[i][5] = qrrFilter.getValue(mName, "QQQ"+"WMC");
		  bigTable2[i][6] = qrrFilter.getValue(mName, "QQQ"+"NOC");
		  bigTable2[i][7] = qrrFilter.getValue(mName, "QQQ"+"classQRR");
		
		 // System.out.println("bigTable2[i][7]"+bigTable2[i][7]);
	  }
	  
	  
	 // tableToPDF(bigTable2, cols, filtered.Get(0), " QRRClass ");
	  if (qrrFilter.GetSize()>0 && sortedList2.GetSize()>0)
	  pagingToPDF(bigTable2, excTable, cols, qrrFilter.Get(0)," QRRClass ");
	  /*SimpleTable st5 = new SimpleTable();
	  st5.SetColumns(cols);
	  st5.SetTableData(bigTable2);
	  OneMetric om = filtered.Get(0);
	   
	  st5.createandShowGUI("Class Based QRR - " + om.getProjectName() + " " +om.getMeasuremenTool() + " "+om.getDateTime());  
	  */
	  
  } 
  
  public String tableToPDF (String [][]bigTable, String [] cols, OneMetric om, String tableCaption) 
  {
	  
	  enterMethod();
	  
	  String fileName = "file";
	  int lineCount = bigTable.length; // bigTable.length < 34 ?  bigTable.length : 34; 
	  String [][] tempTable = new String[lineCount+1][];
	  
	  for (int i=0;i<lineCount+1;i++) 
	  {
		  tempTable [i] = new String [bigTable[0].length]; 
	  }
	  tempTable [0] = cols;  
	  for (int i=1;i<lineCount+1;i++) 
	  {
		
		      //System.out.println ("0123 " + i + " " + bigTable[i-1][0]) ; 
			  int strSize =  bigTable[i-1][0].length(); 
			  String modName =  bigTable[i-1][0]; 
			 /* if (strSize >80) 
			  {
				  String sbString1 = bigTable[i-1][0].substring(0,18); 
				  String sbString2 = " ... ";
				  String sbString3 = bigTable[i-1][0].substring(strSize-66,strSize);
				  modName = sbString1 + sbString2 + sbString3 ;  
			  } 
			  */
			  tempTable [i][0] = modName ; // bigTable[i-1][j].substring(0, strSize);
		
		  for (int j=1; j<bigTable[0].length; j++)
		  {
 
			  if (bigTable[i-1][j]!=null) 
			  {
				  tempTable [i][j] = bigTable[i-1][j] ; 
				  
					   
			  }
			  else 
				  tempTable [i][j] = ""; 
		  }
		       
	  }
	  
	  
	  
	  
	    PDDocument doc = new PDDocument();
	    PDPage page = new PDPage();
	    doc.addPage( page );
	 
	    PDPageContentStream contentStream = null;
	    
	    
	 
	    try {
	    	contentStream = new PDPageContentStream(doc, page);
	    	
	    	String header = "  "+ om.getProjectName() + " - " + om.getMeasuremenTool() + " - " + om.getDateTime()+ " " + tableCaption; 
			drawTable(page, contentStream, 750, 50, tempTable, header);
			contentStream.close();
			String spth = "c:\\f3\\"+om.getProjectName()+"\\";  
			File fpth =  new File (spth);
			fpth.mkdir();
			File fpth2 =  new File (spth+"\\"+om.getDateTime());
			fpth2.mkdir(); 
			//URI u= fpth.toURI(); 
             fileName = spth+"\\"+om.getDateTime()+"\\"+header +  ".pdf" ; 					
			
            System.out.println(fileName);
             
			try {
				//Path path = Paths.get(u);
				doc.save(fileName);
			} catch (COSVisitorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return fileName; 
	  
  }
  
  public String [][] tablePaging(String [][] mTable, double [][] excTable, int pageSize, int rowCount, int order) 
  {
	   
	  enterMethod();
	  String [][] temp  = new String[rowCount][]; 
	  for (int i=0; i<temp.length; i++) 
		  temp[i] = new String [mTable[0].length]; 
	  
	  int marg = pageSize * order; 
	  
	  for (int i=marg; i< marg + rowCount; i++) 
	  {
		 
		 // System.out.println(mTable.length + " " + marg + " " + rowCount + " " + order + " " + i );
		  for (int j=0; j<mTable[0].length; j++)
		  {
			    if (excTable [i][j] == 1) 
			    {
				    temp[i-marg][j] = "#" + mTable[i][j] ;
			    } 
			    else if (excTable [i][j] == 2)
			    {
			    	temp[i-marg][j] = "$" + mTable[i][j] ;
			    }
			    else 
			    	temp[i-marg][j] =  mTable[i][j] ;
		  } 
		  
	  }
	 // System.out.println(mTable.length + " " + marg + " " + rowCount + " " + order);
	  return temp; 
  }
  
  
  public void pagingToPDF (String [][] bigTable, double [][] excTable, String [] cols, OneMetric om, String reportInfo)
  {
	  enterMethod();
	  int pageSize = 35; 
	  int rowCount= pageSize; 
	  int pageCount = bigTable.length/pageSize + 1; 
	  System.out.println ("pagecount " + pageCount + "length " + bigTable.length);
	  //pageCount = bigTable.length%pageSize == 0 ? pageCount-1:pageCount;
	    String mainFile =  "c:\\f4\\"+om.getProjectName()+"\\"+om.getDateTime()+"\\"+om.getProjectName()+" - "
	    		+ ""+reportInfo+".pdf" ;
	    PDFMergerUtility ut = new PDFMergerUtility();
	    String spth = "c:\\f4\\"+om.getProjectName()+"\\";  
		File fpth =  new File (spth);
		fpth.mkdir();
		File fpth2 =  new File (spth+"\\"+om.getDateTime());
		fpth2.mkdir(); 
		//URI u= fpth.toURI(); 
         mainFile = spth+"\\"+om.getDateTime()+"\\"+om.getProjectName()+" - "
 	    		+ om.getDateTime()+" - "+reportInfo+".pdf" ;			
	    
	    
	    ut.setDestinationFileName(mainFile);
	    
	    System.out.println (pageCount + "    " + mainFile);
	  for (int i=0; i<pageCount ; i++)
	  
	  {
		  rowCount = bigTable.length<pageSize ? bigTable.length : rowCount; 
		  rowCount = (bigTable.length > pageSize && i == pageCount-1 && bigTable.length % (pageSize)!=0 ) ? bigTable.length % (pageSize) : rowCount;  
		  //System.out.println ("### " +bigTable.length +  "  " + pageCount + "  "+ rowCount + "  "+ i );
		  String fileName = tableToPDF(tablePaging(bigTable, excTable, pageSize, rowCount, i), cols, om,   reportInfo + " - page "+ (i+1) + " of " + pageCount);
		  ut.addSource(fileName);
		  /*if (i==0) 
		  {
			  mainFile = fileName;
			  
		  }*/
		  
	  }
	  
	  try {
		ut.mergeDocuments();
	} catch (COSVisitorException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		  
		  
	  
	  
	  
	  
	  
	  /*for (int i=0; i<bigTable.length/pageSize+1 ; i++) 
	  {
		  if ((bigTable.length > pageSize && i == bigTable.length/pageSize ) || (bigTable.length<pageSize))
		     rowCount = bigTable.length % (pageSize);  	  
		  System.out.println ("### " +bigTable.length +  "  " + bigTable.length/pageSize + "  "+ rowCount + "  "+ i );
		  tableToPDF(tablePaging(bigTable, pageSize, rowCount, i), cols, om,   reportInfo + " - page "+ (i+1) + " ");
		  
	  }*/
	 
  }
	public String getFuzzyString ()  
	{
		
		if (fuzzyFlag) 
			return "fuzzy"; 
		else 
			return ""; 
		
	}
  
  public void BigTable (OneMetricList filtered,String metricName) 
  {
     enterMethod();
	  OneMetricList omlt = new OneMetricList(filtered);  
	  omlt.Filter(1);
	  omlt.FilterbyWarning(mi,"vg");
	  


	   // SortOperations sort = new SortOperations(omlt, "identifier");
	  //OneMetricList sortedList = sort.quickSort();
	  //OneMetricList sortedList = sort.bubbleSort_top(40);
	  //sortedList.revertList();
	  
	  OneMetricList sortedList = new MaxList(omlt, "identifier", 100).start();
	  
	   
	  String [][] bigTable= new String [sortedList.GetSize()][10];
	  double [][] excTable= new double  [sortedList.GetSize()][10];
	  
	  
	  String [] cols = {"Module Name", "vg","evg","LOC","code","comment", "blank","mixed","cdensity","defects"};
	    
	  
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i);

			 
		  
		     String mName = om.getModuleName(); 
		  
			  bigTable[i][0] = om.getModuleName();
			  excTable[i][0] = 0; 
			  bigTable[i][1] = omlt.getValue(mName, "vg");
			  excTable[i][1] = decision("vg", bigTable[i][1]);
			  bigTable[i][2] = omlt.getValue(mName, "evg"); 
			  excTable[i][2] = decision("evg", bigTable[i][2]);
			  bigTable[i][3] = omlt.getValue(mName, "totalLOC");
			  excTable[i][3] = decision("totalLOC", bigTable[i][3]);
			  bigTable[i][4] = omlt.getValue(mName, "code");
			  excTable[i][4] = decision("code", bigTable[i][4]);
			  bigTable[i][5] = omlt.getValue(mName, "comment");
			  excTable[i][5] = decision("comment", bigTable[i][5]);
			  bigTable[i][6] = omlt.getValue(mName, "blank");
			  excTable[i][6] = decision("blank", bigTable[i][6]);
			  bigTable[i][7] = omlt.getValue(mName, "mixed");
			  excTable[i][7] = decision("mixed", bigTable[i][7]);
			  bigTable[i][8] = omlt.getValue(mName, "comment density");
			  excTable[i][8] = decision("comment density", bigTable[i][8]);
			  bigTable[i][9] = omlt.getValue(mName, "defects");
			  excTable[i][9] = decision("defects", bigTable[i][9]);


		 
		
	  }
	  
	  
	  pagingToPDF(bigTable, excTable, cols, omlt.Get(0), " Method Metrics "+getFuzzyString());
	    
	  showSimpleTable (cols, bigTable, "Method Based Metrics") ; 
	  
	  //toCSV (bigTable, excTable, cols, omlt.Get(0), " Method Metrics ");
	  
	 
  } 
  
  public void toCSV(String [][] bigTable, double [][] excTable, String [] cols, OneMetric om, String reportInfo) 
  {
	  int margin = 0;   
		//BufferedReader br = null;
		//FileWriter fr = null;
		
		String header = reportInfo + "  "+ om.getProjectName() + " - " + om.getMeasuremenTool() + " - " + om.getDateTime()+ " " ; 
/*		String writePath = "c:\\f5\\"+header+".csv"; 
      System.out.println(writePath);				 		
		try {
			fr = new FileWriter (writePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		BufferedWriter wr = new BufferedWriter (fr);
		*/
		BufferedWriter wr = CreateBufferedWriter("c:\\f5\\"+header+".csv");
		String line ;
	    
		try {
	     	
	     	 
	     	for (int i=0; i<bigTable.length; i++) 
	     	{
	     		line = bigTable[i][0]; 
	     		for (int j=1; j<bigTable[i].length; j++)
	     		 
	     		{
	     			line = line + "," + bigTable[i][j]; 
	     			
	     			
	     		}
	     			     		
	     		wr.write (line+"\n");
	     	//	System.out.println("3333333 " + line);		
	     	}
	     	
	     	
	     	
	 
			
	  	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (wr != null) {
				try {
					
					wr.close(); 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  
  }
  
  
  
  public void showSimpleTable (String cols[], String [][] bigTable, String caption) 
  {
      SimpleTable st5 = new SimpleTable();
	  
	  st5.SetColumns(cols);
	  st5.SetTableData(bigTable);
	  st5.createandShowGUI(caption);  
	  
  }
  
  public void BigTablee (OneMetricList filtered,String metricName) 
  {
	 
	  SortOperations sort = new SortOperations(filtered, metricName);
	  //OneMetricList sortedList = sort.quickSort();
	  OneMetricList sortedList = sort.bubbleSort();
	  sortedList.revertList();
	  
	   
	  String [][] bigTable= new String [sortedList.GetSize()][8];
	  double [][] excTable= new double  [sortedList.GetSize()][8];
	  
	  
	  String [] cols = {"Module Name", "vg","evg","LOC","code","comment", "blank","mixed"};
	    
	  
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[i][0] = om.getModuleName();
		  bigTable[i][1] = filtered.getValue(mName, "vg"); 
		  bigTable[i][2] = filtered.getValue(mName, "evg"); 
		  bigTable[i][3] = filtered.getValue(mName, "totalLOC");
		  bigTable[i][4] = filtered.getValue(mName, "code");
		  bigTable[i][5] = filtered.getValue(mName, "comment");
		  bigTable[i][6] = filtered.getValue(mName, "blank");
		  bigTable[i][7] = filtered.getValue(mName, "mixed");
		  
		 
		
	  }
	  
	  pagingToPDF(bigTable, excTable, cols, filtered.Get(0), " Method Metrics ");
	    
	 
	  showSimpleTable(cols, bigTable, "Method Based Metrics");
	
	  
	  
  } 
 
  public String[][] BigTableReliabilityCaptionColumn (String[][] bigTable)
  {
	  bigTable[0][0] = "Program";
	  bigTable[1][0] = "";
	  bigTable[2][0] = "METHOD COUNT BASED RELIABILITY (by rome lab)";
	  bigTable[3][0] = "a"; 
	  bigTable[4][0] =  "b";
	  bigTable[5][0] = "c"; 
	  bigTable[6][0] = "sx";
	  bigTable[7][0] = "x";
	  bigTable[8][0] = "w";
	  bigTable[9][0] = "u" ;
	  bigTable[10][0] =  "sm";
	  bigTable[11][0] =  "reliability";
	  bigTable[12][0] = "";
	  bigTable[13][0] = "VOLUME BASED RELIABILITY (by bilgem d-500)";
	  bigTable[14][0] =  "a_vol"; 
	  bigTable[15][0] =  "b_vol";
	  bigTable[16][0] =  "c_vol"; 
	  bigTable[17][0] =  "sx_vol";
	  bigTable[18][0] =  "x_vol";
	  bigTable[19][0] =  "w_vol";
	  bigTable[20][0] ="u_vol" ;
	  bigTable[21][0] =  "sm_vol";
	  bigTable[22][0] = "reliability_vol";
	  bigTable[23][0] = "";
	  bigTable[24][0] = "MAINTAINABILITY (by tuvit)";
	  bigTable[25][0] = "veryhigh";
	  bigTable[26][0] = "high";
	  bigTable[27][0] = "moderate";
	  bigTable[28][0] = "maintainability";
	  bigTable[29][0] = "";
	  bigTable[30][0] = "MAINTAINABILITY (by oman)";
	  bigTable[31][0] = "3MI";
	  bigTable[32][0] = "4MI";
	  
	  return bigTable;
  }
  public String[][] BigTableMaintainabilityCaptionColumn (String[][] bigTable)
  {
	  
	  
	  
	  bigTable[0][0] = "veryhigh (amount)";
	  bigTable[5][0] = "veryhigh (%)";
	  bigTable[1][0] = "high (amount)"; 
	  bigTable[6][0] =  "high (%)";
	  bigTable[2][0] = "moderate (amount)"; 
	  bigTable[7][0] = "moderate (%)";
	  bigTable[3][0] = "normal (amount)";
	  bigTable[4][0] = "";
	  bigTable[8][0] = "normal (%)";
	  bigTable[9][0] = "";
	  
	  bigTable[10][0] = "Maintability score" ;
	  bigTable[11][0] = "";
	  
	  
	  bigTable[12][0] = "Method Count";
	  bigTable[13][0] = "Total LOC";
	  
	  
	  
	  
	  return bigTable;
  }
  
  public String[][] BigTableReliabilityFormulationColumn (String[][] bigTable)
  {
	 
	  bigTable[0][1] = "";
	  bigTable[1][1] = "";
	  bigTable[2][1] = "";
	  bigTable[3][1] = "vg>20"; 
	  bigTable[4][1] = "10<vg<20";
	  bigTable[5][1] = "vg<10"; 
	  bigTable[6][1] = "(1.5*a+b+0.8*c)/(a+b+c)";
	  bigTable[7][1] = "loc>200";
	  bigTable[8][1] = "100<loc<200";
	  bigTable[9][1] = "loc<100" ;
	  bigTable[10][1] =  "(2*x+w+0.9*u)/(x+w+u)";
	  bigTable[11][1] =  "300 - (sx * sm * 100) ";
	  bigTable[12][1] = "";
	  bigTable[13][1] = "";
	  bigTable[14][1] = "vg>20";  
	  bigTable[15][1] = "10<vg<20";
	  bigTable[16][1] =  "vg<10";  
	  bigTable[17][1] =  "(1.5*a+b+0.8*c)/(a+b+c)";
	  bigTable[18][1] =  "loc>200";
	  bigTable[19][1] =  "100<loc<200";
	  bigTable[20][1] ="loc<100" ;
	  bigTable[21][1] =  "(2*x+w+0.9*u)/(x+w+u)";
	  bigTable[22][1] = "300 - (sx_vol * sm_vol * 100) ";
	  bigTable[23][1] = "";
	  bigTable[24][1] = "";
	  bigTable[25][1] = "vg>50";
	  bigTable[26][1] = "20<vg<50";
	  bigTable[27][1] = "10<vg<20";
	  bigTable[28][1] = "";
	  bigTable[29][1] = "";
	  bigTable[30][1] = "";
	  bigTable[31][1] = "";
	  bigTable[32][1] = "";
	  return bigTable;
  } 
  

  public void BigTable_Maintainability (OneMetricList filtered) 
  {
	  
	  SortOperations sort = new SortOperations(filtered, "maintainability");
	  OneMetricList sortedList = sort.bubbleSort();
	  sortedList.revertList();
	  String [][] bigTable= new String [14][6];
	  double [][] excTable= new double  [14][6];

	  String [] cols = {"" , "vg vol", "loc vol", "vg fun","loc fun", "fuzzy" };
	  bigTable = BigTableMaintainabilityCaptionColumn(bigTable);
	  
	   
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[0][1] = filtered.getValue(mName, "cvhl"); 
		  bigTable[0][2] = filtered.getValue(mName, "lvhl");
		  bigTable[0][3] = filtered.getValue(mName, "cvhf");
		  bigTable[0][4] = filtered.getValue(mName, "lvhf");
		  bigTable[0][5] = filtered.getValue(mName, "fuzzycvhl");
		  
		  bigTable[1][1] = filtered.getValue(mName, "chl"); 
		  bigTable[1][2] = filtered.getValue(mName, "lhl");
		  bigTable[1][3] = filtered.getValue(mName, "chf");
		  bigTable[1][4] = filtered.getValue(mName, "lhf");
		  bigTable[1][5] = filtered.getValue(mName, "fuzzychl");
		  
		  bigTable[2][1] = filtered.getValue(mName, "cml"); 
		  bigTable[2][2] = filtered.getValue(mName, "lml");
		  bigTable[2][3] = filtered.getValue(mName, "cmf");
		  bigTable[2][4] = filtered.getValue(mName, "lmf");
		  bigTable[2][5] = filtered.getValue(mName, "fuzzycml");
		  
		  bigTable[3][1] = filtered.getValue(mName, "cnl"); 
		  bigTable[3][2] = filtered.getValue(mName, "lnl");
		  bigTable[3][3] = filtered.getValue(mName, "cnf");
		  bigTable[3][4] = filtered.getValue(mName, "lnf");
		  bigTable[3][5] = filtered.getValue(mName, "fuzzycnl");

		  bigTable[5][1] = filtered.getValue(mName, "cvhlr"); 
		  bigTable[5][2] = filtered.getValue(mName, "lvhlr");
		  bigTable[5][3] = filtered.getValue(mName, "cvhfr");
		  bigTable[5][4] = filtered.getValue(mName, "lvhfr");
		  bigTable[5][5] = filtered.getValue(mName, "fuzzycvhlr");


		  bigTable[6][1] = filtered.getValue(mName, "chlr"); 
		  bigTable[6][2] = filtered.getValue(mName, "lhlr");
		  bigTable[6][3] = filtered.getValue(mName, "chfr");
		  bigTable[6][4] = filtered.getValue(mName, "lhfr");
		  bigTable[6][5] = filtered.getValue(mName, "fuzzychlr");



		  bigTable[7][1] = filtered.getValue(mName, "cmlr"); 
		  bigTable[7][2] = filtered.getValue(mName, "lmlr");
		  bigTable[7][3] = filtered.getValue(mName, "cmfr");
		  bigTable[7][4] = filtered.getValue(mName, "lmfr");
		  bigTable[7][5] = filtered.getValue(mName, "fuzzycmlr");



		  bigTable[8][1] = filtered.getValue(mName, "cnlr"); 
		  bigTable[8][2] = filtered.getValue(mName, "lnlr");
		  bigTable[8][3] = filtered.getValue(mName, "cnfr");
		  bigTable[8][4] = filtered.getValue(mName, "lnfr");
		  bigTable[8][5] = filtered.getValue(mName, "fuzzycnlr");
		  
		  
		  bigTable[10][1] = filtered.getValue(mName, "mntvgl"); 
		  bigTable[10][2] = filtered.getValue(mName, "mntcl");
		  bigTable[10][3] = filtered.getValue(mName, "mntvgf");
		  bigTable[10][4] = filtered.getValue(mName, "mntcf");
		  bigTable[10][5] = filtered.getValue(mName, "fuzzymnt");
		  
		  
		  bigTable[12][1] = filtered.getValue(mName, "mntmc");
		  bigTable[13][1] = filtered.getValue(mName, "mntloc");
		 
	
	  }
	 
	  /*
	 for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  for (int j=0;j<10;j++) 
		  {
			  logString.add(bigTable[j][i+2]);
		  }
	  } 
	  */

	  pagingToPDF(bigTable, excTable, cols, filtered.Get(0), " Maintainability Metrics "+getFuzzyString());
	  //pagingToTextFile(filtered.Get(0));
	 // toCSV (bigTable, excTable, cols, filtered.Get(0), " Project Metrics ");
	  
	  OneMetric first = sortedList.Get(0);
	  showSimpleTable(cols, bigTable, "Maintainability Metrics : " + first.getProjectName() + " | " + first.getDateTime() + " | " + first.getMeasuremenTool());
	  
	  	  
  } 
  
  
  public void BigTable_Reliability (OneMetricList filtered) 
  {
	  
	  SortOperations sort = new SortOperations(filtered, "maintainability");
	  OneMetricList sortedList = sort.bubbleSort();
	  sortedList.revertList();
	  String [][] bigTable= new String [33][sortedList.GetSize()+2];
	  double [][] excTable= new double  [33][sortedList.GetSize()+2];

	  String [] cols = {"Metric Type" , "Formulation", "Value"};
	  bigTable = BigTableReliabilityCaptionColumn(bigTable);
	  bigTable = BigTableReliabilityFormulationColumn(bigTable);
	   
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[0][i+2] = om.getModuleName();  
		  bigTable[1][i+2] = "";
		  bigTable[2][i+2] = "";
		  bigTable[3][i+2] = filtered.getValue(mName, "a"); 
		  bigTable[4][i+2] = filtered.getValue(mName, "b");
		  
		  bigTable[5][i+2] = filtered.getValue(mName, "c"); 
		  bigTable[6][i+2] = filtered.getValue(mName, "sx");
		  bigTable[7][i+2] = filtered.getValue(mName, "x");
		  bigTable[8][i+2] = filtered.getValue(mName, "w");
		  bigTable[9][i+2] = filtered.getValue(mName, "u");
		  bigTable[10][i+2] = filtered.getValue(mName, "sm");
		  bigTable[11][i+2] = filtered.getValue(mName, "reliability");
		  bigTable[12][i+2] = "";
		  bigTable[13][i+2] = "";
		  bigTable[14][i+2] = filtered.getValue(mName, "a_vol"); 
		  bigTable[15][i+2] = filtered.getValue(mName, "b_vol");
		  bigTable[16][i+2] = filtered.getValue(mName, "c_vol"); 
		  bigTable[17][i+2] = filtered.getValue(mName, "sx_vol");
		  bigTable[18][i+2] = filtered.getValue(mName, "x_vol");
		  bigTable[19][i+2] = filtered.getValue(mName, "w_vol");
		  bigTable[20][i+2] = filtered.getValue(mName, "u_vol" );
		  bigTable[21][i+2] = filtered.getValue(mName, "sm_vol");
		  bigTable[22][i+2] = filtered.getValue(mName, "reliability_vol");
		  bigTable[23][i+2] = "";
		  bigTable[24][i+2] = "";
		  bigTable[25][i+2] = filtered.getValue(mName, "veryhigh"); 
		  bigTable[26][i+2] = filtered.getValue(mName, "high");
		  bigTable[27][i+2] = filtered.getValue(mName, "moderate"); 
		  bigTable[28][i+2] = filtered.getValue(mName, "maintainability");
		  bigTable[29][i+2] = "";
		  bigTable[30][i+2] = "";
		  bigTable[31][i+2] = filtered.getValue(mName, "3MI"); 
		  bigTable[32][i+2] = filtered.getValue(mName, "4MI");
		 
		 
	
	  }
	 
	 for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  for (int j=0;j<33;j++) 
		  {
			  logString.add(bigTable[j][i+2]);
		  }
	  } 
	  

	  pagingToPDF(bigTable, excTable, cols, filtered.Get(0), " Project Metrics "+getFuzzyString());
	  //pagingToTextFile(filtered.Get(0));
	 // toCSV (bigTable, excTable, cols, filtered.Get(0), " Project Metrics ");
	  
	  OneMetric first = sortedList.Get(0);
	  showSimpleTable(cols, bigTable, "Reliability Metrics : " + first.getProjectName() + " | " + first.getDateTime() + " | " + first.getMeasuremenTool());
	  
	  	  
  } 
  
  
  
  
  
  
  
  public void pagingToTextFile (OneMetric om)
  {


      
      new MMFileWriter().WriteToFile("c:\\f3\\"+om.getModuleName()+"_"+om.getDateTime()+".txt",logString);
      
	  
	  
  }
  
  public void BigTable_ProjectSummary(OneMetricList filtered) 
  {
	  
	  SortOperations sort = new SortOperations(filtered, "maintainability");
	  OneMetricList sortedList = sort.bubbleSort();
	  sortedList.revertList();
	  String [][] bigTable= new String [25][sortedList.GetSize()+1];
	  double [][] excTable= new double  [25][sortedList.GetSize()+1];

	  String [] cols = {"Metric Type" , "Value"};
	  
	  bigTable[0][0] = "Program";
	  bigTable[1][0] = "";
	  bigTable[2][0] = "Total Line of Code ";
	  bigTable[3][0] = "Number of Methods"; 
	  bigTable[4][0] =  "Number of Classes";
	  bigTable[5][0] = "Reliability - mb"; 
	  bigTable[6][0] = "Reliability - vb";
	  bigTable[7][0] = "Maintainability - vg";
	  bigTable[8][0] = "Maintainability 4MI";
	  bigTable[9][0] = "vg " ;
      bigTable[10][0] =  "evg "; 
	  
	  bigTable[11][0] = "code"; 
	  bigTable[12][0] =  "comment"; 
	  bigTable[13][0] =  "blank";
	  bigTable[14][0] =  "mixed"; 
	  bigTable[15][0] =  "LOCM";
	  bigTable[16][0] =  "CBO";
	  bigTable[17][0] = "NOC";
	  bigTable[18][0] = "WMC";
	  bigTable[19][0] = "DIT";
	  bigTable[20][0] = "RFC";
	  bigTable[21][0] = "defects";
	  bigTable[22][0] = "MQRR3";
	  bigTable[23][0] = "OOQRR3";
	  bigTable[24][0] = "* Metric values are shown in ->";
      
	 
	 

	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[0][i+1] = om.getModuleName();
		  bigTable[1][i+1] = "";
		  bigTable[2][i+1] =  Double.toString(filtered.Sum("totalLOC"));
		  bigTable[3][i+1] = filtered.getValue(mName, "methodcount");// Integer.toString(filtered.getMethodCount()); //filtered.getValue(mName, "methodCount"); 
		  bigTable[4][i+1] = filtered.getValue(mName, "classcount"); // Integer.toString(filtered.getClassCount());//filtered.getValue(mName, "classCount");
		  bigTable[5][i+1] = filtered.getValue(mName, "reliability"); 
		  bigTable[6][i+1] = filtered.getValue(mName, "reliability_vol");
		  bigTable[7][i+1] = filtered.getValue(mName, "maintainability");
		  bigTable[8][i+1] = filtered.getValue(mName, "4MI");
		  bigTable[9][i+1] = Double.toString(filtered.Maximum("vg")) + " ("+Integer.toString(filtered.ThresholdExceedanceCount("vg", mi))+")"; 
		  bigTable[10][i+1] = Double.toString(filtered.Maximum("evg"))+ " (" +Integer.toString(filtered.ThresholdExceedanceCount("evg", mi))+")" ;
		  bigTable[11][i+1] = Double.toString(filtered.Maximum("code"))+ " (" +Integer.toString(filtered.ThresholdExceedanceCount("code", mi))+")";
		  bigTable[12][i+1] = Double.toString(filtered.Maximum("comment"))+ " (" +Integer.toString(filtered.ThresholdExceedanceCount("comment", mi))+")";
		  bigTable[13][i+1] = Double.toString(filtered.Maximum("blank"))+ " (" +Integer.toString(filtered.ThresholdExceedanceCount("blank", mi))+")";
		  bigTable[14][i+1] = Double.toString(filtered.Maximum("mixed"))+ " (" +Integer.toString(filtered.ThresholdExceedanceCount("mixed", mi))+")";
		  bigTable[15][i+1] = Double.toString(filtered.Maximum("LOCM"))+ " ("+ Integer.toString(filtered.ThresholdExceedanceCount("LOCM", mi))+")";
		  bigTable[16][i+1] = Double.toString(filtered.Maximum("CBO"))+ " ("+Integer.toString(filtered.ThresholdExceedanceCount("CBO", mi))+")";
		  bigTable[17][i+1] = Double.toString(filtered.Maximum("NOC"))+ " ("+Integer.toString(filtered.ThresholdExceedanceCount("NOC", mi))+")";
		  bigTable[18][i+1] = Double.toString(filtered.Maximum("WMC"))+ " ("+Integer.toString(filtered.ThresholdExceedanceCount("WMC", mi))+")";
		  bigTable[19][i+1] = Double.toString(filtered.Maximum("DIT"))+ " ("+Integer.toString(filtered.ThresholdExceedanceCount("DIT", mi))+")";
		  bigTable[20][i+1] = Double.toString(filtered.Maximum("RFC"))+ " ("+Integer.toString(filtered.ThresholdExceedanceCount("RFC", mi))+")";
		  bigTable[21][i+1] = Double.toString(filtered.Maximum("defects"))+ " ("+Integer.toString(filtered.ThresholdExceedanceCount("defects", mi))+")";
		  bigTable[22][i+1] = Double.toString(filtered.Maximum("MQRR3"))+ " ("+Integer.toString(filtered.ThresholdExceedanceCount("MQRR3", mi))+")";
		  bigTable[23][i+1] = Double.toString(filtered.Maximum("OOQRR3"))+ " ("+Integer.toString(filtered.ThresholdExceedanceCount("OOQRR3", mi))+")";
		  bigTable[24][i+1] = " Maximum (Number of exceeded) format";

	
	  }
	  
	  /*for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  for (int j=0;j<33;j++) 
		  {
			  logString.add(bigTable[j][i+2]);
		  }
	  } */
	  

	  pagingToPDF(bigTable, excTable, cols, filtered.Get(0), " Project Summary "+getFuzzyString());
	  //toCSV (bigTable, excTable, cols, filtered.Get(0), " Project Summary ");
	  
	  OneMetric first = sortedList.Get(0);
	  showSimpleTable(cols, bigTable, " Project Summary : " + first.getProjectName() + " | " + first.getDateTime() + " | " + first.getMeasuremenTool());
	  
	  	  
  } 
  
  
  public void BigTable_Understand () 
  {
	  
	  SortOperations sort = new SortOperations(mainList, "vg","Understand");
	  //OneMetricList sortedList = sort.quickSort();
	  OneMetricList sortedList = sort.bubbleSort();
	  sortedList.revertList();
	  
	   
	  String [][] bigTable= new String [sortedList.GetSize()][4]; 
	  
	  String [] cols = {"Module Name", "Understand vg", "Understand evg","Understand LOC"};
	    
	  
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[i][0] = om.getModuleName();
		   
		  bigTable[i][1] = mainList.getValue(mName, "vg", "Understand");
		   
		  bigTable[i][2] = mainList.getValue(mName, "evg", "Understand");
		   
		  bigTable[i][3] = mainList.getValue(mName, "totalLOC", "Understand");

	  }
	  
	  SimpleTable st5 = new SimpleTable();
	  
	  st5.SetColumns(cols);
	  st5.SetTableData(bigTable);
	  st5.createandShowGUI("Method Based Metrics - Understand Measurements");  
	  
	  
  }

  
  public void BigTable_OO () 
  {
	BigTable_OO(mainList);
  }
  
  public double decision (String metric, String value)  
  {
	   
	  if (value != null && mi.ThresholdExceeded(metric, Double.parseDouble(value))) 
	  {
		  return 1; 
	  }
	  if (value != null && mi.ThresholdWarning(metric, Double.parseDouble(value))) 
	  {
		  return 2; 
	  }
	  return 0; 
  }
  
  public void BigTable_OO (OneMetricList filtered) 
  {
	  
	  OneMetricList omlt = new OneMetricList (filtered);
	  omlt.Filter(2);
	  omlt.FilterbyWarningOO(mi,"LOCM");
	  
	  if (omlt.GetSize()>0) 
	  {
	  
	  
	  //SortOperations sort = new SortOperations(omlt, "identifier");
	  //OneMetricList sortedList = sort.bubbleSort_top(40);
	  //sortedList.revertList();
	  
	  OneMetricList sortedList = new MaxList(omlt, "identifier", 100).start();
	  
	   
	  String [][] bigTable= new String [sortedList.GetSize()][9]; 
	  double [][] excTable= new double [sortedList.GetSize()][9]; 
	  
	  String [] cols = {"Module Name", "LOCM","CBO","DIT","RFC","WMC","NOC","defects","#defects"};
	  
	  for (int i=0; i<sortedList.GetSize(); i++) 
	  {
		  OneMetric om = sortedList.Get(i); 
		  String mName = om.getModuleName(); 
		  
		  bigTable[i][0] = om.getModuleName();
		  excTable[i][0] = 0;  
		  bigTable[i][1] = filtered.getValue(mName, "LOCM"); 
		  excTable[i][1] = decision ("LOCM", bigTable[i][1]);
		 
		  bigTable[i][2] = filtered.getValue(mName, "CBO"); 
		  excTable[i][2] = decision ("CBO", bigTable[i][2]);
		  
		  bigTable[i][3] = filtered.getValue(mName, "DIT");
		  excTable[i][3] = decision ("DIT", bigTable[i][3]);
		
		  bigTable[i][4] = filtered.getValue(mName, "RFC"); 
		  excTable[i][4] = decision ("RFC", bigTable[i][4]);
		

		  bigTable[i][5] = filtered.getValue(mName, "WMC"); 
		  excTable[i][5] = decision ("WMC", bigTable[i][5]);
		

		  bigTable[i][6] = filtered.getValue(mName, "NOC");
		  excTable[i][6] = decision ("NOC", bigTable[i][6]);
		  
		  bigTable[i][7] = filtered.getValue(mName, "defects");
		  excTable[i][7] = decision ("defects", bigTable[i][7]);
		
		  bigTable[i][8] = filtered.getValue(mName, "numdefects");
		  excTable[i][8] = decision ("numdefects", bigTable[i][8]);
		  
	  }
	  
	  //tableToPDF(bigTable, cols, filtered.Get(0), " Object Oriented Metrics ");
	  pagingToPDF(bigTable, excTable, cols, omlt.Get(0), " Object Oriented Metrics ");
	  //toCSV (bigTable, excTable, cols, omlt.Get(0), " Object Oriented Metrics ");
	  
	  
	  showSimpleTable(cols, bigTable, "Object Oriented Based Metrics");
	  }

  }
  
  
  

  public void gui () 
  {
	  
	  DateTimeStr dts = new DateTimeStr(); 
	  cdatetime = dts.dtString("yyyyMMdd_HHmm");
	  
	  PrintStream fileStream;
	  
	  try {
		fileStream = new PrintStream("d:\\logs\\mm\\mm "+cdatetime+".txt");
		System.setOut (fileStream);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  mi =  new MetricInformation(); 
	  mi.setCommonMetricIdentifiers(); 
	  
	    frame = new JFrame(); 
		frame.setBounds(0,0,1000,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible (true);
		frame.createBufferStrategy(2); 
		frame.setLayout(new BorderLayout());
		//frame.setContentPane(new SwingScrollBar()); 
		tabbedPane(); 
		 JPanel panell = new JPanel();
		 panell.setPreferredSize(new Dimension(900, 600));
		 Color [] colors = {Color.GREEN, Color.WHITE, Color.RED, Color.BLUE, Color.BLACK, Color.MAGENTA, Color.YELLOW, Color.CYAN,  Color.GRAY,  Color.DARK_GRAY,  Color.ORANGE, Color.LIGHT_GRAY}; 

	     panell.setBackground(colors [dts.getHour()%12]);  

	     frame.setLayout(new GridBagLayout());
	     frame.add(panell, new GridBagConstraints());
	     frame.setSize(new Dimension(1000, 700));

		panell.add(tabbedPane, new GridBagConstraints());
		 // 		System.out.println(elements.length +" " +previous + "   " + elements[1]+ " ");
		JPanel panel = new JPanel(); 
		panel.setPreferredSize(new Dimension(800,3000));
		
		
		panel2.add(panel, new GridBagConstraints());
		panel2.setLayout(new GridBagLayout());
		panel2.setPreferredSize(new Dimension(850, 550));
		
    	JScrollPane scrollOutput = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollOutput.setPreferredSize(new Dimension(790,490));
	    panel2.add(scrollOutput);

		  for (int i=0; i<previous; i++) 
		{
		   JPanel sPanel = new JPanel(); 
		 //  sPanel.setSize(new Dimension(200,40)); 
		   //  sPanel.setLayout(null); 
		   sPanel.setPreferredSize(new Dimension(700, 150));
		   
			  JTextPane pane = new JTextPane(); 
			
			pane.setText(elements[i]); 
			
			pane.setPreferredSize(new Dimension(700, 100)); 
			sPanel.add(pane); 
	
            JPanel subPanel = new JPanel(); 
            subPanel.setLayout(new GridLayout(0,4)); 
			JRadioButton je = new JRadioButton("EVET"); 
					JRadioButton jh = new JRadioButton("HAYIR"); 
					JRadioButton ju= new JRadioButton("UYGULANAMAZ"); 
					JRadioButton jb = new JRadioButton("BÝLÝNMÝYOR"); 
			jb.setSelected(true); 
			
			subPanel.add(je);
			subPanel.add(jh);
			subPanel.add(ju);
			subPanel.add(jb);
			
			 ButtonGroup group = new ButtonGroup();
			
			    group.add(je);
			    group.add(jh);
			    group.add(ju);
			    group.add(jb);
           sPanel.add(subPanel);
		   panel.add(sPanel, new GridBagConstraints());	
			
			
		}
		
		  MeasurementPanel(); 
		  
		  AnalyzePanel();
		  TrendPanel();
		  ToolPanel();
		  QRRPanel();
		  classQRRPanel();
		  
		
		  
		
  }
  
  public void AnalyzePanel () 
  {

	 JPanel analyzePanel = new JPanel(); 
	 analyzePanel.setLayout(new GridLayout (2,2));
	 JPanel panel11 = new JPanel();  
	  
	  
	  panel11.setLayout(new GridLayout(0,2, 40, 40));
	  panel11.add(new Label ("Projects")); 
	  panel11.add(cbProjects);
	  panel11.add(new Label ("Dates")); 
	  panel11.add(cbDates);
	  panel11.add(new Label ("Tools")); 
	  panel11.add(cbTools);
	  
	  panel11.add(new Label ("List Metric Values")); 
	  panel11.add(listMetrics);
	  listMetrics.addActionListener(new ListMetricValues());
	  
	  analyzePanel.add(panel11); 
	  panel1.add(analyzePanel);
	  
  }

  
  
  public void ToolPanel () 
  {
	 
	 JPanel toolPanel = new JPanel(); 
	 toolPanel.setLayout(new GridLayout (0,2));
	 
	 
	 JPanel panel11 = new JPanel();  
	 JPanel panel12 = new JPanel();
	  
	  
	  panel11.setLayout(new GridLayout(0,2, 10, 10));
	 // panel12.setLayout(new GridLayout(0,0, 10, 10));
	  panel11.add(new Label ("Projects")); 
	  panel11.add(cbProjects3);
  
	  JPanel panelm11 = new JPanel(); 
  
	  JPanel panelm12 = new JPanel(); 

	  cbProjects3.addActionListener(new UpdateDates(lbDates3, model3));
	  
	  
	  ButtonGroup bgm1 = new ButtonGroup(); 
	  bgm1.add(sumButton1a);
	  bgm1.add(maxButton1a);
	  bgm1.add(avgButton1a);
	  bgm1.add(excButton1a);
	 
	  ButtonGroup bgm2 = new ButtonGroup(); 
	  bgm2.add(sumButton2a);
	  bgm2.add(maxButton2a);
	  bgm2.add(avgButton2a);
	  bgm2.add(excButton2a);
	  
	  
	  panel11.add(new Label (""));
	  panel11.add(new Label (""));
	  
	  panel11.add(new Label ("Metric 1 (Primary"));
	  panel11.add(cbMetrics3a);
	  panel11.add(sumButton1a);
	  panel11.add(maxButton1a);
	  panel11.add(avgButton1a);
	  panel11.add(excButton1a);
	  
	  panel11.add(new Label (""));
	  panel11.add(new Label (""));
	  panel11.add(new Label ("Metric 2 (Secondary"));
	  panel11.add(cbMetrics3b);
	  panel11.add(sumButton2a);
	  panel11.add(maxButton2a);
	  panel11.add(avgButton2a);
	  panel11.add(excButton2a);
	  
	  panel11.add(new Label (""));
	  panel11.add(new Label (""));
	  
	  
	  
	  panel11.add(new Label ("")); 
	  panel11.add(showTrend2);
	  showTrend2.addActionListener(new ShowTrend2());
	  
	  
	  
	  
	  panel12.add(new Label ("Dates")); 
	  panel12.add(lbDates3);
	  
	  toolPanel.add(panel11);
	  toolPanel.add(panel12); 

//	  trendPanel.add(panel12); 
	  panel4.add(toolPanel); 
	  /*canvas = new DrawCanvas();
	  canvas.setPreferredSize(new Dimension (300, 200));
	  panel3.add(canvas); 
	  */
		
		
	  
	  
	  frame.revalidate();
	  frame.repaint();
	  
	  
	  
  }
  
  public void QRRPanel () 
  {
	 
	 JPanel qrrPanel = new JPanel(); 
	 qrrPanel.setLayout(new GridLayout (0,2));
	 
	 
 	     JPanel panel11 = new JPanel();  
    	 JPanel panel12 = new JPanel();
	  
	  panel11.setLayout(new GridLayout(0,2, 10, 10));
      panel11.add(new Label ("Projects")); 
	  panel11.add(cbProjects4);

	  panel11.add(new Label ("Tools")); 
	  panel11.add(cbTools4);
	  
	  
	  
	  cbProjects4.addActionListener(new UpdateDates(lbDates4, model4));
	  cbTools4.addActionListener(new UpdateMethods(lbMethods4, methodModel4));
	  
	  panel11.add(new Label ("")); 
	  panel11.add(qrrTrend1);
	  qrrTrend1.addActionListener(new ShowTrendQRR());
	  
	  panel12.add(new Label ("Dates")); 
	  panel12.add(lbDates4);
	  panel12.add(new Label ("Methods")); 
	  lbMethods4.setPreferredSize(new Dimension (200,2800));
	  	JScrollPane jsp = new JScrollPane(lbMethods4);
	  	jsp.setPreferredSize(new Dimension (200,500));
	  panel12.add(jsp);
	  
	  qrrPanel.add(panel11);
	  qrrPanel.add(panel12); 
 
	  panel5.add(qrrPanel); 
	  
	  frame.revalidate();
	  frame.repaint();
	  
  }
  
  public void classQRRPanel () 
  {
	 
	 JPanel qrrPanel2 = new JPanel(); 
	 qrrPanel2.setLayout(new GridLayout (0,2));
	 
	 
 	     JPanel panel111 = new JPanel();  
    	 JPanel panel122 = new JPanel();
	  
	  panel111.setLayout(new GridLayout(0,2, 10, 10));
      panel111.add(new Label ("Projects")); 
	  panel111.add(cbProjects5);

	  panel111.add(new Label ("Tools")); 
	  panel111.add(cbTools5);
	  
	  
	  
	  cbProjects5.addActionListener(new UpdateDates(lbDates5, model5));
	  cbTools5.addActionListener(new UpdateClasses(lbClasses5, classModel5));
	  
	  panel111.add(new Label ("")); 
	  panel111.add(qrrTrend2);
	  qrrTrend2.addActionListener(new ShowTrendQRR_OO());
	  
	  panel122.add(new Label ("Dates")); 
	  panel122.add(lbDates5);
	  panel122.add(new Label ("Methods")); 
	  lbClasses5.setPreferredSize(new Dimension (200,2800));
	  	JScrollPane jsp = new JScrollPane(lbClasses5);
	  	jsp.setPreferredSize(new Dimension (200,500));
	  panel122.add(jsp);
	  
	  qrrPanel2.add(panel111);
	  qrrPanel2.add(panel122); 
 
	  panel7.add(qrrPanel2); 
	  
	  frame.revalidate();
	  frame.repaint();
	  
  }
  
  public JPanel trendPanelButtons() 
  {
	  // Trend panelin solundaki button ve combobox grubu  
	  JPanel panel11 = new JPanel();  
	  panel11.setLayout(new GridLayout(0,2, 10, 10)); // Layout 2 sütun halinde, kaç satýr olacaðý tanýmsýz
	  panel11.add(new Label ("Projects")); 
	  panel11.add(cbProjects2);
	  panel11.add(new Label ("Tools")); 
	  panel11.add(cbTools2);
	  
	  cbProjects2.addActionListener(new UpdateDates(lbDates2, model2));
      	  
	  ButtonGroup bgm1 = new ButtonGroup(); // butonlarý radio button formuna çevirebilmek için 
	  bgm1.add(sumButton1);
	  bgm1.add(maxButton1);
	  bgm1.add(avgButton1);
	  bgm1.add(excButton1);
	  ButtonGroup bgm2 = new ButtonGroup(); // butonlarý radio button formuna çevirebilmek için 
	  bgm2.add(sumButton2);
	  bgm2.add(maxButton2);
	  bgm2.add(avgButton2);
	  bgm2.add(excButton2);
	  panel11.add(new Label ("")); // güzel görünüm için boþ satýr
	  panel11.add(new Label (""));
	  
	  panel11.add(new Label ("Metric 1 (Primary"));
	  panel11.add(cbMetrics2a);
	  panel11.add(sumButton1);
	  panel11.add(maxButton1);
	  panel11.add(avgButton1);
	  panel11.add(excButton1);
	  
	  panel11.add(new Label ("")); // güzel görünüm için boþ satýr
	  panel11.add(new Label (""));
	  panel11.add(new Label ("Metric 2 (Secondary"));
	  panel11.add(cbMetrics2b);
	  panel11.add(sumButton2);
	  panel11.add(maxButton2);
	  panel11.add(avgButton2);
	  panel11.add(excButton2);
	  panel11.add(new Label ("")); 
	  panel11.add(showTrend);
 	  showTrend.addActionListener(new ShowTrend()); 
	  return panel11; 
  }
  
  
  public void TrendPanel () 
  {
	 
	 JPanel trendPanel = new JPanel(); 
	 trendPanel.setLayout(new GridLayout (0,2));
	 
  	 JPanel panel12 = new JPanel();
	  
	  panel12.add(new Label ("Dates")); 
	  panel12.add(lbDates2);
	  
	  trendPanel.add(trendPanelButtons());
	  trendPanel.add(panel12); 

	  panel3.add(trendPanel); 
	  frame.revalidate();
	  frame.repaint();
	  
	  
  }
  
  public void setComboBoxContent (JComboBox jcb, List<String> content)
  {
	 
	  jcb.removeAllItems();  
	  for (int i=0; i< content.size(); i++)
	  {
		  jcb.addItem( content.get(i) );
	  }
	  
  }
  
  public void setListBoxContent (JList jlst, DefaultListModel mdl, List<String> content)
  {
	 
	  jlst.removeAll();  
	  mdl.removeAllElements();
	  for (int i=0; i< content.size(); i++)
	  {
		  mdl.addElement(content.get(i));
	  }
	  
  }
  
   
  public void PanelUpdates () 
  {
	  
	  Analyze anl = new Analyze (mainList); 
	  
	  
	  List<String> dateList= anl.listDateTime();
	  if (dateList != null)
	  {
		  
		  setComboBoxContent(cbDates, dateList);
		  setListBoxContent(lbDates2, model2, dateList);
		  setListBoxContent(lbDates3, model3, dateList);
		  setListBoxContent(lbDates4, model4, dateList);
		  setListBoxContent(lbDates5, model5, dateList);
		  
	  }
	  
	  List<String> projectList= anl.listProjects();
	  if (projectList != null)
	  {
		  setComboBoxContent(cbProjects, projectList);
		  setComboBoxContent(cbProjects2, projectList);
		  setComboBoxContent(cbProjects3, projectList);
		  setComboBoxContent(cbProjects4, projectList);
		  setComboBoxContent(cbProjects5, projectList);
		  
	  }

	 
	  
	  List<String> toolList= anl.listTools();
	  if (toolList != null)
	  {
		  setComboBoxContent(cbTools, toolList);
		  setComboBoxContent(cbTools2, toolList);
		  setComboBoxContent(cbTools4, toolList);
		  setComboBoxContent(cbTools5, toolList);
	  }
	  
	  List<String> metricList= anl.listMetrics();
	  if (metricList != null){
		  setComboBoxContent(cbMetrics3a, metricList);
		  setComboBoxContent(cbMetrics3b, metricList);
		  setComboBoxContent(cbMetrics2a, metricList);
		  setComboBoxContent(cbMetrics2b, metricList);
		  
	  }
	  
	  
	  frame.revalidate();
	  frame.repaint();
	  
	  
	
	  
  }
  
  

  public void MeasurementPanel () 
  {
	          panel6.setPreferredSize(new Dimension(850, 550));
			  panel6.setLayout(new BorderLayout());
      		   JPanel cbPanel = new JPanel();
			   cbPanel.setLayout(new GridLayout(10,1));

			   JLabel cbLabel = new JLabel ("Tool");
			   cbLabel.setFont(labelFont);
			   cbPanel.add(cbLabel);
			   cbPanel.add (cb1); 
			   cbPanel.add (cb2);
		   JLabel lanLabel = new JLabel ("Language");
			   lanLabel.setFont(labelFont);
			   cbPanel.add(new Label("")); 
			   cbPanel.add(lanLabel);
			   ButtonGroup lanGroup = new ButtonGroup(); 
			   lanGroup.add(lanJava);
			   lanGroup.add(lanCSharp);
			   lanGroup.add(lanCPlus);
			   cbPanel.add(lanJava);
			   cbPanel.add(lanCSharp); 
			   cbPanel.add(lanCPlus); 
			   // Metric Type CheckBox
			   JPanel mtPanel = new JPanel(); 
			   mtPanel.setLayout(new GridLayout(10,1));
			   JLabel mtLabel = new JLabel ("Metric Type");
			   mtLabel.setFont(labelFont);
			   mtPanel.add(mtLabel);
			   
			  // mtPanel.add(new Label(""));
			   mtPanel.add (mt1); 
			   mtPanel.add (mt2);
			   mtPanel.add (mt3);
			   mtPanel.add (mt4);
			   mt1.setEnabled(false);
			   mt2.setEnabled(false);
			   
			    JPanel propPanel = new JPanel();
			    propPanel.setLayout (new GridLayout(10,1)); 
			    JLabel pLabel = new JLabel ("Preferences");
				   pLabel.setFont(labelFont);
				   propPanel.add(pLabel);
			    
			    propPanel.add(new Label("Project Name"));
			    propPanel.add(taProjName);
			    propPanel.add(new Label("Date "));
			    DateTimeStr dt = new DateTimeStr(); 
			    taMeasDate.setText(dt.dtString("YYYYMMdd")); 
			    propPanel.add(taMeasDate);
			    propPanel.add(new Label(""));
			    propPanel.add(jcbFuzzy);
			    
			    
			    JPanel buttonPanel = new JPanel();		    
			    buttonPanel.setLayout (new GridLayout(9,1)); 
			    buttonPanel.add(new Label(""));
			    buttonPanel.add(fileDlgBtn2);
			    buttonPanel.add(new Label(""));
			    buttonPanel.add(bStartMeasure);
			    buttonPanel.add(new Label(""));
			    buttonPanel.add(fileDlgBtn);
			    buttonPanel.add(new Label(""));
			    buttonPanel.add(fileDlgNASA);
			    buttonPanel.add(new Label(""));
			    
			    textArea1.setEditable(true);
				  textArea1.setColumns(16);
				  textArea1.setLineWrap(true);
				  textArea1.setRows(28);
				  textArea1.setWrapStyleWord(true);
				 JScrollPane jScrollPane1 = new JScrollPane(textArea1);
	
				 JPanel centerPanel = new JPanel();
			    centerPanel.add(cbPanel);
			    centerPanel.add(mtPanel);
			    centerPanel.add(propPanel);
			    centerPanel.add(buttonPanel);
			    centerPanel.add(jScrollPane1);
			  JPanel northPanel = new JPanel(); 
			  northPanel.add(fileProp);
	          fuzzyFlag = jcbFuzzy.isSelected();
	          
	         // System.out.println ("Fuzzy : " +jcbFuzzy.isSelected() + " actual : " +fuzzyFlag );
			  fileDlgBtn2.addActionListener(new SetMeasurementPreferences());
			  bStartMeasure.addActionListener(new StartMeasure());
			  fileDlgBtn.addActionListener(new FileSelection());
			  fileDlgNASA.addActionListener(new FileSelectionNASA());
			  
			   final JButton button2 = new JButton ("Copy"); 
			     button2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						textArea1.selectAll();
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); 
						String text = textArea1.getSelectedText();
						StringSelection selection = new StringSelection(text); 
						
						clipboard.setContents(selection, null);
						
						ConsoleOutput(taProjName.getText());
					}
				});
             //  panel6.add (button2);
			   JPanel southPanel = new JPanel(); 
				  southPanel.add(button2);
			   panel6.add(northPanel, BorderLayout.NORTH);
			   panel6.add(southPanel, BorderLayout.SOUTH);
			   panel6.add(centerPanel, BorderLayout.CENTER);
  }
  
  public OneMetricList MethodQRR (OneMetricList filteredOML) 
  {
	  
	  enterMethod();
	  QualityRiskRatio qrr = new QualityRiskRatio(filteredOML);
	  
	  OneMetricList oml1 = new OneMetricList();
	  
	  oml1 = new OneMetricList(qrr.ListQRR(oml1, "code")); 
	  oml1 = new OneMetricList(qrr.ListQRR(oml1, "vg"));
	  oml1 = new OneMetricList(qrr.ListQRR(oml1, "evg"));
	  oml1 = new OneMetricList(qrr.ListQRR(oml1, "comment"));
	  oml1 = new OneMetricList(qrr.ListQRR(oml1, "blank"));
	  oml1 = new OneMetricList(qrr.ListQRR(oml1, "mixed"));
	  oml1 = new OneMetricList(qrr.ListQRR(oml1, "totalLOC"));
	 // for (int z=0; z<oml1.GetSize(); z++)
	//	 System.out.println(oml1.Get(z).summary());
	  
	  //BigTableQRRMethod(oml1,"identifier");
	  mainList.merge(oml1);
	  PrepareQRRMethodTable(oml1,"QQQidentifier"); 
	  return oml1; 
  }
  
  public OneMetricList ClassQRR (OneMetricList filteredOML) 
  {
	  //OneMetricList fOML = new OneMetricList(filteredOML); 
	  //fOML.QRRFilter();
	  enterMethod();
	  QualityRiskRatio qrr2 = new QualityRiskRatio(filteredOML);
	  
	  OneMetricList oml2 = new OneMetricList();
	  
	  oml2 = new OneMetricList(qrr2.ListQRR(oml2, "LOCM")); 
	  oml2 = new OneMetricList(qrr2.ListQRR(oml2, "DIT"));
	  oml2 = new OneMetricList(qrr2.ListQRR(oml2, "CBO"));
	  oml2 = new OneMetricList(qrr2.ListQRR(oml2, "WMC"));
	  oml2 = new OneMetricList(qrr2.ListQRR(oml2, "RFC"));
	  oml2 = new OneMetricList(qrr2.ListQRR(oml2, "NOC"));
	  
	/*  for (int z=0; z<oml2.GetSize(); z++)
		 System.out.println(oml2.Get(z).summary()); 
	*/
		//BigTableQRRClass(oml2,"identifier"); 
	  mainList.merge(oml2);
	  PrepareQRRClassTable(oml2,"QQQidentifier"); 
	  
		  return oml2; 
  }
  
  public void addMetricToMainList (OneMetric om) 
  {
	  mainList.Add(om);
  }
  

  
  
  public void addReliabilityMetrics(OneMetricList filteredOML)
  {
	 enterMethod();
	  String iProject = filteredOML.Get(0).getProjectName(); 
	  String jDate = filteredOML.Get(0).getDateTime(); 
	  String kTool = filteredOML.Get(0).getMeasuremenTool(); 
	  
	  filteredOML.predictedFaultDensity(fuzzyFlag);
	  Reliability rel = filteredOML.omls.relf;
	  DecimalFormat df = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "a", df.format(rel.getA())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "b", df.format(rel.getB())));
	  mainList.Add( new OneMetric(iProject, iProject, jDate, kTool, "c", df.format(rel.getC())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "x", df.format(rel.getX())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "w", df.format(rel.getW())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "u", df.format(rel.getU())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "sx", df.format(rel.getSx())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "sm", df.format(rel.getSm())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "reliability", df.format(rel.reliability())));
	  
	  

	  
  }

  
  public void addReliabilityVolMetrics(OneMetricList filteredOML)
  {
	 
	  enterMethod();
	  String iProject = filteredOML.Get(0).getProjectName(); 
	  String jDate = filteredOML.Get(0).getDateTime(); 
	  String kTool = filteredOML.Get(0).getMeasuremenTool(); 
	  
	  Reliability rel = filteredOML.omls.rell;
	  DecimalFormat df = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "a_vol", df.format(rel.getA())));
	  mainList.Add(new OneMetric(iProject,iProject, jDate, kTool, "b_vol", df.format(rel.getB())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "c_vol", df.format(rel.getC())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "x_vol", df.format(rel.getX())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "w_vol", df.format(rel.getW())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "u_vol", df.format(rel.getU())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "sx_vol", df.format(rel.getSx())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "sm_vol", df.format(rel.getSm())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "reliability_vol",df.format(rel.reliability())));
	  
  }
  
  public void addMaintainabilityMetrics(OneMetricList filteredOML)
  {
	 enterMethod();
	  String iProject = filteredOML.Get(0).getProjectName(); 
	  String jDate = filteredOML.Get(0).getDateTime(); 
	  String kTool = filteredOML.Get(0).getMeasuremenTool(); 
	  
	  //Maintainability mnt = filteredOML.calculateMaintainability();
	  Maintainability mnt = filteredOML.getMaintainability();
	  DecimalFormat df = new DecimalFormat("##.##", DecimalFormatSymbols.getInstance(Locale.US));
	  DecimalFormat df2 = new DecimalFormat("#");
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "veryhigh", df.format(mnt.getVeryhigh())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "high", df.format(mnt.getHigh())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "moderate", df.format(mnt.getModerate())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "maintainability", df2.format(mnt.maintainability())));
	  
	  ///   14 Haziran 2017 
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "mntcl", df2.format(filteredOML.omls.mntcl.maintainability())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "mntcf", df2.format(filteredOML.omls.mntcf.maintainability())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "mntvgl", df2.format(filteredOML.omls.mntvgl.maintainability())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "mntvgf", df2.format(filteredOML.omls.mntvgf.maintainability())));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzymnt", df2.format(filteredOML.omlf.mntvgl.maintainability())));
	  
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cvhl", df.format(filteredOML.omls.cvhl)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "chl", df.format(filteredOML.omls.chl)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cml", df.format(filteredOML.omls.cml)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cnl", df.format(filteredOML.omls.cnl)));

	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cvhlr", df.format(filteredOML.omls.cvhlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "chlr", df.format(filteredOML.omls.chlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cmlr", df.format(filteredOML.omls.cmlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cnlr", df.format(filteredOML.omls.cnlr)));
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cvhf", df.format(filteredOML.omls.cvhf)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "chf", df.format(filteredOML.omls.chf)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cmf", df.format(filteredOML.omls.cmf)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cnf", df.format(filteredOML.omls.cnf)));
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cvhfr", df.format(filteredOML.omls.cvhfr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "chfr", df.format(filteredOML.omls.chfr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cmfr", df.format(filteredOML.omls.cmfr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "cnfr", df.format(filteredOML.omls.cnfr)));
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lvhl", df.format(filteredOML.omls.lvhl)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lhl", df.format(filteredOML.omls.lhl)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lml", df.format(filteredOML.omls.lml)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lnl", df.format(filteredOML.omls.lnl)));
	  

	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lvhlr", df.format(filteredOML.omls.lvhlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lhlr", df.format(filteredOML.omls.lhlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lmlr", df.format(filteredOML.omls.lmlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lnlr", df.format(filteredOML.omls.lnlr)));

	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lvhf", df.format(filteredOML.omls.lvhf)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lhf", df.format(filteredOML.omls.lhf)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lmf", df.format(filteredOML.omls.lmf)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lnf", df.format(filteredOML.omls.lnf)));
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lvhfr", df.format(filteredOML.omls.lvhfr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lhfr", df.format(filteredOML.omls.lhfr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lmfr", df.format(filteredOML.omls.lmfr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "lnfr", df.format(filteredOML.omls.lnfr)));
	  
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzycvhl", df.format(filteredOML.omlf.cvhl)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzychl", df.format(filteredOML.omlf.chl)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzycml", df.format(filteredOML.omlf.cml)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzycnl", df.format(filteredOML.omlf.cnl)));
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzycvhlr", df.format(filteredOML.omlf.cvhlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzychlr", df.format(filteredOML.omlf.chlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzycmlr", df.format(filteredOML.omlf.cmlr)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "fuzzycnlr", df.format(filteredOML.omlf.cnlr)));
	  
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "mntmc", df.format(filteredOML.omls.methodCount)));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "mntloc", df.format(filteredOML.omls.totalLOC)));
	  
	  
	  

	  
  }
  
  public String AnalyzeReliability () 
  {
	  enterMethod();
	  Analyze anl = new Analyze (mainList); 
	  List<String> dateList= anl.listDateTime();
	  List<String> projectList= anl.listProjects();
	  List<String> toolList= anl.listTools();
	   
	  String str = ""; 
	  
	  for (int i=0; i<projectList.size(); i++ )
	  {
		  String iProject = projectList.get(i);  
		  for (int j=0; j<dateList.size(); j++ )
		  {
			  String jDate = dateList.get(j); 
			  for (int k=0; k<toolList.size(); k++)
			  {
				   
				  String kTool = toolList.get(k); 
				  System.out.println("Processing .... " + iProject + " " + iProject + "    " + jDate +"   " + kTool);				
				  OneMetricList filteredOML = new OneMetricList(mainList); 
				  filteredOML.Filter_p(iProject, jDate, kTool);
				  if (filteredOML.GetSize() > 0) 
				  {
					  // Her modül için toplam satýr sayýsýný (proje, tarih, tool bazýnda) hesaplamak için
					  filteredOML  = addLOCToList(filteredOML);
				  
					  MethodQRR(filteredOML); 
					  ClassQRR(filteredOML); 		
					  					  
	  			
					 // System.out.println ("???" + iProject + " "+ jDate + " " + kTool + " "+ filteredOML.GetSize());
				  
				  
					  tempMetric =	new OneMetric(iProject, iProject, jDate, kTool, "xyz", "xyz");
					  filteredOML.logHeaderInfo(tempMetric);

					  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "methodcount", Double.toString(filteredOML.getMethodCount())));
					 // System.out.println("mc : " + filteredOML.getMethodCount());
					  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "classcount", Double.toString(filteredOML.getClassCount())));
					 // System.out.println("cc : " +filteredOML.getClassCount());
				     
					  
					  addReliabilityMetrics(filteredOML);
					  addReliabilityVolMetrics(filteredOML);
				      addMaintainabilityMetrics(filteredOML);
				      //addDesignComplexity(filteredOML);
				      //addTotalofTops(filteredOML, "methodQRR", "MQRR3",3);
				      addTotalofTops(filteredOML, "classQRR", "OOQRR3",3);
			
					  filteredOML.logMI();
					  filteredOML.logDefectCount(mi);
					  
	     			  str = str + filteredOML.logString; 
				  
				  }
				  else
				  {
					  System.out.println("No record found for filtering criterias   :  " + iProject + " | "+ jDate + " | " + kTool);				
					  str = "No record found for filtering criterias   :  " + iProject + " | "+ jDate + " | " + kTool;
				  }
	  
			  }
			  
		  }
	  }
	  
	  ConsoleOutput (projectList.get(0));
      return str; 
  }

  public void addTotalofTops(OneMetricList filteredOML, String qrrMetricType, String qrrMetricIdentifier, int toWhich) 
  { 
  enterMethod();
	  SortOperations sort = new SortOperations(mainList, qrrMetricType);
  OneMetricList sortedList = sort.bubbleSort();
  sortedList.revertList();
  String iProject = filteredOML.Get(0).getProjectName(); 
  String jDate = filteredOML.Get(0).getDateTime(); 
  String kTool = filteredOML.Get(0).getMeasuremenTool(); 

  double totalX = 0; 
  if (sortedList.GetSize()>=toWhich)
  {
  for (int i=0;i<toWhich; i++) 
  {
	  totalX += Double.parseDouble(sortedList.Get(i).getValue());
  }
  }
  
  
  
	  DecimalFormat df = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, qrrMetricIdentifier, df.format(totalX)));
  
  }
  
  public void addDesignComplexity(OneMetricList filteredOML) 
  {
	  
	  
  }
 public void addDesignComplexity1(OneMetricList filteredOML) 
  { 
  SortOperations sort = new SortOperations(mainList, "classQRR");
  OneMetricList sortedList = sort.bubbleSort();
  sortedList.revertList();
  String iProject = filteredOML.Get(0).getProjectName(); 
  String jDate = filteredOML.Get(0).getDateTime(); 
  String kTool = filteredOML.Get(0).getMeasuremenTool(); 

    double total3 =  Double.parseDouble(sortedList.Get(0).getValue())  + Double.parseDouble(sortedList.Get(1).getValue()) + Double.parseDouble(sortedList.Get(2).getValue());
  
	  DecimalFormat df = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
	  mainList.Add(new OneMetric(iProject, iProject, jDate, kTool, "OOQRR3", df.format(total3)));
  
  }
  
  
	public void MeasurementSettings () 
  {
	
		  MCCABE = cb1.isSelected(); 
		  UNDERSTAND = cb2.isSelected(); 
		  GENELSATIR = mt1.isSelected();
		  GENELKARMASIKLIK = mt2.isSelected(); 
		  GENELNESNE = mt3.isSelected(); 
		  GENELBAKIM = mt4.isSelected();
		  
		  if (lanJava.isSelected())
		  {
			  languageString = " JDK1.7 *.java "; 
		  } 
		  else if (lanCSharp.isSelected())
		  {
			  languageString = " MSVCS10.0  *.cs ";
			  
		  }
		  
	
 
  }
  
  public void run_ (String csvFile) {
	  
		 
		BufferedReader br = null;
		String line ;
		String cvsSplitBy = "_";
		// Regex'i nereden aldým : 
		// http://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes 
		//
		
	   String previousLine = ""; 
	   // System.out.println(csvFile);
		try {
	     	br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				  // System.out.println(line);
				// use comma as separator
				String[] cells = line.split(cvsSplitBy); 
				if (cells[0].compareTo("") != 0 ) 
				{
						if ( Integer.parseInt(cells[0]) == previous)
				{
					previousLine = previousLine.concat("\r\n");
					previousLine = previousLine.concat(cells[1]) ; 
				}
				else 
				{
					elements [previous] = previousLine; 
					// System.out.println("**"+ elements[previous]);
					previousLine = cells[1]; 
					previous = Integer.parseInt(cells [0]); 
					// System.out.println(previous +"***" +previousLine );
					
				}
				}  
			} 				
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
  
	public void writeLogs (String str) throws BadLocationException 
	{
		
	     
	      BufferedWriter bufferedWriter = null;
	   
	        try {
	        	File f = new File(".");
	            //Construct the BufferedWriter object
	            bufferedWriter = new BufferedWriter(new FileWriter(f.getAbsolutePath() + "\\" +fileName ));
	                   	bufferedWriter.write(str); 
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
		 
	        
	     try {
			if (bufferedWriter != null)
	    	 bufferedWriter.close();
			//bufferedWriter = null; 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		
	     textArea1.setText(str);
	     UpdateFileName(fileName);
	
	   
	    
	   
	    // textArea1.setSelectionStart(100);
	    // textArea1.setSelectionEnd(100 + logString.length());
	     textArea1.setSelectedTextColor(Color.RED);
	     
	    
	     
	    // textArea1.SETS
	    // textArea1.se   lect(0, 100);
	     //textArea1.getHighlighter().addHighlight(1, 100, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
	     //textArea1.setCaretPosition(100);
	     
	     
		
	}
  
  public void UpdateFileName (String text) 
  {
	  fileProp.setSize(text.length(), 1);
	     fileProp.setText(text);
	     fileProp.setFocusable(true);
	     fileProp.transferFocus();
	     fileProp.revalidate();
	     fileProp.repaint();
  }
  
  
  
  public OneMetric measurementParameters (String fName) 
  {
  //	System.out.println (fName);
	  OneMetric temp = new OneMetric(); 
	  String[] cells = fName.split("_");
  	temp.setProjectName(cells[0]);
  	//setModuleName(cells[1]); 
  	temp.setDateTime(cells[2]); 
  	temp.setMeasuremenTool(cells[3]);
  	temp.setSourceFile(cells[4].substring(0, cells[4].indexOf("."))); 
  	return temp; 
  }
 

  
  public void run_code (String csvFile) {
	enterMethod();  
	int margin = 0;   
	BufferedReader br = null;
	String line ;
	String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
	int metricType = 0; 
	// Regex'i nereden aldým : 
	// http://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes 
	//
	mainList.logString = "";
	
	

	String moduleName ; 
      
	
    OneMetric temp = measurementParameters(fileName) ; 
    //System.out.println(temp.toString()); 
    mainList.logHeaderInfo(temp);
	try {
     	br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
 			String[] cells = line.split(cvsSplitBy); 
			//System.out.println(cells[0] + margin);
 			margin = processLine (cells, margin, temp); 
 			
		
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
  
  
  public BufferedWriter CreateBufferedWriter(String writePath)
  {
	  FileWriter fr = null;
		//String writePath = "c:\\f2\\nasa datasets\\nasaout.csv";  
      System.out.println(writePath);				 		
		try {
			fr = new FileWriter (writePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		BufferedWriter wr = new BufferedWriter (fr); 
		return wr;
  }
  
  public void NASArun_code (String csvFile, int fileCount) {
	  
		int margin = 0;   
		BufferedReader br = null;
		BufferedWriter wr = CreateBufferedWriter("c:\\f2\\nasa datasets\\nasaout.csv");				
		String line ;
		String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		int metricType = 0; 
		// Regex'i nereden aldým : 
		// http://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes 
		//
		mainList.logString = "";
		
		String moduleName ; 
	      		
	    OneMetric temp = measurementParameters(fileName) ; 
	    //System.out.println(temp.toString()); 
	    mainList.logHeaderInfo(temp);
	     
		try {
	     	br = new BufferedReader(new FileReader(csvFile));
	     	int i=1; 
	     	//line = "Module Name,code,v(G),ev(G),iv(G),P5,P6,P7,P8,P9,P10,P11,P12,P13,P14,P15,P16,P17,P18,19,P20,P21,defects";
	     	line = "Class Name,p1,p2,CBO,Depth,LOCM,NOC,p7,p8,RFC,WMC,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,p32,p33,p34,p35,p36,p37,p38,p39,p40,p41,p42,p43,p44,p45,p46,p47,p48,p49,p50,p51,p52,p53,p54,p55,p56,p57,p58,p59,p60,p61,p62,p63,p64,p65,p66,p67,p68,p69,p70,p71,p72,p73,p74,p75,p76,p77,p78,p79,p80,p81,p82,p83,p84,p85,p86,p87,p88,p89,p90,p91,p92,p93,p94,defects";
	     	
	     	wr.write (line+"\n"); 
			while ((line = br.readLine()) != null) {
	 			
				if (margin>0) 
					{
					line ="M"+Integer.toString(fileCount)+"q"+Integer.toString(i++)+","+line ;
					wr.write (line+"\n");
					}
				 
				
							
				String[] cells = line.split(cvsSplitBy);
				//System.out.println(cells[0] + margin);
	 			margin = processLine (cells, margin, temp); 
	 			
			
	 	    }  // while
			
	  	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					wr.close(); 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  }
  
 
  
  OneMetric defectValue (OneMetric om1) 
  {
	   OneMetric om = new OneMetric(om1); 
	  if (om.getMetricName().compareTo("defects")==0) 
	   {
		   if (om.getValue().compareTo("true")==0 || om.getValue().compareTo("yes")==0 || om.getValue().compareTo("_TRUE")==0) 
			   om.setValue("1");
		   else 
		   {
			   System.out.println("defects : "+om.getModuleName()+" |"+om.getValue()+"|");
			   om.setValue("0");
			   
		   }
	   }
	   return om; 
  }
  
  public int processLine (String [] cells, int margin , OneMetric temp ) 
  {
	  String moduleName; 
	  if (margin>0)
		{ 
     		
			moduleName = cells[margin-1];    
			  
			    // Ayný isimde ikinci bir understand metodu varsa ___sýrano ile ismi deðiþtiriliyor. 
			    if (margin == 2)    { 
		 		      if (cells[margin-1].compareTo(previousModule) == 0)  {
		 				   moduleName  = cells[margin-1] + "___"+Integer.toString(sameModuleCounter++)  ; 
		 				   
		 			   }
		 		      else 
		 		    	   sameModuleCounter = 0; 
		 		      
		 		      previousModule = cells[margin-1]; 
		 		}
	 		   				
	 		   for (int i=margin; i<cells.length; i++)		 		   {	
	 			  //System.out.println(keyList[i]);
	 			   if (margin == 1 || IsUnderstandNaming(cells[0], temp.getSourceFile()) )  {		   		 		   
	 			   
	 				   String mName = mi.getUniversalMetricName(keyList[i]); 
	 				   if (mName != null && cells[i].compareTo("")!=0)  { 
	 					   OneMetric om = new OneMetric(temp.getProjectName(), moduleName, temp.getDateTime(), temp.getMeasuremenTool(), mName, cells[i]); 
	 					   om.setSourceFile(temp.getSourceFile());
	 					   om.setMetricType(mi.getMetricType(mName));
                           om = defectValue(om);
                           
                           	 					   
	 					   mainList.Add(om);
	 					   //mainList.addIdentifier(om, "0");
	 				       //System.out.println(om.toString()); 
	 				       
	 				   }// if mname
	 		      }// if margin
	 		     
	 	       }// for
		}
		else {
		   keyList = checkContent(cells);
		   margin = Integer.parseInt(keyList[0]); 
		} // else
	  
	  return margin; 
  }
public String [] checkContent (String [] cells)

{
	String[] keyList = new String [100]; 
	int margin  = 0; 
    if (cells.length > 0) 
	{
		
			// McCabe file
	  		if (cells[0].equals("Module Name") || cells[0].equals("Class Name") || cells[0].equals("Program")) 
			{	
				margin = 1; 
				
			}
			// Understand file
			else if ( cells[0].equals("Kind") ) 
			{	
				margin = 2; 
				 
   	     	}
			
	  		// Metric names are gathered 
			if (margin>0) 
			{ 
				
						
				for (int i=0; i<cells.length; i++)
				{
					keyList [i]  =  cells [i] ; 
					//System.out.println("*"+keyList[i]+"*");
				}
				
			} 
			   
	}
    keyList[0] = Integer.toString(margin);
  return keyList;  
}  
  
public OneMetricList addLOCToList (OneMetricList oml) 
{
	enterMethod();    
	for (int i=0; i<oml.GetSize(); i++) 
    {
		OneMetric element1 = oml.Get(i);
		
	    double summ = 0; 
	    if (element1.getMetricName().compareTo("code") ==0) 
        {
	       	summ+= Double.parseDouble(element1.getValue());
	       	OneMetric element2 = oml.Get(i+1);
			OneMetric element3 = oml.Get(i+2);
			OneMetric element4 = oml.Get(i+3);   	
			if (element2.getMetricName().compareTo("comment") ==0)
	    	{
	    		summ+= Double.parseDouble(element2.getValue());
	    	}
			if (element3.getMetricName().compareTo("blank") ==0)
	    	{
	    		summ+= Double.parseDouble(element3.getValue());
	    	}
			if (element4.getMetricName().compareTo("mixed") ==0)
	    	{
	    		summ+= Double.parseDouble(element4.getValue());
	    	}
	       	
			OneMetric omt = new OneMetric();
	    	   String moduleName = element1.getModuleName();
	    	   //int loc =  (int) oml.totalLOCofaModule (element.getModuleName()) ; 
	   	       omt.setProjectName(element1.getProjectName());
	   	       omt.setDateTime(element1.getDateTime());
	   	       omt.setMeasuremenTool(element1.getMeasuremenTool());
	   	       omt.setModuleName(moduleName);
	   	       omt.setMetricName("totalLOC"); 
	   	       omt.setMetricType(1);
	   	       omt.setValue(Integer.toString((int)summ)); 
	     	   mainList.Add(omt); 
	     	   //mainList.setIdentifier(omt,Integer.toString(loc) );
	     	   oml.Add(omt);
        } 
	    		    	
	    	
	    	
	    	
	           
	     	  //System.out.println(omt.getValue()); 
    	
	  //  }
    	
    }
	return oml;
	 
	
	
}

 
}