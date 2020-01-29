
public class OMLSummary {

	
/*	private static OMLSummary instance = null; 
	
	protected OMLSummary () 
	{
		
	}
	
	public static OMLSummary getInstance() {
		
		if (instance == null) 
			instance = new OMLSummary(); 
		return instance; 
	}
	*/
	
	public double cvhl;   // x_vol
	public double chl;   // x_vol
	public double cml;  // w_vol
	public double cnl;  // u_vol
	
	public double cvhlr; 
	public double chlr;
	public double cmlr;
	public double cnlr;
	
	public double cvhf;    //x  
	public double chf;     //x
	public double cmf;     // w
	public double cnf;     // u
	
	public double cvhfr; 
	public double chfr;
	public double cmfr;
	public double cnfr;
	
	public double lvhl;   // a_vol
	public double lhl;   // a_vol
	public double lml;  // b_vol
	public double lnl;  // b_vol
	
	public double lvhlr; 
	public double lhlr;
	public double lmlr;
	public double lnlr;
	
	public double lvhf;    //a  
	public double lhf;     //a
	public double lmf;     // b
	public double lnf;     // c
	
	public double lvhfr; 
	public double lhfr;
	public double lmfr;
	public double lnfr;
	
	
	
	double totalLOC; 
	double methodCount; 
	Maintainability mntvgl, mntvgf, mntcl, mntcf;   
	Reliability rell, relf;
	
	public void CalculateAllRatios () 
	{
		if (totalLOC>0)
		{
			cvhlr = cvhl * 100 / totalLOC; 
			chlr = chl * 100 / totalLOC;
			cmlr = cml * 100 / totalLOC;
			cnlr = cnl * 100 / totalLOC;
			
			lvhlr = lvhl * 100 / totalLOC; 
			lhlr = lhl * 100 / totalLOC; 
			lmlr = lml * 100 / totalLOC; 
			lnlr = lnl * 100 / totalLOC;
			
		}
		
		if (methodCount>0)
		{
			cvhfr = cvhf * 100 / methodCount; 
			chfr = chf * 100 / methodCount;
			cmfr = cmf * 100 / methodCount;
			cnfr = cnf * 100 / methodCount;
			
			lvhfr = lvhf * 100 / methodCount; 
			lhfr = lhf * 100 / methodCount;
			lmfr = lmf * 100 / methodCount;
			lnfr = lnf * 100 / methodCount;
			
			
			
		}
		
		
		
	}
	
	
}
