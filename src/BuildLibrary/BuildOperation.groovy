package BuildLibrary

def BuildOperationCall(def propertyFileLoc)
{
  def projectname = null;
  def BuildUrl = null;
  def BuildCopyLoc = null;
  def Filename = null;
  def file = new File(propertyFileLoc);
  if (file.exists() && file.isFile()) 
  {
    String[] lines = file.text.split('\n')
	for(int i =0; i<lines.size(); i++)
	{
		def row = lines[i];
		println"Current Row :"+ row
		if(row.contains("ProjectName")){String[] rowvalues = row.split('=');projectname =rowvalues[1].trim();println"project name : "+projectname;}		
		if(row.contains("BuildUrl"))
		{
		String[] rowvalues = row.split('=');
		BuildUrl = rowvalues[1].trim(); 
		String[] filenametemp = BuildUrl.split('/');
		Filename = filenametemp[filenametemp.size()-1]
		println"File Name :"+Filename
		println"BuildUrl :"+BuildUrl
		BuildCopyLoc = "C:\\"+projectname+"_Build"+Filename;
		println"BuildCopyLoc :"+BuildCopyLoc
		//Download latest build 
		httpRequest ignoreSslErrors: true, outputFile: BuildCopyLoc, responseHandle: 'NONE', url: BuildUrl
		}
	}
  }
}