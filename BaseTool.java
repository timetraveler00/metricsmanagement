   public abstract class BaseTool
    {
        
		private boolean[] measurementType = new boolean[4] ; 
		private String[] measurementTypeStr = {"GENELSATIR", "GENELKARMASIKLIK", "GENELNESNE", "GENELBAKIM"};
		private String pcfPath;
		private String projectPath; 
		private String projectName;
		private String dateStr ; 
		private String language; 
		private String dialect; 
	   


	    public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getDialect() {
			return dialect;
		}

		public void setDialect(String dialect) {
			this.dialect = dialect;
		}

	public boolean[] getMeasurementType() {
			return measurementType;
		}

		public void setMeasurementType(boolean[] measurementType) {
			this.measurementType = measurementType;
		}

		public String getDateStr() {
			return dateStr;
		}

		public void setDateStr(String dateStr) {
			this.dateStr = dateStr;
		}

	public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

	public void CreateCSV()
        {
            DeleteFile();
            WriteFile();
            ExecuteFile();
        }

       public abstract void DeleteFile();
       public abstract void ExecuteFile();
       public abstract void WriteFile();
   	public boolean getMeasurementType(int index ) {
		return measurementType[index];
	}

	public void setMeasurementType(boolean gs, boolean gk, boolean gn, boolean gb) {
		measurementType [0] = gs ;
		measurementType [1] = gk ;
		measurementType [2] = gn ;
		measurementType [3] = gb ;
	}

	public String getPcfPath() {
		return pcfPath;
	}

	public void setPcfPath(String pcfPath) {
		this.pcfPath = pcfPath;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	
	 public String echoString (boolean metricType) 
	    {
	    	String echo = " echo "; 
	    	if (metricType) 
	    		echo = "";
	    	return echo; 
	    	
	    }

    }
