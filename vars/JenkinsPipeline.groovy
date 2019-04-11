/*
Scope : Jenkins CI Pipeine Template for  all Project
Parameters :
  propertyFileLoc - Project pipeline Property file location  
*/
def call() 
{
 node 
  {
    stage('VmSetup') 
	{
	def propertyFileLoc = "D:\\Jenkins\\JobConfig.csv"
    //def methodcall = new VmSetup.VmOperation()
	//methodcall.VMOperationCall(propertyFileLoc)
	echo "Vm operations completed"
    }
    stage('Server_BuildDownload')
	{
	def methodcall = new BuildLibrary.BuildOperation()
	methodcall.BuildOperationCall(propertyFileLoc)
	echo "Test Stage Completed"
    }
	stage('ServerSetup')
	{
    
    }
	stage('Client')
	{
    
    }
  }
}
