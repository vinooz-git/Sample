/*
Scope : Jenkins CI Pipeine Template for  all Project
Parameters :
  propertyFileLoc - Project pipeline Property file location  
*/
def call() 
{
 def propertyFileLoc = "D:\\Jenkins\\JobConfig.csv"
 node 
  {
    stage('VmSetup') 
	{
    def methodcall = new VmSetup.VmOperation()
	methodcall.VMOperationCall(propertyFileLoc)
	echo "Vm operations completed"
    }
    stage('Exec_ServerSetup')
	{
	//def methodcall = new BuildLibrary.BuildOperation()
	//methodcall.BuildOperationCall(propertyFileLoc)
	echo "Execution Server side setup Stage Completed"
    }
	stage('Exec_ClientSetup')
	{
    
    }
	stage('Client')
	{
    
    }
  }
}
