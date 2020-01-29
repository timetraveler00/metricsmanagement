import java.util.Iterator;
import java.util.List;



public class SortOperations {

	OneMetricList oml = new OneMetricList();
	
	public SortOperations() 
	{
		
	}
	
	
	public SortOperations(List<OneMetric> list) {
			
		oml.SetList(list); 
			
	} 
	
	public SortOperations(List<OneMetric> list, String metricType) {
		
		oml.SetList(list); 
		oml.Filter(metricType);
				
	}
	
	public SortOperations(OneMetricList list, String metricType) {
		
		oml.SetList(list); 
		oml.Filter(metricType);
				
	}
	
public SortOperations(OneMetricList list, String metricType, String tool) {
		
		oml = new OneMetricList(); 
	    oml.SetList(list); 
		oml.Filter(metricType, tool);
				
	}
	
	
	private int partition (OneMetricList sortedList, int left, int right) 
	{
		
		int i= left; 
		int j= right;
		 
		
		double pivot = sortedList.GetValue(sortedList.GetSize()/2); 
		
		while ( i<= j) 
		{
			while (sortedList.GetValue(i) < pivot) 
			{
				i++; 
			}
			while (sortedList.GetValue(j) > pivot)
			{
				j++; 
			}
			if (i<= j) 
			{
				sortedList.Swap(i, j);
				i++;
				j--; 
			}
		};
		
		return i; 
		
		
	}
	
	private OneMetricList quickSort (OneMetricList sortedList, int left, int right) 
	{
		
		int index = partition (sortedList, left, right) ; 
		if (left < index -1 ) 
		{
			quickSort(sortedList, left, index-1); 
		}
		if (index < right )
		{
			quickSort(sortedList, index, right);
		}
		
		return sortedList; 
			
	}
	
	public OneMetricList quickSort () 
	{
		OneMetricList sortedList = this.oml;
		int left = 0; 
		int right = sortedList.GetSize()-1; 
		
		int index = partition (sortedList, left, right) ; 
		if (left < index -1 ) 
		{
			quickSort(sortedList, left, index-1); 
		}
		if (index < right )
		{
			quickSort(sortedList, index, right);
		}
		
		return sortedList; 
			
	}
	
	public OneMetricList bubbleSort () 
	{
		OneMetricList sortedList = this.oml;
		int size = oml.GetSize(); 
		for (int i=1; i<size ; i++) 
		{
			for (int j=0; j<size-i; j++) 
			{
				//System.out.println (sortedList.Get(j).getValue());
				if (sortedList.GetValue(j) > sortedList.GetValue(j+1)) 
				{
					sortedList.Swap(j, j+1);
				}
			}
		}
		
		return sortedList; 
	}
	
	public OneMetricList bubbleSort_top (int top) 
	{
		
		OneMetricList sortedList = this.oml;
		OneMetricList newList = new OneMetricList();
		
		
		
		
		return newList; 
	}
	
	public OneMetricList bubbleSort_top2 (int top) 
	{
		OneMetricList sortedList = this.oml;
		OneMetricList newList = new OneMetricList();
		
		int size = oml.GetSize();
		System.out.println("size of " + "qrr list " + " is " +size);
		double maxx = 10000000; 
		System.out.println("maxx of  000000 is " +maxx);
		
		for (int i=0; i<top ; i++) 
		{
			
			maxx = max1(maxx); 
			System.out.println("maxx of " + (i) + " is " +maxx);
			
			for (int j=0; j<size; j++) 
			{

				if (Double.compare(sortedList.GetValue(j), maxx) == 0) 
				{
					newList.Add(sortedList.Get(j));
				}
			}
		}
		
		return newList; 
	}
	
	
	public double max1 (double prevMax) 
	{
		OneMetricList sortedList = this.oml;
		int size = oml.GetSize(); 
		double maxx = 0;
		for (int i=0; i<size ; i++) 
		{
			    
			System.out.println("Max1 function : value "+sortedList.GetValue(i) + " maxx : "+maxx);
			if (sortedList.GetValue(i) > maxx && sortedList.GetValue(i) < prevMax)  
				{
					maxx = sortedList.GetValue(i); 
				}
			
		}
		
		return maxx; 
	}
	
	public OneMetricList bubbleSort_poc () 
	{
		OneMetricList sortedList = this.oml;
		int size = oml.GetSize(); 
		for (int i=1; i<size ; i++) 
		{
			for (int j=0; j<size-i; j++) 
			{
				if (sortedList.Get(j).getPoc() > sortedList.Get(j+1).getPoc()) 
				{
					sortedList.Swap(j, j+1);
				}
			}
		}
		
		return sortedList; 
	}
	
	

}
