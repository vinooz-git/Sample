def call()
{
	InputStream input = null;
    String filePath
 def returnData(filePath)
	{
	String data = readData(filePath)
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
