class ProertyReader
{
def call()
{
	InputStream input = null;
    Properties prop = new Properties();
	input = new FileInputStream("${WORKSPACE}/pipeline.properties");
	prop.load(input);
	return prop	 
}
}