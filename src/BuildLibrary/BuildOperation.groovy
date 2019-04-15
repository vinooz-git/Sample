package BuildLibrary

def BuildOperationCall(def propertyFileLoc)
{
  def projectname = null;
  def BuildUrl = [];
  String[] serverList = null;
  def BuildOutputLoc = null;
  def file = new File(propertyFileLoc);
  def ServersBuildDownload = [:]
  if (file.exists() && file.isFile()) 
  {
    String[] lines = file.text.split('\n')
	for(int i =0; i<lines.size(); i++)
	{
	 def row = lines[i];
	 if(row.contains("ProjectName")){projectname = getProjectName(row);}	
	 if(row.contains("BuildOutputlocation")){BuildOutputLoc = getBuildOutLoc(row)}
	 if(row.contains("BuildUrl")){BuildUrl = getBuildUrl(projectname,row,BuildOutputLoc)}
	 if(row.contains("ExecutionServer_List"))
	 {
	   serverList = getServerList(row);
		for(int j =0; j<serverList.size(); j++)
			{
			def nodeName = serverList[j]
			ServersBuildDownload["node_" + nodeName] = {
			node(nodeName) 
				{		
				/*
				 //Download latest build 
				 httpRequest ignoreSslErrors: true, outputFile: BuildUrl.get(1), responseHandle: 'NONE', url: BuildUrl.get(0)
				 
				 String[] Tempfoldername  = BuildUrl.get(2).split("[.]");
				 String FolderName = Tempfoldername[0].replaceAll("%20"," ");
				 String CopyFromFolder = BuildOutputLoc +"\\"+FolderName;
				 def deleteFile = BuildOutputLoc +"\\*.zip"
				 
				 //Extract the Build
				 fileOperations([fileUnZipOperation(filePath: BuildUrl.get(1), targetLocation: BuildOutputLoc)])
				 
				 //Copy File and folder /* This step only for PACS Server Setup*
				 bat label: '', script: "((robocopy \"${CopyFromFolder}\" ${BuildOutputLoc} /S /MT:100 > C:\\log.txt) ^& IF %ERRORLEVEL% LEQ 4 exit /B 0)"
				 
				 //Delete unwanted folders and files
				 bat label: '', script: "(RD /S /Q \"${CopyFromFolder}\" > C:\\Deletelog1.txt)"
				 bat label: '', script: "(del ${deleteFile} > C:\\Deletelog1.txt)"
				*/ 
				 //Running Server Instalation Script
				 bat label: '', script: 'call "C:\\Perl64\\bin\\perl.exe C:\\imgdrv\\Supdate.pl"'
				}
			  }		
		   }
	    } 
	}
	parallel ServersBuildDownload;
  }
  //Extract the Build 
    
}

def getServerList(row)
{
	def serverList = [];
	String[] rowvalues = row.split('=');
	def ServerListTemp = rowvalues[1]
	//println"Row Value :"+ServerListTemp
	if(ServerListTemp.contains(","))
	{
	serverList = ServerListTemp.trim().split(',');
	}
	else{
	serverList.add(ServerListTemp.trim());
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

def getBuildOutLoc(row)
{
def outLoc = null;
	String[] rowvalues = row.split('=');
	outLoc =rowvalues[1].trim();
  return outLoc
}

def getBuildUrl(projectname,row,buildOutLoc)
{	
	def buildCmd = [];
	String[] rowvalues = row.split('=');
	def BuildUrl = rowvalues[1].trim(); 
	buildCmd.add(BuildUrl);
	String[] filenametemp = BuildUrl.split('/');
	def Filename = filenametemp[filenametemp.size()-1]
	def BuildCopyLoc = buildOutLoc+"\\"+Filename;  //Build download Location 
	buildCmd.add(BuildCopyLoc);
	buildCmd.add(Filename)
  return buildCmd
}