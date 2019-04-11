package BuildLibrary

def BuildOperationCall(def propertyFileLoc)
{
  def projectname = null;
  def BuildUrl = [];
  def file = new File(propertyFileLoc);
  if (file.exists() && file.isFile()) 
  {
    String[] lines = file.text.split('\n')
	for(int i =0; i<lines.size(); i++)
	{
		def row = lines[i];
		if(row.contains("ExecutionServer_List"))
		{
		String[] serverList = getServerList(row)
		println"Server List "+serverList
		}
		if(row.contains("ProjectName")){projectname = getProjectName(row)}		
		if(row.contains("BuildUrl"))
		{
		//BuildUrl = getBuildUrl(projectname,row)
		//Download latest build 
		//httpRequest ignoreSslErrors: true, outputFile: BuildUrl.get(1), responseHandle: 'NONE', url: BuildUrl.get(0)
		}
	}
  }
}

def getServerList(row)
{
	String[] serverList = null;
	String[] rowvalues = row.split('=');
	def ServerListTemp = rowvalues[1]
	if(ServerListTemp.contains(','))
	{
	serverList = ServerListTemp.split(',');
	}
	else
	{
	serverList = ServerListTemp
	}
	println"Sever List :"+serverList
  return serverList
}

def getProjectName(row)
{
	def projectname = null;
	String[] rowvalues = row.split('=');
	projectname =rowvalues[1].trim();
  return projectname
}

def getBuildUrl(projectname,row)
{	
	def buildCmd = [];
	String[] rowvalues = row.split('=');
	def BuildUrl = rowvalues[1].trim(); 
	buildCmd.add(BuildUrl);
	String[] filenametemp = BuildUrl.split('/');
	def Filename = filenametemp[filenametemp.size()-1]
	def BuildCopyLoc = "C:\\"+projectname+"_Build\\"+Filename;  //Build download Location 
	buildCmd.add(BuildCopyLoc);
  return buildCmd
}