class PropertyReader {
	InputStream input = null;
    String filePath
   	String returnData(filePath)
	{
	Properties prop = new Properties();
	input = new FileInputStream(filePath);
	prop.load(input);
	return prop	
	}    
}