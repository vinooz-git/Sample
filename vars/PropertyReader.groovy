class PropertyReader {

   String filePath
   PropertyReader(String filePath) {
        this.filePath = filePath
	returnData(filePath)
    }
	String returnData(Path)
	{
	InputStream input = null;
	input = new FileInputStream(Path);
	prop.load(input);
	return prop	
	}
    
}