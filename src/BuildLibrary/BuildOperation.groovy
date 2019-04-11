package BuildLibrary

def BuildOperationCall(def propertyFileLoc)
{
  def projectname = null;
  def BuildUrl = [];
  String[] serverList = null;
  def file = new File(propertyFileLoc);
  def ServersBuildDownload = [:]
  if (file.exists() && file.isFile()) 
  {
    String[] lines = file.text.split('\n')
	for(int i =0; i<lines.size(); i++)
	{
	 def row = lines[i];
	 if(row.contains("ProjectName")){projectname = getProjectName(row);}	
	 if(row.contains("BuildUrl")){BuildUrl = getBuildUrl(projectname,row)}
	 if(row.contains("ExecutionServer_List"))
	 {
	   serverList = getServerList(row);
	   println"serverList :"+serverList
	   println"size: "+serverList.size()
		for(int j =0; j<serverList.size(); j++)
			{
			ServersBuildDownload["node_" + serverList[j]] = {
			node(server) 
				{		
				 //Download latest build 
				 httpRequest ignoreSslErrors: true, outputFile: BuildUrl.get(1), responseHandle: 'NONE', url: BuildUrl.get(0)
				}
			  }		
		   }
	    } 
	}
	parallel ServersBuildDownload;
  }
}

def getServerList(row)
{
	String[] serverList = null;
	String[] rowvalues = row.split('=');
	def ServerListTemp = rowvalues[1]
	println"Row Value :"+ServerListTemp
	if(ServerListTemp.contains(","))
	{
	serverList = ServerListTemp.split(',');
	println"serverList with "+serverList
	}
	else{
	serverList = ServerListTemp.toString()
	println"Server LIST :"+serverList
	}
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