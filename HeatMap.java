

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class HeatMap {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double heatMatrix[][] ;  
    public int tasksforEachPeak = 7; 
    public int clusterCount ;
    public int clusters [][]; 

	public int tWidth ; 
	public int tHeight; 
	public String templateSelection; 
	public ColorSet heatColors [] ; 
	int colorCount = 0; 
	public int heatRadius = 15; //15; 
	public int allTasks; 
	public int allStations; 
	public int stationCount; 
	
	public int idealStationDistance; //100; 
	public int idealPeakDistance ;
	public int cNumbers = 0; 
    double maxxx ;
    double minnn ; 
    
    int xst, xen, yst, yen; 
    int nearClusters [] ;
    int nClusters =0;
    private ColorSet [] cset = new ColorSet[100]; 
    private int colorSetCount = 0; 
    
    OneMetricList oml ; 
	public int HOR_OFFSET = 50	; 
	public int VER_OFFSET = 50;
    
    
	public void Yaz(String st) 
	{
		 
		//System.out.println(st);
		 	 
	}
	
	HeatMapFrame f ; 

	public HeatMap (int radius, OneMetricList om) 
	{
		
	
		tWidth = 1200; 
		tHeight = 900; 
		heatMatrix = new double [3000][tHeight]; 
		//clusters = new int [tWidth][tHeight];
		heatColors = new ColorSet [1000000];
		
	//	heatRadius = radius; 
	//	idealPeakDistance = 50; 
		oml = om; 
		 
		 
		
		
		//ResetClusterMatrix();
				
		Yaz("Generic Heatmap Created ....................................................................................." );
		
		drawHeatMap();
		
		f = new HeatMapFrame(); 
		
		 
     	f.setBounds(0,0,tWidth  ,tHeight );
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//f.setUndecorated(true);
	    f.setTitle(oml.Get(0).getProjectName()+" "+oml.Get(0).getDateTime()); 
			

		 
		f.setVisible (true);
		f.createBufferStrategy(1); 
		f.drawStuff();
		//  f.revalidate();
		 // f.repaint();
		// RefreshTicker rt = new RefreshTicker(this, 1000);
		//addMouseMotionListener(this);
		

	} 
	/*  public class RefreshTicker extends TickerBehaviour {

    	  /**
		  
		 
		private static final long serialVersionUID = 584155733334479024L;

		public RefreshTicker (Agent a, long interval)
    	  {
    	    	super(a,interval);     	
    	  }
    	  
    	  protected void onTick() 
    	  {
    		  f.drawStuff(); 
    		  
    	  }
	  } */
	
	public class HeatMapFrame extends JFrame implements MouseListener 
	{
	 
		public HeatMapFrame () 
		
		{
			
			addMouseListener(this);
			
			
		}
		@Override 
		public void setSize (int width, int height) 
		{
			drawStuff();
			System.out.println ("setsize") ;
		}
		
		
		public void drawStuff () 
		{
		
			System.out.println("drawStuff");
			BufferStrategy bf = this.getBufferStrategy();
			Graphics g = null; 
			 Graphics2D g2 = null; 
			
			try 
			{
			     g = bf.getDrawGraphics();
			     g.setFont(new Font("arial",Font.PLAIN,10));
			     Rectangle bounds= f.getBounds();
			     Color r= g.getColor(); 
			     g.setColor(Color.white);
			     g.fillRect(0, 0, bounds.width,bounds.height);
			     g.setColor(r);
			    
			      g2 = (Graphics2D) g; 
			      
			      drawHeatMap(g2);
			}
			finally 
			{
				//g2.dispose();
				
				//g.dispose(); 
				
			//	Yaz(terrain+"> Grafikler çizdirilemiyor."); 
			}
			
			//bf.show(); 
			//Toolkit.getDefaultToolkit().sync(); 
		}
		
		
		public void repaint () 
		{
			 drawStuff();
		}
		
		
		/*	Frame fr = new Frame(); 
		
		
		for (int i=)
		*/
		public void fillCircle (int x, int y, int radius, Graphics2D g) 
		{
			g.fillOval(x-radius, y-radius, radius*2, radius*2) ; 
		}
		public void drawCircle (int x, int y, int radius,Graphics2D g) 
		{
			g.drawOval(x-radius, y-radius, radius*2, radius*2) ; 
		}
		public void drawHeatMap (Graphics2D    g) 
		{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1)) ; 
					for (int k=0;k<colorCount;k++) 
					{
						 //imageHeat.setRGB( hm.heatColors[k].xx, hm.heatColors[k].yy, hm.heatColors[k].cl.getRGB());
						 g.setColor(new Color (heatColors[k].cl.getRGB()));
					     fillCircle(HOR_OFFSET+heatColors[k].xx, VER_OFFSET + heatColors[k].yy, 1, g)   ;
					    // System.out.println("x : "+heatColors[k].xx + " y: " + heatColors[k].yy +" renk: " + heatColors[k].cl.getBlue());
					     
					}
		  	g.setColor(Color.red);
					/*for (int k=0; k<oml.GetSize(); k++)
					{
						
						OneMetric element = oml.Get(k); 
						if (element != null && element.getMetricName().compareTo("code") == 0 ) 
						{
							double mLoc = Double.parseDouble(element.getValue());   	 
							double mComp = oml.getValueD(element.getModuleName(), "vg");
							drawCircle((int) mComp + (int) HOR_OFFSET, (int) VER_OFFSET + 350,  (int) mLoc/4 , g)   ;
							
							
						}	
					}*/
				
					for (int i=0; i<30; i++) 
					{
						g.setColor(Color.red);
						g.drawString(""+i*50, i*50 + HOR_OFFSET, 550);
						g.setColor(Color.LIGHT_GRAY);
						g.drawLine(i*50 + HOR_OFFSET , VER_OFFSET, i*50 + HOR_OFFSET, 500 + VER_OFFSET);
					}
					
										g.setColor(Color.GRAY);
										g.drawString("LOC", 1050 + HOR_OFFSET, 510 + VER_OFFSET);
										g.drawLine(20 + HOR_OFFSET , VER_OFFSET, 20 + HOR_OFFSET, 520 + VER_OFFSET);
					 g.setColor(Color.BLACK);
					 drawHeatMapInfo(g);
					 
			 g.setColor(Color.white); 
			 
			
		}
		
		public void drawHeatMapInfo (Graphics2D    g) 
		{
			 SortOperations sort = new SortOperations(oml, "maintainability");
			  OneMetricList sortedList = sort.bubbleSort();
			  sortedList.revertList();
			 
			  OneMetric om1 = sortedList.Get(0); 
			 
			  
			  
			 //g.drawString("maintainability : " +oml.getValue(om1.getModuleName(),"maintainability") , 500 + HOR_OFFSET, 530 + VER_OFFSET);
			 int mnt = Integer.parseInt(oml.getValue(om1.getModuleName(),"maintainability"));
			 g.drawString("Proje : " + om1.getProjectName() , 600 + HOR_OFFSET, 560 + VER_OFFSET);
			 for (int i=0; i<mnt; i++) 
				 fillCircle (550 + HOR_OFFSET + i*40, 600 + VER_OFFSET, 20 , g );
			 for (int i=mnt; i<5; i++) 
				 drawCircle (550 + HOR_OFFSET + i*40, 600 + VER_OFFSET, 20 , g );
			 
			 double sx_vol =Double.parseDouble(oml.getValue(om1.getModuleName(),"sx_vol"));
			 g.drawString("sx_vol : " +sx_vol , 500 + HOR_OFFSET, 750 + VER_OFFSET);
			 double sm_vol =Double.parseDouble(oml.getValue(om1.getModuleName(),"sm_vol"));
			 g.drawString("sm_vol : " +sm_vol , 600 + HOR_OFFSET, 750 + VER_OFFSET); 
			 double reliability = sx_vol * sm_vol; 
			 g.drawString("reliability : " + reliability , 800 + HOR_OFFSET,750 + VER_OFFSET);
			 
			 double loc = oml.Sum("totalLOC");
			 double maxloc = oml.Maximum("totalLOC");
			 double mc = Double.parseDouble(oml.getValue(om1.getModuleName(), "methodcount"));
			 double cc = Double.parseDouble(oml.getValue(om1.getModuleName(), "classcount"));
			 g.drawString("LOC : " +loc , 500 + HOR_OFFSET, 790 + VER_OFFSET);
			 g.drawString("methods : " +mc , 600 + HOR_OFFSET, 790 + VER_OFFSET); 
			 g.drawString("classes : " + cc , 720 + HOR_OFFSET,790 + VER_OFFSET);
			 g.drawString("max LOC : " +maxloc , 820 + HOR_OFFSET, 790 + VER_OFFSET);
			 
			 g.drawLine((int) maxloc + HOR_OFFSET , VER_OFFSET, (int) maxloc + HOR_OFFSET, 550 + VER_OFFSET);
			 g.drawLine((int) maxloc + HOR_OFFSET +2  , VER_OFFSET, (int) maxloc + HOR_OFFSET +2 , 550 + VER_OFFSET);
			 

			 
			 double veryhigh = Double.parseDouble(oml.getValue(om1.getModuleName(),"veryhigh")); 
			 double high = Double.parseDouble(oml.getValue(om1.getModuleName(),"high"));
			 double moderate = Double.parseDouble(oml.getValue(om1.getModuleName(),"moderate"));
			 double normal = 100- (veryhigh+high+moderate); 
			 
			 g.drawString("very high : " +veryhigh , 500 + HOR_OFFSET, 720 + VER_OFFSET);
			 g.drawString("high : " +high , 600 + HOR_OFFSET, 720 + VER_OFFSET); 
			 g.drawString("moderate : " + moderate , 720 + HOR_OFFSET,720 + VER_OFFSET);
			 
			 
			 int vh = (int) veryhigh * 5; 
			 int h = (int) high * 5;
			 int m = (int) moderate * 5;
			 int n = (int) normal * 5;
			 
			 
			 g.setColor(Color.RED);
			 g.fillRect(500+HOR_OFFSET , 700, vh , 40);
			 g.setColor(Color.ORANGE);
			 g.fillRect((int) 500 +HOR_OFFSET+ vh , 700, h , 40);
			 g.setColor(Color.BLUE);
			 g.fillRect((int) 500 +HOR_OFFSET+ vh + h , 700,  m, 40);
			 g.setColor(Color.GREEN);
			 g.fillRect((int) 500 +HOR_OFFSET+ vh + h + m , 700, n , 40);
			 g.setColor(Color.BLACK);
			 
			 g.drawLine(525+HOR_OFFSET , 680, 525+HOR_OFFSET, 760);
			 g.drawLine(600+HOR_OFFSET , 690, 600+HOR_OFFSET, 750);
			/*for (int ii=0; ii<TERRAIN_WIDTH; ii+=50) 
			{
				for (int jj=0;jj<TERRAIN_HEIGHT; jj+=50) 
			
				{
					if(hm.clusters[ii][jj]>=0) 
						g.drawString("" +hm.clusters[ii][jj], ii+HOR_OFFSET, jj+VER_OFFSET); 
				
				}
			
			}*/
			 /* hm.ClusterRange(); 
		        
		        CropImageFilter KesilecekAlan=new CropImageFilter( hm.xst, hm.yst, hm.xen-hm.xst, hm.yen-hm.yst);
		        
		        FilteredImageSource KesilmisHali=new FilteredImageSource(imageHeat.getSource(), KesilecekAlan);

		       Image KesilmisResim=createImage(KesilmisHali);
			
			g.drawImage(KesilmisResim, HOR_OFFSET + hm.xst, VER_OFFSET + hm.yst, null); */
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			try {
			    
				setExtendedState(JFrame.NORMAL);
				setAlwaysOnTop(true);
				requestFocus();
				setAlwaysOnTop(false);
				
				/*f.setAlwaysOnTop(true); 	
				    f.toFront(); 
				    
				    f.repaint();
					f.setAlwaysOnTop(true);*/ 
				     drawStuff(); 
					
					File file = new File(".");
					String fileName = "_"; 		
					fileName =  "c:\\f6\\"+oml.Get(0).getProjectName()+"_"+oml.Get(0).getDateTime()+".JPeG";			
					
					//BufferedImage screenCapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					BufferedImage screenCapture = new Robot().createScreenCapture(new Rectangle(0, 0 , tWidth, tHeight));	
					File target = new File (fileName);
					ImageIO.write(screenCapture, "jpeg", target); 
				} 
			catch (IOException ae)
			{
				
			}
			catch (AWTException AA)
			{
							
			}
			
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*public void ResetClusterMatrix () 
	{
		for (int i=0; i<tWidth; i++) 
		{
			for (int j=0; j<tHeight; j++) 
			{
				clusters[i][j] = -1; 
			}
		}
		
	}*/
	/*
	public void ClusterRange () 
	{
		int minx = 1000; 
		int miny = 1000; 
		
		int maxx = 0; 
		int maxy = 0; 
		
		for (int i=0; i<tWidth; i++) 
		{
			for (int j=0; j<tHeight; j++) 
			{
				if (clusters[i][j] > 0 ) 
				{
					 if (i<minx) 
						 minx = i; 
					 if (j<miny)
						 miny = j; 
					 if (i>maxx) 
						 maxx = i; 
					 if (j>maxy) 
						 maxy = j; 
						 
				}
			}
		}
		
		xst = minx ; 
		xen = maxx; 
		yst = miny; 
		yen = maxy; 
	}
	*/
	public boolean IsNearCluster (int nearClusters[], int nc, int id)  
	{
		for (int i=0; i<nc; i++ ) 
		{
			if (nearClusters[i] == id) 
			{
				return true; 
			}
			
		}
		return false; 
	}

	
	public void ResetHeatMatrix () 
	{
		
		for (int i=0; i<tWidth; i++) 
		{
		    for (int j=0;j<tHeight; j++) 
			    heatMatrix [i][j] = 0;
		}
		
	}
	
	public void FillInTheHeatMatrix ()
	{
		
        double maxLOCInTheList = oml.Maximum("code"); 
        double maxVGInTheList = oml.Maximum("vg"); 
		double locFactor =  maxLOCInTheList<250?1 : maxLOCInTheList/250;
		double vgFactor =  maxVGInTheList<250?1 : maxVGInTheList/250;
		
		
		
		for (int i=0; i<oml.GetSize(); i++) 
		{
			
			OneMetric element = oml.Get(i); 
			if (element != null && element.getMetricName().compareTo("code") == 0 ) 
			{
				double mLoc = Double.parseDouble(element.getValue());   	 
				double mComp = oml.getValueD(element.getModuleName(), "vg");
				
				if (mLoc>100 || mComp>20)									
				    AddToHeatMatrix((int) mLoc, 150, (int) mComp );					
				//System.out.println(" mComp : " +mComp + " mLoc " + mLoc); 
			}
			
			  
			//System.out.println("Task "+i+" added to heat matrix " + t.xLoc + "-"+t.yLoc); 
			
		}
		
		
		double minmin = MinHeatMatrix();
		double maxmax = MaxHeatMatrix();
		
		
		
		
	}
	

	
	
	
	public void AddToHeatMatrix (int xx, int yy, int power)
	{
		
		int radius = 1 * power; 
		 
		//fr.ReadReferencePoint(templateSelection); 
			
		if (power <= 20 ) 
		{
		    // draw square if not complex 
			radius = 10; 
			for (int k=-radius; k<radius; k++) 
			{
				for (int l=-radius; l<radius; l++) 
				{
			         heatMatrix [xx+k][yy+l] += 1 ;
				}
			}
			      
		}
		
		else 
		{
			
			HeatCircle (xx,yy,power,radius); 
			
		}	
			
		
		
	}
	
	// if complexity is larger than 20, heatcircle is added  
	public void HeatCircle (int xx, int yy, int power, int radius) 
	{
		for (int k=-radius; k<radius; k++) 
		{
			for (int l=-radius; l<radius; l++) 
			{
				if (xx+k>-1 && xx+k<tWidth && yy+l >-1 && yy+l <tHeight) 
				{
					int dist =(int) CalcDistance(xx+k, yy+l,xx, yy);
					
					if (dist < radius && power>20) 
					     //heatMatrix [xx+k][yy+l] += radius ; 
						heatMatrix [xx+k][yy+l] += 1 ;
					
					
					
																								
				}
				
			}
				
			
			
		}
	}

	public Color GetAlphaColor(double alpha) 
	{
		Color c = null; 
		
		double blue = 0; 
		double red = 0; 
		double green = 0; 
		double value= alpha; 
	//	double spread = maxxx-minnn;
		
		
		    
		    double ratio = 2 * (value-minnn) / (maxxx - minnn);
		    blue = (Math.max(0, 255*(1 - ratio)));
		    red = (Math.max(0, 255*(ratio - 1)));
		    green = 255 - blue - red;
		    c = new Color ((int)red, (int)green, (int)blue) ; 
		    return c; 
	         	
		}

	public double MaxHeatMatrix () 
	{
		double maxx = 0.0; 
		
		for (int i=0; i<tWidth; i++) 
		{
			for (int j=0; j<tHeight; j++) 
			{
				
				if (heatMatrix[i][j]>maxx) 
				{
					maxx = heatMatrix[i][j]; 
					
				}
			}
		}
		return maxx; 
	} 
	

	/*
	public ColorSet MaxInCluster (TaskCluster tc, double smallerThan) 
	{
		double maxx = 0; 
		ColorSet cl = new ColorSet(); 
		
		for (int i=tc.rect.x1; i<tc.rect.x2; i++) 
		{
			for (int j=tc.rect.y1; j<tc.rect.y2; j++) 
			{

				if (clusters[i][j] == tc.index && heatMatrix[i][j] < smallerThan && heatMatrix[i][j]>maxx && !TooCloseToOtherPeaks(tc, i, j) ) 
				{
					maxx = heatMatrix[i][j];
					cl.xx = i; 
					cl.yy = j; 
					cl.maxRoboTime = maxx; 
					Yaz("Heatmap-MaxInCluster()> i : " +  i  +  " j : " + j + " clusterId : " + tc.index + " maxx : " + maxx + " smallerThan : " + smallerThan );
					
				}
			}
		}
		
		
		return cl; 
	}
	*/
	public double MinHeatMatrix () 
	{
		double minn = 1000000; 
		
		for (int i=0; i<tWidth; i++) 
		{
			for (int j=0; j<tHeight; j++) 
			{
				
				if (heatMatrix[i][j]>0 && heatMatrix[i][j]<minn) 
				{
					minn = heatMatrix[i][j]; 
					
				}
			}
		}
		return minn;  
	}
	
	public void Process ()  {
		
	    maxxx = MaxHeatMatrix();
	    minnn = MinHeatMatrix(); 

	    
	for (int i=0; i<tWidth; i++) 
	{
		for (int j=0; j<tHeight; j++) 
		{
			
			if (heatMatrix[i][j]>0) 
			{
				
				//Yaz("i : "+i+" j : "+ j + " heatMatrix : "+heatMatrix[i][j]); 
				Color cl = GetAlphaColor(heatMatrix[i][j]); 
				ColorSet cs = new ColorSet(); 
				cs.cl = cl; 
				cs.xx = i; 
				cs.yy = j;
				//clusters[i][j] = colorCount;
				heatColors[colorCount++] = cs; 
				//System.out.println ("x: " + cs.xx + " y: "+cs.yy + " renk : " +heatMatrix[i][j]);
				
				 
				
				
				
			} 
			
			
	
		
	}	
	
	    }
	}
	
	public void drawHeatMap () 
	{
		Yaz("HEATMAP>drawHeatMap() "); 
		
		   ResetHeatMatrix();
		   FillInTheHeatMatrix();
   		   Process ();
   		    

	}
	
		
	public double CalcDistance(int xs, int ys, int xd, int yd)
	{
		
		int x_fark = xs-xd ; 
		int y_fark =  ys-yd ;
		
		return Math.sqrt( x_fark * x_fark  + y_fark*y_fark ) ;	


	}
	/*
	public double CalcTPDistance(int xs, int ys, int xd, int yd)
	{
		
		Task ts = new Task (xs,ys,"Tasksource");
		Task td = new Task (xd,yd,"Taskdestination");
		 
		double dist = is.ShortestofClosestsLength(ts, td); 
		if (dist>=0 && dist <3000)
		    return dist; 
		else 
			return 9999;
		
		
		
	}*/


	
	

}
