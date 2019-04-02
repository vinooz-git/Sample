def call()
{
		node 
		{ 
		// To get environmental value
		def jenkins = Jenkins.instance
    
        //get job Item
		def item = Jenkins.instance.getItemByFullName("${env.JOB_NAME}")
		println "Job name - " + item
		// get workspacePath for the job Item
		def workspacePath = Jenkins.instance.getWorkspaceFor(item)
		println "Workspace path - " + workspacePath
    		
		InputStream input = null;
		Properties prop = new Properties();
		println"workspace path is "+workspace
		input = new FileInputStream(workspace.toString()+"\\pipelineJob.properties");
		prop.load(input);
		return prop	 
        }
}