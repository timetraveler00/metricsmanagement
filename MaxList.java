
public class MaxList /*extends SortOperations*/ {

	protected int capacity;  
	OneMetricList oml  = new OneMetricList();   
	OneMetricList buffer = new OneMetricList();  
	OneMetricList processed = new OneMetricList();  
	
	public void setCapacity (int cpcty) 
	{
		capacity = cpcty; 
	}
	
	public MaxList (OneMetricList omll, String filteredMetric, int cpcty)
	{
		this.oml = new OneMetricList(omll); 
		this.oml.Filter(filteredMetric);
		setCapacity(cpcty);
	}
	
	public void add (OneMetric om) 
	{
		
		if (buffer.GetSize()<capacity) 
		{
			buffer.Add(om);
		//	System.out.println("ADD capacity low --- buffer size "+buffer.GetSize());
			if (buffer.GetSize() == capacity)
				processed = bubbleSort();
		}
		
		else if (Double.parseDouble(buffer.Get(0).getValue()) < Double.parseDouble(om.getValue())) 
		{
			// remove the least important element of the sorted list 
			buffer.Remove(0);
			// add new one to the list and sort again
			buffer.Add(om);
			processed = bubbleSort(); 
			
		//	System.out.println("ADD buffer size "+buffer.GetSize());
		//	System.out.println("ADD processed size "+processed.GetSize());
			
		}
		else 
		{
			//System.out.println ("----------------------------------------------");
			//System.out.println ("MAX LIST ARRAY ADDITION REQUEST");
			//System.out.println ("List Capacity is " + capacity );
			//System.out.println ("Current Size is " + buffer.GetSize() );
			//System.out.println ("Max of the list is " + buffer.Get (buffer.GetSize()-1).getValue() );
			//System.out.println ("Min of the list is " + buffer.Get (0).getValue());
			//System.out.println ("Added/rejected value is " + om.getValue());
			//System.out.println ("----------------------------------------------");
			//System.out.println ("");
			
			
		}
		 
		
	}
	
	public OneMetricList start() 
	{
	
	//	System.out.println("START oml size : "+this.oml.GetSize());
		for (int i=0; i<this.oml.GetSize(); i++) 
		{
			add(this.oml.Get(i)); 
		}
		
		if (this.oml.GetSize()<capacity)
			processed = bubbleSort();
		
		processed.revertList();
	//	System.out.println("START processed size "+processed.GetSize());
		return processed; 
		
	}
	
	public OneMetricList bubbleSort () 
	{
		OneMetricList sortedList = this.buffer;
		System.out.println("START BUFFER size "+buffer.GetSize());
		int size = sortedList.GetSize(); 
		System.out.println("START SORTEDLIST size "+sortedList.GetSize());
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
		System.out.println("START SORTEDLIST size "+sortedList.GetSize());
		return sortedList; 
	}
	
	
}
