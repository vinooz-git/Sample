class ProertyReader
{
	public ProertyReader()
	{returnDatas()}
	def returnDatas()
	{
		InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream("C:\\Program Files (x86)\\Jenkins\\workspace\\AdminPipeLine\\pipeline.properties");
		prop.load(input);
		return prop	 
	}
}