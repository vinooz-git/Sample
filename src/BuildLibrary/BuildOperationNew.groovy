package BuildLibrary

def BuildOperationCall(def propertyFileLoc)
{
  def BuildUrl = [];
  def BuildOutputLoc = null;
  def file = new File(propertyFileLoc);
  def ServersBuildDownload = [:]
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
		if(Action.contains("BuildDownload"))
		{
		 def VmName = rowvalues[1].trim();
		 println"Vm Name is "+VmName
			node(VmName) 
			{		
			 //Download latest build 
			 httpRequest ignoreSslErrors: true, outputFile: BuildUrl.get(1), responseHandle: 'NONE', url: BuildUrl.get(0)
				 
			 String[] Tempfoldername  = BuildUrl.get(2).split("[.]");
			 String FolderName = Tempfoldername[0].replaceAll("%20"," ");
			 String CopyFromFolder = BuildOutputLoc +"\\"+FolderName;     //Copy and paste the content outside the Directory - Only for Pacs Project
			 def deleteFile = BuildOutputLoc +"\\*.zip"
				 
			 //Extract the Build
			 fileOperations([fileUnZipOperation(filePath: BuildUrl.get(1), targetLocation: BuildOutputLoc)])
				 
			 //Copy File and folder /* This step only for PACS Server Setup*
			 bat label: '', script: "((robocopy \"${CopyFromFolder}\" ${BuildOutputLoc} /S /MT:100 > C:\\log.txt) ^& IF %ERRORLEVEL% LEQ 4 exit /B 0)"
				 
			 //Delete unwanted folders and files
			 bat label: '', script: "(RD /S /Q \"${CopyFromFolder}\" > C:\\Deletelog1.txt)"
			 bat label: '', script: "(del ${deleteFile} > C:\\Deletelog1.txt)"
			}
		}
		if(Action.contains("Exec_ServerInstall"))
		{
		 //Running Server Installation Script
			 bat label: '', script: "(cmd/c call C:\\imgdrv\\Supdate.pl)"
		}
	}
  }
  else
  {
  echo "Exception Occurs, Problem in reading Input File"
  }
}
def getBuildOutLoc(row)
{
	def outLoc = null;
	String[] rowvalues = row.split('=');
	outLoc =rowvalues[1].trim();
  return outLoc
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