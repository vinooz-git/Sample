class PropertyReader {

   String filePath
   PropertyReader()
   {
   
   }
   public PropertyReader(String filePath) {
        this.filePath = filePath
	  returnData(filePath)
    }
	String returnData(Path)
	{
	InputStream input = null;
	input = new FileInputStream(Path);
	return prop	
	prop.load(input);
	}
    
}