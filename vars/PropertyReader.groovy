class ProertyReader
{
def path
	def returnDatas(String path)
	{	println "Path Value is"+path
		InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream("D:\\Jenkins\\pipeline.properties");
		prop.load(input);
		return prop	 
	}
}