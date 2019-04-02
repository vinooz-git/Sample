class ProertyReader
{
	def returnDatas()
	{
		node 
		{
			def workspace = env.WORKSPACE
			println"workspace path is "+workspace
		}
		InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream("D:\\Jenkins\\pipeline.properties");
		prop.load(input);
		return prop	 
	}
}