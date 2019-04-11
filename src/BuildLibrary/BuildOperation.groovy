package BuildLibrary

def BuildOperationCall(def propertyFileLoc)
{
  def file = new File(propertyFileLoc);
  if (file.exists() && file.isFile()) 
  {
    String[] lines = file.text.split('\n')
	for(int i =1; i<lines.size(); i++)
	{
		def  = lines[i];
		println"Current Row :"+ row
		if(row.contains("ProjectName"))
		{
		String[] rowvalues = row.split('=');
		def projectname =rowvalues[1].trim();
		println"project name : "+projectname;
		}
		
		if(row.contains("BuildUrl"))
		{
		String[] rowvalues = row.split('=');
		def BuildUrl = rowvalues[1].trim(); 
		println"BuildUrl :"+BuildUrl
		def BuildCopyLoc = "C:\\"+projectname+"_Build";
		println"BuildCopyLoc :"+BuildCopyLoc
		//Download latest build 
		 httpRequest ignoreSslErrors: true, outputFile: BuildCopyLoc, responseHandle: 'NONE', url: BuildUrl
		}
	}
  }
}