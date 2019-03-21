class PropertyReader {
	InputStream input = null;
    String filePath
   	String returnData(filePath)
	{
	input = new FileInputStream(filePath);
	prop.load(input);
	return prop	
	}    
}