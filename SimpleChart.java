
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class SimpleChart extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a time series chart with dual axes.
     *
     * @param title  the frame title.
     * 
     * 
     */
	
	String primaryAxisLabel, secondaryAxisLabel  ; 
	String [][] data; 
	
		
	public void CopyStringMatrix (String [][] datam) 
	{
		  data = new String[datam.length][]; 
	        for (int i=0; i<datam.length; i++) 
	        {
	        	data[i] = new String[datam[0].length]; 
	        }
	        
	        for (int i=0; i< datam.length; i++) 
	        	for (int j=0; j<datam[i].length; j++) 
	        	{
	        		data[i][j] = datam[i][j]; 
	        	}

		
	}
	
	public void exportToPDF1(JFreeChart chart, String filePath) {
	    PDDocument doc = null;
	    PDPage page = null;
	    PDXObjectImage ximage = null;

	    try {
	        doc = new PDDocument();
	        page = new PDPage();
	        doc.addPage(page);
	        PDPageContentStream content = new PDPageContentStream(doc, page);
	        BufferedImage image = chart.createBufferedImage(500, 350);
	        ximage = new PDJpeg(doc, image);
	        content.drawImage(ximage, 1, 1);
	        content.close();
	    } catch(IOException ie) {
	    }
	    try {
			doc.save(filePath);
		} catch (COSVisitorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exportToPDF2(JFreeChart chart, String filePath){
	    PDDocument doc = null;
	    PDPage page = null;
	    PDXObjectImage ximage = null;
	    try {
	        doc = new PDDocument();
	        page = new PDPage();
	        doc.addPage(page);
	        PDPageContentStream content = new PDPageContentStream(doc, page);

	        //create a new outStream
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        ChartUtilities.writeChartAsJPEG(out, chart, 500, 450);//write to outstream
	        //create a new inputstream
	        InputStream in = new ByteArrayInputStream(out.toByteArray());
	        ximage = new PDJpeg(doc, in);
	        content.drawImage(ximage, 40, 320);
	        content.close();
	    }
	    catch (IOException ie){
	        //handle exception
	    }
	    //save and close
	    try {
			doc.save(filePath);
		} catch (COSVisitorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

    public SimpleChart(final String title, String [][] datam) {

        super(title);
        
        CopyStringMatrix (datam); 
        // create a title...
        final String chartTitle = title;
        final XYDataset dataset = createDataset(1);
        primaryAxisLabel = "MethodQRR"; 
        

        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
            chartTitle, 
            "Date", 
            primaryAxisLabel,
            dataset, 
            true, 
            true, 
            false
        );
        
      
        
        

  //      final StandardLegend legend = (StandardLegend) chart.getLegend();
    //    legend.setDisplaySeriesShapes(true);

        
        final XYPlot plot1 = chart.getXYPlot();
        final NumberAxis axis1 = new NumberAxis(primaryAxisLabel);
        //axis2.setLabelFont();
        axis1.setAutoRangeIncludesZero(true);
        plot1.setRangeAxis(0, axis1);
        Color[] colors =  {Color.RED, Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
  
    
        for (int i=1; i<datam.length; i++) 
        {
        	plot1.setDataset(i-1, createDataset(i));
          	XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            
            plot1.setRenderer(i-1, renderer);
            //renderer.setSeriesPaint(i-1, colors[i%10]);
            plot1.getRendererForDataset(plot1.getDataset(i-1)).setSeriesPaint(i-1, colors[i%10]);
            
            
        }
        
       
        //plot1.mapDatasetToRangeAxis(0, 0);
      

/*       
        final XYItemRenderer renderer1 = plot1.getRenderer();
        renderer1.setPlot(plot1);
        renderer1.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        renderer1.setSeriesPaint(0, Color.red);
        if (renderer1 instanceof StandardXYItemRenderer) {
            final StandardXYItemRenderer rr1 = (StandardXYItemRenderer) renderer1;
       //     rr.setPlotShapes(true);
            rr1.setShapesFilled(true);
        }
  */      
        
        
        
        final DateAxis axis = (DateAxis) plot1.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 470));
        setContentPane(chartPanel);
        
        
      
			exportToPDF1(chart, "c:\\f3\\"+ title + "_v1.pdf");
			exportToPDF2(chart, "c:\\f3\\"+ title + "_v2.pdf");
		

    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private XYDataset createDataset(int order) {

        final TimeSeries s1 = new TimeSeries(data[order][0]);
    	for (int j=1; j<data[0].length; j++) 
		{

			//System.out.println (getDay(data[0][j])+" "+  getMonth(data[0][j])+" "+ getYear(data[0][j]));
    		s1.add (new Day (getDay(data[0][j]), getMonth(data[0][j]), getYear(data[0][j])), Double.parseDouble(data[order][j]));
		}
        
    	 final TimeSeriesCollection dataset = new TimeSeriesCollection();
         dataset.addSeries(s1);

         return dataset;
        
        

    }
    
    public int getMonth (String date) 
    {
    	return Integer.parseInt(date.substring (4,6));     
    }
    
    public int getYear (String date) 
    {
    	return Integer.parseInt(date.substring (0,4));     
    }
    public int getDay (String date) 
    {
    	return Integer.parseInt(date.substring (6,8));     
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
 

}