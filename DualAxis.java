import java.awt.Color;
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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DualAxis extends ApplicationFrame {

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
	
    public DualAxis(final String title, String [][] datam) {

        super(title);
        
        CopyStringMatrix (datam); 
        // create a title...
        final String chartTitle = title;
        final XYDataset dataset = createDataset(1);
        primaryAxisLabel = datam[1][0]; 
        secondaryAxisLabel = datam[2][0];

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
        plot1.setDataset(0, createDataset(1));
        if (datam.length>3) plot1.setDataset(1, createDataset(3));
       
        plot1.mapDatasetToRangeAxis(0, 0);
        if (datam.length>3)plot1.mapDatasetToRangeAxis(1, 0);
        
        final XYPlot plot2 = chart.getXYPlot();
        final NumberAxis axis2 = new NumberAxis(secondaryAxisLabel);
        //axis2.setLabelFont();
        axis2.setAutoRangeIncludesZero(true);
        plot2.setRangeAxis(1, axis2);
        plot2.setDataset(2, createDataset(2));
        if (datam.length>3)plot2.setDataset(3, createDataset(4));
       
        plot2.mapDatasetToRangeAxis(2, 1);
        if (datam.length>3) plot2.mapDatasetToRangeAxis(3, 1);
        

       
        final XYItemRenderer renderer1 = plot1.getRenderer();
        renderer1.setPlot(plot1);
        renderer1.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        renderer1.setSeriesPaint(0, Color.red);
        if (renderer1 instanceof StandardXYItemRenderer) {
            final StandardXYItemRenderer rr1 = (StandardXYItemRenderer) renderer1;
       //     rr.setPlotShapes(true);
            rr1.setShapesFilled(true);
        }
        
        if (datam.length>3) 
            
        {
        
        final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setPlot(plot1);
        renderer2.setSeriesPaint(0, Color.black);
       // renderer2.setPlotShapes(true);
        renderer2.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        plot1.setRenderer(1, renderer2);
        }
     
        final StandardXYItemRenderer renderer3 = new StandardXYItemRenderer();
        renderer3.setPlot(plot2);
        renderer3.setSeriesPaint(0, Color.magenta);
       // renderer2.setPlotShapes(true);
        renderer3.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        
        plot2.setRenderer(2, renderer3);
        
        if (datam.length>3) 
            
        {
        final StandardXYItemRenderer renderer4 = new StandardXYItemRenderer();
        renderer4.setPlot(plot2);
        renderer4.setSeriesPaint(0, Color.blue);
       // renderer2.setPlotShapes(true);
        renderer4.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        
        plot2.setRenderer(3, renderer4);
        }
        
        final DateAxis axis = (DateAxis) plot1.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 470));
        setContentPane(chartPanel);
        
        exportToPDF2(chart,"c:\\f3\\"+ title + ".pdf");
        

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
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

      /*  final DualAxis demo = new DualAxis("Dual Axis Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
*/
    }

}
