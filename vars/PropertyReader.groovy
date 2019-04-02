def call()
{
		node 
		{ // To get environmental value
			def workspace = env.WORKSPACE
			println"workspace path is "+workspace
		}
		InputStream input = null;
		Properties prop = new Properties();
		println"workspace path is "+workspace
		input = new FileInputStream(workspace.toString()+"\\pipelineJob.properties");
		prop.load(input);
		return prop	 

}