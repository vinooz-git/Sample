package BuildLibrary

def BuildOperationCall(def propertyFileLoc)
{
  def BuildUrl = [];
  def BuildOutputLoc = null;
  def Serv_ExecFileLoc = null;
  def file = new File(propertyFileLoc);
  def ServersBuildDownload = [:]
  def ServerExecution =[:]
  if (file.exists() && file.isFile()) 
  {
    String[] lines = file.text.split('\n')
	for(int i =0; i<lines.size(); i++)
	{
		def row = lines[i];
		String[] rowvalues = row.split(',');
		def Action = rowvalues[0].trim(); 
		if(row.contains("BuildOutputlocation")){BuildOutputLoc = getBuildOutLoc(row)}
		if(row.contains("BuildUrl")){BuildUrl = getBuildUrl(row,BuildOutputLoc)}
		if(row.contains("ServerExecutionFile")){Serv_ExecFileLoc = getServerExecutionFile(row)}
		if(Action.contains("BuildDownload"))
		{
		 def VmName = rowvalues[1].trim();
		 println"Vm Name is "+VmName
		 ServersBuildDownload["node_" + VmName] = {
			node(VmName) 
			{		
			 //Download latest build 
			 httpRequest ignoreSslErrors: true, outputFile: BuildUrl.get(1), responseHandle: 'NONE', url: BuildUrl.get(0)
				
			 //Extract the Build
			 fileOperations([fileUnZipOperation(filePath: BuildUrl.get(1), targetLocation: BuildOutputLoc)])
			
		    /*For PACS Project */
			 String[] Tempfoldername  = BuildUrl.get(2).split("[.]");
			 String FolderName = Tempfoldername[0].replaceAll("%20"," ");
			 String CopyFromFolder = BuildOutputLoc +"\\"+FolderName;     
			 def deleteFile = BuildOutputLoc +"\\*.zip";
			 //Copy and paste the content outside the Directory Delete the File and other unwanted Folder - Only for Pacs Project
			 PACS_CopyAndDelete(CopyFromFolder,BuildOutputLoc,deleteFile)
			 
			}
		  }
		}
		if(Action.contains("Exec_ServerInstall"))
		{
		 def VmName = rowvalues[1].trim();
		 println"Vm Name is "+VmName
		 ServerExecution["Exec_" + VmName] = {
			node(VmName) 
			{		
			//Running Server Installation Script
			 bat label: '', script: "(cmd/c call \"${Serv_ExecFileLoc}\")"
			}
		  }
		}
	}
  }
  parallel ServersBuildDownload;
  parallel ServerExecution;
}

def PACS_CopyAndDelete(CopyFromFolder,BuildOutputLoc,deleteFile)
{
	//Copy File and folder /* This step only for PACS Server Setup*
	bat label: '', script: "((robocopy \"${CopyFromFolder}\" ${BuildOutputLoc} /S /MT:100 > C:\\log.txt) ^& IF %ERRORLEVEL% LEQ 4 exit /B 0)"
	//Delete unwanted folders and files
	bat label: '', script: "(RD /S /Q \"${CopyFromFolder}\" > C:\\Deletelog1.txt)"
    bat label: '', script: "(del ${deleteFile} > C:\\Deletelog1.txt)"
}

def getBuildOutLoc(row)
{
	def outLoc = null;
	String[] rowvalues = row.split('=');
	outLoc =rowvalues[1].trim();
  return outLoc
}

def getServerExecutionFile(row)
{
def ExecFileLoc = null;
	String[] rowvalues = row.split('=');
	ExecFileLoc =rowvalues[1].trim();
  return ExecFileLoc
}

def getBuildUrl(row,buildOutLoc)
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