class ProertyReader
{
public ProertyReader()
{
	InputStream input = null;
    Properties prop = new Properties();
	input = new FileInputStream("${WORKSPACE}/pipeline.properties");
	prop.load(input);
	return prop	 
}
}