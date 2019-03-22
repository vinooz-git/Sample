class PropertyReader 
{
	InputStream input = null;
    String filePath
 String returnData(filePath)
	{
	def data = readData(filePath)
	return data
	}
}
@NonCPS
 String readData(filePath)
{
	Properties prop = new Properties();
	input = new FileInputStream(filePath);
	prop.load(input);
	return prop	
}    
}