class ProertyReader
{
	public ProertyReader()
	{returnDatas()}
	def returnDatas()
	{
		InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream("//var//jenkinsFiles//pipeline.properties");
		prop.load(input);
		return prop	 
	}
}