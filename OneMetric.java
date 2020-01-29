
public class OneMetric {

private String projectName ; 
private String moduleName ; 
private String value; 
private String dateTime; 
private String measuremenTool;
private String metricName; 
private int metricType ; 
private String sourceFile; 
private double poc; 



public String getSourceFile() {
	return sourceFile;
}
public void setSourceFile(String sourceFile) {
	this.sourceFile = sourceFile;
}
public int getMetricType() {
	return metricType;
}
public void setMetricType(int metricType) {
	this.metricType = metricType;
}
public String getMetricName() {
	return metricName;
}
public void setMetricName(String metricName) {
	this.metricName = metricName;
}
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}
public String getModuleName() {
	return moduleName;
}
public void setModuleName(String moduleName) {
	this.moduleName = moduleName;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public String getDateTime() {
	return dateTime;
}
public void setDateTime(String dateTime) {
	this.dateTime = dateTime;
}
public String getMeasuremenTool() {
	return measuremenTool;
}
public void setMeasuremenTool(String maeasuremenTool) {
	this.measuremenTool = maeasuremenTool;
}

public String toString () 
{
	return "Project Name : " + projectName + " | Date : " + dateTime + " | Module Name : "+moduleName + " | Metric Type : " +metricName + " | Value : " + value + " | Tool : " + measuremenTool + " | poc : " +poc + " | Metric Type : "+metricType;  
}

public String summary () 
{
	return "Project Name : " + projectName + " | Date : " + dateTime + " | Module Name : "+moduleName + " | Metric Type : " +metricName + " | Value : " + value + " | Tool : " + measuremenTool + " | poc : " +poc + " | Metric Type : "+metricType;  
}

public OneMetric(String project, String moduleString, String date, String measureString, String metric, String val) {
	
	super();
	projectName = project; 
	dateTime = date; 
	measuremenTool = measureString; 
	metricName = metric;
	moduleName = moduleString; 
	metricType = 0; 
	this.value = val; 
			 
	
	
	// TODO Auto-generated constructor stub
}

public double getPoc() {
	return poc;
}
public void setPoc(double poc) {
	this.poc = poc;
}
public OneMetric() {
	
		metricType = 0; 
	// TODO Auto-generated constructor stub
}
public OneMetric(OneMetric om1) {
	
	this.projectName = om1.projectName; 
	this.moduleName = om1.moduleName; 
	this.dateTime = om1.dateTime; 
	this.measuremenTool = om1.measuremenTool ; 
	this.metricName = om1.metricName; 
	this.value = om1.value; 
	this.metricType = om1.metricType ; 
	this.value = om1.value; 
	toString(); 
}

	
	
}
